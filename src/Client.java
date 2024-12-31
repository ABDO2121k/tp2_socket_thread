

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket socket = new Socket("localhost", 1234); // connect to server sockte 
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream()); // create output stream
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());// create input stream
        Scanner scanner = new Scanner(System.in); // create scanner

        try {
            System.out.println((String) ois.readObject());// hadi knjibo message dial server lwl li kaykon string fih user number

            while (true) {
                System.out.println("Enter first number: ");
                double number1 = scanner.nextDouble();
                System.out.println("Enter second number: "); //hadi kanakhdo l operation infos b scanner
                double number2 = scanner.nextDouble();
                System.out.println("Enter operation (+, -, *, /): ");
                String operation = scanner.next();

                Operation operationObject = new Operation(number1, number2, operation);// creation operation object
                oos.writeObject(operationObject); // envoie d l'opjet operation to server
                Operation result = (Operation) ois.readObject(); // reception of result from server
                // hado deux method bax ndiro l affichage du resule 1: getter ,2: toString
                System.out.println("result b method 1 : " + result.getResult()); 
                System.out.println("result b method 2 (toString) : " + result.toString());
                // exit operation
                System.out.println("insert exit to exit or any key to continue");
                scanner.nextLine(); 
                String exit = scanner.nextLine();

                if (exit.trim().equals("exit")) { // ila kan exit
                    oos.writeObject("exit");
                    System.out.println((String) ois.readObject());// affichage de message d'exit from server
                    break;
                }
            }
        } finally {
            // liberation du ressources
            scanner.close();
            ois.close();
            oos.close();
            socket.close();
        }
    }
}