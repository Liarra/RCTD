package datasource.mongo;

import datasource.TypeDataSource;
import datasource.storedentities.Type;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Nina
 * Date: 18.07.13
 * Time: 20:24
 */
public class MongoTypeDataSource implements TypeDataSource{
    @Override
    public Collection<Type> getAllTypes() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Type getTypeById(Long typeId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
