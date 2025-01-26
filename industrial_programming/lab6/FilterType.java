import java.util.ArrayList;

public class FilterType{
    private FilterStrategy strategy;

    public void setStrategy(FilterStrategy strategy) {
        this.strategy = strategy;
    }

    public ArrayList<Student> filterStudents(ArrayList<Student> Students) {
        return strategy.filter(Students);
    }
}