import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Controller {
    private Queue<MyInteger> queue;
    private JTextArea queueArea;
    private JTextArea dataArea;
    private JList<MyInteger> list;
    private JTextField secondQueueDataField;
    ArrayList<JButton> buttons;

    public Controller() {
        queue = Queue.create();
        queueArea = v.getJTextAreas().get(0);
        this.list = v.getJList();
        dataArea = v.getJTextAreas().get(1);
        buttons = v.getJButtons();
        this.secondQueueDataField = v.getTextField();
    }

    public void execute() {
        frontButFunc(buttons.get(0));
        backButFunc(buttons.get(1));
        clearButFunc(buttons.get(2));
        pushButFunc(buttons.get(3));
        popButFunc(buttons.get(4));
        pushAllButFunc(buttons.get(5));
        equalsButFunc(buttons.get(6));
        averageButFunc(buttons.get(7));
        dataAreaFunc();
        secondQueueDataFieldFunc();
    }

    public void dataAreaFunc() {
        dataArea.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                dataArea.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
            }
        });
    }

    public void equalsButFunc(JButton equalsButton) {
        equalsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleEquals();
            }
        });
    }

    public void secondQueueDataFieldFunc() {
        secondQueueDataField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                secondQueueDataField.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
            }
        });
    }

    public void frontButFunc(JButton front) {
        front.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleFront();
            }
        });
    }

    public void backButFunc(JButton back) {
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleBack();
            }
        });
    }

    public void clearButFunc(JButton clear) {
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleClear();
            }
        });
    }

    public void pushButFunc(JButton push) {
        push.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handlePush();
            }
        });
    }

    public void popButFunc(JButton pop) {
        pop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handlePop();
            }
        });
    }

    public void pushAllButFunc(JButton pushAll) {
        pushAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handlePushAll();
            }
        });
    }

    public void averageButFunc(JButton averageBut) {
        averageBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getAverage();
            }
        });
    }

    public void getAverage() {
        MyVisitor visitor = new MyVisitor();
        Iterator<MyInteger> it = queue.createIterator();
        while (it.hasNext()) {
            it.currentItem().accept(visitor);
            it.next();
        }
        queueArea.setText("Average value is " + visitor.getAverage());
    }

    private void handleFront() {
        try {
            queueArea.setText("Front: " + queue.front().toString());
        } catch (IndexOutOfBoundsException exception) {
            showErrorDialog(exception.getMessage());
        }
    }

    private void handleBack() {
        try {
            queueArea.setText("Back: " + queue.back().toString());
        } catch (IndexOutOfBoundsException exception) {
            showErrorDialog(exception.getMessage());
        }
    }

    private void handleClear() {
        queue.clear();
        update();
        secondQueueDataField.setText("Enter data for second queue");
    }

    private void handlePush() {
        try {
            MyInteger element = new MyInteger(Integer.parseInt(dataArea.getText().trim()));
            queue.push(element);
            update();
            dataArea.setText("Write data here");
        } catch (NumberFormatException exception) {
            showErrorDialog("Write one value that you want to add to the end");
        }
    }

    private void handlePushAll() {
        try {
            StringTokenizer stringTokenizer = new StringTokenizer(dataArea.getText());
            ArrayList<MyInteger> elements = new ArrayList<>();
            while (stringTokenizer.hasMoreTokens()) {
                MyInteger element = new MyInteger(Integer.parseInt(stringTokenizer.nextToken()));
                elements.add(element);
            }
            queue.pushAll(elements);
            update();
            dataArea.setText("Write data here");
        } catch (NumberFormatException exception) {
            showErrorDialog("Invalid input format");
        }
    }

    private void handlePop() {
        try {
            queue.pop();
            update();
        } catch (IndexOutOfBoundsException exception) {
            showErrorDialog(exception.getMessage());
        }
    }

    private void handleEquals() {
        Queue<MyInteger> anotherQueue = createQueueFromInput();
        boolean result = queue.equals(anotherQueue);
        queueArea.setText("equal: " + result);
    }

    private Queue<MyInteger> createQueueFromInput() {
        Queue<MyInteger> newQueue = Queue.create();

        try {
            StringTokenizer stringTokenizer = new StringTokenizer(secondQueueDataField.getText());
            while (stringTokenizer.hasMoreTokens()) {
                MyInteger element = new MyInteger(Integer.parseInt(stringTokenizer.nextToken()));
                newQueue.push(element);
            }
        } catch (NumberFormatException e) {
            showErrorDialog("Invalid input format for the second queue");
        }

        return newQueue;
    }

    private void update() {
        queueArea.setText(queue.toString());
        printQueue(queue);
    }

    private void showErrorDialog(String errorMessage) {
        JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }

}
