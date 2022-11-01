class ImproperFraction {
    private int num;
    private int den;

    /**
     * @param f
     */
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
     * @return String
     */
    public String toString() {
        return String.format("%d/%d", num, den);
    }
}
