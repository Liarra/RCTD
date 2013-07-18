package datasource;

import datasource.storedentities.Donate;

import java.util.Collection;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * UserClickData: Buchina
 * Date: 30.01.2013
 * Time: 16:50:25
 */
public interface UserClicksDataSource {
    public void click(String userId, Donate donate);

    public Date getLastClickDate(String userId, Donate donate);

    public Date getLastClickDate(String userId);

    public boolean isAbleToClick(String userId, Donate donate);

    public boolean existRecord(String userId);

    public Collection<String> getAllUserIds();
}
