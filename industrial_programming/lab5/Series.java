import java.io.FileWriter;
import java.io.IOException;

public abstract class Series {
    double zeroElem;
    double increment;
    int count;

    Series(double zerEl, double inc, int countElem) throws ArithmeticException {
        zeroElem = zerEl;
        increment = inc;
        if (countElem < 1) {
            throw new ArithmeticException();
        }
        count = countElem;
    }

    public abstract double jElement(int index);

    public double sumProgression() {
        double sum = 0;
        for (int i = 1; i <= count; i++) {
            sum += jElement(i);
        }
        return sum;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= count; i++) {
            result.append((jElement(i))).append("; ");
        }
        return result.toString();
    }

    public String toString(int a) throws IndexOutOfBoundsException{
        if (a < 0 || a > count){
            throw new IndexOutOfBoundsException();
        }
        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= a; i++) {
            result.append((jElement(i))).append("; ");
        }
        return result.toString();
    }

    public void writeToFile(String path) throws IOException {
        FileWriter writer = new FileWriter(path, true);
        writer.append(toString());
        writer.append("\n");
        writer.flush(); // clean the data from writer
        writer.close();
    }
}