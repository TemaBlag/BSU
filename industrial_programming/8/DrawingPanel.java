import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

public class DrawingPanel extends JPanel {
    Color color = Color.RED;
    Graphics graphics;
    private int currX, currY, oldX, oldY;
    BufferedImage image;
    DrawingPanel(){
        image = new BufferedImage(1440,720,BufferedImage.TYPE_INT_RGB);
        graphics = image.createGraphics();
        graphics.fillRect(0, 0, 1440, 720);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                oldX = e.getX();
                oldY = e.getY();
                graphics.setColor(color);
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                currX = e.getX();
                currY = e.getY();
                graphics.drawLine(oldX,oldY,currX,currY);
                graphics.setColor(color);
                repaint();
                oldX = currX;
                oldY = currY;
            }
        });
    }
    @Override
    public void paintComponent(Graphics graphics){
        graphics.drawImage(image,0,0,this);
    }
    public void setColor(Color color){
        this.color = color;
    }
    public void clear() {
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, getWidth(), getHeight());
        graphics.setColor(color);
        repaint();
    }
}
