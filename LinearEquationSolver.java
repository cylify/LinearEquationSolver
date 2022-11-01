import java.util.*;

class LinearEquation {
    private int equalIndex;
    private String leftSide;
    private String rightSide;
    private ArrayList<String> ops = new ArrayList<>();
    private ArrayList<String> constants = new ArrayList<>();
    private ArrayList<String> terms = new ArrayList<>();


    public LinearEquation(String expr) {

    }

    public void GetEqualIndex(String expr) {
        this.equalIndex = expr.indexOf("=");
    }

    public String GetRightSide(String expr) {
        String right = expr.substring(equalIndex + 2);
        return right;
    }
    
    public String GetLeftSide(String expr) {
        String left = expr.substring(0, this.equalIndex);
        return left;
    }

    public ArrayList<String> ConvertToArray(String side) {
        ArrayList<String> ArrayOfSide = new ArrayList<>();
        for(String items : side.split(" ")) {
            ArrayOfSide.add(items);
        }
        return ArrayOfSide;
    }

    public ArrayList<String> GetOperators(ArrayList<String> side) {
        ArrayList<String> operands = new ArrayList<String>();
        for(int i = 0; i < side.size(); i++) {
            if(side.get(i).matches("\\+")) {
                operands.add("-");
            } else if(side.get(i).matches("-")) {
                operands.add("\\+");
            }
        }
        return operands;
    }

    public ArrayList<String> CollectLikeTerms(ArrayList<String> side) {
        int whole = 0, num = 0;
        int den = 1;
        Fraction finaltermcoeff = new Fraction(whole, num, den);
        Fraction finalconstant = new Fraction(whole, num, den);
        ArrayList<String> operator = GetOperators(side);
        ArrayList<String> finalSide = new ArrayList<>();

        String mixedFrac = "((-{0,1}[1-9][0-9]*)|-{0,1}0) (([1-9][0-9]*)|-{0,1}0)/[1-9][0-9]*";
        String frac = "((-{0,1}[1-9][0-9]*)|-{0,1}0)/-{0,1}[1-9][0-9]*";
        String wholeFrac = "(-{0,1}[1-9][0-9]*)|-{0,1}0";

        for(int i = 0; i < side.size(); i++) {
            if(side.get(i).matches(frac) || side.get(i).matches(mixedFrac) || side.get(i).matches(wholeFrac)) {
                if(operator.get(i).matches("\\+")) {
                    finalconstant.add(Fraction.valueOf(side.get(i)));
                } else if(operator.get(i).matches("-")) {
                    finalconstant.subtract(Fraction.valueOf(side.get(i)));
                }
            } else if(side.get(i).matches(frac + "x") || side.get(i).matches(mixedFrac + "x") || side.get(i).matches(wholeFrac + "x")) {
                if(operator.get(i).matches("\\+")) {
                    finaltermcoeff.add(Fraction.valueOf(side.get(i).replace("x", "")));
                } else if(operator.get(i).matches("-")) {
                    finaltermcoeff.subtract(Fraction.valueOf(side.get(i).replace("x", "")));
                }
            }
        }
        String term = finaltermcoeff.toString() + "x";
        String constant1 = finalconstant.toString();
        String finalside = term + " " + constant1;
        
        for(String items : finalside.split(" ")) {
            finalSide.add(items);
        }

        return finalSide;

    }

    public boolean CheckForDivision(String side) {
        boolean flag = false;
        int indofbrack = side.indexOf("\\)"); 
        if(side.substring(indofbrack, indofbrack + 1).matches("\\/")) {
            flag = true;
        }
        return flag;
    }
        
    public Fraction GetDivisor(String side) {
        String mixedFrac = "((-{0,1}[1-9][0-9]*)|-{0,1}0) (([1-9][0-9]*)|-{0,1}0)/[1-9][0-9]*";
        String frac = "((-{0,1}[1-9][0-9]*)|-{0,1}0)/-{0,1}[1-9][0-9]*";
        String wholeFrac = "(-{0,1}[1-9][0-9]*)|-{0,1}0";

        int indofbrack = side.indexOf("\\)");
        int indexofdivision = side.indexOf(side.substring(indofbrack, indofbrack + 1));
        String divisor = side.substring(indexofdivision + 1);

        Fraction val = Fraction.valueOf(divisor);

        return val;
    }

    public void RemoveDivisor(String side) {

    }

    public Fraction NumericalCoeff(String side) {
        int indexOfOpenBrack = side.indexOf("\\(");
        String n = side.substring(0, indexOfOpenBrack);
        Fraction coeff = Fraction.valueOf(n);
        return coeff;
    }

    public void ApplyCoeff(String side, Fraction coeff) {
        
    }


    public static void main(String[] args) {
        
    }

}