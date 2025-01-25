import java.io.IOException;

public class Main {
    public static void main(String[] args){
        try {
            Liner linTest1 = new Liner(1.0, 1.0, 10);
            Liner linTest2 = new Liner(5.0, 2.0, 8);
            Liner linTest3 = new Liner(-6.0, 5.0, 12);
            Exponential exTest1 = new Exponential(3, 2, 10);
            Exponential exTest2 = new Exponential(5, 3, 8);
            System.out.println("Exponential(5, 3, 8)");
            System.out.println(exTest2);
            System.out.println(exTest2.sumProgression());
            String path = "/JavaTasks/lab5/output.txt";
            linTest1.writeToFile(path);
            exTest1.writeToFile(path);
        } catch (ArithmeticException ar){
            System.out.println("Count elements of series can not be < 1");
            System.out.println(ar.getMessage());
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}