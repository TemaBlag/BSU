import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;


public class Frame2 extends JPanel {
    private final int COLUMS = 5;
    private final int ROWS = 5;
    Color oldColor;
    Color currColor;
    String oldText;
    Frame2() {
        setSize(600, 400);
        setVisible(true);
        setLayout(new GridLayout(ROWS, COLUMS));

        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                JButton currButton = (JButton) e.getSource();
                oldColor = currButton.getBackground();
                Random random = new Random();
                int red = random.nextInt(256);
                int green = random.nextInt(256);
                int blue = random.nextInt(256);
                Color randomColor = new Color(red, green, blue);
                currColor = randomColor;
                currButton.setBackground(randomColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                    JButton currButton = (JButton) e.getSource();
                    currButton.setBackground(oldColor);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    JButton currButton = (JButton) e.getSource();
                    oldText = currButton.getText();
                    JButton sourceButton = (JButton) e.getSource();
                    if (sourceButton.contains(e.getPoint())) {
                        currButton.setText("Clicked!");
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                JButton currButton = (JButton) e.getSource();
                currButton.setText(oldText);
            }
        };

        for(int i = 1; i <= COLUMS * ROWS; i++){
            JButton but = new JButton(String.format("%d", i));
            but.addMouseListener(mouseAdapter);
            add(but);
        }

    }
}