import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Frame extends JFrame {
    File file;

    public static void main(String[] args) {
        JFrame frame = new Frame("Painter");
        frame.setSize(600, 450);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    Frame(String header) {
        super(header);
        setLayout(new BorderLayout());

        JLabel welcomeLab = new JLabel("Welcome to lamp shop!");
        welcomeLab.setHorizontalAlignment(JLabel.CENTER);
        add(welcomeLab, BorderLayout.NORTH);

        JPanel settingsPanel = new JPanel();
        BoxLayout layout = new BoxLayout(settingsPanel, BoxLayout.Y_AXIS);
        settingsPanel.setLayout(layout);

        JPanel setPanel = new JPanel(new GridLayout(1, 2));

        JTextArea text1 = new JTextArea();
        text1.setPreferredSize(new Dimension(200, 400));
        JTextArea text2 = new JTextArea();
        text2.setPreferredSize(new Dimension(200, 400));

        setPanel.add(text1);
        setPanel.add(text2);

        add(setPanel, BorderLayout.CENTER);


        JLabel fileLab = new JLabel("File: ");
        fileLab.setPreferredSize(new Dimension(85, 30));
        Component glue1 = Box.createGlue();
        glue1.setMaximumSize(new Dimension(90, 10));
        settingsPanel.add(glue1);
        settingsPanel.add(fileLab);

        Component glue2 = Box.createGlue();
        glue2.setMaximumSize(new Dimension(90, 10));
        JButton openBut = new JButton("Open file");
        openBut.setMaximumSize(new Dimension(150, 30));
        settingsPanel.add(openBut);

        JLabel methodsLab = new JLabel("Methods: ");
        methodsLab.setPreferredSize(new Dimension(85, 30));
        Component glue3 = Box.createGlue();
        glue3.setMaximumSize(new Dimension(90, 10));
        settingsPanel.add(glue3);
        settingsPanel.add(methodsLab);

        Component glue4 = Box.createGlue();
        glue4.setMaximumSize(new Dimension(90, 10));
        JButton incBut = new JButton("Increase price");
        incBut.setMaximumSize(new Dimension(150, 30));
        settingsPanel.add(glue4);
        settingsPanel.add(incBut);

        Component glue5 = Box.createGlue();
        glue5.setMaximumSize(new Dimension(90, 10));
        JButton pricePowerBut = new JButton("Price / Power");
        pricePowerBut.setMaximumSize(new Dimension(150, 30));
        settingsPanel.add(glue5);
        settingsPanel.add(pricePowerBut);

        Component glue6 = Box.createGlue();
        glue6.setMaximumSize(new Dimension(90, 10));
        JButton manBut = new JButton("C-manufactures");
        manBut.setMaximumSize(new Dimension(150, 30));
        settingsPanel.add(glue6);
        settingsPanel.add(manBut);

        JLabel averageLab = new JLabel("Average price: ");
        averageLab.setPreferredSize(new Dimension(85, 30));
        Component glue8 = Box.createGlue();
        glue8.setMaximumSize(new Dimension(90, 10));
        settingsPanel.add(glue8);
        settingsPanel.add(averageLab);

        JLabel manLab = new JLabel("Choose manufacture: ");
        manLab.setPreferredSize(new Dimension(85, 30));
        Component glue9 = Box.createGlue();
        glue9.setMaximumSize(new Dimension(90, 10));
        settingsPanel.add(glue9);
        settingsPanel.add(manLab);

        JTextField textField = new JTextField();
        textField.setMaximumSize(new Dimension(250, 30));
        Component glue10 = Box.createGlue();
        glue10.setMaximumSize(new Dimension(90, 10));
        settingsPanel.add(glue10);
        settingsPanel.add(textField);

        Component glue7 = Box.createGlue();
        glue7.setMaximumSize(new Dimension(90, 10));
        JButton countBut = new JButton("Count");
        countBut.setMaximumSize(new Dimension(150, 30));
        settingsPanel.add(glue7);
        settingsPanel.add(countBut);

        JLabel resLab = new JLabel();
        resLab.setPreferredSize(new Dimension(85, 30));
        Component glue11 = Box.createGlue();
        glue11.setMaximumSize(new Dimension(90, 10));
        settingsPanel.add(glue11);
        settingsPanel.add(resLab);

        add(settingsPanel, BorderLayout.EAST);

        pricePowerBut.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //text2.setText(Methods.pricePower());
            }
        });

        manBut.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //text2.setText(Methods.manufactureStartedWithC());
            }
        });

        countBut.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //resLab.setText("Result:" + Methods.averagePriceManufacture(textField.getText()));
            }
        });

        pricePowerBut.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                    //text2.setText(Methods.increasePrice());
            }
        });

        openBut.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser chooseFile = new JFileChooser();
                chooseFile.setDialogTitle("Choose file");
                chooseFile.setCurrentDirectory(new File("D:\\"));
                int result = chooseFile.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    Scanner scan = null;
                    try {
                        scan = new Scanner(chooseFile.getSelectedFile());
                        StringBuilder result1 = new StringBuilder();
                        while (scan.hasNextLine()) {
                            result1.append(scan.nextLine()).append("\n");
                        }
                        text1.setText(result1.toString());
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
    }

}