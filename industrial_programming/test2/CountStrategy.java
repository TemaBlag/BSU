import java.util.ArrayList;

public class CountStrategy implements FilterStrategy {
    @Override
    public Integer cardinality(ArrayList<MyInteger> nums) {
        MyVisitor visitor = new MyVisitor();
        for (MyInteger number: nums){
            number.accept(visitor);
        }
        return  visitor.getCount();
    }
}