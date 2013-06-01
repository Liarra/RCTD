package datasource.persistance;

import datasource.TypeDataSource;
import storedentities.Type;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Buchina
 * Date: 10.01.2013
 * Time: 14:07:28
 */
public class TypeGetter implements TypeDataSource {
    private final EntityManager typeManager;

    public TypeGetter() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("NewPersistanceUnit");
        typeManager = entityManagerFactory.createEntityManager();
    }

    public Collection<Type> getAllTypes() {
        Query query = typeManager.createQuery("SELECT types FROM Type types");
        return query.getResultList();
    }

    public Type getTypeById(Long typeId) {
        return typeManager.find(Type.class, typeId);
    }


}
