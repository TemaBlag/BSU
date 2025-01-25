import java.util.ArrayList;

public class BasicFilter implements FilterStrategy {
    @Override
    public ArrayList<Student> filter(ArrayList<Student> Students) {
        ArrayList<Student> result = new ArrayList<>();
        for (Student stud : Students){
            if(stud.isGood()){
                result.add(stud);
            }
        }
        return result;
    }
}
