import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

public class SaveXML {
    public static void add(String path, ArrayList<Student> studs) throws IOException, XMLStreamException, ParserConfigurationException, SAXException, TransformerException {
        File xmlFile = new File(path);

        if (!xmlFile.exists()) xmlFile.createNewFile();

        XMLOutputFactory factory = XMLOutputFactory.newFactory();
        XMLStreamWriter writer = factory.createXMLStreamWriter(new FileOutputStream(xmlFile));

        writer.writeStartDocument();
        writer.writeStartElement("students");
        for (Student stud : studs) {
            writer.writeStartElement("student");
            writer.writeAttribute("name", stud.surname);
            writer.writeAttribute("number_record_book", String.valueOf(stud.numOfRecordBook));
            for (Map.Entry entry : stud.info.entrySet()) {
                writer.writeStartElement("subject");
                writer.writeAttribute("name", (String) entry.getKey());
                writer.writeCharacters(String.valueOf(entry.getValue()));
                //newElement.appendChild(el);
                writer.writeEndElement();
            }
            //rootElement.appendChild(newElement);
            writer.writeEndElement();
        }
        writer.writeEndElement();
        writer.writeEndDocument();
        writer.flush();
        writer.close();


        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(path);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(xmlFile);
        transformer.transform(source, result);
    }
}