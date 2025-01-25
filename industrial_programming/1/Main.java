import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println(Arrays.toString("5 3".split(" ")));
        try {
           if (args.length != 2) {  // count argument from parameters call function
                throw new IndexOutOfBoundsException();
            }
            double res = sumOfRow(Double.parseDouble(args[0]), Double.parseDouble(args[1])); // get answer for the task
            System.out.println("Sum: " + res); // print result
        } catch (IndexOutOfBoundsException e) {   // exception handling
            System.out.println(e.getMessage());
        }
    }

    public static double sumOfRow(double x, double difference){
        if (difference < 0){  // exception handling of negative difference
            System.out.println("Difference can not be negative");
            return -1;
        }
        int k = 1;  // start index
        double startValue = (Math.pow(4.0 / 3.0, 6) * x * x)  / 6;  // start value in case k = 1
        double nextValue = -(startValue * Math.pow(4.0 / 3.0, 4) / 20);  // next value in case k = 2
        double sum = startValue; // start result of the function
        System.out.println("Sum = " + sum + "; value = " + startValue + "; k = " + k);  // print start result
        while (Math.abs(Math.abs(startValue) - Math.abs(nextValue)) > difference){
            sum += nextValue;
            ++k;
            startValue =  nextValue;
            nextValue *= -(Math.pow(4.0 / 3.0, 4) / (2 * k * (2 * k + 1)));  // count new value of row
            System.out.println("Sum = " + sum + "; value = " + startValue + "; k = " + k);  // print current result
        }
        return sum;
    }

    public static void checkFunctionality(){ // check functionality sumOfRow due to 5 tests
        //Test1
        double x = 5;
        double difference = 0.000001;
        double expectedResult = 20.254039911366647;
        double currentResult = sumOfRow(x, difference);
        if (expectedResult == currentResult){
            System.out.println("Test1 - Ok");
        } else {
            System.out.println("Test1 - Fail");
            System.out.println("Expected Result: " + expectedResult);
            System.out.println("Current Result: " + currentResult);
        }
        //Test2
        x = 0;
        difference = 0.000001;
        expectedResult = 0;
        currentResult = sumOfRow(x, difference);
        if (expectedResult == currentResult){
            System.out.println("Test2 - Ok");
        } else {
            System.out.println("Test2 - Fail");
            System.out.println("Expected Result: " + expectedResult);
            System.out.println("Current Result: " + currentResult);
        }
        //Test3
        x = 1.5;
        difference = 0.00001;
        expectedResult = 1.8228634923796072;
        currentResult = sumOfRow(x, difference);
        if (expectedResult == currentResult){
            System.out.println("Test3 - Ok");
        } else {
            System.out.println("Test3 - Fail");
            System.out.println("Expected Result: " + expectedResult);
            System.out.println("Current Result: " + currentResult);
        }
        //Test4
        x = -8;
        difference = 0.000001;
        expectedResult = 51.85034217309863;
        currentResult = sumOfRow(x, difference);
        if (expectedResult == currentResult){
            System.out.println("Test4 - Ok");
        } else {
            System.out.println("Test4 - Fail");
            System.out.println("Expected Result: " + expectedResult);
            System.out.println("Current Result: " + currentResult);
        }
        //Test5
        x = 1;
        difference = -0.000001;
        expectedResult = -1;
        currentResult = sumOfRow(x, difference);
        if (expectedResult == currentResult){
            System.out.println("Test5 - Ok");
        } else {
            System.out.println("Test5 - Fail");
            System.out.println("Expected Result: " + expectedResult);
            System.out.println("Current Result: " + currentResult);
        }
    }

}
