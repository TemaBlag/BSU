import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLStreamException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;

public class DOMMethod implements ParseStrategy{
    public String method(String nameFile) throws
        FactoryConfigurationError, IOException, SecurityException, XMLStreamException, ParserConfigurationException,
            SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(nameFile);
        NodeList studentList = document.getElementsByTagName("student");
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < studentList.getLength(); i++) {
            Node studentNode = studentList.item(i);
            if (studentNode.getNodeType() == Node.ELEMENT_NODE) {
                Element personElement = (Element) studentNode;
                String name = personElement.getAttribute("name");
                String numRecBook = personElement.getAttribute("number_record_book");
                string.append(printElements(studentNode, name, numRecBook));
            }
        }
        return string.toString();
    }

    static String printElements(Node node, String name, String numRecBook) {
        StringBuilder res = new StringBuilder();
        if (node instanceof Element) {
            NodeList subjectList = ((Element) node).getElementsByTagName("subject");
            for (int j = 0; j < subjectList.getLength(); j++) {
                Element subjectElement = (Element) subjectList.item(j);
                String subjectName = subjectElement.getAttribute("name");
                String subjectValue = subjectElement.getTextContent();
                res.append("Number of record book: ").append(numRecBook).append("; Surname: ").
                        append(name).append("; subject: ").append(subjectName).
                        append("; mark: ").append(subjectValue).append("\n");
            }
        }
        return res.toString();
    }
}