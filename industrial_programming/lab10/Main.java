
public class Main{

    public static void main(String[] args) {
        View view = new View("Painter");
        view.setSize(600, 450);
        view.setVisible(true);
        view.setDefaultCloseOperation(View.EXIT_ON_CLOSE);
        Controller controller = new Controller();
        controller.setParams(view);
        controller.execute();
    }

}