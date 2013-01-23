package datasource;

import storedentities.Type;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: Buchina
 * Date: 22.01.2013
 * Time: 12:01:39
 * To change this template use File | Settings | File Templates.
 */
public interface TypeDataSource {
    public Collection<Type> getAllTypes();
    public Type getTypeById(Long typeId);
}
