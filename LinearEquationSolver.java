package LinearEquationSolver;

import java.util.*;

class LinearEquation {
    private int equalsIndex;
    private int operatorCount;
    private ArrayList<String> expression = new ArrayList<String>();
    private ArrayList<String> operators = new ArrayList<String>();
    private ArrayList<String> rightSide = new ArrayList<String>();
    private ArrayList<String> leftSide = new ArrayList<String>();
    private ArrayList<String> coeffandnums = new ArrayList<String>();
    private Fraction finalAnswer;


    /**
     * @param expr
     */
    public LinearEquation(String expr) {

        for(String items : expr.split(" ")) {
            this.expression.add(items);
        }

        this.equalsIndex = expression.indexOf("=");

        GetLeftSide(expression);

        GetRightSide(expression);

        GetOperators(leftSide);

        GetCount(operators);

        GetNums(leftSide);

        System.out.print(leftSide + " " + rightSide + " " + operatorCount + " " + operators + " " + coeffandnums);
    }

    
    /** 
     * @param expression
     */
    public void GetLeftSide(ArrayList<String> expression) {
        for(int i = 0; i < this.equalsIndex; i++) {
            this.leftSide.add(this.expression.get(i));
        }
    }

    
    /** 
     * @param expression
     */
    public void GetRightSide(ArrayList<String> expression) {
        for(int i = this.equalsIndex + 1; i < expression.size(); i++) {
            this.rightSide.add(this.expression.get(i));
        }
    }

    
    /** 
     * @param leftSide
     */
    public void GetOperators(ArrayList<String> leftSide) {
        for(int i = 0; i < this.leftSide.size(); i++) {
            if(leftSide.get(i).matches("-{0,1}[0-9]+\\(-{0,1}[1-9]+x")) {
                this.operators.add("/");
                this.operators.add("/");
            } else if(leftSide.get(i).matches("-{0,1}[0-9]+\\(x")) {
                this.operators.add("/");
            } else if(leftSide.get(i).matches("-{0,1}[0-9]+x")) {
                this.operators.add("/");
            } else if(leftSide.get(i).contains("+")) {
                this.operators.add("-");
            } else if(leftSide.get(i).contains("-")) {
                this.operators.add("+");
            } else if(leftSide.get(i).matches("-{0,1}[0-9]+/x")) {
                this.operators.add("*");
            } else if(leftSide.get(i).matches("-{0,1}[0-9]+\\)\\/x")) {
                this.operators.add("*");
            } else if(leftSide.get(i).matches("-{0,1}[0-9]+\\)\\/-{0,1}[0-9]+")) {
                this.operators.add("*");
            }
        }
    }

    
    /** 
     * @param operators
     */
    public void GetCount(ArrayList<String> operators) {
        this.operatorCount = operators.size();
    }


    
    /** 
     * @param leftSide
     */
    public void GetNums(ArrayList<String> leftSide) {
        for(int i = 0; i < leftSide.size(); i++) {
            if(leftSide.get(i).matches("-{0,1}[1-9]+")) {
                coeffandnums.add(leftSide.get(i));
            }
        }
    }

    
    /** 
     * @param rightSide
     * @param coeffandnums
     */
    public void Evaluate(ArrayList<String> rightSide, ArrayList<String> coeffandnums) {
        for(int i = 0; i < rightSide.size(); i++) {
            
        }
    }


    /**
     * @return
     * @throws IllegalArgumentException
     */
    public Fraction valueOf() {
        String mixedFrac = "((-{0,1}[1-9][0-9]*)|-{0,1}0) (([1-9][0-9]*)|-{0,1}0)/[1-9][0-9]*";
        String frac = "((-{0,1}[1-9][0-9]*)|-{0,1}0)/-{0,1}[1-9][0-9]*";
        String wholeFrac = "(-{0,1}[1-9][0-9]*)|-{0,1}0";
        
        if(this.rightSide.size() > 1) {

        } else if(this.rightSide.size() == 1) {

        } else {
            throw new IllegalArgumentException("Expression must equal a number");
        }

        if(this.leftSide.size() >= 1) {

        } else {
            throw new IllegalArgumentException("Must have an expression to check");
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
