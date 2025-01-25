public class MyVisitor implements Visitor{
    int sum = 0;
    int count = 0;
    @Override
    public void visit(MyInteger el) {
        sum += el.num;
        count += 1;
    }
    public double getAverage(){
        return  (double) sum / count;
    }
}
