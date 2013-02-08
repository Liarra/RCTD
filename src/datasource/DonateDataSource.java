package datasource;

import storedentities.Donate;
import storedentities.Type;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: Buchina
 * Date: 22.01.2013
 * Time: 12:01:49
 * To change this template use File | Settings | File Templates.
 */
public interface DonateDataSource {
     public Collection<Donate> getAllDonates();
    
    @Deprecated
    public Collection<Donate> getDonatesByType(Type t);
    public Collection<Donate> getDonatesByTypeId(Long typeId);
     public Donate getDonateById(Long donateId);

}
