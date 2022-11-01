import java.util.*;

class Fraction {
    private int whole = 0;
    private int num;
    private int den;
    private Fraction firstFraction;
    private Fraction secondFraction;

    public Fraction(int whole) {
        this.whole = whole;
        this.num = 0;
        this.den = 1;
    }

    public Fraction(int num, int den) {
        if(den == 0) {
            throw new IllegalArgumentException("Denominator cannot be 0");
        }
        
        this.whole = 0;
        this.num = num;
        this.den = den;


        int sign = getSign(0, this.num, this.den);
        // making sure it is not improper
        if (Math.abs(this.num) >= Math.abs(this.den)) {
            this.whole = Math.abs(this.num) / Math.abs(this.den);
            this.num = Math.abs(this.num) % Math.abs(this.den);
        }

        if (this.whole != 0) {
            this.whole = sign * this.whole;
            this.num = Math.abs(this.num);
            this.den = Math.abs(this.den);
        } else {
            this.num = sign * Math.abs(this.num);
            this.den = Math.abs(this.den);
        }
        
        int d = gcd(this.num, this.den);
        this.num = this.num / d;
        this.den = this.den / d;

    }

    public Fraction(int whole, int num, int den) {
        
        this(num, den);

        if(den == 0) {
            throw new IllegalArgumentException("Denominator cannot be 0");
        }

        int sign = getSign(whole, num, den);
        this.whole = sign * (Math.abs(whole) + Math.abs(this.whole));   
        
    }

    public Fraction(String expr) throws IllegalArgumentException {
        ArrayList<String> expression = new ArrayList<String>();
        ArrayList<String> firstset = new ArrayList<String>();
        ArrayList<String> secondset = new ArrayList<String>();
        String operator = "";
        int operatorindex = 0;

        for(String item : expr.split(" ")) {
            expression.add(item);
        }


        if(expression.contains("+")) {
            operator = "+";
            operatorindex = expression.indexOf("+");
        } else if(expression.contains("-")) {
            operator = "-";
            operatorindex = expression.indexOf("-");
        } else if(expression.contains("*")) {
            operator = "*";
            operatorindex = expression.indexOf("*");
        } else if(expression.contains("/")) {
            operator = "/";
            operatorindex = expression.indexOf("/");
        } else {
            throw new IllegalArgumentException("Not in the correct format");
        }
        

        for(int j = 0; j < operatorindex; j++) {
            firstset.add(expression.get(j));
        }

        for(int k = operatorindex + 1; k < expression.size() ; k++) {
            secondset.add(expression.get(k));
        }

        if(firstset.size() > 1) {    
            if(firstset.get(0).matches("-*[0-9]+")) {
                int whole1 = Integer.parseInt(firstset.get(0));
                if(firstset.get(1).matches("-*[0-9]+/-*[1-9][0-9]*")) {
                    int indexOfFirstSlash = firstset.get(1).indexOf("/");
                    String fractionOne = firstset.get(1);
                    int num1 = Integer.parseInt(fractionOne.substring(0, indexOfFirstSlash));
                    int den1 = Integer.parseInt(fractionOne.substring(indexOfFirstSlash + 1));
                    if(whole1 == 0 && (num1 != 0 && den1 != 0)) {
                        this.firstFraction = new Fraction(num1, den1);
                    } else if(whole1 > 0) {
                        if(num1 != 0 && den1 != 0) {
                            this.firstFraction = new Fraction(whole1, num1, den1);
                        } else if(num1 == 0 && den1 > 0) {
                            this.firstFraction = new Fraction(whole1);
                        } else {
                            throw new IllegalArgumentException("Not in the correct format");
                        }
                    } else if(whole1 < 0) {
                        if(num1 > 0 && den1 > 0) {
                            this.firstFraction = new Fraction(whole1, num1, den1);
                        } else if(num1 == 0 && den1 > 0) {
                            this.firstFraction = new Fraction(whole1);
                        } else {
                            throw new IllegalArgumentException("Not in the correct format");
                        }
                    } else {
                        throw new IllegalArgumentException("Not in the correct format");
                    }
                } else {
                    throw new IllegalArgumentException("Not in the correct format");
                }
            } else {
                throw new IllegalArgumentException("Not in the correct format");
            }

        } else if(firstset.size() == 1) {
            if(firstset.get(0).matches("-*[0-9]+")) {
                int whole1 = Integer.parseInt(firstset.get(0));
                this.firstFraction = new Fraction(whole1);
            } else if(firstset.get(0).matches("-*[0-9]+/-*[1-9][0-9]*")) {
                int indexOfFirstSlash = firstset.get(0).indexOf("/");
                String fractionOne = firstset.get(0);
                int num1 = Integer.parseInt(fractionOne.substring(0, indexOfFirstSlash));
                int den1 = Integer.parseInt(fractionOne.substring(indexOfFirstSlash + 1));
                this.firstFraction = new Fraction(num1, den1);
            } else {
                throw new IllegalArgumentException("Not in the correct format");
            }
        } else {
            throw new IllegalArgumentException("Not in the correct format");
        }


        if(secondset.size() > 1) {
            if(secondset.get(0).matches("-*[0-9]+")) {
                int whole2 = Integer.parseInt(secondset.get(0));
                if(secondset.get(1).matches("-*[0-9]+/-*[1-9][0-9]*")) {
                    int indexOfSecondSlash = secondset.get(1).indexOf("/");
                    String fractionTwo = secondset.get(1);
                    int num2 = Integer.parseInt(fractionTwo.substring(0, indexOfSecondSlash));
                    int den2 = Integer.parseInt(fractionTwo.substring(indexOfSecondSlash + 1));
                    if(whole2 == 0 && (num2 != 0 && den2 != 0)) {
                        this.secondFraction = new Fraction(num2, den2);
                    } else if(whole2 > 0) {
                        if(num2 != 0 && den2 != 0) {
                            this.secondFraction = new Fraction(whole2, num2, den2);
                        } else if(num2 == 0 && den2 > 0) {
                            this.secondFraction = new Fraction(whole2);
                        } else {
                            throw new IllegalArgumentException("Not in the correct format");
                        }
                    } else if(whole2 < 0) {
                        if(num2 > 0 && den2 > 0) {
                            this.secondFraction = new Fraction(whole2, num2, den2);
                        } else if(num2 == 0 && den2 > 0) {
                            this.secondFraction = new Fraction(whole2);
                        } else {
                            throw new IllegalArgumentException("Not in the correct format");
                        }
                    } else {
                        throw new IllegalArgumentException("Not in the correct format");
                    }
                } else {
                    throw new IllegalArgumentException("Not in the correct format");
                }
            } else {
                throw new IllegalArgumentException("Not in the correct format");
            }

        } else if(secondset.size() == 1) {
            if(secondset.get(0).matches("-*[0-9]+")) {
                int whole2 = Integer.parseInt(secondset.get(0));
                this.secondFraction = new Fraction(whole2);
            } else if(secondset.get(0).matches("-*[0-9]+/-*[1-9][0-9]*")) {
                int indexOfSecondSlash = secondset.get(0).indexOf("/");
                String fractionTwo = secondset.get(0);
                int num2 = Integer.parseInt(fractionTwo.substring(0, indexOfSecondSlash));
                int den2 = Integer.parseInt(fractionTwo.substring(indexOfSecondSlash + 1));
                this.secondFraction = new Fraction(num2, den2);
            } else {
                throw new IllegalArgumentException("Not in the correct format");
            }
        } else {
            throw new IllegalArgumentException("Not in the correct format");
        }


        if(operator.equals("+")) {
            Fraction result = this.firstFraction.add(this.secondFraction);
            System.out.println(result);
        } else if(operator.equals("-")) {
            Fraction result = this.firstFraction.subtract(secondFraction);
            System.out.println(result);
        } else if(operator.equals("*")) {
            Fraction result = this.firstFraction.multiply(secondFraction);
            System.out.println(result);
        } else if(operator.equals("/")) {
            Fraction result = this.firstFraction.divide(secondFraction);
            System.out.println(result);
        } else {
            throw new IllegalArgumentException("Not in the correct format");
        }
        
    }

    public static Fraction valueOf(String expr) {
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
        } else{
            throw new IllegalArgumentException("Expression does not represent a fraction");
        }
    }


    
    /** 
     * @return int
     */
    public int getWhole() {
        return whole;
    }

    
    /** 
     * @return int
     */
    public int getNum() {
        return num;
    }

    
    /** 
     * @return int
     */
    public int getDen() {
        return den;
    }

    
    /** 
     * @param newWholeVal
     */
    public void setWhole(int newWholeVal) {
        whole = newWholeVal;
    }

    
    /** 
     * @param newNumVal
     */
    public void setNum(int newNumVal) {
        num = newNumVal;
    }

    
    /** 
     * @param newDenVal
     */
    public void setDen(int newDenVal) {
        whole = newDenVal;
    }

    
    /** 
     * @param a
     * @param b
     * @return int
     */
    public static int gcd(int a, int b) {
        if (a == 1 || b == 1 || a == 0 || b == 0) {
            return 1;
        }
        int d = Math.min(Math.abs(a), Math.abs(b));
        while (true) {
            if (a % d == 0 && b % d == 0) {
                return d;
            }
            d--;
        }

    }

    
    /** 
     * @param whole
     * @param num
     * @param den
     * @return int
     */
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

    
    /** 
     * @return String
     */
    public String toString() {
        String exp = "";
        if (num == 0) {
            return Integer.toString(whole);
        } else if (whole == 0) {
            exp = String.format("%d/%d", num, den);
        } else if (whole != 0) {
            exp = String.format("%d %d/%d", whole, num, den);
        }
        return exp;
    }

    
    /** 
     * @return Fraction
     */
    public Fraction clone() {
        return new Fraction(whole, num, den);
    }

    
    /** 
     * @param other
     * @return Fraction
     */
    public Fraction add(Fraction other) {
        ImproperFraction f = new ImproperFraction(this);
        ImproperFraction g = new ImproperFraction(other);
        int a = f.getNum();
        int b = f.getDen();
        int c = g.getNum();
        int d = g.getDen();
        int newNum = a * d + b * c;
        int newDen = b * d;
        return new Fraction(newNum, newDen);
    }

    
    /** 
     * @param other
     * @return Fraction
     */
    public Fraction subtract(Fraction other) {
        Fraction c = other.clone();
        if (other.whole == 0) {
            c.setNum(-1 * other.num);
        } else {
            c.setWhole(-1 * other.whole);
        }
        return this.add(c);

    }

    
    /** 
     * @param other
     * @return Fraction
     */
    public Fraction multiply(Fraction other){
        ImproperFraction f = new ImproperFraction(this);
        ImproperFraction g = new ImproperFraction(other);
        int a = f.getNum();
        int b = f.getDen();
        int c = g.getNum();
        int d = g.getDen();
        int newNum = a * c;
        int newDen = b * d;
        return new Fraction(newNum, newDen);
    }

    
    /** 
     * @param other
     * @return Fraction
     */
    public Fraction divide(Fraction other){
        ImproperFraction g = new ImproperFraction(other);
        int c = g.getNum();
        int d = g.getDen();
        return this.multiply(new Fraction(d, c));
    }

    
    /** 
     * @param args
     */
    public static void main(String[] args) {

        Fraction g = new Fraction(15, -6);

        System.out.println(g);

        Fraction f = new Fraction(-1, 3, 4);

        System.out.println(f);

        System.out.println(g.add(f));
        
        Fraction h = new Fraction(-2, 3, 8);

        System.out.println(f.add(h));

        System.out.println(g.subtract(h));

        System.out.println(h.divide(f));

    }

}

class FractionCalculator {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in); 
        while(true) {
            String expr = in.nextLine();
            try {
                Fraction f = new Fraction(expr);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Try again!");
            }
        }
        in.close();
    }
}