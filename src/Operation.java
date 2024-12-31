import java.io.Serializable;

class Operation implements Serializable {
    private double number1;
    private double number2;
    private String operation;
    private double result;
    private String errorMessage;// zdt hadi bax f affichage y afficher l'erreur

    public Operation(double number1, double number2, String operation) {
        this.number1 = number1;
        this.number2 = number2;
        this.operation = operation;
    }

    public void calculate() {
        switch (operation.trim()) { // drtha bax n7iyd l'espace'
            case "+":
                result = number1 + number2;// switchit 3la les operation possible
                break;
            case "-":
                result = number1 - number2;
                break;
            case "*":
                result = number1 * number2;
                break;
            case "/":
                if (number2 == 0) {
                    errorMessage = "Division by zero is not allowed";
                } else {
                    result = number1 / number2;
                }
                break;
            default: // hadi ila kant op non valide
                errorMessage = "Invalid operation";
        }
    }

    // getters w setters
    public double getNumber1() {
        return number1;
    }

    public void setNumber1(double number1) {
        this.number1 = number1;
    }

    public double getNumber2() {
        return number2;
    }

    public void setNumber2(double number2) {
        this.number2 = number2;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
   // toString qui affiche le rsultat ou l'erreur
    @Override
    public String toString() {
        if (errorMessage != null) {
            return errorMessage;
        }
        return number1 + " " + operation + " " + number2 + " = " + result;
    }
}
