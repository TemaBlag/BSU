import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    Main(String str) {
        super(str);
        setSize(600,550);
        setLocationRelativeTo(null); // set the position window relatively centre
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(1,2));

        Keyboard keyPressedEvent = new Keyboard();
        KeyPressed keyObserver = new KeyPressed();
        LogPressedKey logPressedKeys = new LogPressedKey();

        JTextArea keyObserverTextArea = keyObserver.textArea;
        JTextArea logPressedKeysTextArea = logPressedKeys.textArea;
        logPressedKeysTextArea.setLineWrap(true);

        JScrollPane scroller = new JScrollPane(logPressedKeysTextArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        keyPressedEvent.attach(keyObserver);
        keyPressedEvent.attach(logPressedKeys);

        JPanel panel = new JPanel();
        panel.add(keyObserverTextArea);
        panel.add(scroller);
        setContentPane(panel);

        keyObserverTextArea.addKeyListener(keyPressedEvent.adapter);
        logPressedKeysTextArea.addKeyListener(keyPressedEvent.adapter);
        keyObserverTextArea.setEditable(false);
        logPressedKeysTextArea.setEditable(false);
    }

    public static void main(String[] args) {
        Main window = new Main("Keyboard");
        window.setVisible(true);
    }
}