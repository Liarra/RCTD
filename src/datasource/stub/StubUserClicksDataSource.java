package datasource.stub;

import datasource.UserClicksDataSource;
import datasource.storedentities.Donate;
import datasource.storedentities.UserClickData;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * UserClickData: Buchina
 * Date: 30.01.2013
 * Time: 17:10:28
 */
public class StubUserClicksDataSource implements UserClicksDataSource {
    private final List<UserClickData> lastDonates = new ArrayList<UserClickData>();

    public void click(String userId, Donate donate) {
        UserClickData don = getLastUserDonate(userId, donate);
        if (don != null)
            don.setDonateDate(new Date());

        else {
            UserClickData newDonate = new UserClickData();
            newDonate.setDonateDate(new Date());
            newDonate.setUser_id(userId);
            newDonate.setDonate(donate);

            lastDonates.add(newDonate);
        }
    }

    public Date getLastClickDate(String userId, Donate donate) {
        UserClickData lastDonate = getLastUserDonate(userId, donate);
        if (lastDonate == null) return new Date(0);
        return lastDonate.getDonateDate();
    }

    public Date getLastClickDate(String userId) {
        Date lastDonate = getLastUserDonateDate(userId);
        if (lastDonate == null) return new Date(0);
        return lastDonate;
    }

    public boolean isAbleToClick(String userId, Donate donate) {
        Date now = new Date();
        Date d = getLastClickDate(userId, donate);
        Calendar then_click = Calendar.getInstance();
        then_click.setTime(d);

        Calendar now_click = Calendar.getInstance();
        now_click.setTime(now);

        if (then_click.get(Calendar.DAY_OF_YEAR) == now_click.get(Calendar.DAY_OF_YEAR)) {
            if (then_click.get(Calendar.YEAR) == now_click.get(Calendar.YEAR))
                return false;
        }

        return true;
    }

    @Override
    public boolean existRecord(String user) {
        for (UserClickData d : lastDonates) {
            if (d.getUser_id().equals(user))
                return true;
        }
        return false;
    }

    public Collection<String> getAllUserIds() {
        List<String> ret = new ArrayList<String>();
        for (UserClickData d : lastDonates) {
            if (ret.contains(d.getUser_id())) continue;
            ret.add(d.getUser_id());
        }

        return ret;
    }

    private UserClickData getLastUserDonate(String user, Donate donate) {
        for (UserClickData d : lastDonates) {
            if (d.getUser_id().equals(user) && d.getDonate().getId().equals(donate.getId()))
                return d;
        }

        return null;
    }

    private Date getLastUserDonateDate(String user) {
        List<UserClickData> userClickDatas = new ArrayList<UserClickData>();
        for (UserClickData d : lastDonates) {
            if (d.getUser_id().equals(user))
                userClickDatas.add(d);
        }

        if (userClickDatas.size() == 0) return null;
        Date last = userClickDatas.get(0).getDonateDate();
        for (UserClickData d : userClickDatas) {
            if (d.getDonateDate().after(last))
                last = d.getDonateDate();
        }

        return last;
    }
}
