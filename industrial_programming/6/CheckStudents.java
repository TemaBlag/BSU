import java.util.ArrayList;
import java.util.stream.Collectors;

public class CheckStudents {
    static ArrayList<Student> listGoodStudents (ArrayList<Student> students){
        return students.stream().filter(Student::isGood).sorted(new SurnameComparator()).collect(Collectors.
                toCollection(ArrayList::new));
    }
}
