public abstract class Bulb {
    String manufacture;
    Integer power;
    Bulb(String producer, Integer powerBulb) throws ArithmeticException{
        manufacture = producer;
        if (powerBulb < 0){
          throw new ArithmeticException("Power of bulb can not be < 0");
        }
        power = powerBulb;

    }
    abstract Integer countCost();
}
