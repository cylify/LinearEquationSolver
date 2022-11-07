import java.util.*;
import java.util.stream.*;

class LinearEquation {
    private String rightSide;
    private String leftSide;
    static String answer;

    public LinearEquation(String expr) throws IllegalArgumentException {
        this.rightSide = GetRightSide(expr);
        this.leftSide = GetLeftSide(expr);

        boolean checkForX = CheckForUnknown(expr);
        boolean flagForXdivisor = CheckForXDivisor(this.leftSide);
        boolean flagForDivisor = CheckForDivision(this.leftSide);
        boolean flagForDistributor = CheckForDistributor(this.leftSide);

        if(checkForX) {
            if(flagForXdivisor) {
                String ans = SolveForXDivisor(leftSide, rightSide);
                answer = SolveForXDivisor(leftSide, rightSide);
                System.out.println("x = " + ans);
            } else if(flagForDivisor) {
                String ans = SolveForDivisor(leftSide, flagForDistributor);
                answer = SolveForDivisor(leftSide, flagForDistributor);
                System.out.println("x = " + ans);
            } else if(flagForDistributor) {
                String ans = SolveForDistribution(leftSide, rightSide);
                answer = SolveForDistribution(leftSide, rightSide);
                System.out.println("x = " + ans);
            } else {
                String ans = SolveForBase(leftSide, this.rightSide);
                answer = SolveForBase(leftSide, this.rightSide);
                System.out.println("x = " + ans);
            }
        }
    }

    public String SolveForXDivisor(String leftSide, String rightside) {
        String switched = SwitchedSides(leftSide, rightSide);
        String switchedleft = GetLeftSide(switched);
        String switchedright = GetRightSide(switched);
        ArrayList<String> ArrayOfswitchedRight = ConvertToArray(switchedright);
        ArrayList<String> ArrayOfswitchedLeft = ConvertToArray(switchedleft);
        String finalval = Evaluate(ArrayOfswitchedLeft, ArrayOfswitchedRight).toString();
        return finalval;
    }

    public String SolveForDivisor(String leftSide, boolean flagForDistributor) {
        String newRight = "";
        String finalAns = "";
        String divisor = GetDivisor(leftSide).toString();
        String newLeft = RemoveDivisor(leftSide);
        ArrayList<String> right = ConvertToArray(this.rightSide);
        if(!(newLeft.contains("("))) {
            if(newLeft.matches(".*[1-9].*")) {
                newRight = Evaluate(ConvertToArray(newLeft), right).toString();
                newRight = ApplyDivisor(Fraction.valueOf(divisor), ConvertToArray(newRight)).toString();
            } else {
                newRight = ApplyDivisor(Fraction.valueOf(divisor), right).toString();
            }
        } else {            
            newRight = ApplyDivisor(Fraction.valueOf(divisor), right).toString();
            if(newRight.length() > 3) {
                newRight = Unsimplify(Fraction.valueOf(newRight));
            }
        }
        
        flagForDistributor = CheckForDistributor(newLeft);
        if(flagForDistributor) {
            finalAns = SolveForDistribution(newLeft, newRight);
        } else {
            finalAns = newRight;
        }
        return finalAns;
    }

    public String SolveForDistribution(String leftSide, String rightSide) {
        Fraction distributor = GetDistributor(leftSide);
        ArrayList<String> numsInBracket = ConvertToArray(GetNumbersInBracket(leftSide));
        ArrayList<String> finalLeft = Distribute(numsInBracket, distributor);
        Fraction finalVal = Evaluate(finalLeft, ConvertToArray(rightSide));
        String finalAns = finalVal.toString();
        return finalAns;
    }

    public String SolveForBase(String leftSide, String rightSide) {
        ArrayList<String> right = ConvertToArray(rightSide);
        ArrayList<String> left = ConvertToArray(leftSide);
        Fraction finalval = Evaluate(left, right);
        String finalans = finalval.toString();
        return finalans;
    }

    static int GetEqualIndex(String expr) {
        int equalIndex = expr.indexOf("=");
        return equalIndex;
    }

    public String GetRightSide(String expr) {
        String right = expr.substring(GetEqualIndex(expr) + 2);
        return right;
    }
    
    public String GetLeftSide(String expr) {
        String left = expr.substring(0, GetEqualIndex(expr) - 1);
        return left;
    }

    static ArrayList<String> ConvertToArray(String side) {
        ArrayList<String> ArrayOfSide = new ArrayList<>();
        for(String items : side.split(" ")) {
            ArrayOfSide.add(items);
        }
        return ArrayOfSide;
    }

    public boolean CheckForUnknown(String expr) throws IllegalArgumentException {
        boolean flag = false;
        if(expr.contains("x")) {
            flag = true;
        } else {
            throw new IllegalArgumentException("Must contain an unknown to solve.");
        }
        return flag;
    }

    public boolean CheckForXDivisor(String side) {
        boolean flag = false;
        if(side.contains("/x")) {
            flag = true;
        }
        return flag;
    }

    public String SwitchedSides(String leftside, String rightside) {
        Fraction finalRight = new Fraction(0,0,1);
        ArrayList<String> ArrayOfLeft = ConvertToArray(leftside);
        ArrayList<String> toApplyOnOtherSide = getConstants(ArrayOfLeft);
        String finalExpr = "";
        String ops = "";
        ArrayList<String> dividendOfx = new ArrayList<>();

        for(int i = 0; i < ArrayOfLeft.size(); i++) {
            if(ArrayOfLeft.get(i).equals("+")) {
                ops = "-";
            } else if(ArrayOfLeft.get(i).equals("-")) {
                ops = "+";
            } else if(ArrayOfLeft.get(i).matches("-{0,1}[0-9]+/x")) {
                dividendOfx.add(ArrayOfLeft.get(i).replace("/x", ""));
            }
        }

        ArrayList<Fraction> fracOfRight = new ArrayList<>();
        fracOfRight.add(Fraction.valueOf(rightside));
        
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
        } else {
            String newRight = dividendOfx.get(0);
            String newLeft = rightside + "x";
            finalExpr = newLeft + " " + "=" + " " + newRight;
        }
        return finalExpr;
    }

    static ArrayList<String> GetOperators(ArrayList<String> side) {
        ArrayList<String> operands = new ArrayList<String>();
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
     * @return
     */
    public boolean CheckForCoeff(String side) {
        boolean flag = false;
        String frac = "((-{0,1}[1-9][0-9]*)|-{0,1}0)/-{0,1}[1-9][0-9]*";
        String wholeFrac = "(-{0,1}[1-9][0-9]*)|-{0,1}0";
        int indexOfunknown = side.indexOf("x");
        if(side.substring(0, indexOfunknown).matches(wholeFrac) || side.substring(0, indexOfunknown).matches(frac)) {
            flag = true;            
        }
        return flag;
    }

    public String Unsimplify(Fraction val) {
        int valNum = val.getNum();
        int valWhole = val.getWhole();
        int valDen = val.getDen();
        int sign = getSign(valWhole, valNum, valDen);
        String finalFrac = "";
        if(valWhole > 0 && valNum > 0) {
            valNum = ((valWhole * valDen) + valNum) * sign;
            finalFrac = String.valueOf(valNum) + "/" + String.valueOf(valDen);
        }
        return finalFrac;
    }

    /**
     * 
     * @param side
     * @return
     */
    public boolean CheckForDivision(String side) {
        boolean flag = false;
        if(side.contains(")/") || side.contains("x/")) {
            flag = true;
        } else {
            flag = false;
        }      
        return flag;
    }
    
    /**
     * 
     * @param side
     * @return
     */
    public Fraction GetDivisor(String side) {
        Fraction val = new Fraction(0, 0, 1);
        if(side.contains(")")) {
            int indofbrack = side.indexOf(")");
            String divisor = side.substring(indofbrack + 2);

            val = Fraction.valueOf(divisor);
        } else if(side.contains("x/")) {
            int indexOfunknown = side.indexOf("x");
            if(side.contains(" ")) { 
                int indexOfSpace = side.indexOf(" ");
                String divisor = side.substring(indexOfunknown + 2, indexOfSpace);

                val = Fraction.valueOf(divisor);
            } else {
                String divisor = side.substring(indexOfunknown + 2);

                val = Fraction.valueOf(divisor);
            }
        }

        return val;
    }

    public Fraction ApplyDivisor(Fraction val, ArrayList<String> rightSide) {
        Fraction finalVal = new Fraction(0, 0, 1);
        for(int i = 0; i < rightSide.size(); i++) {
            val = val.multiply(Fraction.valueOf(rightSide.get(i)));
        }
        finalVal = val;
        return finalVal;
    }

    public String RemoveDivisor(String side) {
        String newSide = "";
        if(side.contains(")")) {
            int indofbrack = side.indexOf(")");
            String divisor = side.substring(indofbrack + 1);
            newSide = side.replace(divisor, "");
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

    public static int getSign(int whole, int num, int den) {
        int neg = 0;
        if (whole < 0) {
            neg++;
        }
        if (num < 0) {
            neg++;
        }
        if (den < 0) {
            neg++;
        }
        if (neg % 2 == 1) {
            return -1;
        } else {
            return 1;
        }
    }

    public boolean CheckForDistributor(String side) {
        String frac = "((-{0,1}[1-9][0-9]*)|-{0,1}0)/-{0,1}[1-9][0-9]*";
        String wholeFrac = "(-{0,1}[1-9][0-9]*)|-{0,1}0";

        boolean flag = false;

        if(side.contains("(")) {
            int indexOfOpenBrack = side.indexOf("(");
            if(side.substring(0, indexOfOpenBrack).matches(frac) || side.substring(0, indexOfOpenBrack).matches(wholeFrac) || side.substring(0, indexOfOpenBrack).isEmpty() || side.substring(0, indexOfOpenBrack).equals("-")) {
                flag = true;
            }
        }
        return flag;
    }

    public Fraction GetDistributor(String side) {
        int indexOfOpenBrack = side.indexOf("(");
        Fraction coeff = new Fraction(0,0,1);
        String frac = "((-{0,1}[1-9][0-9]*)|-{0,1}0)/-{0,1}[1-9][0-9]*";
        String wholeFrac = "(-{0,1}[1-9][0-9]*)|-{0,1}0";
        String distributor = side.substring(0, indexOfOpenBrack);
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

    public String GetNumbersInBracket(String side) {
        int indexOfOpenBracket = side.indexOf("(");
        int indexOfClosedBracket = side.indexOf(")");

        String nums = side.substring(indexOfOpenBracket + 1, indexOfClosedBracket);

        return nums;
    }

    public ArrayList<String> Distribute(ArrayList<String> nums, Fraction distributor) {
        ArrayList<String> termsOfNums = GetTerms(nums);
        ArrayList<String> constantsOfNums = getConstants(nums);
        ArrayList<String> op = GetOperators(nums);

        ArrayList<Fraction> coeff = new ArrayList<>();
        ArrayList<Fraction> cons = new ArrayList<>();

        ArrayList<String> finalterm = new ArrayList<>();
        ArrayList<String> finalconstant = new ArrayList<>();

        for(int i = 0; i < termsOfNums.size(); i++) {
            coeff.add(Fraction.valueOf(termsOfNums.get(i)));
        }

        for(int i = 0; i < constantsOfNums.size(); i++) {
            cons.add(Fraction.valueOf(constantsOfNums.get(i)));
        }

        for(Fraction item : coeff) {
            finalterm.add(item.multiply(distributor).toString());
        }

        for(Fraction items : cons) {
            finalconstant.add(items.multiply(distributor).toString());
        }

        ArrayList<String> terms = new ArrayList<>();

        for(int i = 0; i < finalterm.size(); i++) {
            terms.add(finalterm.get(i) + "x");
        } 
        List<String> ListOfNums = Stream.of(terms, op, finalconstant).flatMap(Collection::stream).collect(Collectors.toList());

        ArrayList<String> finalListOfNums = new ArrayList<>(ListOfNums);

        return finalListOfNums;
    }

    static ArrayList<String> getConstants(ArrayList<String> side) {
        ArrayList<String> constants = new ArrayList<>();
        String frac = "((-{0,1}[1-9][0-9]*)|-{0,1}0)/-{0,1}[1-9][0-9]*";
        String wholeFrac = "(-{0,1}[1-9][0-9]*)|-{0,1}0";
        for(int i = 0; i < side.size(); i++) {
            if(side.get(i).matches(wholeFrac) || side.get(i).matches(frac)) {
                constants.add(side.get(i));
            }
        }
        return constants;
    }

    public static ArrayList<String> GetTerms(ArrayList<String> side) {
        ArrayList<String> terms = new ArrayList<>();
        String frac = "(((-{0,1}[1-9][0-9]*)|-{0,1}0)/-{0,1}[1-9][0-9]*)x";
        String wholeFrac = "((-{0,1}[1-9][0-9]*)|-{0,1}0)x";
        for(int i = 0; i < side.size(); i++) {
            if(side.get(i).equals("x")) {
                terms.add("1");
            } else if(side.get(i).matches(frac) || side.get(i).matches(wholeFrac)) {
                terms.add(side.get(i).replace("x", ""));
            } else if(side.get(i).equals("-x")) {
                terms.add("-1");
            }
        }
        return terms;
    }

    public Fraction Evaluate(ArrayList<String> leftside, ArrayList<String> rightside) {
        String frac = "(((-{0,1}[1-9][0-9]*)|-{0,1}0)/-{0,1}[1-9][0-9]*)x";
        String wholeFrac = "((-{0,1}[1-9][0-9]*)|-{0,1}0)x";
        Fraction xValue = new Fraction(0, 0, 1);
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
                xValue = FractionOfRightSide.get(0).subtract(FractionOfConstants.get(0));
            } else if(operators.get(0).equals("-")) {
                xValue = FractionOfRightSide.get(0).add(FractionOfConstants.get(0));
            }
            xValue = xValue.divide(CoeffOfTerms.get(0));
        } else if(leftside.get(0).matches(frac) || leftside.get(0).matches(wholeFrac)) {
            xValue = FractionOfRightSide.get(0).divide(CoeffOfTerms.get(0));
        }
        return xValue;
    }
}  