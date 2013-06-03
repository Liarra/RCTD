package datasource;

import storedentities.Donate;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Buchina
 * Date: 30.01.2013
 * Time: 16:50:25
 */
public interface UserClicksDataSource {
    public void click(String userId, Donate donate);
    public Date getLastClickDate(String userId, Donate donate);
    public boolean isAbleToClick(String userId, Donate donate);
    public boolean existRecord(String userId);
}
