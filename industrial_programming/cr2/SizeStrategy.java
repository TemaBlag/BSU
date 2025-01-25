import java.util.ArrayList;

public class SizeStrategy implements FilterStrategy {
    @Override
    public Integer cardinality(ArrayList<MyInteger> nums) {
        return nums.size();
    }
}
