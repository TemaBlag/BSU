import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class KeyPressed implements KeyObserver {
    JTextArea textArea = new JTextArea(3, 3);
    @Override
    public void update(KeyEvent ev) {
        textArea.setFont(new Font("Times new roman",Font.BOLD + Font.ITALIC, 45));
        textArea.setText(KeyEvent.getKeyText(ev.getKeyCode()));
    }
}

