import java.util.Comparator;

public class SurnameComparator implements Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) {
        String surname1 = o1.getSurname();
        String surname2 = o2.getSurname();
        return surname1.compareTo(surname2);
    }
}
