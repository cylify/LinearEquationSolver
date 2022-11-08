class InputValidator {

    /**
     * Checks if user expression is valid or not
     * @param leftSide
     * @param rightSide
     * @return boolean
     */
    public boolean CheckForExpression(String leftSide, String rightSide) {
        boolean flag = true;
        String frac = "((-{0,1}[1-9][0-9]*)|-{0,1}0)/-{0,1}[1-9][0-9]*";
        String wholeFrac = "(-{0,1}[1-9][0-9]*)|-{0,1}0";

        // Checks is rightside is valid or not
        if(rightSide.isEmpty()) {
            flag = false;
        } else if(!(rightSide.matches(wholeFrac) || rightSide.matches(frac))) {
            flag = false;
        }

        // Call method to get all possible variants of left side
        String[] possibleLeftSide = possibleLeftSides();

        if(leftSide.contains("(")) {
            for(int i = 0; i < possibleLeftSide.length; i++) {
                if(!(leftSide.matches(possibleLeftSide[i]))) {
                    flag = false;
                    if(flag) {
                        break;
                    } else {
                        continue;
                    }
                }
            }
        } else {

        }

        return flag;

    }

    /**
     * Array of all possible left sides
     * @return String[]
     */
    String[] possibleLeftSides() {
        String[] possibleExpr = {"\\(x (\\+|-) -?[1-9][0-9]*\\)/-?[1-9][0-9]*", "-?[1-9][0-9]*/-?x",
        "-?[1-9][0-9]*", "-?[1-9][0-9]*/-?[1-9][0-9]*","-?[1-9][0-9]*x", "-?x-?[1-9][0-9]",
        "-?x-?[1-9][0-9]*/-?[1-9][0-9]*","-?[1-9][0-9]*/-?[1-9][0-9]*x", "-?[1-9][0-9]*\\(x (\\+|-) -?[1-9][0-9]*\\)",
        "-?[1-9][0-9]*\\(x (\\+|-) -?[1-9][0-9]*/-?[1-9][0-9]*\\)", "-?[1-9][0-9]*\\(x (\\+|-) -?[1-9][0-9]*/-?[1-9][0-9]*\\)/-?[1-9][0-9]*",
        "-?[1-9][0-9]*/-?[1-9][0-9]*\\(x (\\+|-) -?[1-9][0-9]*)", "-?[1-9][0-9]*/-?[1-9][0-9]*\\(x (\\+|-) -?[1-9][0-9]*/-?[1-9][0-9]*\\)",
        "-?[1-9][0-9]*/-?[1-9][0-9]*\\(x (\\+|-) -?[1-9][0-9]*/-?[1-9][0-9]*\\)/-?[1-9][0-9]*",
        "\\(x (\\+|-) -?[1-9][0-9]*/-?[1-9][0-9]*\\)/-?[1-9][0-9]*","-?[1-9][0-9]\\(x (\\+|-) -?[1-9][0-9]*/-?[1-9][0-9]*\\)/-?[1-9][0-9]*",
        "\\(-?[1-9][0-9]*x (\\+|-) -?[1-9][0-9]*/-?[1-9][0-9]*\\)/-?[1-9][0-9]*", 
        "\\(-?[1-9][0-9]*/-?[1-9][0-9]*x (\\+|-) -?[1-9][0-9]*/-?[1-9][0-9]*\\)/-?[1-9][0-9]*","-?x/-?[1-9][0-9]*"};
        return possibleExpr;

    }
}
