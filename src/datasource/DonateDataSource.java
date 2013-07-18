package datasource;

import datasource.storedentities.Donate;
import datasource.storedentities.Type;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * UserClickData: Buchina
 * Date: 22.01.2013
 * Time: 12:01:49
 */
public interface DonateDataSource {
     public Collection<Donate> getAllDonates();
    
    @Deprecated
    public Collection<Donate> getDonatesByType(Type t);
    public Collection<Donate> getDonatesByTypeId(Long typeId);
     public Donate getDonateById(Long donateId);

}
