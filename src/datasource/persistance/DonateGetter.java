package datasource.persistance;

import datasource.DonateDataSource;
import storedentities.Donate;
import storedentities.Type;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Buchina
 * Date: 10.01.2013
 * Time: 14:05:06
 */
public class DonateGetter implements DonateDataSource {
    private final EntityManager donateManager;

    public DonateGetter() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("NewPersistanceUnit");
        donateManager = entityManagerFactory.createEntityManager();
    }

    public Collection<Donate> getAllDonates() {
        Query query = donateManager.createQuery("SELECT donates FROM Donate donates");
        return query.getResultList();
    }

    public Collection<Donate> getDonatesByType(Type t) {
        Query query = donateManager.createQuery("SELECT donates FROM Donate donates WHERE donates.type =?1");
        query.setParameter("1", t);
        return query.getResultList();
    }

    public Collection<Donate> getDonatesByTypeId(Long typeId) {
        Collection<Donate> all = getAllDonates();
        Collection<Donate> ret = new ArrayList<Donate>();

        for (Donate d : all) {
            if (d.getType().getId().equals(typeId))
                ret.add(d);
        }

        return ret;
    }

    public Collection<Donate> getDonatesByTypeID(Long typeId) {
        Type t = new TypeGetter().getTypeById(typeId);
        return getDonatesByType(t);
    }

    public Donate getDonateById(Long donateId) {
        return donateManager.find(Donate.class, donateId);
    }
}
