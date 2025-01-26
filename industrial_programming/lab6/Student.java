import java.util.Map;
public class Student {
    String surname;
    Integer numOfRecordBook;
    Map<String, Integer> info;
    Student(String nameOfStudent, Integer num, Map<String,Integer> information){
        surname = nameOfStudent;
        numOfRecordBook = num;
        info = information;
    }
    boolean isGood(){
        for ( var value : info.entrySet()){
            if (value.getValue() < 9) return false;
        }
        return true;
    }
    String getSurname(){
        return surname;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (Map.Entry entry: info.entrySet()) {
            string.append("Number of record book: ").append(numOfRecordBook).append("; Surname: ").
                    append(surname).append("; subject: ").append(entry.getKey()).
                    append("; mark: ").append(entry.getValue()).append("\n");
        }
        return string.toString();
    }
}
