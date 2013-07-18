package vk;

import datasource.UserClicksDataSource;
import datasource.UserReminderDataSource;
import datasource.storedentities.Donate;
import datasource.storedentities.UserRemindData;
import datasource.stub.StubUserClicksDataSource;
import datasource.stub.StubUserReminderDataSource;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * UserClickData: Nina
 * Date: 17.07.13
 * Time: 19:46
 */
public class UserReminder {
    UserClicksDataSource userClicksDataSource;
    UserReminderDataSource userReminderDataSource;

    public void remindBunchOfUsers() {
        Collection<String> usersToRemind = getUsersToRemind();
        RemindUsers(usersToRemind);
    }

    private Collection<String> getUsersToRemind() {
        Collection<String> users = getRegisterdUserIds();
        users = getThoseNotClickedFor2DaysFromNow(users);
        Collection<String> notRemindedUsers = getThoseWithoutReminderFlag(users);
        if (notRemindedUsers.size() == 0) {
            unremindAll(users);
        } else users = notRemindedUsers;

        users = getFirstN_OrRemaining(users);
        return users;
    }

    private Collection<String> getRegisterdUserIds() {
        return userClicksDataSource.getAllUserIds();
    }

    private Collection<String> getThoseNotClickedFor2DaysFromNow(Collection<String> users) {
        Collection<String> ret = new ArrayList<String>();
        Date yesterday = new Date();
        yesterday.setSeconds(0);
        yesterday.setMinutes(0);
        yesterday.setHours(0);

        Calendar yesterday_cal = Calendar.getInstance();
        yesterday_cal.setTime(yesterday);
        yesterday_cal.add(Calendar.DATE, -1);

        yesterday = yesterday_cal.getTime();
        for (String userId : users) {
            if (userClicksDataSource.getLastClickDate(userId).before(yesterday)) {
                ret.add(userId);
            }
        }

        return users;

//        return ret;
    }

    private Collection<String> getThoseWithoutReminderFlag(Collection<String> users) {
        Collection<String> ret = new ArrayList<String>();

        for (String user : users)
            if (userReminderDataSource.isReminded(user)) ret.add(user);

        return ret;
    }

    private void unremindAll(Collection<String> users) {
        for (String user : users)
            userReminderDataSource.setReminded(user, false);
    }

    private final int N = 100;

    private Collection<String> getFirstN_OrRemaining(Collection<String> users) {
        if (users.size() <= N) return users;
        Collection<String> ret = new ArrayList<String>();
        int i = 0;
        for (String user : users) {
            ret.add(user);
            i++;
            if (i >= 100) break;
        }

        return ret;
    }

    private void RemindUsers(Collection<String> users) {
        for (String user : users) {
            new UserReminderSender().NotifyUser(user);
            userReminderDataSource.setReminded(user, true);
        }
    }

    public static void main(String[] args) {
        UserRemindData me = new UserRemindData();
        me.setId("18462180");

        UserReminder userReminder = new UserReminder();
        userReminder.userReminderDataSource = new StubUserReminderDataSource();
        userReminder.userClicksDataSource = new StubUserClicksDataSource();

        userReminder.userClicksDataSource.click(me.getId(),new Donate(0L,"nya"));

        userReminder.remindBunchOfUsers();
    }
}
