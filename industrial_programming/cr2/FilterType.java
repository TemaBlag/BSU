import java.util.ArrayList;

public class FilterType{
    private FilterStrategy strategy;

    public void setStrategy(FilterStrategy strategy) {
        this.strategy = strategy;
    }

    public Integer cardinality(ArrayList<MyInteger> nums) {
        return strategy.cardinality(nums);
    }
}
