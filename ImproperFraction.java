package LinearEquationSolver;

class ImproperFraction {
    private int num;
    private int den;

    public ImproperFraction(Fraction f) {

        if (f.getWhole() == 0) {
            num = f.getNum();
            den = f.getDen();
        } else {
            int sign = Fraction.getSign(f.getWhole(), f.getNum(), f.getDen());
            num = sign * (Math.abs(f.getWhole()) * Math.abs(f.getDen()) + Math.abs(f.getNum()));
            den = Math.abs(f.getDen());
        }

    }

    public int getNum() {
        return num;
    }

    public int getDen() {
        return den;
    }

    public String toString() {
        return String.format("%d/%d", num, den);
    }
}
