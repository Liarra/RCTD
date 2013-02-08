package datasource.persistance;

import storedentities.Donate;
import storedentities.Type;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
import java.util.Collection;
import java.util.ArrayList;

import datasource.DonateDataSource;

/**
 * Created by IntelliJ IDEA.
 * User: Buchina
 * Date: 10.01.2013
 * Time: 14:05:06
 * To change this template use File | Settings | File Templates.
 */
public class DonateGetter implements DonateDataSource{

    EntityManagerFactory entityManagerFactory;
    EntityManager donateManager;

    public DonateGetter(){
        entityManagerFactory= Persistence.createEntityManagerFactory("NewPersistanceUnit");
        donateManager=entityManagerFactory.createEntityManager();
    }

    public Collection<Donate> getAllDonates(){
        Query query = donateManager.createQuery("SELECT donates FROM Donate donates");
         List<Donate> DonateList= query.getResultList();
       return DonateList;
    }

    public Collection<Donate> getDonatesByType(Type t){
       Query query = donateManager.createQuery("SELECT donates FROM Donate donates WHERE donates.type =?1");
        query.setParameter("1",t);
         List<Donate> DonateList= query.getResultList();
       return DonateList;
    }

    public Collection<Donate> getDonatesByTypeId(Long typeId) {
        Collection<Donate> all=getAllDonates();
        Collection<Donate> ret=new ArrayList<Donate>();

        for(Donate d:all){
            if(d.getType().getId().equals(typeId))
                ret.add(d);
        }

        return ret;
    }

    public Collection<Donate> getDonatesByTypeID(Long typeId){
        Type t=new TypeGetter().getTypeById(typeId);
        return getDonatesByType(t);
    }

    public Donate getDonateById(Long donateId){
        return donateManager.find(Donate.class,donateId);
    }
}
