package datasource.xml;

import datasource.TypeDataSource;
import datasource.xml.jaxbGenerated.Donates;
import storedentities.Type;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Buchina
 * Date: 31.01.2013
 * Time: 13:07:45
 */
public class XmlTypeDataSource implements TypeDataSource {
    private final InputStream storageFile;

    public XmlTypeDataSource(InputStream storageFile) {
        this.storageFile = storageFile;
    }

    public Collection<Type> getAllTypes() {
        try {
            Donates jaxbDonates = JaxbReader.unmarshallFile(storageFile);

            return Transformer.getAllTypes(jaxbDonates);
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Type getTypeById(Long typeId) {
        Collection<Type> types = getAllTypes();
        for (Type t : types) {
            if (t.getId().equals(typeId))
                return t;
        }
        return null;
    }
}
