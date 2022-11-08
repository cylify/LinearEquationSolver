class InputValidator {

    /**
     * Checks if user expression is valid or not
     * @param leftSide
     * @param rightSide
     * @return boolean
     */
    public boolean CheckForExpression(String leftside, String rightside) {
        // Sets bool to true so if it doesn't match then I know its an invalid expression
        boolean flag = false;
        
        // Call method to get all possible variants of right side
        String[] possibleRightSide = possibleRightSides();

        // Check is right side is valid
        for(int i = 0; i < possibleRightSide.length; i++) {
            if(rightside.matches(possibleRightSide[i])) {
                flag = true;
                break;
            }
        }

        // Call method to get all possible variants of left side
        String[] possibleLeftSide = possibleLeftSides();

        // Check if left side is valid
        for(int i = 0; i < possibleLeftSide.length; i++) {
            if(leftside.matches(possibleLeftSide[i])) {
                flag = true;
                break;
            }
        }
        

        return flag;

    }

    /**
     * Array of all possible left sides
     * @return String[]
     */
    private String[] possibleLeftSides() {
        String[] possibleLeftSide = {"-?[1-9][0-9]*/x", 
        "-?[1-9][0-9]*x", 
        "-?x/-?[1-9][0-9]*", 
        "-?x/-?[1-9][0-9]*/-?[1-9][0-9]*", 
        "-?[1-9][0-9]*/-?[1-9][0-9]*x",
        "-?[1-9][0-9]*/-?[1-9][0-9]*/x", 
        "-?[1-9][0-9]*/-?[1-9][0-9]*/x (\\+|-) -?[1-9][0-9]*/-?[1-9][0-9]*", 
        "-?[1-9][0-9]*/-?[1-9][0-9]*/x (\\+|-) -?[1-9][0-9]*",
        "-?x (\\+|-) -?[1-9][0-9]*",
        "-?x (\\+|-) -?[1-9][0-9]*/-?[1-9][0-9]*",
        "-?[1-9][0-9]*/-?[1-9][0-9]*x (\\+|-) -?[1-9][0-9]*/-?[1-9][0-9]*",
        "-?[1-9][0-9]*/-?[1-9][0-9]*x (\\+|-) -?[1-9][0-9]*",
        "-?[1-9][0-9]*x (\\+|-) -?[1-9][0-9]*/-?[1-9][0-9]*",
        "-?[1-9][0-9]*x (\\+|-) -?[1-9][0-9]*",
        "\\(-?[1-9][0-9]*/-?[1-9][0-9]*x (\\+|-) -?[1-9][0-9]*/-?[1-9][0-9]*\\)/-?[1-9][0-9]*/-?[1-9][0-9]*",
        "-?[1-9][0-9]*\\(-?[1-9][0-9]*/-?[1-9][0-9]*x (\\+|-) -?[1-9][0-9]*/-?[1-9][0-9]*\\)/-?[1-9][0-9]*/-?[1-9][0-9]*",
        "-?[1-9][0-9]*/-?[1-9][0-9]*\\(-?[1-9][0-9]*/-?[1-9][0-9]*x (\\+|-) -?[1-9][0-9]*/-?[1-9][0-9]*\\)/-?[1-9][0-9]*/-?[1-9][0-9]*",
        "-?[1-9][0-9]*/-?[1-9][0-9]*\\(-?[1-9][0-9]*x (\\+|-) -?[1-9][0-9]*/-?[1-9][0-9]*\\)/-?[1-9][0-9]*/-?[1-9][0-9]*",
        "-?[1-9][0-9]*/-?[1-9][0-9]*\\(-?[1-9][0-9]*/-?[1-9][0-9]*x (\\+|-) -?[1-9][0-9]*\\)/-?[1-9][0-9]*/-?[1-9][0-9]*",
        "-?[1-9][0-9]*/-?[1-9][0-9]*\\(-?[1-9][0-9]*/-?[1-9][0-9]*x (\\+|-) -?[1-9][0-9]*/-?[1-9][0-9]*\\)/-?[1-9][0-9]*",
        "-?[1-9][0-9]*\\(-?[1-9][0-9]*/-?[1-9][0-9]*x (\\+|-) -?[1-9][0-9]*/-?[1-9][0-9]*\\)/-?[1-9][0-9]*",
        "-?[1-9][0-9]*/-?[1-9][0-9]*\\(-?[1-9][0-9]*x (\\+|-) -?[1-9][0-9]*\\)/-?[1-9][0-9]*/-?[1-9][0-9]*",
        "-?[1-9][0-9]*\\((-?[1-9][0-9]*|-)?x (\\+|-) -?[1-9][0-9]*\\)/-?[1-9][0-9]*",
        "\\((-?[1-9][0-9]*|-)?x (\\+|-) -?[1-9][0-9]*\\)/-?[1-9][0-9]*",
        "\\((-?[1-9][0-9]*|-)?x (\\+|-) -?[1-9][0-9]*\\)/-?[1-9][0-9]*/-?[1-9][0-9]*",
        "\\((-?[1-9][0-9]*|-)?x (\\+|-) -?[1-9][0-9]*/-?[1-9][0-9]*\\)/-?[1-9][0-9]*/-?[1-9][0-9]*",
        "-?[1-9][0-9]*\\((-?[1-9][0-9]*|-)?x (\\+|-) -?[1-9][0-9]*/-?[1-9][0-9]*\\)",
        "-?[1-9][0-9]*/-?[1-9][0-9]*\\((-?[1-9][0-9]*|-)?x (\\+|-) -?[1-9][0-9]*/-?[1-9][0-9]*\\)",
        "-?[1-9][0-9]*/-?[1-9][0-9]*\\((-?[1-9][0-9]*|-)?x (\\+|-) -?[1-9][0-9]*\\)",
        "-?[1-9][0-9]*/-?[1-9][0-9]*\\((-?[1-9][0-9]*|-)?x (\\+|-) -?[1-9][0-9]*/-?[1-9][0-9]*\\)",
        "-?[1-9][0-9]*\\((-?[1-9][0-9]*|-)?x (\\+|-) -?[1-9][0-9]*\\)"};
        return possibleLeftSide;

    }

    private String[] possibleRightSides() {
        String[] possibleRightSides= {"-?[1-9][0-9]", "-?[1-9][0-9]*/-?[1-9][0-9]*"};
        return possibleRightSides;
    }
}
