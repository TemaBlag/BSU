import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
public class View extends JFrame {
    JTextArea textArea = new JTextArea();
    JList<MyInteger> textList = new JList<>(new Deck<MyInteger>( new ArrayList<>()).listParameter());
    JTextArea averageTextArea = new JTextArea("Average value");
    ArrayList<JButton> buttons;

    View(String header) {
        super(header);
        setLayout(new BorderLayout());

        JLabel welcomeLab = new JLabel("Welcome to Deck creator!!");
        welcomeLab.setHorizontalAlignment(JLabel.CENTER);
        add(welcomeLab, BorderLayout.NORTH);

        JPanel settingsPanel = new JPanel();
        BoxLayout layout = new BoxLayout(settingsPanel, BoxLayout.Y_AXIS);
        settingsPanel.setLayout(layout);

        JPanel setPanel = new JPanel();

        textArea.setPreferredSize(new Dimension(150, 400));
        setPanel.add(textArea);

        textList.setPreferredSize(new Dimension(150, 400));
        setPanel.add(textList);

        add(setPanel, BorderLayout.CENTER);

        JLabel methodsLab = new JLabel("Deck methods: ");
        methodsLab.setPreferredSize(new Dimension(85, 30));
        Component glue3 = Box.createGlue();
        glue3.setMaximumSize(new Dimension(90, 10));
        settingsPanel.add(glue3);
        settingsPanel.add(methodsLab);

        Component glue4 = Box.createGlue();
        glue4.setMaximumSize(new Dimension(90, 10));
        JButton frontBut = new JButton("Front");
        frontBut.setMaximumSize(new Dimension(150, 30));
        settingsPanel.add(glue4);
        settingsPanel.add(frontBut);

        Component glue5 = Box.createGlue();
        glue5.setMaximumSize(new Dimension(90, 10));
        JButton backBut = new JButton("Back");
        backBut.setMaximumSize(new Dimension(150, 30));
        settingsPanel.add(glue5);
        settingsPanel.add(backBut);

        Component glue6 = Box.createGlue();
        glue6.setMaximumSize(new Dimension(90, 10));
        JButton pushFrontBut = new JButton("PushFront");
        pushFrontBut.setMaximumSize(new Dimension(150, 30));
        settingsPanel.add(glue6);
        settingsPanel.add(pushFrontBut);

        Component glue7 = Box.createGlue();
        glue7.setMaximumSize(new Dimension(90, 10));
        JButton pushBackBut = new JButton("PushBack");
        pushBackBut.setMaximumSize(new Dimension(150, 30));
        settingsPanel.add(glue7);
        settingsPanel.add(pushBackBut);

        Component glue8 = Box.createGlue();
        glue8.setMaximumSize(new Dimension(90, 10));
        JButton popFrontBut = new JButton("PopFront");
        popFrontBut.setMaximumSize(new Dimension(150, 30));
        settingsPanel.add(glue8);
        settingsPanel.add(popFrontBut);

        Component glue9 = Box.createGlue();
        glue9.setMaximumSize(new Dimension(90, 10));
        JButton popBackBut = new JButton("PopBack");
        popBackBut.setMaximumSize(new Dimension(150, 30));
        settingsPanel.add(glue9);
        settingsPanel.add(popBackBut);

        Component glue10 = Box.createGlue();
        glue10.setMaximumSize(new Dimension(90, 10));
        JButton pushFrontAllBut = new JButton("PushFrontAll");
        pushFrontAllBut.setMaximumSize(new Dimension(150, 30));
        settingsPanel.add(glue10);
        settingsPanel.add(pushFrontAllBut);

        Component glue11 = Box.createGlue();
        glue11.setMaximumSize(new Dimension(90, 10));
        JButton pushBackAllBut = new JButton("PushBackAll");
        pushBackAllBut.setMaximumSize(new Dimension(150, 30));
        settingsPanel.add(glue11);
        settingsPanel.add(pushBackAllBut);


        add(settingsPanel, BorderLayout.EAST);

        JPanel defaultSettingPanel = new JPanel();
        BoxLayout layout1 = new BoxLayout(defaultSettingPanel, BoxLayout.Y_AXIS);
        defaultSettingPanel.setLayout(layout1);

        JLabel generalMethodsLab = new JLabel("Methods: ");
        generalMethodsLab.setPreferredSize(new Dimension(85, 30));
        Component glue01 = Box.createGlue();
        glue01.setMaximumSize(new Dimension(90, 10));
        defaultSettingPanel.add(glue01);
        defaultSettingPanel.add(generalMethodsLab);

        Component glue02 = Box.createGlue();
        glue02.setMaximumSize(new Dimension(90, 10));
        JButton sizeBut = new JButton("Size");
        sizeBut.setMaximumSize(new Dimension(150, 30));
        defaultSettingPanel.add(glue02);
        defaultSettingPanel.add(sizeBut);

        Component glue03 = Box.createGlue();
        glue03.setMaximumSize(new Dimension(90, 10));
        JButton isEmptyBut = new JButton("isEmpty");
        isEmptyBut.setMaximumSize(new Dimension(150, 30));
        defaultSettingPanel.add(glue03);
        defaultSettingPanel.add(isEmptyBut);

        Component glue04 = Box.createGlue();
        glue04.setMaximumSize(new Dimension(90, 10));
        JButton clearBut = new JButton("Clear");
        clearBut.setMaximumSize(new Dimension(150, 30));
        defaultSettingPanel.add(glue04);
        defaultSettingPanel.add(clearBut);

        Component glue05 = Box.createGlue();
        glue05.setMaximumSize(new Dimension(90, 10));
        JButton equalsBut = new JButton("Equals");
        equalsBut.setMaximumSize(new Dimension(150, 30));
        defaultSettingPanel.add(glue05);
        defaultSettingPanel.add(equalsBut);

        JLabel clearFrameLab = new JLabel("Clear frame: ");
        clearFrameLab.setPreferredSize(new Dimension(85, 30));
        Component glue07 = Box.createGlue();
        glue07.setMaximumSize(new Dimension(90, 10));
        defaultSettingPanel.add(glue07);
        defaultSettingPanel.add(clearFrameLab);

        Component glue08 = Box.createGlue();
        glue08.setMaximumSize(new Dimension(90, 10));
        JButton clearFrameBut = new JButton("Clear Frame");
        clearFrameBut.setMaximumSize(new Dimension(150, 30));
        defaultSettingPanel.add(glue08);
        defaultSettingPanel.add(clearFrameBut);

        Component glue09 = Box.createGlue();
        glue09.setMaximumSize(new Dimension(90, 10));
        JLabel averageLab = new JLabel("Average value: ");
        averageLab.setMaximumSize(new Dimension(150, 30));
        defaultSettingPanel.add(glue09);
        defaultSettingPanel.add(averageLab);

        Component glue20 = Box.createGlue();
        glue20.setMaximumSize(new Dimension(90, 10));
        averageTextArea.setMaximumSize(new Dimension(100, 30));
        averageTextArea.setEditable(false);
        defaultSettingPanel.add(glue20);
        defaultSettingPanel.add(averageTextArea);

        Component glue19 = Box.createGlue();

        glue19.setMaximumSize(new Dimension(90, 10));
        JButton averageBut = new JButton("Average");
        averageBut.setMaximumSize(new Dimension(150, 30));
        defaultSettingPanel.add(glue19);
        defaultSettingPanel.add(averageBut);

        add(defaultSettingPanel, BorderLayout.WEST);

        buttons = new ArrayList<>();
        buttons.add(clearBut);
        buttons.add(equalsBut);
        buttons.add(pushFrontBut);
        buttons.add(pushBackBut);
        buttons.add(frontBut);
        buttons.add(backBut);
        buttons.add(popBackBut);
        buttons.add(popFrontBut);
        buttons.add(pushFrontAllBut);
        buttons.add(pushBackAllBut);
        buttons.add(clearFrameBut);
        buttons.add(averageBut);
        buttons.add(sizeBut);
        buttons.add(isEmptyBut);
    }
    public ArrayList<JTextArea> getJTextAreas(){
        ArrayList<JTextArea> result = new ArrayList<>();
        result.add(textArea);
        result.add(averageTextArea);
        return result;
    }

    public JList<MyInteger> getJList(){
        return textList;
    }

    public ArrayList<JButton> getJButtons(){
        return buttons;
    }

    public void printDeck(Deck<MyInteger> deck){ textList.setModel(deck.listParameter());}
}