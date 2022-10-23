package LinearEquationSolver;

import java.beans.Expression;
import java.util.*;

class LinearEquationSolver {
    private int operatorCount = 0;


    public ArrayList<String> getExpression(String expr) {
        ArrayList<String> expression = new ArrayList<String>();
        for(String items : expr.split(" ")) {
            expression.add(items);
        }
        return expression;
    }



}
