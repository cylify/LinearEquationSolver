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


    public LinearEquation(String expr) {

        for(String items : expr.split(" ")) {
            this.expression.add(items);
        }

        this.equalsIndex = expression.indexOf("=");

        for(int i = 0; i < this.equalsIndex; i++) {
            this.leftSide.add(this.expression.get(i));
        }

        for(int i = this.equalsIndex + 1; i < expression.size(); i++) {
            this.rightSide.add(this.expression.get(i));
        }

        getCountAndOperators(leftSide);

        System.out.print(leftSide + " " + rightSide + " " + operatorCount + " " + operators);
    }

    public void getCountAndOperators(ArrayList<String> leftSide) {
        for(int i = 0; i < this.leftSide.size(); i++) {
            if(this.leftSide.get(i).matches("-{0,1}[0-9]+\\(-{0,1}[1-9]+x")) {
                this.operatorCount += 2;
                this.operators.add("/");
                this.operators.add("/");
            } else if(this.leftSide.get(i).matches("-{0,1}[0-9]+\\(x")) {
                this.operatorCount++;
                this.operators.add("/");
                this.leftSide.get(i).replace("\\(x", "");
            } else if(this.leftSide.get(i).matches("-{0,1}[0-9]+x")) {
                this.operatorCount++;
                this.operators.add("/");
            } else if(this.leftSide.get(i).contains("+")) {
                this.operatorCount++;
                this.operators.add("-");
            } else if(this.leftSide.get(i).contains("-")) {
                this.operatorCount++;
                this.operators.add("+");
            } else if(this.leftSide.get(i).matches("-{0,1}[0-9]+/x")) {
                this.operatorCount++;
                this.operators.add("*");
            } else if(this.leftSide.get(i).matches("-{0,1}[0-9]+\\)\\/x")) {
                this.operatorCount++;
                this.operators.add("*");
            } 
        }
    }

    public void getNums() {

    }


    


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String expr = in.nextLine();
        LinearEquation d = new LinearEquation(expr);
        in.close();
    }
}
