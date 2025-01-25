import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EmptyStackException;

//passive model
public class Model {
    int[] binaryNumbers;
    int min = 0;
    ArrayList<MyInteger> decimalNumbers = new ArrayList<>();

    Model(){
        super();
    };

    class Iterator {
        private int point;

        Iterator() {
            point = 0;
        }

        public void next() throws EmptyStackException {
            if (!hasNext()) {
                throw new EmptyStackException();
            }
            point += 1;
        }

        public boolean hasNext() {
            return point < decimalNumbers.size();
        }

        public MyInteger current() {
            return decimalNumbers.get(point);
        }
    }

    public Iterator iterator() {
        return new Iterator();
    }

    public void createBinaryNumbers(){
        MyInteger maxNumber = new MyInteger(min);
        for (MyInteger number : decimalNumbers) {
            if (number.num > maxNumber.num) {
                maxNumber = number;
            }
        }
        int sizeNumbers = maxNumber.num + 1 - min;
        binaryNumbers = new int[sizeNumbers];
        for (MyInteger value: decimalNumbers) {
            binaryNumbers[value.num - min] = 1;
        }
    }

    public void upgradeDecimalNumbers(MyInteger number){
        if (number.num < min){
            int buf = min;
            min = number.num;
            number.num = buf;
        }
        if (!decimalNumbers.contains(number)){
            decimalNumbers.add(number);
        }
    }

    public String getBinNums(){
        createBinaryNumbers();
        StringBuilder result = new StringBuilder();
        for(int num : binaryNumbers){
            result.append(num).append(" ");
        }
        return result.toString();
    }

    public String getDecNums(){
        StringBuilder result = new StringBuilder();
        Iterator it = iterator();
        while (it.hasNext()) {
            result.append(it.current()).append(" ");
            it.next();
        }
        return result.toString();
    }

    public ArrayList<MyInteger> getdecimalNumbers(){
        return decimalNumbers;
    }

    public void saveAsFile() throws IOException{
        JFileChooser chooseFile = new JFileChooser();
        chooseFile.setApproveButtonText("Choose");
        chooseFile.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooseFile.setDialogTitle("Choose file");
        chooseFile.setCurrentDirectory(new File("D:\\"));
        int result = chooseFile.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {

            File file = chooseFile.getSelectedFile();
            int k = 0;
            File newFile = new File(file + "\\set(" + k + ").txt");
            while(newFile.isFile()){
                k += 1;
                newFile = new File(file + "\\set(" + k + ").txt");
            }
            FileWriter fileWriter = new FileWriter(newFile);
            for (int number : binaryNumbers) fileWriter.write(String.valueOf(number)+" ");
            fileWriter.write("\n");
            for (MyInteger number : decimalNumbers) fileWriter.write(String.valueOf(number.num)+" ");
            fileWriter.close();
        }
    }
}
