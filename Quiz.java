import java.util.*;

class Quiz {
    static String question;

    public Quiz(String type) {
        question = GetQuestion(type);
        System.out.println(question);
    }

    public String GetQuestion(String choice) throws IllegalArgumentException {
        String question = "";
        if(choice.equals("O")) {
            question = OneStepQuestions();
        } else if(choice.equals("T")) {
            question = TwoStepQuestions();
        } else if(choice.equals("M")) {
            question = MultiStepQuestions();
        } else {
            throw new IllegalArgumentException("Valid choice must be inputted");
        }
        return question;
    }

    public int GetRandomInt() {
        Random rand = new Random();
        int num = rand.nextInt(1, 20);
        return num;
    }

    public int GetRandomNumber() {
        Random rand = new Random();
        int chance = rand.nextInt(1, 100);
        return chance;
    }
    
    public String OneStepQuestions() {
        String[] onestep = {(GetRandomNumber() > 50 ? "" : "-") + "%dx = " + (GetRandomNumber() > 50 ? "" : "-") +"%d", 
        (GetRandomNumber() > 50 ? "" : "-") + "x/" + (GetRandomNumber() > 50 ? "" : "-") + "%d = " + 
        (GetRandomNumber() > 50 ? "" : "-") + "%d",
        (GetRandomNumber() > 50 ? "" : "-") + "x " + (GetRandomNumber() > 50 ? "+" : "-") + " " + (GetRandomNumber() > 50 ? "" : "-") + 
        "%d = " + (GetRandomNumber() > 50 ? "" : "-") + "%d"};
        return String.format(onestep[(new Random()).nextInt(onestep.length)], GetRandomInt(), GetRandomInt());
    }

    public String TwoStepQuestions() {
        String[] twostep = {(GetRandomNumber() > 50 ? "" : "-") + "%dx " + (GetRandomNumber() > 50 ? "+" : "-") + " " + 
                            (GetRandomNumber() > 50 ? "" : "-") + "%d = " + (GetRandomNumber() > 50 ? "" : "-") + "%d", 
                            (GetRandomNumber() > 50 ? "" : "-") + "x/" + (GetRandomNumber() > 50 ? "" : "-") + 
                            "%d " + (GetRandomNumber() > 50 ? "+" : "-") + " " + (GetRandomNumber() > 50 ? "" : "-") + 
                            "%d = " + (GetRandomNumber() > 50 ? "" : "-") + "%d",
                            (GetRandomNumber() > 50 ? "" : "-") + "(" + (GetRandomNumber() > 50 ? "" : "-") + 
                            "x " + (GetRandomNumber() > 50 ? "+" : "-") + " "  + (GetRandomNumber() > 50 ? "" : "-") + 
                            "%d)/" + (GetRandomNumber() > 50 ? "" : "-") + 
                            "%d = " + (GetRandomNumber() > 50 ? "" : "-") + "%d",
                            (GetRandomNumber() > 50 ? "" : "-") + "%d(" + (GetRandomNumber() > 50 ? "" : "-") + 
                            "x " + (GetRandomNumber() > 50 ? "+" : "-") + " " + (GetRandomNumber() > 50 ? "" : "-") + 
                            "%d) = " + (GetRandomNumber() > 50 ? "" : "-") +"%d",
                            "%d/x = %d"};
        return String.format(twostep[(new Random()).nextInt(twostep.length)], GetRandomInt(), GetRandomInt(), GetRandomInt());
    }

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
                            " %d/%d = %d/%d", "%d/x = %d/%d", "%d/%dx + %d/%d = %d/%d", "%d/x + %d/%d = %d/%d"};

        return String.format(multistep[(new Random()).nextInt(multistep.length)], GetRandomInt(), GetRandomInt(), GetRandomInt(), GetRandomInt(), GetRandomInt());
    }

    public static boolean CheckAnswer(String question, String answer) {
        LinearEquation ans = new LinearEquation(question);
        boolean flag = false;
        if(LinearEquation.answer.equals(answer)) {
            flag = true;
        } else {
            flag = false;
        }
        return flag;
    }
}