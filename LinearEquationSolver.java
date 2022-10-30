package LinearEquationSolver;

import java.util.*;

class LinearEquation {
    private int equalsIndex;
    private ArrayList<String> operators = new ArrayList<String>();
    private ArrayList<String> rightSide = new ArrayList<String>();
    private ArrayList<String> leftSide = new ArrayList<String>();
    private ArrayList<String> terms = new ArrayList<String>();
    private ArrayList<String> constants = new ArrayList<String>();


    /**
     * @param expr
     */
    public LinearEquation(String expr) {
        GetEqualIndex(expr);
        GetLeftSide(expr);
        GetRightSide(expr);
        System.out.println(this.leftSide);
        System.out.println(this.rightSide);


    }

    public void GetEqualIndex(String expr) {
        this.equalsIndex = expr.indexOf("=");
    }

    public void Distribute() {
        
    }

    
    /** 
     * @param expr
     */
    public void GetLeftSide(String expr) {
        String left = expr.substring(0, this.equalsIndex);

        for (String items : left.split(" ")) {
            this.leftSide.add(items);
        }
    }

    
    /** 
     * @param expression
     */
    public void GetRightSide(String expr) {
        String right = expr.substring(this.equalsIndex + 2);

        for (String items : right.split(" ")) {
            this.rightSide.add(items);
        }
    }

    public void CollectLikeTerms(ArrayList<String> side) {
        for(int i = 0; i < side.size(); i++) {

        }
    }

    public void GetOperators() {
        
    }



    public Fraction valueOf(String expr) {
        String mixedFrac = "((-{0,1}[1-9][0-9]*)|-{0,1}0) (([1-9][0-9]*)|-{0,1}0)/[1-9][0-9]*";
        String frac = "((-{0,1}[1-9][0-9]*)|-{0,1}0)/-{0,1}[1-9][0-9]*";
        String wholeFrac = "(-{0,1}[1-9][0-9]*)|-{0,1}0";

        if (expr.matches(mixedFrac)) {
            int indOfSpace = expr.indexOf(" ");
            int indOfSlash = expr.indexOf("/");
            int whole = Integer.valueOf(expr.substring(0, indOfSpace));
            int num = Integer.valueOf(expr.substring(indOfSpace + 1, indOfSlash));
            int den = Integer.valueOf(expr.substring(indOfSlash + 1));
            return new Fraction(whole, num, den);  
        } else if (expr.matches(frac)) {
            int indOfSlash = expr.indexOf("/");
            int num = Integer.valueOf(expr.substring(0, indOfSlash));
            int den = Integer.valueOf(expr.substring(indOfSlash + 1));
            return new Fraction(num, den);  
        } else if (expr.matches(wholeFrac)) {
            int whole = Integer.valueOf(expr);
            return new Fraction(whole);
        } else {
            throw new IllegalArgumentException("Expression does not represent a fraction");
        }
    }
    





    
    /** 
     * @param args
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String expr = in.nextLine();
        LinearEquation d = new LinearEquation(expr);
        in.close();
    }
}
