package storedentities.retrieve;

import storedentities.Donate;
import storedentities.Type;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

import datasource.TypeDataSource;

/**
 * Created by IntelliJ IDEA.
 * User: Buchina
 * Date: 10.01.2013
 * Time: 14:07:28
 * To change this template use File | Settings | File Templates.
 */
public class TypeGetter implements TypeDataSource{
    EntityManagerFactory entityManagerFactory;
    EntityManager typeManager;


    public TypeGetter(){
        entityManagerFactory= Persistence.createEntityManagerFactory("NewPersistanceUnit");
        typeManager=entityManagerFactory.createEntityManager();
    }
     public Collection<Type> getAllTypes(){
         Query query = typeManager.createQuery("SELECT types FROM Type types");
         List<Type> Typelist= query.getResultList();
       return Typelist;
    }

    public Type getTypeById(Long typeId){
        return typeManager.find(Type.class,typeId);
    }


}
