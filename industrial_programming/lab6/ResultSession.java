import javax.swing.*;
import java.awt.*;

public class ResultSession extends JFrame {

    public ResultSession(String text){
        JFrame frame = new ResultSession("Result of session", text);
        frame.setSize(600,600);
        frame.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    ResultSession(String header, String text){
        super(header);  // constructor JFrame class
        setLayout(new FlowLayout()); // location of elements
        JTextArea elemArea = new JTextArea(30,50);
        elemArea.setLineWrap(true);
        elemArea.setText(text);
        JScrollPane scroll = new JScrollPane(elemArea);
        scroll.setVerticalScrollBarPolicy(JScrollPane. VERTICAL_SCROLLBAR_ALWAYS);
        elemArea.setEditable(false);
        add(scroll);
    }
}
