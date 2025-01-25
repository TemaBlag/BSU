public class MyInteger implements Element {
    Integer num;
    MyInteger(Integer number){
        super();
        num = number;
    };

    MyInteger(String number){
        super();
        num = Integer.parseInt(number);
    };

    @Override
    public String toString() {
        return String.valueOf(num);
    }

    @Override
    public boolean equals(Object obj){
        MyInteger myNum = (MyInteger) obj;
        return num.equals(myNum.num);
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
