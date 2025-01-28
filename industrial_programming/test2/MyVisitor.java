public class MyVisitor implements Visitor{
    int count = 0;
    @Override
    public void visit(Integer el) {
        count += el;
    }
    public Integer getCount(){
        return count;
    }
}
