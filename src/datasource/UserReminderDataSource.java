package datasource;

import datasource.storedentities.UserRemindData;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Nina
 * Date: 18.07.13
 * Time: 14:04
 */
public interface UserReminderDataSource {

    public boolean isReminded(String userId);

    public void setReminded(String user, boolean reminded);
}
