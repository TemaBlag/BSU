import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class DrawingFrame extends JFrame {
    private Color color;
    private final DrawingPanel drawingPanel;

    public static void main(String[] args) {
        new DrawingFrame("Paint");
    }
    public DrawingFrame(String string) {
        super(string);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        drawingPanel = new DrawingPanel();
        drawingPanel.setPreferredSize(new Dimension(1440, 720));
        JButton buttonOpen = new JButton("Open");
        JButton buttonSave = new JButton("Save");
        JButton clearButton = new JButton("Clear");
        JButton redButton = new JButton();
        redButton.setBackground(Color.red);
        JButton greenButton = new JButton();
        greenButton.setBackground(Color.green);
        JButton blueButton = new JButton();
        blueButton.setBackground(Color.blue);

        JScrollPane scroller = new JScrollPane(drawingPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        scroller.setPreferredSize(new Dimension(400, 300));

        setLayout(new BorderLayout());
        add(scroller, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(redButton);
        buttonPanel.add(greenButton);
        buttonPanel.add(blueButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(buttonOpen);
        buttonPanel.add(buttonSave);
        add(buttonPanel, BorderLayout.NORTH);

        pack();
        setVisible(true);

        redButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                color = Color.RED;
                drawingPanel.setColor(color);
            }
        });

        greenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                color = Color.GREEN;
                drawingPanel.setColor(color);
            }
        });

        blueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                color = Color.BLUE;
                drawingPanel.setColor(color);
            }
        });

        buttonOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser("D:\\study\\Java\\j8\\src");
                if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    try {
                        drawingPanel.image = ImageIO.read(fileChooser.getSelectedFile());
                        if (drawingPanel.image != null) {
                            drawingPanel.setPreferredSize(new Dimension(drawingPanel.image.getWidth(), drawingPanel.image.getHeight()));
                            drawingPanel.graphics = drawingPanel.image.createGraphics();
                            drawingPanel.setColor(drawingPanel.color);
                            drawingPanel.repaint();
                        } else {
                            JOptionPane.showMessageDialog(null, "Selected file is not a valid image", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Error reading the image file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (IllegalArgumentException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid image file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser("D:\\study\\Java\\j8\\src");
                if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    try {
                        File selectedFile = fileChooser.getSelectedFile();
                        String filePath = selectedFile.getAbsolutePath();
                        if (!filePath.toLowerCase().endsWith(".jpg")) {
                            selectedFile = new File(filePath + ".jpg");
                        }
                        ImageIO.write(drawingPanel.image, "jpg", selectedFile);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawingPanel.clear();
            }
        });
    }
}