import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Frame3 extends JPanel {
    Icon icon1 = new ImageIcon("1.png");
    Icon icon2 = new ImageIcon("2.png");
    Icon icon3 = new ImageIcon("3.png");
    Icon icon4 = new ImageIcon("4.png");
    Icon icon5 = new ImageIcon("5.png");
    Frame3() {
        setSize(600, 200);
        setVisible(true);
        setLayout(new FlowLayout());

        ButtonGroup radButGroup = new ButtonGroup();

        for(int i = 0; i < 8; i++){
            JRadioButton radBut = new JRadioButton();
            radButGroup.add(radBut);
            radBut.setIcon(icon2);
            radBut.setPressedIcon(icon1);
            radBut.setDisabledIcon(icon3);
            radBut.setSelectedIcon(icon4);
            radBut.setRolloverIcon(icon5);
            add(radBut);
        }

    }
}