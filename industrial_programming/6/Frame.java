import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class Frame extends JFrame {
    ArrayList<Student> studList = new ArrayList<>();
    public static void main(String[] args){
        JFrame frame = new Frame("Session");
        frame.setSize(600,400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    Frame(String header){
        super(header);  // constructor JFrame class
        setLayout(new FlowLayout()); // location of elements

        JLabel strategyLab = new JLabel("Strategy: ");
        add(strategyLab);

        ButtonGroup radionsGroup = new ButtonGroup();

        JRadioButton streamBut = new JRadioButton("Stream");
        radionsGroup.add(streamBut);
        streamBut.setSelected(true);
        JRadioButton basicBut = new JRadioButton("Basic Filter");
        radionsGroup.add(basicBut);

        add(streamBut);
        add(basicBut);

        JLabel typeLab = new JLabel("Type file: ");
        add(typeLab);

        ButtonGroup buttonGroup = new ButtonGroup();
        JRadioButton txtBut = new JRadioButton("txt");
        txtBut.setSelected(true);
        buttonGroup.add(txtBut);
        add(txtBut);

        JRadioButton xmlBut = new JRadioButton("xml");
        buttonGroup.add(xmlBut);
        add(xmlBut);

        ButtonGroup buttonGroup1 = new ButtonGroup();
        JRadioButton domBut = new JRadioButton("DOM");
        domBut.setSelected(true);
        domBut.setVisible(false);
        buttonGroup1.add(domBut);
        add(domBut);

        JRadioButton staxBut = new JRadioButton("StAX");
        buttonGroup1.add(staxBut);
        staxBut.setVisible(false);
        add(staxBut);

        xmlBut.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                domBut.setVisible(true);
                staxBut.setVisible(true);
            }
        });

        txtBut.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                domBut.setVisible(false);
                staxBut.setVisible(false);
            }
        });


        JLabel pathLab = new JLabel("Path to file: ");
        add(pathLab);
        JTextField pathTextField = new JTextField(10);
        add(pathTextField);
        JLabel newStud = new JLabel("New result: ");
        add(newStud);
        JLabel numOfRecordBookLab = new JLabel("Number of record book: ");
        add(numOfRecordBookLab);
        JTextField numOfRecordBookTextField = new JTextField(10);
        add(numOfRecordBookTextField);
        JLabel surnameLab = new JLabel("Surname of the student: ");
        add(surnameLab);
        JTextField surnameTextField = new JTextField(10);
        add(surnameTextField);
        JLabel nameSubjLab = new JLabel("Names of the subject: ");
        add(nameSubjLab);
        JTextField nameSubjTextField = new JTextField(10);
        add(nameSubjTextField);
        JLabel markLab = new JLabel("Marks: ");
        add(markLab);
        JTextField markTextField = new JTextField(10);
        add(markTextField);
        JButton addBut = new JButton("Add");
        add(addBut);
        JButton cleanBut = new JButton("Clean");
        add(cleanBut);
        JButton readFileBut = new JButton("Read file");
        add(readFileBut);
        JButton showResBut = new JButton("Show results");
        add(showResBut);
        JButton showSortedResBut = new JButton("Sort Students and Show");
        add(showSortedResBut);
        JButton saveBut = new JButton("Save");
        add(saveBut);
        // button "clean" click processing
        cleanBut.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                pathTextField.setText("");
                numOfRecordBookTextField.setText("");
                surnameTextField.setText("");
                nameSubjTextField.setText("");
                markTextField.setText("");
            }
        });
        saveBut.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if(txtBut.isSelected()) {
                        if (!pathTextField.getText().split("\\.")[1].equals("txt")){
                            throw new IOException();
                        }
                        writeToFile(studList, pathTextField.getText());
                    } else {
                        if (!pathTextField.getText().split("\\.")[1].equals("xml")){
                            throw new IOException();
                        }
                        SaveXML save = new SaveXML();
                        save.add(pathTextField.getText(), studList);
                    }
                    numOfRecordBookTextField.setText("");
                    surnameTextField.setText("");
                    nameSubjTextField.setText("");
                    markTextField.setText("");
                } catch(NullPointerException ex1){
                    JOptionPane.showMessageDialog(Frame.this, "Empty input data");
                } catch(IndexOutOfBoundsException ex2){
                    JOptionPane.showMessageDialog(Frame.this, "Not equal count" +
                            " of marks and subjects");
                } catch(NumberFormatException ex3){
                    JOptionPane.showMessageDialog(Frame.this, "Invalid or empty input data");
                } catch (IOException ex4){
                    JOptionPane.showMessageDialog(Frame.this, "Invalid path to file");
                } catch (IllegalArgumentException ex5){
                    JOptionPane.showMessageDialog(Frame.this, "Mark can not be > 10 or < 1");
                } /*catch (TransformerException ex) {
                    JOptionPane.showMessageDialog(Frame.this, "Error");
                } catch (XMLStreamException ex) {
                    JOptionPane.showMessageDialog(Frame.this, "Error");
                } catch (ParserConfigurationException ex) {
                    JOptionPane.showMessageDialog(Frame.this, "Invalid path to file");
                } catch (SAXException ex) {
                    JOptionPane.showMessageDialog(Frame.this, "Error");
                }*/ catch (XMLStreamException ex) {
                    throw new RuntimeException(ex);
                } catch (ParserConfigurationException ex) {
                    throw new RuntimeException(ex);
                } catch (TransformerException ex) {
                    throw new RuntimeException(ex);
                } catch (SAXException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        readFileBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String text = fileText(txtBut, pathTextField, domBut);
                    ArrayList<Student> st = new ArrayList<>();
                    for (Student stud : createArrayStudents(text)){
                        st.add(stud);
                    }
                    studList = st;
                    } catch (IOException ex5){
                    JOptionPane.showMessageDialog(Frame.this, "Invalid path to file");
                } catch (XMLStreamException ex) {
                    JOptionPane.showMessageDialog(Frame.this, "Error");
                } catch (ParserConfigurationException ex) {
                    JOptionPane.showMessageDialog(Frame.this, "Invalid path to file");
                } catch (SAXException ex) {
                    JOptionPane.showMessageDialog(Frame.this, "Error");
                }
            }
        });
        // button "Add" click processing
        addBut.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Student stud = new Student(surnameTextField.getText(), Integer.parseInt(numOfRecordBookTextField.getText()),
                            createMapOfMarks(nameSubjTextField.getText(), markTextField.getText()));
                    studList.add(stud);
                    numOfRecordBookTextField.setText("");
                    surnameTextField.setText("");
                    nameSubjTextField.setText("");
                    markTextField.setText("");
                } catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(Frame.this, "Empty string");
                }
            }
        });
        // button "Show results" click processing
        showResBut.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder string = new StringBuilder();
                for (Student stud: studList){
                    string.append(stud);
                }
                ResultSession res = new ResultSession(string.toString());
            }
        });
        // button "Sort students and Show" click processing
        showSortedResBut.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String text = fileText(txtBut, pathTextField, domBut);
                    FilterType type = new FilterType();
                    type.setStrategy(streamBut.isSelected() ? new StreamFilterStrategy() : new BasicFilter());
                    ResultSession res = new ResultSession(createStudents(text, type));
                } catch (IOException ex5){
                    JOptionPane.showMessageDialog(Frame.this, "Invalid path to file");
                } catch (XMLStreamException ex) {
                    JOptionPane.showMessageDialog(Frame.this, "Error");
                } catch (ParserConfigurationException ex) {
                    JOptionPane.showMessageDialog(Frame.this, "Invalid path to file");
                } catch (SAXException ex) {
                    JOptionPane.showMessageDialog(Frame.this, "Error");
                }
            }
        });
    }
    private String readFromFile(String path) throws IOException {
        FileReader reader = new FileReader(path);
        Scanner scan = new Scanner(reader);
        StringBuilder result = new StringBuilder();
        while (scan.hasNextLine()) {
            result.append(scan.nextLine()).append("\n");
        }
        reader.close();
        return result.toString();
    }

    private void writeToFile(ArrayList<Student> studs, String path) throws IOException {
        FileWriter writer = new FileWriter(path);
        for (Student stud : studs){
            writer.append(stud.toString());
        }
        writer.flush(); // clean the data from writer
        writer.close();
    }
    private boolean checkMark(Integer num){
        return num <= 10 && num >= 1;
    }
    private HashMap<String, Integer> createMapOfMarks(String subject, String mark) throws NullPointerException,
            NumberFormatException, IndexOutOfBoundsException, IllegalArgumentException{
        String[] subjects = subject.split(" ");
        List<String> marksStr = Arrays.asList(mark.split(" "));
        List<Integer> marks = marksStr.stream().map(Integer::parseInt).toList();
        if (subjects.length != marks.size()){
            throw new IndexOutOfBoundsException();
        }
        marks = marks.stream().filter(this::checkMark).toList();
        if (subjects.length != marks.size()){
            throw new IllegalArgumentException();
        }
        HashMap<String, Integer> result = new HashMap<String, Integer>();
        for (int i = 0; i < subjects.length; i++){
            result.put(subjects[i], marks.get(i));
        }
        return result;
    }

    public String createStudents(String studentString, FilterType type){
        String[] dataStudents = studentString.split("\n");
        ArrayList<Student> students = new ArrayList<>();
        for (String stud : dataStudents){
            String[] dataStudent = stud.split(" ");
            Map<String, Integer> value = new HashMap<String, Integer>();
            if (dataStudent[0].isEmpty()){
                continue;
            }
            value.put(dataStudent[8].substring(0,dataStudent[8].length() - 1), Integer.parseInt(dataStudent[10]));
            if (students.isEmpty()){
                Student newstud = new Student(dataStudent[6].substring(0,dataStudent[6].length() - 1),
                        Integer.parseInt(dataStudent[4].substring(0, dataStudent[4].length() - 1)),value);
                students.add(newstud);
                continue;
            }
            int k = 1;
            for (Student student : students){
                if (student.numOfRecordBook == Integer.parseInt(dataStudent[4].substring(0,
                        dataStudent[4].length() - 1))){
                    for (String subj :student.info.keySet())
                        value.put(subj, student.info.get(subj));
                    Student newstud = new Student(dataStudent[6].substring(0,dataStudent[6].length() - 1),
                            Integer.parseInt(dataStudent[4].substring(0, dataStudent[4].length() - 1)),value);
                    students.remove(student);
                    students.add(newstud);
                    k = 0;
                    break;
                }
            }
            if (k == 1){
                Student newstud = new Student(dataStudent[6].substring(0,dataStudent[6].length() - 1),
                        Integer.parseInt(dataStudent[4].substring(0, dataStudent[4].length() - 1)),value);
                students.add(newstud);
            }
        }
        students = type.filterStudents(students);
        students.sort(new SurnameComparator());
        StringBuilder result = new StringBuilder();
        for (Student stud : students){
                result.append(stud.surname).append(" ");
        }
        return result.toString();
    }

    public String fileText(JRadioButton txtBut,JTextField pathTextField, JRadioButton domBut)
            throws IOException, XMLStreamException, ParserConfigurationException, SAXException {
        String text = new String();
        if (txtBut.isSelected()) {
            if (!pathTextField.getText().split("\\.")[1].equals("txt")){
                throw new IOException();
            }
            text = readFromFile(pathTextField.getText());
        } else {
            if (!pathTextField.getText().split("\\.")[1].equals("xml")){
                throw new IOException();
            }
            ParseStrategy parseType = domBut.isSelected() ? new DOMMethod() : new StAXMethod();
            text = parseType.method(pathTextField.getText());
        }
        return text;
    }

    public ArrayList<Student> createArrayStudents(String studentString){
        String[] dataStudents = studentString.split("\n");
        ArrayList<Student> students = new ArrayList<>();
        for (String stud : dataStudents){
            String[] dataStudent = stud.split(" ");
            Map<String, Integer> value = new HashMap<String, Integer>();
            if (dataStudent[0].isEmpty()){
                continue;
            }
            value.put(dataStudent[8].substring(0,dataStudent[8].length() - 1), Integer.parseInt(dataStudent[10]));
            if (students.isEmpty()){
                Student newstud = new Student(dataStudent[6].substring(0,dataStudent[6].length() - 1),
                        Integer.parseInt(dataStudent[4].substring(0, dataStudent[4].length() - 1)),value);
                students.add(newstud);
                continue;
            }
            int k = 1;
            for (Student student : students){
                if (student.numOfRecordBook == Integer.parseInt(dataStudent[4].substring(0,
                        dataStudent[4].length() - 1))){
                    for (String subj :student.info.keySet())
                        value.put(subj, student.info.get(subj));
                    Student newstud = new Student(dataStudent[6].substring(0,dataStudent[6].length() - 1),
                            Integer.parseInt(dataStudent[4].substring(0, dataStudent[4].length() - 1)),value);
                    students.remove(student);
                    students.add(newstud);
                    k = 0;
                    break;
                }
            }
            if (k == 1){
                Student newstud = new Student(dataStudent[6].substring(0,dataStudent[6].length() - 1),
                        Integer.parseInt(dataStudent[4].substring(0, dataStudent[4].length() - 1)),value);
                students.add(newstud);
            }
        }
        students.sort(new SurnameComparator());
        return students;
    }
}
