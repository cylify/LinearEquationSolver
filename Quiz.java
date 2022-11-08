import java.util.*;

class Quiz {
    private String question;

    /**
     * Gets random question for user and prints it
     * @param type
     */
    public Quiz(String type) {
        question = GetQuestion(type);
        System.out.println(question);
    }

    /**
     * For other files to get question
     * @return String
     */
    public String getQuestion() {
        return question;
    }
    
    /**
     * Set question
     * @param question
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * For choice get question
     * @param choice
     * @return String
     * @throws IllegalArgumentException
     */
    public String GetQuestion(String choice) throws IllegalArgumentException {
        String question = "";
        // If choice of user is O then run one step questions
        if(choice.equals("O")) {
            question = OneStepQuestions();
        // If choice of user is T then run two step questions
        } else if(choice.equals("T")) {
            question = TwoStepQuestions();
        // If choice of user is M then run multi step questions
        } else if(choice.equals("M")) {
            question = MultiStepQuestions();
        // If choice does not match these choices, then throw IllegalArgumentException
        } else {
            throw new IllegalArgumentException("Valid choice must be inputted");
        }
        return question;
    }

    /**
     * Gets random int for question
     * @return int
     */
    public int GetRandomInt() {
        Random rand = new Random();
        int num = rand.nextInt(2, 20);
        return num;
    }

    /**
     * Chance for num
     * @return int
     */
    public int GetRandomNumber() {
        Random rand = new Random();
        int chance = rand.nextInt(1, 100);
        return chance;
    }
    
    /**
     * Array of all one step types of equations
     * @return String
     */
    public String OneStepQuestions() {
        String[] onestep = {(GetRandomNumber() > 50 ? "" : "-") + "%dx = " + (GetRandomNumber() > 50 ? "" : "-") +"%d", 
        (GetRandomNumber() > 50 ? "" : "-") + "x/" + (GetRandomNumber() > 50 ? "" : "-") + "%d = " + 
        (GetRandomNumber() > 50 ? "" : "-") + "%d",
        (GetRandomNumber() > 50 ? "" : "-") + "x " + (GetRandomNumber() > 50 ? "+" : "-") + " " + (GetRandomNumber() > 50 ? "" : "-") + 
        "%d = " + (GetRandomNumber() > 50 ? "" : "-") + "%d"};

        // Formats question with chance of operator and digits
        return String.format(onestep[(new Random()).nextInt(onestep.length)], GetRandomInt(), GetRandomInt());
    }

    /**
     * Array of all two step types of equations
     * @return String
     */
    public String TwoStepQuestions() {
        String[] twostep = {(GetRandomNumber() > 50 ? "" : "-") + "%dx " + (GetRandomNumber() > 50 ? "+" : "-") + " " + 
        (GetRandomNumber() > 50 ? "" : "-") + "%d = " + (GetRandomNumber() > 50 ? "" : "-") + "%d", 
        (GetRandomNumber() > 50 ? "" : "-") + "x/" + (GetRandomNumber() > 50 ? "" : "-") + 
        "%d " + (GetRandomNumber() > 50 ? "+" : "-") + " " + (GetRandomNumber() > 50 ? "" : "-") 
        + "%d = " + (GetRandomNumber() > 50 ? "" : "-") + "%d",
        (GetRandomNumber() > 50 ? "" : "-") + "(" + (GetRandomNumber() > 50 ? "" : "-") + 
        "x " + (GetRandomNumber() > 50 ? "+" : "-") + " "  + (GetRandomNumber() > 50 ? "" : "-") + 
        "%d)/" + (GetRandomNumber() > 50 ? "" : "-") + "%d = " + (GetRandomNumber() > 50 ? "" : "-") + "%d",
        (GetRandomNumber() > 50 ? "" : "-") + "%d(" + (GetRandomNumber() > 50 ? "" : "-") + 
        "x " + (GetRandomNumber() > 50 ? "+" : "-") + " " + (GetRandomNumber() > 50 ? "" : "-") + 
        "%d) = " + (GetRandomNumber() > 50 ? "" : "-") +"%d", "%d/x = %d"};

        // Formats question with chance of operator and digits
        return String.format(twostep[(new Random()).nextInt(twostep.length)], GetRandomInt(), GetRandomInt(), GetRandomInt());
    }

    /**
     * Array of all multi step types of questions
     * @return String
     */
    public String MultiStepQuestions() {
        String[] multistep = {(GetRandomNumber() > 50 ? "" : "-") + "%d/" + (GetRandomNumber() > 50 ? "" : "-") + 
        "%dx " + (GetRandomNumber() > 50 ? "+" : "-") + 
        " " + (GetRandomNumber() > 50 ? "" : "-") + "%d = " + (GetRandomNumber() > 50 ? "" : "-") + "%d", 
        (GetRandomNumber() > 50 ? "" : "-") + "%d/" + (GetRandomNumber() > 50 ? "" : "-") + "%dx " + 
        (GetRandomNumber() > 50 ? "+" : "-") + 
        " " + (GetRandomNumber() > 50 ? "" : "-") + "%d = " + (GetRandomNumber() > 50 ? "" : "-") + "%d/" + 
        (GetRandomNumber() > 50 ? "" : "-") + "%d", (GetRandomNumber() > 50 ? "" : "-") + "%d(" + (GetRandomNumber() > 50 ? "" : "-") + 
        "%dx " + (GetRandomNumber() > 50 ? "+" : "-") + 
        " " + (GetRandomNumber() > 50 ? "" : "-") + "%d)/" + (GetRandomNumber() > 50 ? "" : "-") + "%d = %d", 
        (GetRandomNumber() > 50 ? "" : "-") + "%d/" + (GetRandomNumber() > 50 ? "" : "-") + "%dx " + (GetRandomNumber() > 50 ? "+" : "-") + 
        " " + (GetRandomNumber() > 50 ? "" : "-") + "%d/" + (GetRandomNumber() > 50 ? "" : "-") + "%d = " + (GetRandomNumber() > 50 ? "" : "-") + 
        "%d", (GetRandomNumber() > 50 ? "" : "-") + "%d/" + (GetRandomNumber() > 50 ? "" : "-") + "%dx " + (GetRandomNumber() > 50 ? "+" : "-") + 
        " " + (GetRandomNumber() > 50 ? "" : "-") + "%d/" + (GetRandomNumber() > 50 ? "" : "-") + "%d = " + (GetRandomNumber() > 50 ? "" : "-") + 
        "%d/" + (GetRandomNumber() > 50 ? "" : "-") + "%d",  (GetRandomNumber() > 50 ? "" : "-") + "%d/x = " + (GetRandomNumber() > 50 ? "" : "-") + 
        "%d/" + (GetRandomNumber() > 50 ? "" : "-") + "%d", "%d/%dx " + (GetRandomNumber() > 50 ? "+" : "-") + " %d/%d = %d/%d", 
        (GetRandomNumber() > 50 ? "" : "-") + "%d/x " + (GetRandomNumber() > 50 ? "+" : "-") + " " + (GetRandomNumber() > 50 ? "" : "-") + 
        "%d/%d = %d/%d"};

        // Formats question with chance of operator and digits
        return String.format(multistep[(new Random()).nextInt(multistep.length)], GetRandomInt(), GetRandomInt(), GetRandomInt(), GetRandomInt(), GetRandomInt(), GetRandomInt(), GetRandomInt());
    }

    /**
     *  Checks whether user answer is equal to actual answer of question
     * @param question
     * @param answer
     * @return boolean
     */
    public static boolean CheckAnswer(String question, String answer) {
        LinearEquation ans = new LinearEquation(question);
        boolean flag = false;

        // If answer of user is same as actual answer
        if(LinearEquation.xVal.equals(answer)) {
            // Answer of user is correct
            flag = true;
        } else {
            // Otherwise answer of user is wrong
            flag = false;
        }
        // Return if answer is right or wrong
        return flag;
    }
}