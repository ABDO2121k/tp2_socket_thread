
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static int numberU = 0;

    public static void main(String[] args) throws IOException {
        System.out.println("Server is running...");
        ServerSocket serverSocket = new ServerSocket(1234);// create server socket
        while (true) {
            System.out.println("Waiting for client...");
            Socket socket = serverSocket.accept();// hna l acceptation de client
            numberU++; // kandir incrementation l numberU
            System.out.println("Client connected");
            new ClientHandler(socket, numberU).start();// call thread and start it , sf
        }
    }

}

class ClientHandler extends Thread {
    private Socket socket;
    private int numberU = 0;

    public ClientHandler(Socket socket, int numberU) { // constructor
        this.socket = socket;
        this.numberU = numberU;
    }

    @Override
    public void run() {

        try {
            String message = "Welcome to the server, you are user number " + numberU; // hada l message li ansft l user
                                                                                      // fih number dialo
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream()); // creation dial output Stream
            oos.writeObject(message); // envoyer le message
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            while (true) { // hadi loop li kat3aml m3a les operations w l exit
                Object object = ois.readObject(); // kanjib l client message 3la xkal object
                if (object instanceof Operation) { // ila kan l message d user operation
                    Operation operation = (Operation) object; // get opeartion object
                    operation.calculate(); // call calclate method li kat7sb l result
                    oos.writeObject(operation);// send opeartion object to client with the calculated result
                } else if (object instanceof String && ((String) object).trim().equals("exit")) { // ila kn mesage d
                                                                                                  // client string w
                                                                                                  // equals exit
                    oos.writeObject("you will exit now !!!!"); // envoyer message l client de l'exit
                    break; // break loop pour closer la socket apres l'exit
                }
            }
            socket.close(); // c;loser le socket
            ois.close();
            oos.close();
        } catch (ClassNotFoundException | IOException e) { // hada code dial editor pour gerer l'exception
            e.printStackTrace();
        }

    }
}
