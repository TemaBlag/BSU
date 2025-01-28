public class LedLamp extends Bulb{
    Integer countLed;
    final static double LED_LAMP_COUNT = 2.5;
    LedLamp(String producer, Integer powerLamp, Integer ledCount)throws ArithmeticException {
        super(producer, powerLamp);
        if (ledCount < 0){
            throw new ArithmeticException("Count of LEDs in the lamp can not be < 0");
        }
        countLed = ledCount;
    }

    @Override
    Integer countCost() {
        return Math.toIntExact(Math.round(power * countLed / LED_LAMP_COUNT));
    }
}
