import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String fileName = "/JavaTasks/lab3/input.txt";
        Path path = Paths.get(fileName);
        double[][] matrix = readData(path);
        System.out.println("Inverse matrix:");
        for (double[] doubles : inverseMatrix(matrix)) {
            for (double aDouble : doubles){
                System.out.printf("%.2f", aDouble);
                System.out.print("  ");
            }
            System.out.println();
        }
    }

    /**
     *  Function which return inverseMatrix of the matrix
     */
    public static double[][] inverseMatrix(double[][] matrix){
        double detMatrix = determinant(matrix);
        double[][] invMatrix = new double[matrix.length][matrix.length];
        if (detMatrix != 0) { // check for inverse matrix( if det = 0, matrix does not have inverse matrix)
            int im = 0;
            int jm = 0;
            double[][] minor = new double[matrix.length - 1][matrix.length - 1];
            for (int i = 0; i < matrix.length; i++) {  // full minor
                for (int j = 0; j < matrix.length; j++) {
                    for (int s = 0; s < matrix.length; s++) {
                        if (s == i){
                            continue;
                        }
                        for (int k = 0; k < matrix.length; k++) {
                            if (k == j){
                                continue;
                            }
                            if (jm >= matrix.length - 1){
                                ++im;
                                jm = 0;
                            }
                            minor[im][jm] = matrix[s][k];
                            ++jm;
                        }
                    }
                    double detMinor =  determinant(minor);
                    if ((i + j) % 2 == 1){
                        detMinor *= -1;
                    }
                    invMatrix[i][j] = detMinor / detMatrix;
                    im = 0;
                    jm = 0;
                }
            }
            for (int i = 0; i < matrix.length; i++){  // transposes the matrix
                for (int j = i + 1; j  < matrix.length; j++){
                    double buf = invMatrix[i][j];
                    invMatrix[i][j] = invMatrix[j][i];
                    invMatrix[j][i] = buf;
                }
            }
        } else {
            System.out.println("Matrix does not have inverse matrix");
            System.exit(0);
        }
        return invMatrix;
    }

    /**
     *  Function which count determinant of the matrix[][]
     */
    public static double determinant(double[][] matrix){
        double[][] arr = new double[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++){
            System.arraycopy(matrix[i], 0, arr[i], 0, matrix.length);
        }
        double det = 1;
        double flag = 1;
        double coefficient = 0; // to make zeros
        for (int i = 0; i < arr.length - 1; i++) { // not zero string
            int row = 0, col = 0;
            int sel = row;
            for (int l = row; l < matrix.length; l++)
                if (Math.abs(arr[l][col]) > Math.abs(arr[sel][col]))
                    sel = l;
            if (sel != 0){
                flag *= -1;
            }
            double[] temp = arr[sel];
            arr[sel] = arr[row];
            arr[row] = temp;
            for (int k = i + 1; k < arr.length; k++) {
                try {
                    if (arr[i][i] == 0){
                        throw new ArithmeticException(); // division by zero
                    }
                    coefficient = arr[k][i] / arr[i][i];
                } catch (ArithmeticException ex){
                    continue;
                }
                for (int j = 0; j < arr.length; j++) {
                    arr[k][j] -= (arr[i][j] * coefficient); // make triangle matrix
                }
            }
        }
        for (int i = 0; i < arr.length; i++){
            det *= arr[i][i]; // determinant of triangle matrix - multiply diagonal elements
        }
        return det * flag;
    }

    /**
     *  Function which read data from file and return int matrix[][]
     */
    public static double[][] readData (Path path) {
        double[][] matrix = new double[0][]; // create matrix [][]
        try (Scanner scan = new Scanner(path)) {  // read data from file from scanner using path
            double[] strMatrixDouble;  // intermediate int array
            scan.useDelimiter(System.getProperty("line.separator"));
            if (scan.hasNext()) { // check for not empty line
                String str = scan.nextLine();  // scan line
                String[] elements = str.split(" ");  // split elements for matrix
                strMatrixDouble = new double[elements.length];
                for (int i = 0; i < elements.length; i++) {
                    try {
                        strMatrixDouble[i] = Double.parseDouble(elements[i]);  // try to bring string to double
                    } catch (NumberFormatException ex){  // handle exception
                        System.out.println(ex.getMessage());
                        System.exit(1);
                    }
                }
                matrix = new double[strMatrixDouble.length][strMatrixDouble.length]; // update result matrix
                System.arraycopy(strMatrixDouble, 0, matrix[0], 0, strMatrixDouble.length);
                int k = 1;
                while (scan.hasNext() && k < strMatrixDouble.length) { // read data from file
                    str = scan.nextLine();
                    elements = str.split(" ");
                    if (elements.length > matrix[0].length){ // check for correct data
                        System.out.println("Invalid Data: more data is inputted");
                        System.exit(2);
                    }
                    if (elements.length < matrix[0].length){ // check for correct data
                        System.out.println("Invalid Data: less data is inputted");
                        System.exit(2);
                    }
                    for (int i = 0; i < elements.length; i++) {
                        try {
                            strMatrixDouble[i] = Double.parseDouble(elements[i]);
                        } catch (NumberFormatException ex){
                            System.out.println(ex.getMessage());
                            System.exit(1);
                        }
                    }
                    System.arraycopy(strMatrixDouble, 0, matrix[k], 0, strMatrixDouble.length);
                    ++k;
                }
                if (k != strMatrixDouble.length || scan.hasNextLine()){ // check for correct data
                    System.out.println("Invalid Data: more data is inputted");
                    System.exit(2);
                }
                System.out.println("Input matrix:");
                for (double[] doubles : matrix) {
                    for (double aDouble : doubles){
                        System.out.printf("%.2f", aDouble);
                        System.out.print("  ");
                    }
                    System.out.println();
                }
                System.out.println();
            } else {
                System.out.println("Empty file");
                System.exit(3);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            System.exit(4);
        }
        return matrix;
    }
}