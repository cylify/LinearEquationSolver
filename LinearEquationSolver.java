/*
 * Name: Adi Lachman
 * Class: ICS4U0-A
 * Teacher: Pasitkhammanh, J
 * Project: Equation Software
 */

import java.util.*;
import java.util.stream.*;

class LinearEquation {
    private String rightSide;
    private String leftSide;
    public static String xVal;

    /**
     * Puts equation in two sides
     * Check if equation is valid
     * Check what type of equation needs to be solved and solve
     * @param expr
     * @throws IllegalArgumentException
     */
    public LinearEquation(String expr) throws IllegalArgumentException {
        // Initializes rightSide and leftSide
        this.rightSide = GetRightSide(expr);
        this.leftSide = GetLeftSide(expr);

        // Validates expression and checks type of expression
        boolean checkForX = CheckForUnknown(expr);
        boolean flagForXdivisor = CheckForXDivisor(this.leftSide);
        boolean flagForDivisor = CheckForDivision(this.leftSide);
        boolean flagForDistributor = CheckForDistributor(this.leftSide);

        InputValidator validator = new InputValidator();
        boolean checkExpr = validator.CheckForExpression(leftSide, rightSide);

        // Runs code according to checks
        if(checkForX) {
            if(checkExpr) {
                if(flagForXdivisor) {
                    xVal = SolveForXDivisor(leftSide, rightSide);
                } else if(flagForDivisor) {
                    xVal = SolveForDivisor(leftSide, rightSide, flagForDistributor);
                } else if(flagForDistributor) {
                    xVal = SolveForDistribution(leftSide, rightSide);
                } else {
                    xVal = SolveForBase(leftSide, this.rightSide);
                }
            // If invalid throw error
            } else {
                throw new IllegalArgumentException("Valid expression must be inputted");
            }
        // If invalid throw error
        } else {
            throw new IllegalArgumentException("Valid expression must be inputted");
        }
    }

    /**
     * Solve for when x is a divisor
     * @param leftSide
     * @param rightside
     * @return String
     */
    public String SolveForXDivisor(String leftSide, String rightside) {
        // Switches side if original expression was divided by x
        String switched = SwitchedSides(leftSide, rightSide);
        // Gets leftSide and rightSide of new expression
        String switchedleft = GetLeftSide(switched);
        String switchedright = GetRightSide(switched);
        ArrayList<String> ArrayOfswitchedRight = ConvertToArray(switchedright);
        ArrayList<String> ArrayOfswitchedLeft = ConvertToArray(switchedleft);
        // Solve
        String finalval = Evaluate(ArrayOfswitchedLeft, ArrayOfswitchedRight).toString();
        return finalval;
    }

    /**
     * Solves for division
     * @param leftSide
     * @param flagForDistributor
     * @return String
     */
    public String SolveForDivisor(String leftSide, String rightSide, boolean flagForDistributor) {
        String newRight = "";
        String finalAns = "";
        String divisor = GetDivisor(leftSide).toString();
        String newLeft = RemoveDivisor(leftSide);
        ArrayList<String> right = ConvertToArray(rightSide);
        // If newLeft doesn't have a bracket
        if(!(newLeft.contains("("))) {
            // if newLeft has a constant
            if(newLeft.matches(".*[1-9].*")) {
                // Evaluate then apply divisor
                newRight = Evaluate(ConvertToArray(newLeft), right).toString();
                newRight = ApplyDivisor(Fraction.valueOf(divisor), ConvertToArray(newRight)).toString();
            } else {
                // If doesn't then apply divisor
                newRight = ApplyDivisor(Fraction.valueOf(divisor), right).toString();
            }
        } else {     
            // Apply divisor on right side if no constant       
            newRight = ApplyDivisor(Fraction.valueOf(divisor), right).toString();
            // If the newRight has a mixed fraction then unsimplify otherwise keep it as fraction
            if(newRight.length() > 3) {
                newRight = Unsimplify(Fraction.valueOf(newRight));
            }
        }
        
        // Check if there is distribution to be done
        flagForDistributor = CheckForDistributor(newLeft);
        // If there is, solve for distribution
        if(flagForDistributor) {
            finalAns = SolveForDistribution(newLeft, newRight);
        // If not check if there is a coeff on x
        } else {
            boolean check = CheckForCoeff(newLeft);
            // If there is then divide right side by coeff to get final answer
            if(check) {
                ArrayList<String> coeff = GetTerms(ConvertToArray(newLeft));
                finalAns = Fraction.valueOf(newRight).divide(Fraction.valueOf(coeff.get(0))).toString();
            // If not distribution or coefficient then final answer is the right side
            } else {
                finalAns = newRight;
            }
        }
        // Return final answer
        return finalAns;
    }

    /**
     * Solves for distribution
     * @param leftSide
     * @param rightSide
     * @return String
     */
    public String SolveForDistribution(String leftSide, String rightSide) {
        int countofspace = 0;
        String finalAns = "";
        Fraction distributor = GetDistributor(leftSide);
        ArrayList<String> numsInBracket = ConvertToArray(GetNumbersInBracket(leftSide));
        String finalLeft = String.join(" ", Distribute(numsInBracket, distributor));
        // Check for how many spaces there are
        for(int i = 0; i < finalLeft.length(); i++) {
            if(finalLeft.substring(i, i + 1).equals(" ")) {
                countofspace++;
            }
        }
        
        // if there are more than 2 then there are mixed numbers
        if(countofspace > 2) {
            // Solve for mixed numbers
            finalAns = solveForMixedDistribution(finalLeft, countofspace, rightSide);
        } else {
            // If no mixed numbers then normally evaluate
            Fraction finalVal = Evaluate(ConvertToArray(finalLeft), ConvertToArray(rightSide));
            finalAns = finalVal.toString();
        }
        // Return final answer
        return finalAns;
    }

    /**
     * Solves if there is mixed distribution
     * @param finalLeft
     * @param countofspace
     * @param rightSide
     * @return String
     */
    private String solveForMixedDistribution(String finalLeft, int countofspace, String rightSide) {
        String mixedFrac = "((-{0,1}[1-9][0-9]*)|-{0,1}0) (([1-9][0-9]*)|-{0,1}0)/[1-9][0-9]*";
        String finalAns = "";
        int indexOfunknown = finalLeft.indexOf("x");
        if(finalLeft.substring(0, indexOfunknown).matches(mixedFrac)) {
            String frac = Unsimplify(Fraction.valueOf(finalLeft.substring(0, indexOfunknown)));
            if(countofspace > 3) {
                finalAns = SolveForTwoMixedDistribution(finalLeft, rightSide, frac);
            } else {
                String expr = frac + finalLeft.substring(indexOfunknown);
                Fraction finalVal = Evaluate(ConvertToArray(expr), ConvertToArray(rightSide));
                finalAns = finalVal.toString();
            }
        } else {
            finalAns = SolveForConstantMixed(finalLeft);
        }
        return finalAns;
    }

    /**
     * Solve if the constant is a mixed number
     * @param finalLeft
     * @return String
     */
    private String SolveForConstantMixed(String finalLeft) {
        String finalAns = "";
        // Convert mixed number for constant to improper then solve
        if(finalLeft.contains(" + ")) {
            int indexOfOp = finalLeft.indexOf("+");
            String frac = Unsimplify(Fraction.valueOf(finalLeft.substring(indexOfOp + 2)));
            String expr = finalLeft.substring(0, indexOfOp) + "+ " + frac;
            Fraction finalVal = Evaluate(ConvertToArray(expr), ConvertToArray(rightSide));
            finalAns = finalVal.toString();
        } else if(finalLeft.contains(" - ")) {
            int indexOfOp = finalLeft.indexOf("-");
            String frac = Unsimplify(Fraction.valueOf(finalLeft.substring(indexOfOp + 2)));
            String expr = finalLeft.substring(0, indexOfOp) + "- " + frac;
            Fraction finalVal = Evaluate(ConvertToArray(expr), ConvertToArray(rightSide));
            finalAns = finalVal.toString();
        }
        return finalAns;
    }

    /**
     * Solve if there are 2 mixed numbers in distribution
     * @param finalLeft
     * @param rightSide
     * @param frac
     * @return String
     */
    private String SolveForTwoMixedDistribution(String finalLeft, String rightSide, String frac) {
        String finalAns = "";
        // Unsimplify 2nd mixed fraction and create new expression
        if(finalLeft.contains(" + ")) {
            int indexOfOp = finalLeft.indexOf("+");
            String frac2 = Unsimplify(Fraction.valueOf(finalLeft.substring(indexOfOp + 2)));
            String expr = frac + "x" + " + " + frac2;
            Fraction finalVal = Evaluate(ConvertToArray(expr), ConvertToArray(rightSide));
            finalAns = finalVal.toString();
        } else if(finalLeft.contains(" - ")) {
            int indexOfOp = finalLeft.indexOf("-");
            String frac2 = Unsimplify(Fraction.valueOf(finalLeft.substring(indexOfOp + 2)));
            String expr = frac + "x" + " - " + frac2;
            Fraction finalVal = Evaluate(ConvertToArray(expr), ConvertToArray(rightSide));
            finalAns = finalVal.toString();
        }
        return finalAns;
    }

    /**
     * Solves for base linear expression
     * @param leftSide
     * @param rightSide
     * @return String
     */
    public String SolveForBase(String leftSide, String rightSide) {
        ArrayList<String> right = ConvertToArray(rightSide);
        ArrayList<String> left = ConvertToArray(leftSide);
        Fraction finalval = Evaluate(left, right);
        String finalans = finalval.toString();
        return finalans;
    }

    /**
     * Get equal index of expression
     * @param expr
     * @return int
     */
    private static int GetEqualIndex(String expr) {
        int equalIndex = 0;
        if(expr.contains("=")) {
            equalIndex = expr.indexOf("=");
        } else {
            throw new IllegalArgumentException("Not a valid expression");
        }
        return equalIndex;
    }

    /**
     * Gets right side of expression
     * @param expr
     * @return String
     */
    private String GetRightSide(String expr) {
        String right = expr.substring(GetEqualIndex(expr) + 2);
        return right;
    }
    
    /**
     * Get left side of expression
     * @param expr
     * @return String
     */
    private String GetLeftSide(String expr) {
        String left = expr.substring(0, GetEqualIndex(expr) - 1);
        return left;
    }

    /**
     * Convert side to array
     * @param side
     * @return ArrayList<String>
     */
    public static ArrayList<String> ConvertToArray(String side) {
        ArrayList<String> ArrayOfSide = new ArrayList<>();
        // For each items in side that is split by a space I add it to ArrayOfSide
        for(String items : side.split(" ")) {
            ArrayOfSide.add(items);
        }
        return ArrayOfSide;
    }

    /**
     * Check if there is an unknown in expression
     * @param expr
     * @return boolean
     * @throws IllegalArgumentException
     */
    public boolean CheckForUnknown(String expr) throws IllegalArgumentException {
        boolean flag = false;
        // If expr contains an x, there is an unknown
        if(expr.contains("x")) {
            flag = true;
        } else {
            // Throws error if there is not x
            throw new IllegalArgumentException("Must contain an unknown to solve.");
        }
        return flag;
    }

    /**
     * Check if side is being divided by x
     * @param side
     * @return boolean
     */
    public boolean CheckForXDivisor(String side) {
        boolean flag = false;
        if(side.contains("/x")) {
            flag = true;
        }
        return flag;
    }

    /**
     * Switches side of expression if divided by x
     * @param leftside
     * @param rightside
     * @return String
     */
    public String SwitchedSides(String leftside, String rightside) {
        Fraction finalRight = new Fraction(0,0,1);
        ArrayList<String> ArrayOfLeft = ConvertToArray(leftside);
        ArrayList<String> toApplyOnOtherSide = getConstants(ArrayOfLeft);
        String finalExpr = "";
        String ops = "";
        ArrayList<String> dividendOfx = new ArrayList<>();

        // Go through array and check if there is an operator or not
        for(int i = 0; i < ArrayOfLeft.size(); i++) {
            if(ArrayOfLeft.get(i).equals("+")) {
                ops = "-";
            } else if(ArrayOfLeft.get(i).equals("-")) {
                ops = "+";
            // If no operator then get the coeff of x
            } else if(ArrayOfLeft.get(i).matches("-?[1-9][0-9]*/x") || ArrayOfLeft.get(i).matches("-?[1-9][0-9]*/-?[1-9][0-9]*/x")) {
                dividendOfx.add(ArrayOfLeft.get(i).replace("/x", ""));
            }
        }

        ArrayList<Fraction> fracOfRight = new ArrayList<>();
        fracOfRight.add(Fraction.valueOf(rightside));
        
        // If there is a constant then get operator and simplify other side
        if(toApplyOnOtherSide.size() > 0) {
            ArrayList<Fraction> fracOfToBeApplied = new ArrayList<>();
            fracOfToBeApplied.add(Fraction.valueOf(toApplyOnOtherSide.get(0)));
            
            if(ops.equals("+")) {
                finalRight = fracOfRight.get(0).add(fracOfToBeApplied.get(0));
            } else if(ops.equals("-")) {
                finalRight = fracOfRight.get(0).subtract(fracOfToBeApplied.get(0));
            }
            String newLeft = finalRight.toString() + "x";
            String newRight = dividendOfx.get(0);
            finalExpr = newLeft + " " + "=" + " " + newRight;
        // Otherwise flip sides and add x on new left side to make new expression
        } else {
            String newRight = dividendOfx.get(0);
            String newLeft = rightside + "x";
            finalExpr = newLeft + " " + "=" + " " + newRight;
        }
        return finalExpr;
    }

    /**
     * Gets operators on side
     * @param side
     * @return ArrayList<String>
     */
    private static ArrayList<String> GetOperators(ArrayList<String> side) {
        ArrayList<String> operands = new ArrayList<String>();
        // Gets operators of array of side given
        for(int i = 0; i < side.size(); i++) {
            if(side.get(i).matches("\\+")) {
                operands.add("+");
            } else if(side.get(i).matches("-")) {
                operands.add("-");
            }
        }
        return operands;
    }


    /**
     * Checks for a coefficient
     * @param side
     * @return boolean
     */
    public boolean CheckForCoeff(String side) {
        boolean flag = false;
        String frac = "((-{0,1}[1-9][0-9]*)|-{0,1}0)/-{0,1}[1-9][0-9]*";
        String wholeFrac = "(-{0,1}[1-9][0-9]*)|-{0,1}0";
        int indexOfunknown = side.indexOf("x");
        // If there is a fraction, number, or negative sign there is a coeff otherwise there is no coeff
        if(side.substring(0, indexOfunknown).matches(wholeFrac) || side.substring(0, indexOfunknown).matches(frac) || side.substring(0, indexOfunknown).matches("-")) {
            flag = true;            
        }
        return flag;
    }

    /**
     * Unsimplifies a fraction
     * @param val
     * @return String
     */
    public String Unsimplify(Fraction val) {
        // Get num, den, and whole
        int valNum = val.getNum();
        int valWhole = val.getWhole();
        int valDen = val.getDen();
        // Get absolute values of num, den, and whole
        int newWhole = Math.abs(valWhole);
        int newDen = Math.abs(valDen);
        int newNum = Math.abs(valNum);
        // Get sign of frac
        int sign = Fraction.getSign(valWhole, valNum, valDen);
        String finalFrac = "";
        // If there is a whole number and a num, then get new frac
        if(newWhole > 0 && newNum > 0) {
            newNum = ((newWhole * newDen) + newNum) * sign;
            finalFrac = String.valueOf(newNum) + "/" + String.valueOf(newDen);
        } else {
            finalFrac = val.toString();
        }
        return finalFrac;
    }

    /**
     * Check if there is division on side
     * @param side
     * @return boolean
     */
    public boolean CheckForDivision(String side) {
        boolean flag = false;
        // If there is a slash after an x or bracket there is division, otherwise not
        if(side.contains(")/") || side.contains("x/")) {
            flag = true;
        } else {
            flag = false;
        }      
        return flag;
    }
    
    /**
     * Get the divisor
     * @param side
     * @return Fraction
     */
    public Fraction GetDivisor(String side) {
        Fraction val = new Fraction(0, 0, 1);
        // If its a case of dividing beside a bracket or unknown
        if(side.contains(")")) {
            int indofbrack = side.indexOf(")");
            String divisor = side.substring(indofbrack + 2);

            val = Fraction.valueOf(divisor);
        } else if(side.contains("x/")) {
            int indexOfunknown = side.indexOf("x");
            // If there is a space, then there is a constant and I take the divisor until that space
            if(side.contains(" ")) { 
                int indexOfSpace = side.indexOf(" ");
                String divisor = side.substring(indexOfunknown + 2, indexOfSpace);

                val = Fraction.valueOf(divisor);
            // Otherwise I just take divisor from after unknown
            } else {
                String divisor = side.substring(indexOfunknown + 2);

                val = Fraction.valueOf(divisor);
            }
        }

        return val;
    }

    /**
     * Apply divisor on right side
     * @param val
     * @param rightSide
     * @return Fraction
     */
    public Fraction ApplyDivisor(Fraction val, ArrayList<String> rightSide) {
        Fraction finalVal = new Fraction(0, 0, 1);
        for(int i = 0; i < rightSide.size(); i++) {
            val = val.multiply(Fraction.valueOf(rightSide.get(i)));
        }
        finalVal = val;
        return finalVal;
    }

    /**
     * Remove divisor from side
     * @param side
     * @return String
     */
    public String RemoveDivisor(String side) {
        String newSide = "";
        // If there is a bracket, remove everything after bracket
        if(side.contains(")")) {
            int indofbrack = side.indexOf(")");
            String divisor = side.substring(indofbrack + 1);
            newSide = side.replace(divisor, "");
        // If dividend is x then remove until first space or everything after
        } else if(side.contains("x/")) {
            int indexOfunknown = side.indexOf("x");
            if(side.contains(" ")) { 
                int indexOfSpace = side.indexOf(" ");
                newSide = side.replace(side.substring(indexOfunknown + 1, indexOfSpace), "");
            } else {
                newSide = side.replace(side.substring(indexOfunknown + 1), "");
            }
        }
        
        return newSide;
    }

    /**
     * Checks whether there is a distributor or not
     * @param side
     * @return boolean
     */
    public boolean CheckForDistributor(String side) {
        String frac = "((-{0,1}[1-9][0-9]*)|-{0,1}0)/-{0,1}[1-9][0-9]*";
        String wholeFrac = "(-{0,1}[1-9][0-9]*)|-{0,1}0";

        boolean flag = false;
        if(side.contains("(")) {
            int indexOfOpenBrack = side.indexOf("(");
            if(side.substring(0, indexOfOpenBrack).matches(frac) || side.substring(0, indexOfOpenBrack).matches(wholeFrac) || 
            side.substring(0, indexOfOpenBrack).isEmpty() || side.substring(0, indexOfOpenBrack).equals("-")) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * Gets the distributor
     * @param side
     * @return Fraction
     */
    public Fraction GetDistributor(String side) {
        int indexOfOpenBrack = side.indexOf("(");
        Fraction coeff = new Fraction(0,0,1);
        String frac = "((-{0,1}[1-9][0-9]*)|-{0,1}0)/-{0,1}[1-9][0-9]*";
        String wholeFrac = "(-{0,1}[1-9][0-9]*)|-{0,1}0";
        String distributor = side.substring(0, indexOfOpenBrack);
        // Gets value of distributor
        if(distributor.matches(frac) || distributor.matches(wholeFrac)) {
            coeff = Fraction.valueOf(distributor);
        } else if(distributor.isEmpty()) {
            distributor = "1";
            coeff = Fraction.valueOf(distributor);
        } else if(distributor.equals("-")) {
            distributor = "-1";
            coeff = Fraction.valueOf("-1");
        }
        return coeff;
    }

    /**
     * Gets numbers in bracket
     * @param side
     * @return String
     */
    public String GetNumbersInBracket(String side) {
        int indexOfOpenBracket = side.indexOf("(");
        int indexOfClosedBracket = side.indexOf(")");

        String nums = side.substring(indexOfOpenBracket + 1, indexOfClosedBracket);

        return nums;
    }

    /**
     * Distributes a number in brackets
     * @param nums
     * @param distributor
     * @return ArrayList<String>
     */
    public ArrayList<String> Distribute(ArrayList<String> nums, Fraction distributor) {
        ArrayList<String> termsOfNums = GetTerms(nums);
        ArrayList<String> constantsOfNums = getConstants(nums);
        ArrayList<String> op = GetOperators(nums);

        ArrayList<Fraction> coeff = new ArrayList<>();
        ArrayList<Fraction> cons = new ArrayList<>();

        ArrayList<String> finalterm = new ArrayList<>();
        ArrayList<String> finalconstant = new ArrayList<>();

        // Get all terms
        for(int i = 0; i < termsOfNums.size(); i++) {
            coeff.add(Fraction.valueOf(termsOfNums.get(i)));
        }

        // Get all constants
        for(int i = 0; i < constantsOfNums.size(); i++) {
            cons.add(Fraction.valueOf(constantsOfNums.get(i)));
        }

        // Multiply to distribute in each term
        for(Fraction item : coeff) {
            finalterm.add(item.multiply(distributor).toString());
        }

        // Multiply to distribute in each constant
        for(Fraction items : cons) {
            finalconstant.add(items.multiply(distributor).toString());
        }

        ArrayList<String> terms = new ArrayList<>();

        // Add x to each term
        for(int i = 0; i < finalterm.size(); i++) {
            terms.add(finalterm.get(i) + "x");
        }
        // Merge 3 lists into 1
        List<String> ListOfNums = Stream.of(terms, op, finalconstant).flatMap(Collection::stream).collect(Collectors.toList());

        // Convert List<String> to ArrayList<String>
        ArrayList<String> finalListOfNums = new ArrayList<>(ListOfNums);

        return finalListOfNums;
    }

    /**
     * Gets constants of side
     * @param side
     * @return ArrayList<String>
     */
    static ArrayList<String> getConstants(ArrayList<String> side) {
        ArrayList<String> constants = new ArrayList<>();
        String frac = "((-{0,1}[1-9][0-9]*)|-{0,1}0)/-{0,1}[1-9][0-9]*";
        String wholeFrac = "(-{0,1}[1-9][0-9]*)|-{0,1}0";
        // Get constants in ArrayList<String> and add to another array
        for(int i = 0; i < side.size(); i++) {
            if(side.get(i).matches(wholeFrac) || side.get(i).matches(frac)) {
                constants.add(side.get(i));
            }
        }
        return constants;
    }

    /**
     * Gets terms of side
     * @param side
     * @return ArrayList<String>
     */
    public static ArrayList<String> GetTerms(ArrayList<String> side) {
        ArrayList<String> terms = new ArrayList<>();
        String frac = "(((-{0,1}[1-9][0-9]*)|-{0,1}0)/-{0,1}[1-9][0-9]*)x";
        String wholeFrac = "((-{0,1}[1-9][0-9]*)|-{0,1}0)x";
        for(int i = 0; i < side.size(); i++) {
            // If empty coeff then it is 1
            if(side.get(i).equals("x")) {
                terms.add("1");
            // If it matches a whole frac or frac with x then add to terms array without x
            } else if(side.get(i).matches(frac) || side.get(i).matches(wholeFrac)) {
                terms.add(side.get(i).replace("x", ""));
            // If it is -x, add -1 to terms
            } else if(side.get(i).equals("-x")) {
                terms.add("-1");
            }
        }
        return terms;
    }

    /**
     * Evaluates to get value from ArrayList of left and right side
     * @param leftside
     * @param rightside
     * @return Fraction
     */
    public Fraction Evaluate(ArrayList<String> leftside, ArrayList<String> rightside) {
        String frac = "(((-{0,1}[1-9][0-9]*)|-{0,1}0)/-{0,1}[1-9][0-9]*)x";
        String wholeFrac = "((-{0,1}[1-9][0-9]*)|-{0,1}0)x";
        Fraction Value = new Fraction(0, 0, 1);
        ArrayList<String> operators = GetOperators(leftside);
        ArrayList<Fraction> CoeffOfTerms = new ArrayList<>();
        ArrayList<String> StringOfTerms = GetTerms(leftside);
        ArrayList<Fraction> FractionOfConstants = new ArrayList<>();
        ArrayList<String> Constants = getConstants(leftside);
        ArrayList<Fraction> FractionOfRightSide = new ArrayList<>();

        for(int i = 0; i < StringOfTerms.size(); i++) {
            CoeffOfTerms.add(Fraction.valueOf(StringOfTerms.get(i)));
        }

        for(int i = 0; i < Constants.size(); i++) {
            FractionOfConstants.add(Fraction.valueOf(Constants.get(i)));
        }
        for(int i = 0; i < rightside.size(); i++) {
            FractionOfRightSide.add(Fraction.valueOf(rightside.get(i)));
        }
        if(CoeffOfTerms.size() == 1 && FractionOfConstants.size() == 1) {
            if(operators.get(0).equals("+")) {        
                Value = FractionOfRightSide.get(0).subtract(FractionOfConstants.get(0));
            } else if(operators.get(0).equals("-")) {
                Value = FractionOfRightSide.get(0).add(FractionOfConstants.get(0));
            }
            Value = Value.divide(CoeffOfTerms.get(0));
        } else if(leftside.get(0).matches(frac) || leftside.get(0).matches(wholeFrac)) {
            Value = FractionOfRightSide.get(0).divide(CoeffOfTerms.get(0));
        }
        return Value;
    }
}  