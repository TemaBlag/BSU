class Exponential extends Series {
    Exponential(double zerEl, double inc, int countElem){
        super(zerEl, inc, countElem);
    }

    @Override
    public double jElement(int index) {
        return zeroElem * Math.pow(increment, index - 1);
    }

    @Override
    public double sumProgression() {
        if (increment == 1){
            return count * zeroElem;
        }
        return (zeroElem * (1 - Math.pow(increment, count))) / (1 - increment);
    }
}