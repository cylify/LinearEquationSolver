package LinearEquationSolver;

import java.util.*;

class LinearEquation {
    private int equalsIndex;
    private int operatorCount;
    private ArrayList<String> expression = new ArrayList<String>();


    public int getOperatorCount() {
        return operatorCount;
    }


    public void setOperatorCount(int operatorCount) {
        this.operatorCount = operatorCount;
    }

    public void getExpression(String expr) {
        for(String items : expr.split(" ")) {
            this.expression.add(items);
        }
    }

    public void getEqualsIndex() {
        for(int i = 0; i < this.expression.size(); i++) {
            if(expression.get(i).contains("=")) {
                this.equalsIndex = expression.indexOf("=");
            }
        }
    }

    


    public static void main(String[] args) {
        
    }
}
