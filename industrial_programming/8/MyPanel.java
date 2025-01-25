import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

public class MyPanel extends JPanel {
    Color color = null; // color mouse
    Graphics graphics;
    private int curX, curY, newX, newY; // draw lines
    BufferedImage image;
    MyPanel() {
        image = new BufferedImage(1500, 750, BufferedImage.TYPE_INT_RGB);
        graphics = image.createGraphics();
        graphics.fillRect(0, 0, 2000, 1500);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                newX = e.getX();
                newY = e.getY();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                curX = e.getX();
                curY = e.getY();
                graphics.drawLine(newX,newY,curX,curY);
                graphics.setColor(color);
                repaint();
                newX = curX;
                newY = curY;
            }
        });
    }

    @Override
    public void paintComponent(Graphics graphics){
        graphics.drawImage(image,0,0,this);
    }

    public void clear() {
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, getWidth(), getHeight());
        repaint();
    }
}
