import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Frame extends JFrame {
    JLabel coordinatesLab;
    JButton but;
    public static void main(String[] args){
        JFrame frame = new Frame("Cursor");
        frame.setSize(600, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    Frame(String header){
        super(header);
        setLayout(new BorderLayout());
        coordinatesLab = new JLabel("Coordinates:");
        add(coordinatesLab, BorderLayout.SOUTH);
        but  = new JButton("click");
        but.setBounds(270, 180, 100, 30);
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.add(but);
        add(panel, BorderLayout.CENTER);
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent click) {
                setCoordinatesInLabel(click.getX(), click.getY());
                but.setLocation(click.getX() - 50, click.getY() - 15);
            }
        });
        panel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent move) {
                setCoordinatesInLabel(move.getX(), move.getY());
            }
            @Override
            public void mouseDragged(MouseEvent drag) {
                setCoordinatesInLabel(drag.getX(), drag.getY());
            }
        });
        but.addMouseMotionListener(new MouseAdapter(){
            @Override
            public void mouseMoved(MouseEvent move) {
                setCoordinatesInLabel(move.getX() + but.getX(), move.getY() + but.getY());
            }

            @Override
            public void mouseDragged(MouseEvent drag) {
                mouseMoved(drag);
                if (drag.isControlDown()) {
                    but.setLocation(drag.getX() + but.getX() - 50, drag.getY() + but.getY() - 15);
                }
            }
        });
        but.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e) {
                try {
                    if (e.getKeyChar()==KeyEvent.VK_BACK_SPACE){
                        but.setText(but.getText().substring(0, but.getText().length() - 1));
                    } else {
                        if (Character.isDefined(e.getKeyChar())&& e.getKeyChar()!=KeyEvent.VK_ESCAPE &&
                                e.getKeyChar()!=KeyEvent.VK_ENTER&& e.getKeyChar()!=KeyEvent.VK_SPACE) {
                            but.setText(but.getText() + e.getKeyChar());
                        }
                    }
                }catch (IndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(Frame.this, "There is no text to delete");
                }
            }
        });
    }

    void setCoordinatesInLabel(int x, int y){
        coordinatesLab.setText("Coordinates: x=" + x + ", y=" + y);
    }
    public boolean isDefined(char c){
        return Character.isDefined(c);
    }
}