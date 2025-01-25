import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class Frame extends JFrame{
    private final MyPanel panel;
    public static void main(String[] args) {
        JFrame frame = new Frame("Painter");
        frame.setSize(600, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    Frame(String header){
        super(header);
        setLayout(new BorderLayout());

        JLabel welcomeLab = new JLabel("Welcome to painter!");
        welcomeLab.setHorizontalAlignment(JLabel.CENTER);
        add(welcomeLab, BorderLayout.NORTH);

        JPanel settingsPanel = new JPanel();
        BoxLayout layout = new BoxLayout(settingsPanel, BoxLayout.Y_AXIS);
        settingsPanel.setLayout(layout);

        JLabel colorsLab = new JLabel("Choose color: ");
        colorsLab.setPreferredSize(new Dimension(85, 30));
        settingsPanel.add(colorsLab);

        Component glue1 = Box.createGlue();
        Component glue2 = Box.createGlue();
        Component glue3 = Box.createGlue();
        glue1.setMaximumSize(new Dimension(90, 10));
        glue2.setMaximumSize(new Dimension(90, 10));
        glue3.setMaximumSize(new Dimension(90, 10));
        settingsPanel.add(glue1);

        JButton redBut = new JButton("Red");
        redBut.setMaximumSize(new Dimension(90, 30));
        settingsPanel.add(redBut);
        settingsPanel.add(glue2);

        JButton greenBut = new JButton("Green");
        greenBut.setMaximumSize(new Dimension(90, 30));
        settingsPanel.add(greenBut);
        settingsPanel.add(glue3);

        JButton yellowBut = new JButton("Yellow");
        yellowBut.setMaximumSize(new Dimension(90, 30));
        settingsPanel.add(yellowBut);

        JLabel cleanLab = new JLabel("Clean picture: ");
        cleanLab.setPreferredSize(new Dimension(85, 30));
        Component glue8 = Box.createGlue();
        glue8.setMaximumSize(new Dimension(90, 10));
        settingsPanel.add(glue8);
        settingsPanel.add(cleanLab);

        JButton cleanBut = new JButton("Clean");
        cleanBut.setMaximumSize(new Dimension(85, 30));
        Component glue6 = Box.createGlue();
        glue6.setMaximumSize(new Dimension(90, 10));
        settingsPanel.add(glue6);
        settingsPanel.add(cleanBut);

        JLabel fileLab = new JLabel("File: ");
        fileLab.setPreferredSize(new Dimension(85, 30));
        Component glue4 = Box.createGlue();
        glue4.setMaximumSize(new Dimension(90, 10));
        settingsPanel.add(glue4);
        settingsPanel.add(fileLab);

        Component glue5 = Box.createGlue();
        glue5.setMaximumSize(new Dimension(90, 10));
        settingsPanel.add(glue5);

        JButton insertBut = new JButton("Insert picture");
        insertBut.setMaximumSize(new Dimension(150, 30));
        settingsPanel.add(insertBut);

        Component glue7 = Box.createGlue();
        glue7.setMaximumSize(new Dimension(90, 10));
        settingsPanel.add(glue7);

        JButton saveBut = new JButton("Save picture");
        saveBut.setMaximumSize(new Dimension(150, 30));
        settingsPanel.add(saveBut);

        add(settingsPanel, BorderLayout.EAST);

        pack();

        panel = new MyPanel();
        panel.setPreferredSize(new Dimension(1500, 750));
        JScrollPane scroller = new JScrollPane(panel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroller.setPreferredSize(new Dimension(200, 100));
        add(scroller, BorderLayout.CENTER);
        setVisible(true);

        panel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
            }
        });

        redBut.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                panel.color = Color.RED;
            }
        });

        greenBut.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                panel.color = Color.GREEN;
            }
        });

        yellowBut.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                panel.color = Color.yellow;
            }
        });

        cleanBut.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                panel.clear();
            }
        });

        insertBut.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser chooseFile = new JFileChooser();
                chooseFile.setDialogTitle("Choose file");
                chooseFile.setCurrentDirectory(new File("D:\\"));
                int result = chooseFile.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    try {
                        panel.image = ImageIO.read(chooseFile.getSelectedFile());
                        panel.setPreferredSize(new Dimension(panel.image.getWidth(), panel.image.getHeight()));
                        panel.graphics = panel.image.createGraphics();
                        panel.repaint();
                    } catch (IOException ex){
                        JOptionPane.showMessageDialog(Frame.this, "Error reading" +
                                " the image file: " + ex.getMessage());
                    } catch (IllegalArgumentException ex1){
                        JOptionPane.showMessageDialog(Frame.this, "Invalid image" +
                                " file: " + ex1.getMessage());
                    }
                }
            }
        });

        saveBut.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    JFileChooser chooseFile = new JFileChooser();
                    chooseFile.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    chooseFile.setDialogTitle("Choose file");
                    chooseFile.setCurrentDirectory(new File("D:\\"));
                    int result = chooseFile.showOpenDialog(null);
                    if (result == JFileChooser.APPROVE_OPTION) {

                        File file = chooseFile.getSelectedFile();
                        int k = 0;
                        File newFile = new File(file + "\\picture(" + k + ").jpg");
                        while(newFile.isFile()){
                            k += 1;
                            newFile = new File(file + "\\picture(" + k + ").jpg");
                        }
                        ImageIO.write(panel.image, "jpg", newFile);
                    }
                } catch (IOException ex){
                    JOptionPane.showMessageDialog(Frame.this, "Incorrect path");
                }
            }
        });
    }
}
