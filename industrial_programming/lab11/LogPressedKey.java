import javax.swing.*;
import java.awt.event.KeyEvent;

public class LogPressedKey implements KeyObserver {
    JTextArea textArea = new JTextArea(10,15);
    @Override
    public void update(KeyEvent ev) {
        textArea.append(KeyEvent.getKeyText(ev.getKeyCode()) + " ");
    }
}