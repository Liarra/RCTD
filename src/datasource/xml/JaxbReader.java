package datasource.xml;

import datasource.xml.jaxbGenerated.Donates;

import javax.xml.bind.*;
import java.io.InputStream;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Buchina
 * Date: 31.01.2013
 * Time: 14:36:26
 * To change this template use File | Settings | File Templates.
 */
public class JaxbReader {
    public static Donates unmarshallFile(InputStream storageFile) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(Donates.class);
    	Unmarshaller um = context.createUnmarshaller();
    	um.setEventHandler(
    		    new ValidationEventHandler() {
    		        public boolean handleEvent(ValidationEvent event ) {
    		            throw new RuntimeException(event.getMessage(),
    		                                       event.getLinkedException());
    		        }
    		});
    	Donates res=(Donates)um.unmarshal(storageFile);
        storageFile.reset();
    	return res;
    }
}
