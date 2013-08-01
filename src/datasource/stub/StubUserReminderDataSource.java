package datasource.stub;

import datasource.UserReminderDataSource;
import datasource.storedentities.UserRemindData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Nina
 * Date: 18.07.13
 * Time: 14:14
 */
public class StubUserReminderDataSource implements UserReminderDataSource {
    private final List<UserRemindData> reminders = new ArrayList<UserRemindData>();

    @Override
    public boolean isReminded(String user) {
        for (UserRemindData userdata : reminders) {
            if ((userdata.getId() + "").equals(user)) {
                return userdata.isReminded();
            }
        }
        addNewReminder(user, false);
        return false;
    }

    @Override
    public void setReminded(String user, boolean reminded) {
        for (UserRemindData userdata : reminders) {
            if ((userdata.getId() + "").equals(user)) {
                userdata.setReminded(reminded);
            } else {
                addNewReminder(user, reminded);
            }
        }
    }

    private void addNewReminder(String user, boolean reminded) {
        UserRemindData udata = new UserRemindData();
        udata.setId(user);
        udata.setReminded(reminded);
        reminders.add(udata);
    }
}
