package LinearEquationSolver;

import java.util.*;

class LinearEquation {
    private int equalsIndex;
    private int operatorCount;
    private ArrayList<String> expression = new ArrayList<String>();
    private ArrayList<String> rightSide = new ArrayList<String>();
    private ArrayList<String> leftSide = new ArrayList<String>();
    private Fraction finalAnswer;


    public LinearEquation() {

    }

    public void GetExpression(String expr) {
        for(String items : expr.split(" ")) {
            this.expression.add(items);
        }
    }

    public void getEqualIndex() {
        for(int i = 0; i < this.expression.size(); i++) {
            if(this.expression.get(i).contains("=")) {
                this.equalsIndex = this.expression.indexOf("=");
            }
        }
    }

    public void getRightSide() {
        for(int i = this.equalsIndex + 1; i < this.expression.size(); i++) {
            this.rightSide.add(expression.get(i));
        }
    }

    public void getLeftside() {
        for(int i = 0; i < this.equalsIndex; i++) {
            this.leftSide.add(expression.get(i));
        }
    }

    public void getOperators() {

    }

    public Fraction evaluate() {
        
    }



    

    public static void main(String[] args) {
        
    }
}
