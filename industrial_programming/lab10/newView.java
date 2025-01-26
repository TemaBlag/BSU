import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Queue;

public class View extends JFrame {
    private JTextArea queueArea;
    private JTextArea dataArea;
    private JList<MyInteger> list;
    private JTextField secondQueueDataField;
    public ArrayList<JButton> buttons;

    public View() {
        super("My Queue");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        setContentPane(mainPanel);

        JPanel textPanel = new JPanel(new GridLayout(2, 1));
        queueArea = new JTextArea("Your queue will be here");
        queueArea.setEditable(false);
        queueArea.setBorder(new LineBorder(Color.BLACK));
        dataArea = new JTextArea("Write data here");
        dataArea.setBorder(new LineBorder(Color.BLACK));

        textPanel.add(queueArea);
        textPanel.add(dataArea);

        mainPanel.add(textPanel);

        list = new JList<>();
        JScrollPane listScrollPane = new JScrollPane(list);
        mainPanel.add(listScrollPane);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        mainPanel.add(buttonPanel);

        JButton front = new JButton("front");
        JButton back = new JButton("back");
        JButton clear = new JButton("clear");
        JButton push = new JButton("push");
        JButton pop = new JButton("pop");
        JButton pushAll = new JButton("pushAll");
        JButton equalsButton = new JButton("Equals");
        JButton averageButton = new JButton("Average");

        buttonPanel.add(front);
        buttonPanel.add(back);
        buttonPanel.add(clear);
        buttonPanel.add(push);
        buttonPanel.add(pop);
        buttonPanel.add(pushAll);
        buttonPanel.add(equalsButton);
        buttonPanel.add(averageButton);

        secondQueueDataField = new JTextField("Enter data for second queue");
        secondQueueDataField.setBorder(new LineBorder(Color.BLACK));

        buttonPanel.add(secondQueueDataField);

        setSize(800, 500);
        setLocationRelativeTo(null);
        setResizable(false);

        buttons = new ArrayList<>();
        buttons.add(front);
        buttons.add(back);
        buttons.add(clear);
        buttons.add(push);
        buttons.add(pop);
        buttons.add(pushAll);
        buttons.add(equalsButton);
        buttons.add(averageButton);
    }
    public ArrayList<JTextArea> getJTextAreas(){
        ArrayList<JTextArea> result = new ArrayList<>();
        result.add(queueArea);
        result.add(dataArea);
        return result;
    }

    public JList<MyInteger> getJList(){
        return list;
    }

    public ArrayList<JButton> getJButtons(){
        return buttons;
    }

    public JTextField getTextField(){
        return secondQueueDataField;
    }
    public void printQueue(Queue<MyInteger> queue){ list.setModel(queue.getListModel());}
}
