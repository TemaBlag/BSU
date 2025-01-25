import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Frame extends JFrame {
    public static void main(String[] args){
        JFrame frame = new Frame("Series");
        frame.setSize(600,400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    Frame(String header){
        super(header);  // constructor JFrame class
        setLayout(new FlowLayout()); // location of elements
        ButtonGroup group = new ButtonGroup(); // create group lin ex buttons
        JRadioButton linBut = new JRadioButton();
        JRadioButton exBut = new JRadioButton();
        linBut.setSelected(true); // select linBut
        group.add(linBut);
        group.add(exBut);
        add(linBut);
        JLabel linLabel = new JLabel("Linear series");
        add(linLabel);
        add(exBut);
        JLabel exLabel = new JLabel("Exponential series");
        add(exLabel);
        JLabel zeroElLab = new JLabel("Zero element of series: ");
        add(zeroElLab);
        JTextField zeroElTextField = new JTextField(10);
        add(zeroElTextField);
        JLabel countLab = new JLabel("Count elements of series: ");
        add(countLab);
        JTextField countTextField = new JTextField(10);
        add(countTextField);
        JLabel incLab = new JLabel("Increment of series: ");
        add(incLab);
        JTextField incTextField = new JTextField(10);
        add(incTextField);
        JLabel sumLab = new JLabel("Sum of series: ");
        add(sumLab);
        JLabel sumNumLab = new JLabel();
        sumNumLab.setVisible(false);
        add(sumNumLab);
        JLabel showLab = new JLabel("Number elements to show: ");
        add(showLab);
        JTextField numShowTextField = new JTextField(10);
        add(numShowTextField);
        JLabel elemLab = new JLabel("Elements of series: ");
        add(elemLab);
        JTextArea elemArea = new JTextArea(5,15);
        elemArea.setLineWrap(true);
        elemArea.setVisible(false);
        JScrollPane scroll = new JScrollPane(elemArea);
        scroll.setVerticalScrollBarPolicy(JScrollPane. VERTICAL_SCROLLBAR_ALWAYS);
        elemArea.setEditable(false);
        add(scroll);
        JLabel writeToFileLab = new JLabel("Write to file");
        add(writeToFileLab);
        JLabel pathLab = new JLabel("Path to file: ");
        add(pathLab);
        JTextField pathTextField = new JTextField(15);
        add(pathTextField);
        JCheckBox writeToFileCheckBox = new JCheckBox("Write to file");
        add(writeToFileCheckBox);
        JButton countBut = new JButton("Count");
        add(countBut);
        JButton cleanBut = new JButton("Clean");
        add(cleanBut);
        // button "count" click processing
        countBut.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Series series = createSeries(linBut, zeroElTextField, countTextField, incTextField);
                    sumNumLab.setText(String.valueOf(series.sumProgression()));
                    sumNumLab.setVisible(true);
                    elemArea.setText(String.valueOf(series.toString(Integer.parseInt(numShowTextField.getText()))));
                    elemArea.setVisible(true);
                    if (writeToFileCheckBox.isSelected()){
                        series.writeToFile(String.valueOf(pathTextField.getText()));
                    }
                } catch (NullPointerException ex1) {
                    JOptionPane.showMessageDialog(Frame.this, "Input data in empty");
                } catch (NumberFormatException ex2){
                    JOptionPane.showMessageDialog(Frame.this, "Invalid or empty input data");
                } catch (IOException ex3) {
                    JOptionPane.showMessageDialog(Frame.this, "Invalid path to file");
                } catch (IndexOutOfBoundsException ex4){
                    JOptionPane.showMessageDialog(Frame.this, "Number elements to show " +
                            "is out of bounds series");
                }
            }
        });
        // button "clean" click processing
        cleanBut.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                zeroElTextField.setText("");
                countTextField.setText("");
                incTextField.setText("");
                pathTextField.setText("");
                sumNumLab.setText("");
                elemArea.setText("");
                numShowTextField.setText("");
            }
        });
    }

    public Series createSeries(JRadioButton but, JTextField zerEl, JTextField countEl, JTextField inc)
            throws NullPointerException, NumberFormatException{
        Series series;
        if (but.isSelected()){
            series = new Liner(Double.parseDouble(zerEl.getText()), Double.parseDouble(inc.getText()),
                    Integer.parseInt(countEl.getText()));
        } else {
            series = new Exponential(Double.parseDouble(zerEl.getText()), Double.parseDouble(inc.getText()),
                    Integer.parseInt(countEl.getText()));
        }
        return  series;
    }
}
