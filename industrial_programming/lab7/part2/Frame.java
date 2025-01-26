import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Frame extends JFrame {
    JLabel questionLab;
    JButton yesBut, noBut;
    public static void main(String[] args){
        JFrame frame = new Frame("Cursor");
        frame.setSize(600, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    Frame(String header){
        super(header);
        setLayout(null);
        questionLab = new JLabel("Нравится вам жить в общежитии?");
        questionLab.setBounds(190, 80, 400, 30);
        add(questionLab);
        yesBut  = new JButton("Да");
        noBut  = new JButton("Нет");
        yesBut.setBounds(150, 150, 100, 30);
        noBut.setBounds(320, 150, 100, 30);
        add(noBut);
        add(yesBut);
        noBut.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent click) {
                System.out.println("Зато весело, дёшево и незабываемо!");
            }
        });
        yesBut.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent enter) {
                int maxX = getContentPane().getWidth() - yesBut.getWidth();
                int maxY = getContentPane().getHeight() - yesBut.getHeight();
                int newX = (int) (Math.random() * maxX);
                int newY = (int) (Math.random() * maxY);
                yesBut.setLocation(newX, newY);
                }
        });
    }
}