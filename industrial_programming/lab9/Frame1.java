import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Frame1 extends JPanel {
    Frame1() {
        setSize(600, 400);
        setVisible(true);
        setLayout(new BorderLayout());

        DefaultListModel<String> leftModel = new DefaultListModel<>();
        DefaultListModel<String> rightModel = new DefaultListModel<>();
        for(int i = 0; i < 10; ++i){
            leftModel.addElement(String.format("left element%d", i));
            rightModel.addElement(String.format("right element%d", i));
        }

        JList<String> leftList = new JList<String>(leftModel);
        leftList.setPreferredSize(new Dimension(200, 200));
        add(leftList, BorderLayout.WEST);

        JList<String> rightList = new JList<String>(rightModel);
        rightList.setPreferredSize(new Dimension(200, 200));
        add(rightList, BorderLayout.EAST);

        JPanel panel = new JPanel(new BorderLayout());

        JButton upBut = new JButton(">");
        upBut.setPreferredSize(new Dimension(150, 50));
        panel.add(upBut, BorderLayout.NORTH);

        JButton downBut = new JButton("<");
        downBut.setPreferredSize(new Dimension(150, 50));
        panel.add(downBut, BorderLayout.SOUTH);

        add(panel, BorderLayout.CENTER);

        upBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] selectedIndices = leftList.getSelectedIndices();
                for (int i = selectedIndices.length - 1; i >= 0; i--) {
                    String value = leftModel.getElementAt(selectedIndices[i]);
                    rightModel.addElement(value);
                    leftModel.removeElementAt(selectedIndices[i]);
                }
            }
        });

        downBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] selectedIndices = rightList.getSelectedIndices();
                for (int i = selectedIndices.length - 1; i >= 0; i--) {
                    String value = rightModel.getElementAt(selectedIndices[i]);
                    leftModel.addElement(value);
                    rightModel.removeElementAt(selectedIndices[i]);
                }
            }
        });

    }
}