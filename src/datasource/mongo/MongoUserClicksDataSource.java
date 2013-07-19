package datasource.mongo;

import com.mongodb.*;
import datasource.UserClicksDataSource;
import datasource.storedentities.Donate;
import datasource.storedentities.UserClickData;

import java.io.Serializable;
import java.net.UnknownHostException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Nina
 * Date: 18.07.13
 * Time: 21:32
 */
public class MongoUserClicksDataSource implements UserClicksDataSource {
    MongoClient mongoClient;
    DB database;
    DBCollection userclicks;
    final String collectionName = "userclicks";

    public MongoUserClicksDataSource(MongoDataSourcesConfig config) {
        try {
            DB database = config.getDatabase();
            userclicks = database.getCollection(collectionName);
            if (userclicks == null)
                userclicks = database.createCollection(collectionName, null);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void click(String userId, Donate donate) {
        removeUserClicks(userId, donate);

        UserClickData data = new UserClickData();
        data.setDonate(donate);
        data.setDonateDate(new Date());
        data.setUser_id(userId);

        userclicks.save(getUserClickDBObject(data));
    }

    private DBObject getUserClickDBObject(UserClickData userClickData) {
        Map<String, Object> newObjectValues = new HashMap<String, Object>();
        newObjectValues.put("user_id", userClickData.getUser_id());
        newObjectValues.put("donateDate", userClickData.getDonateDate());
        newObjectValues.put("donate", getDonateDBObject(userClickData.getDonate()));

        return new BasicDBObject(newObjectValues);
    }

    private DBObject getDonateDBObject(Donate d) {
        Map<String, Serializable> newObjectValues = new HashMap<String, Serializable>();
        newObjectValues.put("id", d.getId());
        newObjectValues.put("name", d.getName());

        return new BasicDBObject(newObjectValues);
    }

    @Override
    public Date getLastClickDate(String userId, Donate donate) {
        List<UserClickData> userClickDatas = getUserClicks(userId, donate);
        if (userClickDatas.size() == 0) return new Date(0);
        Date max = new Date(0);

        for (UserClickData userClickData : userClickDatas) {
            if (userClickData.getDonateDate().after(max))
                max = userClickData.getDonateDate();
        }
        return max;
    }

    @Override
    public Date getLastClickDate(String userId) {
        List<UserClickData> userClickDatas = getUserClicks(userId);
        if (userClickDatas.size() == 0) return new Date(0);
        Date max = new Date(0);

        for (UserClickData userClickData : userClickDatas) {
            if (userClickData.getDonateDate().after(max))
                max = userClickData.getDonateDate();
        }
        return max;
    }

    @Override
    public boolean isAbleToClick(String userId, Donate donate) {
        Date d = getLastClickDate(userId, donate);
        Date now = new Date();

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
    public boolean existRecord(String userId) {
        return getUserClicks(userId).size() > 0;
    }

    @Override
    public Collection<String> getAllUserIds() {
        DBCursor cursor = userclicks.find();
        Set<String> ret = new HashSet<String>();
        while (cursor.hasNext()) {
            DBObject current = cursor.next();
            String user_id = current.get("user_id").toString();
            if (ret.contains(user_id)) continue;
            ret.add(user_id);
        }
        return ret;
    }

    private List<UserClickData> getUserClicks(String userId) {
        List<UserClickData> ret = new ArrayList<UserClickData>();
        DBCursor cursor = userclicks.find(new BasicDBObject("user_id", userId));

        while (cursor.hasNext()) {
            DBObject current = cursor.next();
            if (current.get("user_id").equals(userId)) {
                DBObject stored_donate = (DBObject) current.get("donate");


                UserClickData data = new UserClickData();
                data.setUser_id(userId);
                data.setDonateDate((Date) current.get("donateDate"));
                data.setDonate(new Donate(
                        ((Number) (stored_donate.get("id"))).longValue(),
                        stored_donate.get("name").toString())
                );

                ret.add(data);
            }
        }
        cursor.close();
        return ret;
    }

    private List<UserClickData> getUserClicks(String userId, Donate donate) {
        List<UserClickData> ret = new ArrayList<UserClickData>();
        DBCursor cursor = userclicks.find(new BasicDBObject("user_id", userId));

        while (cursor.hasNext()) {
            DBObject current = cursor.next();
            if (current.get("user_id").equals(userId)) {
                DBObject stored_donate = (DBObject) current.get("donate");
                Long donate_id = ((Number) (stored_donate.get("id"))).longValue();

                if (donate_id.equals(donate.getId())) {
                    UserClickData data = new UserClickData();
                    data.setUser_id(userId);
                    data.setDonateDate((Date) current.get("donateDate"));
                    data.setDonate(new Donate(
                            donate_id,
                            stored_donate.get("name").toString())
                    );

                    ret.add(data);
                }
            }
        }

        cursor.close();
        return ret;
    }

    private void removeUserClicks(String userId, Donate donate) {
        DBCursor cursor = userclicks.find(new BasicDBObject("user_id", userId));
        List<DBObject> todelete = new ArrayList<DBObject>();

        while (cursor.hasNext()) {
            DBObject current = cursor.next();
            if (current.get("user_id").equals(userId)) {
                DBObject stored_donate = (DBObject) current.get("donate");
                Long donate_id = ((Number) (stored_donate.get("id"))).longValue();

                if (donate_id.equals(donate.getId())) {
                    todelete.add(current);
                }
            }
        }
        cursor.close();

        for (DBObject o : todelete) {
            userclicks.remove(o);
        }
    }
}
