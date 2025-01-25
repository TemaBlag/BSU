import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class Controller {
    JTextArea inputTextArea = new JTextArea();
    JTextArea outputTextArea = new JTextArea();
    ArrayList<JButton> buttons;
    ArrayList<JRadioButton> radioButtons;

    ArrayList<MyInteger> decimalNumbers = new ArrayList<>();

    View view;

    Model model;

    Controller() {
        super();
    }
    public void setParams(View ActiveView, Model activeModel) {
        inputTextArea = ActiveView.getJTextAreas().get(0);
        outputTextArea = ActiveView.getJTextAreas().get(1);
        buttons = ActiveView.getJButtons();
        model = activeModel;
        radioButtons = ActiveView.getJRadioButtons();
        decimalNumbers =  model.getdecimalNumbers();
        view = ActiveView;
    }

    public void execute() {
        binViewButFunc(buttons.get(0));
        decViewButFunc(buttons.get(1));
        cardinalityButFunc(buttons.get(2));
        addButFunc(buttons.get(3));
        saveButFunc(buttons.get(4));
        clearButFunc(buttons.get(5));
    }

    public void clearButFunc(JButton clearBut) {
        clearBut.addActionListener(e -> {
            inputTextArea.setText("");
        });
    }
    public void binViewButFunc(JButton binViewBut) {
        binViewBut.addActionListener(e -> {
            view.printBinModel(model);
        });
    }
    public void decViewButFunc(JButton decViewBut) {
        decViewBut.addActionListener(e -> {
            view.printDecModel(model);
        });
    }


    public void addButFunc(JButton addBut) {
        addBut.addActionListener(e -> {
            try {
                model.upgradeDecimalNumbers(new MyInteger(Integer.parseInt(inputTextArea.getText())));
                inputTextArea.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid input data" + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public void saveButFunc(JButton saveBut) {
        saveBut.addActionListener(e -> {
            try{
                model.saveAsFile();
            } catch (IOException ex){
                JOptionPane.showMessageDialog(null, "Invalid path to file");
            }
        });
    }

    public void cardinalityButFunc(JButton cardinalityBut) {
        cardinalityBut.addActionListener(e -> {
            FilterType type = new FilterType();
            type.setStrategy(buttons.get(0).isSelected() ? new CountStrategy() : new SizeStrategy());
            outputTextArea.setText(String.valueOf(type.cardinality(decimalNumbers)));
        });
    }
}