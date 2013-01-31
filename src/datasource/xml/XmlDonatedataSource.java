package datasource.xml;

import datasource.DonateDataSource;
import datasource.xml.jaxbGenerated.Donates;
import storedentities.Donate;
import storedentities.Type;

import javax.xml.bind.JAXBException;
import java.util.Collection;
import java.util.ArrayList;
import java.io.InputStream;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Buchina
 * Date: 31.01.2013
 * Time: 13:07:57
 * To change this template use File | Settings | File Templates.
 */
public class XmlDonatedataSource implements DonateDataSource{
    public XmlDonatedataSource(InputStream storageFile) {
        this.storageFile = storageFile;
    }

    InputStream storageFile;

    public Collection<Donate> getAllDonates() {
        Collection<Donate> ret=new ArrayList<Donate>();

        try {
            Donates jaxbDonates=JaxbReader.unmarshallFile(storageFile);
            Collection<Type> types=Transformer.getAllTypes(jaxbDonates);

            for(Type t:types)
                ret.addAll(getDonatesByType(t));
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace(); 
        }

        return ret;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Collection<Donate> getDonatesByType(Type t) {
        return t.getDonates();
    }

    public Donate getDonateById(Long donateId) {
        Collection<Donate> donates=getAllDonates();

        for(Donate donate:donates){
            if(donate.getId().equals(donateId))
                return donate;
        }
        return null;
    }
}