public class IncandescentLamp extends Bulb{
    int operatingTime;
    final static double INCANDESCENT_LAMP_COUNT = 12.5;
    IncandescentLamp(String producer, Integer powerLamp, Integer time)throws ArithmeticException {
        super(producer, powerLamp);
        if (time < 0){
            throw new ArithmeticException("Operating time of lamp can not be < 0");
        }
        operatingTime = time;
    }
    @Override
    Integer countCost() {
        return Math.toIntExact(Math.round(power * INCANDESCENT_LAMP_COUNT * operatingTime));
    }
}
