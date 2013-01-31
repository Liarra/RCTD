package datasource.xml;

import datasource.TypeDataSource;
import datasource.xml.jaxbGenerated.Donates;
import storedentities.Type;
import storedentities.Donate;

import javax.xml.bind.*;
import java.io.InputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Buchina
 * Date: 31.01.2013
 * Time: 13:07:45
 * To change this template use File | Settings | File Templates.
 */
public class XmlTypeDataSource implements TypeDataSource{
    InputStream storageFile;

    public XmlTypeDataSource(InputStream storageFile) {
        this.storageFile = storageFile;
    }

    public Collection<Type> getAllTypes()  {
        try {
            Donates jaxbDonates=JaxbReader.unmarshallFile(storageFile);

            return Transformer.getAllTypes(jaxbDonates);
                } catch (JAXBException e) {
            e.printStackTrace();
        }  catch (
    IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Type getTypeById(Long typeId)  {
        Collection<Type> types=getAllTypes();
        for(Type t:types){
            if(t.getId().equals(typeId))
                return t;
        }
        return null;
    }

}
