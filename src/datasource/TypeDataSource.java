package datasource;

import storedentities.Type;

import javax.xml.bind.JAXBException;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Buchina
 * Date: 22.01.2013
 * Time: 12:01:39
 */
public interface TypeDataSource {
    public Collection<Type> getAllTypes();
    public Type getTypeById(Long typeId) ;
}
