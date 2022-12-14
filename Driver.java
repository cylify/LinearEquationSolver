import java.util.Scanner;

class Driver {

    /**
     * Print menu for user
     */
    public static void PrintMenu() {
        String title = "\n\n\n\t\t\t\t\u001b[31m\033[31m\u001B[4m\u001B[1m Welcome to the Linear Equation Solver! \033[0m\u001B[0m";
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

    /**
     * Gets choice of what program user wants to run
     * @param choice
     * @return
     * @throws IllegalArgumentException
     */
    public static int GetChoice(int choice) throws IllegalArgumentException {
        // Makes sure choice is in bounds
        if(choice > 0 && choice < 3) {
            return choice;
        // If not in correct boundaries throws IllegalArgumentException
        } else {
            throw new IllegalArgumentException("Not a valid choice");
        }
    }

    /**
     * Runs LinearEquation Solver for user
     */
    public static void runLinearEquationSolver() {
        Scanner in = new Scanner(System.in);
        String expr = in.nextLine();
        LinearEquation eq = new LinearEquation(expr);
        System.out.println("x = " + LinearEquation.xVal);
        in.close();
    }

    /**
     * Runs Quiz for user
     */
    public static void runQuiz() {
        Scanner in = new Scanner(System.in);
        String type = in.nextLine();
        Quiz q = new Quiz(type);
        System.out.print("Solve for the unknown in this expression\nx = ");
        String answer = in.nextLine();
        boolean flag = Quiz.CheckAnswer(q.getQuestion(), answer);
        if(flag) {
            System.out.println("\u001B[32mYou have got the question right!\033[0m");
        } else {
            System.out.println("\033[0;31m\033[1mYou have got the question wrong.\033[0m\033[0m");
            System.out.println("\033[1mThe correct answer is: " + LinearEquation.xVal + "\033[0m");
        }
        in.close();
    }

    // 2x + 4


    public static void main(String[] args) {
        PrintMenu();
        Scanner in = new Scanner(System.in);
        System.out.print("Enter what program you would like to run?: ");

        while(true) {
            try {
                int choice = in.nextInt();
                int choiceVal = GetChoice(choice);
                if(choiceVal == 1) {
                    try {
                        in.nextLine();
                        System.out.print("Enter an expression: ");
                        runLinearEquationSolver();
                        break;
                    } catch(IllegalArgumentException e) {
                        System.out.println("Enter a valid expression.\nProgram Choice: ");
                    }
                } else if(choiceVal == 2) {
                    try {
                        in.nextLine();
                        System.out.print("What type of equation would you solve? (O/T/M): ");
                        runQuiz();
                        break;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Enter a valid type of equation.");
                    }
                }
            } catch(IllegalArgumentException e) {
                System.out.print("Enter a valid choice\nProgram number:\n");
            }
        }
        in.close();
    }
}


