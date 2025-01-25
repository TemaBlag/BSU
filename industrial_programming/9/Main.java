import javax.swing.*;

public class Main extends JFrame {
    Main(String name) {
        super(name);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600,400);
        JTabbedPane tabsLeft = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
        Frame1 window1 = new Frame1();
        Frame2 window2 = new Frame2();
        Frame3 window3 = new Frame3();
        tabsLeft.addTab("Tab 1", window1);
        tabsLeft.addTab("Tab 2", window2);
        tabsLeft.addTab("Tab 3", window3);
        add(tabsLeft);
    }

    public static void main(String[] args) {
        Main window = new Main("App");
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}