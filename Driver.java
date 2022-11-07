import java.util.Scanner;

class Driver {

    public static void PrintMenu() {
        String title = "\n\n\n\t\t\t\t\u001b[31m\033[31m Welcome to the Linear Equation Solver! \033[0m";
        String headline = "\n\n\t\t\t       \u001B[1mChoose what program you would like to use!";
        String calcChoice = "\n\t\t\t\t   1. Linear Equation Calculator";
        String quizChoice = "\n\t\t\t\t   2. Quiz for Linear Equations";
        String spacer = "\n\n\n  ";

        System.out.println(title);
        System.out.println(headline);
        System.out.println(calcChoice);
        System.out.println(quizChoice);
        System.out.println(spacer);
    }

    public static int GetChoice(int choice) throws IllegalArgumentException {
        if(choice > 0 && choice < 3) {
            return choice;
        } else {
            throw new IllegalArgumentException("Not a valid choice");
        }
    }

    public static void main(String[] args) {
        PrintMenu();
        Scanner in = new Scanner(System.in);
        System.out.print("Enter what program you would like to run?: ");
        String c = in.next();
        int k = GetChoice(Integer.valueOf(c));

        if(k == 1) {
            in.nextLine();
            System.out.print("Enter an expression: ");
            String expr = in.nextLine();
            LinearEquation g = new LinearEquation(expr);
        } else if(k == 2) {
            in.nextLine();
        }
        in.close();
    }
}