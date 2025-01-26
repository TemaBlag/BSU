import java.util.ArrayList;
import java.util.stream.Collectors;

public class StreamFilterStrategy implements FilterStrategy {
    @Override
    public ArrayList<Student> filter(ArrayList<Student> students) {
        return students.stream().filter(Student::isGood).sorted(new SurnameComparator()).collect(Collectors.
                toCollection(ArrayList::new));
    }
}
