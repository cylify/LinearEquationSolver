import java.util.*;

class Quiz {
    public Quiz() {
        
    }

    public String GetChoice(String choice) throws IllegalArgumentException {
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

    public int GetRandomNumber() {
        Random rand = new Random();
        int num = rand.nextInt(1, 100);
        return num;
    }

    public int ChanceForNegative() {
        Random rand = new Random();
        int chance = rand.nextInt(0, 100);
        return chance;
    }
    
    public String OneStepQuestions() {
        String[] onestep = {"%dx = %d", "x/%d = %d", "x " + (GetRandomNumber() > 50 ? "+" : "-") + " %d = %d"};
        return String.format(onestep[(new Random()).nextInt(onestep.length)], GetRandomNumber(), GetRandomNumber());
    }

    public String TwoStepQuestions() {
        String[] twostep = {"%dx " + (GetRandomNumber() > 50 ? "+" : "-") + " %d = %d", 
                            "x/%d " + (GetRandomNumber() > 50 ? "+" : "-") + " %d = %d",
                            "(x " + (GetRandomNumber() > 50 ? "+" : "-") + " %d)/%d = %d",
                            "%d(x " + (GetRandomNumber() > 50 ? "+" : "-") + " %d) = %d",
                            "%d/x = %d"};
        return String.format(twostep[(new Random()).nextInt(twostep.length)],GetRandomNumber(), GetRandomNumber(), GetRandomNumber());
    }

    public String MultiStepQuestions() {
        String[] multistep = {"%d/%dx " + (GetRandomNumber() > 50 ? "+" : "-") + 
                            " %d = %d", "%d/%dx " + (GetRandomNumber() > 50 ? "+" : "-") + 
                            " %d = %d/%d", "%d(%dx " + (GetRandomNumber() > 50 ? "+" : "-") + 
                            " %d)/%d = %d", "%d/%dx " + (GetRandomNumber() > 50 ? "+" : "-") + 
                            " %d/%d = %d", "%d/%dx " + (GetRandomNumber() > 50 ? "+" : "-") + 
                            " %d/%d = %d/%d", "%d/x = %d/%d", "%d/%dx + %d/%s = %d/%d", "%d/x + %d/%d = %d/%d"};

        return String.format(multistep[(new Random()).nextInt(multistep.length)],GetRandomNumber(),GetRandomNumber(),GetRandomNumber(),GetRandomNumber(),GetRandomNumber());
    }

    public boolean CheckAnswer(String question, String answer) {
        LinearEquation ans = new LinearEquation(question);
        String StringOfAns = String.valueOf(ans);
        boolean flag = false;
        if(StringOfAns.equals(answer)) {
            flag = true;
        } else {
            flag = false;
        }
        return flag;
    }
}
