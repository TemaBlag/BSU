class Liner extends Series {
    Liner(double zerEl, double inc, int countElem){
        super(zerEl, inc, countElem);
    }

    @Override
    public double jElement(int index) {
        return zeroElem + increment * (index - 1);
    }

    @Override
    public double sumProgression() {
        return (2 * zeroElem + increment * (count - 1)) * count / 2;
    }
}