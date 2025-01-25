import javax.swing.*;
import java.util.ArrayList;
import java.util.EmptyStackException;

public class Controller {
    JTextArea textArea;
    JList<MyInteger> textList;
    Deck<MyInteger> deck = new Deck<>(new ArrayList<>());
    JTextArea averageTextArea;
    ArrayList<JButton> buttons;

    View view;
    Controller(){
         super();
    }

    public void setParams(View view){
        textArea = view.getJTextAreas().get(0);
        textList = view.getJList();
        averageTextArea = view.getJTextAreas().get(1);
        buttons = view.getJButtons();
        this.view = view;
    }

    public void execute(){
        clearButFunc(buttons.get(0));
        equalsButFunc(buttons.get(1));
        pushFrontButFunc(buttons.get(2));
        pushBackButFunc(buttons.get(3));
        frontButFunc(buttons.get(4));
        backButFunc(buttons.get(5));
        popBackButFunc(buttons.get(6));
        popFrontButFunc(buttons.get(7));
        pushFrontAllButFunc(buttons.get(8));
        pushBackAllButFunc(buttons.get(9));
        clearFrameButFunc(buttons.get(10));
        averageButFunc(buttons.get(11));
        sizeButFunc(buttons.get(12));
        isEmptyButFunc(buttons.get(13));
    }


    public void clearButFunc( JButton clearBut) {
        clearBut.addActionListener(e -> {
            deck.clear();
            textArea.setText("Deck cleared");
            textList.setModel(deck.listParameter());
        });
    }

    public void equalsButFunc( JButton equalsBut) {
        equalsBut.addActionListener(e -> {
            try {
                textArea.setText(String.valueOf(deck.equals(new Deck<>(parser(textArea.getText())))));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid input data" + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public void pushFrontButFunc( JButton pushFrontBut) {
        pushFrontBut.addActionListener(e -> {
            try {
                deck.pushFront(parserElement(textArea.getText()));
                textArea.setText("");
                textList.setModel(deck.listParameter());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid input data" + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public void pushBackButFunc( JButton pushBackBut) {
        pushBackBut.addActionListener(e -> {
            try {
                deck.pushBack(parserElement(textArea.getText()));
                textArea.setText("");
                view.printDeck(deck);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid input data" + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public void frontButFunc( JButton frontBut) {
        frontBut.addActionListener(e -> {
            try {
                textArea.setText(String.valueOf(deck.front()));
                view.printDeck(deck);
            } catch (EmptyStackException ex) {
                JOptionPane.showMessageDialog(null, "Deck is empty",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public void backButFunc( JButton backBut) {
        backBut.addActionListener(e -> {
            try {
                textArea.setText(String.valueOf(deck.back()));
                view.printDeck(deck);
            } catch (EmptyStackException ex) {
                JOptionPane.showMessageDialog(null, "Deck is empty",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public void popBackButFunc( JButton popBackBut) {
        popBackBut.addActionListener(e -> {
            try {
                textArea.setText(String.valueOf(deck.popBack()));
                view.printDeck(deck);
            } catch (EmptyStackException ex) {
                JOptionPane.showMessageDialog(null, "Deck is empty",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public void popFrontButFunc( JButton popFrontBut) {
        popFrontBut.addActionListener(e -> {
            try {
                textArea.setText(String.valueOf(deck.popFront()));
                view.printDeck(deck);
            } catch (EmptyStackException ex) {
                JOptionPane.showMessageDialog(null, "Deck is empty",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public void pushFrontAllButFunc( JButton pushFrontAllBut) {
        pushFrontAllBut.addActionListener(e -> {
            try {
                deck.pushFrontAll(parser(textArea.getText()));
                view.printDeck(deck);
                textArea.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid input data" + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public void pushBackAllButFunc( JButton pushBackAllBut) {
        pushBackAllBut.addActionListener(e -> {
            try {
                deck.pushBackAll(parser(textArea.getText()));
                view.printDeck(deck);
                textArea.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid input data" + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public void clearFrameButFunc( JButton clearFrameBut) {
        clearFrameBut.addActionListener(e -> {
            textArea.setText("");
        });
    }

    public void averageButFunc( JButton averageBut) {
        averageBut.addActionListener(e -> {
            averageTextArea.setText(String.valueOf(getAverage()));
        });
    }

    public void sizeButFunc( JButton sizeBut) {
        sizeBut.addActionListener(e -> textArea.setText(String.valueOf(deck.size())));
    }

    public void isEmptyButFunc( JButton isEmptyBut) {
        isEmptyBut.addActionListener(e -> textArea.setText(String.valueOf(deck.isEmpty())));
    }
    public double getAverage(){
        MyVisitor visitor = new MyVisitor();
        Deck<MyInteger>.Iterator it = deck.iterator();
        while(it.hasNext()){
            it.current().accept(visitor);
            it.next();
        }
        return visitor.getAverage();
    }

    public ArrayList<MyInteger> parser(String str) throws NumberFormatException {
        ArrayList<MyInteger> result = new ArrayList<>(new ArrayList<>());
        for(String el: str.split(" ")){
            result.add(new MyInteger(Integer.parseInt(el)));
        }
        return result;
    }

    public MyInteger parserElement(String str) throws NumberFormatException {
        return new MyInteger(Integer.parseInt(str));
    }
}
