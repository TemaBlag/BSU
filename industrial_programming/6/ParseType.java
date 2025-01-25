import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class ParseType{
    private ParseStrategy strategy;

    public void setStrategy(ParseStrategy strategy) {
        this.strategy = strategy;
    }

    public String method(String nameFile) throws
            FactoryConfigurationError, SecurityException, IOException, XMLStreamException,
            ParserConfigurationException, SAXException {
        return strategy.method(nameFile);
    }
}