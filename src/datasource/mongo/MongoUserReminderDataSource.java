package datasource.mongo;

import com.mongodb.*;
import datasource.UserReminderDataSource;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Nina
 * Date: 19.07.13
 * Time: 14:35
 */
public class MongoUserReminderDataSource implements UserReminderDataSource {
    DBCollection userrem;
    final String collectionName = "userrem";

    public MongoUserReminderDataSource(MongoDataSourcesConfig config) {
        try {
            DB database = config.getDatabase();
            userrem = database.getCollection(collectionName);
            if (userrem == null)
                userrem = database.createCollection(collectionName, null);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isReminded(String userId) {
        DBObject user = findUser(userId);
        if (user == null) {
            writeNew(userId, false);
            return false;
        }
        if ((Boolean) (user.get("reminded"))) {
            return true;
        }
        return false;
    }

    @Override
    public void setReminded(String user, boolean reminded) {
        DBObject existingUser = findUser(user);
        if (existingUser == null) {
            writeNew(user, reminded);
            return;
        }
        existingUser.put("reminded", reminded);
        userrem.save(existingUser);
    }

    private DBObject findUser(String userId) {
        DBObject ret = null;
        DBCursor cursor = userrem.find(new BasicDBObject("id", userId));

        while (cursor.hasNext()) {
            DBObject current = cursor.next();
            ret = current;
        }

        return ret;
    }

    private void writeNew(String user, boolean reminded) {
        Map m = new HashMap();
        m.put("id", user);
        m.put("reminded", reminded);
        userrem.save(new BasicDBObject(m));
    }
}
