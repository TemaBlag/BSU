//M-Model passive
//V-View
//C-Controller
public class Main {
    public static void main(String[] args) {
        Model model = new Model();
        View view = new View("Set");
        view.setSize(600, 400);
        view.setVisible(true);
        view.setDefaultCloseOperation(View.EXIT_ON_CLOSE);
        Controller controller = new Controller();
        controller.setParams(view, model);
        controller.execute();
    }
}