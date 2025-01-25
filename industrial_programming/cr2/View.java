import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
public class View extends JFrame {

    JTextArea inputTextArea = new JTextArea();
    JTextArea outputTextArea = new JTextArea();
    ArrayList<JButton> buttons;
    ArrayList<JRadioButton> radioButtons;
    View(String header) {
        super(header);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(layout);

        JLabel methodsLab = new JLabel("Methods: ");
        panel.add(methodsLab);

        Component glue1 = Box.createGlue();
        glue1.setMaximumSize(new Dimension(90, 10));
        panel.add(glue1);

        JButton binViewBut = new JButton("Binary view");
        binViewBut.setMaximumSize(new Dimension(110, 30));
        panel.add(binViewBut);

        Component glue2 = Box.createGlue();
        glue2.setMaximumSize(new Dimension(90, 10));
        panel.add(glue2);

        JButton decViewBut = new JButton("Decimal view");
        decViewBut.setMaximumSize(new Dimension(110, 30));
        panel.add(decViewBut);

        Component glue3 = Box.createGlue();
        glue3.setMaximumSize(new Dimension(90, 10));
        panel.add(glue3);

        JLabel chooseStrategyLab = new JLabel("Choose strategy: ");
        panel.add(chooseStrategyLab);

        ButtonGroup buttonsGroup = new ButtonGroup();

        JRadioButton steamBut = new JRadioButton("Size count");
        steamBut.setMaximumSize(new Dimension(110, 30));
        steamBut.setSelected(true);
        buttonsGroup.add(steamBut);
        panel.add(steamBut);

        JRadioButton basicCountBut = new JRadioButton("Basic count");
        basicCountBut.setMaximumSize(new Dimension(110, 30));
        buttonsGroup.add(basicCountBut);
        panel.add(basicCountBut);

        JButton cardinalityBut = new JButton("Cardinality");
        cardinalityBut.setMaximumSize(new Dimension(110, 30));
        panel.add(cardinalityBut);

        Component glue4 = Box.createGlue();
        glue4.setMaximumSize(new Dimension(90, 10));
        panel.add(glue4);

        JButton addBut = new JButton("Add");
        addBut.setMaximumSize(new Dimension(110, 30));
        panel.add(addBut);

        Component glue5 = Box.createGlue();
        glue5.setMaximumSize(new Dimension(90, 10));
        panel.add(glue5);

        JButton saveBut = new JButton("Save");
        saveBut.setMaximumSize(new Dimension(110, 30));
        panel.add(saveBut);

        Component glue6 = Box.createGlue();
        glue6.setMaximumSize(new Dimension(90, 10));
        panel.add(glue6);

        JButton clearBut = new JButton("Clear");
        clearBut.setMaximumSize(new Dimension(110, 30));
        panel.add(clearBut);


        JPanel viewPanel = new JPanel();
        BoxLayout viewLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(viewLayout);


        inputTextArea.setPreferredSize( new Dimension(350,150));
        outputTextArea.setPreferredSize( new Dimension(3500,150));

        JScrollPane scrollpane = new JScrollPane(outputTextArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        scrollpane.setPreferredSize(new Dimension(350,150));

        outputTextArea.setEditable(false);
        viewPanel.add(inputTextArea);
        viewPanel.add(scrollpane);


        add(panel, BorderLayout.WEST);
        add(viewPanel, BorderLayout.CENTER);

        buttons = new ArrayList<>();
        buttons.add(binViewBut);
        buttons.add(decViewBut);
        buttons.add(cardinalityBut);
        buttons.add(addBut);
        buttons.add(saveBut);
        buttons.add(clearBut);

        radioButtons = new ArrayList<>();
        radioButtons.add(basicCountBut);
        radioButtons.add(steamBut);
    }

    public ArrayList<JTextArea> getJTextAreas(){
        ArrayList<JTextArea> result = new ArrayList<>();
        result.add(inputTextArea);
        result.add(outputTextArea);
        return result;
    }

    public ArrayList<JButton> getJButtons(){
        return buttons;
    }

    public ArrayList<JRadioButton> getJRadioButtons(){
        return radioButtons;
    }

    public void printBinModel(Model model){
        outputTextArea.setText(model.getBinNums());
    }

    public void printDecModel(Model model){
        outputTextArea.setText(model.getDecNums());
    }
}
