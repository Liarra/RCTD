package datasource.mongo;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import datasource.UserClicksDataSource;
import datasource.storedentities.Donate;

import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Nina
 * Date: 18.07.13
 * Time: 21:32
 */
public class MongoUserClicksDataSource implements UserClicksDataSource{
    MongoClient mongoClient;
    DB database;

    public MongoUserClicksDataSource(MongoDataSourcesConfig config) throws UnknownHostException {
        mongoClient = new MongoClient(config.getHost(), config.getPort());
        database = mongoClient.getDB(config.getDBName());

        if (!config.getUserName().isEmpty()) {
            database.authenticate(config.getUserName(), config.getPass().toCharArray());
        }
    }

    @Override
    public void click(String userId, Donate donate) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Date getLastClickDate(String userId, Donate donate) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Date getLastClickDate(String userId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isAbleToClick(String userId, Donate donate) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean existRecord(String userId) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Collection<String> getAllUserIds() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
