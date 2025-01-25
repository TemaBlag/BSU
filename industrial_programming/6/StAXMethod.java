import javax.xml.stream.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Objects;

public class StAXMethod implements ParseStrategy{
public String method(String nameFile) throws
        FactoryConfigurationError, FileNotFoundException, SecurityException, XMLStreamException {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader parser = factory.createXMLStreamReader(new FileInputStream(nameFile));
        int buf = parser.next();
        StringBuilder string = new StringBuilder();
        String name = "";
        String number = "";
        String subject = "";
        String value;
        while (parser.hasNext()){
            int event = parser.next();
            if(event == XMLStreamConstants.START_ELEMENT){
                if (parser.getLocalName().equals("student")){
                    name = parser.getAttributeValue(0);
                    number = parser.getAttributeValue(1);
                }
                subject = parser.getAttributeValue(0);
            }else if(event == XMLStreamConstants.CHARACTERS){
                value = parser.getText();
                if (value.matches("\\d+")) string.append("Number of record book: ").append(number).append("; Surname: ").
                        append(name).append("; subject: ").append(subject).
                        append("; mark: ").append(value).append("\n");
            }
        }
        return string.toString();
    }
}