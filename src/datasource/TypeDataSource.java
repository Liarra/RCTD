package datasource;

import datasource.storedentities.Type;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * UserClickData: Buchina
 * Date: 22.01.2013
 * Time: 12:01:39
 */
public interface TypeDataSource {
    public Collection<Type> getAllTypes();
    public Type getTypeById(Long typeId) ;
}
