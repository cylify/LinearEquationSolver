import java.util.*;


class Quiz {
    private String question;
    private String answer;

    public Quiz() {

        String n = OneStepQuestions();
        System.out.println(n);
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
        Random rand = new Random();
        String finalQuestion = "";
        int num = GetRandomNumber();

        if(num > 50) {
            String[] onestep = {"%sx = %s", "x/%s = %s", "x + %s = %s"};
            int randomQIndex = rand.nextInt(onestep.length);
            String question = onestep[randomQIndex];

            finalQuestion = String.format(question, GetRandomNumber());
        } else if(num < 50 && num > 0) {
            String[] onestep = {"%sx = %s", "x/%s = %s", "x - %s = %s"};
            int randomQIndex = rand.nextInt(onestep.length);
            String question = onestep[randomQIndex];

            finalQuestion = String.format(question, GetRandomNumber());
        }

        return finalQuestion;
    }

    public void TwoStepQuestions() {
        String type_1 = "%sx (\\+-) %s = %s";
        String type_2 = "x/%s (\\+-) %s = %s";
        String type_3 = "(x (\\+-) %s)/%s = %s";
        String type_4 = "%s(x (\\+-) %s) = %s";
        String type_5 = "%s/x = %s";
    }

    public void MultiStepQuestions() {
        String type_1 = "%s/%sx (\\+-) %s = %s";
        String type_2 = "%s/%sx (\\+-) %s = %s/%s";
        String type_3 = "%s(%sx (\\+-) %s)/%s = %s";
        String type_4 = "%s/%sx (\\+-) %s/%s = %s";
        String type_5 = "%s/x = %s/%s";
        String type_6 = "%s/%sx + %s/%s = %s/%s";
        String type_7 = "%s/x + %s/%s = %s/%s";
    }

    public static void main(String[] args) {
        Quiz g = new Quiz();
    }
}
