package datasource.mongo;

import com.mongodb.*;
import datasource.TypeDataSource;
import datasource.storedentities.Type;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Nina
 * Date: 18.07.13
 * Time: 20:24
 */
public class MongoTypeDataSource implements TypeDataSource {
    MongoClient mongoClient;
    DB database;

    public MongoTypeDataSource(MongoDataSourcesConfig config) {
        try {
            mongoClient = new MongoClient(config.getHost(), config.getPort());
            database = mongoClient.getDB(config.getDBName());

            if (!config.getUserName().isEmpty()) {
                database.authenticate(config.getUserName(), config.getPass().toCharArray());
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Collection<Type> getAllTypes() {
        List<Type> ret = new ArrayList<Type>();
        DBCollection types = database.getCollection("types");
        if (types == null) {
            types = database.createCollection("types", null);
        }

        DBCursor typesCursor = types.find();

        try {
            while (typesCursor.hasNext()) {
                DBObject object = typesCursor.next();
                Type t = new Type(((Number) (object.get("id"))).longValue(), object.get("name").toString());
                ret.add(t);
            }
        } finally {
            typesCursor.close();
        }

        return ret;
    }

    @Override
    public Type getTypeById(Long typeId) {
        Type ret = null;
        DBCollection types = database.getCollection("types");
        if (types == null) {
            types = database.createCollection("types", null);
        }

        BasicDBObject query = new BasicDBObject("id", typeId);
        DBCursor typeCursor = types.find(query);

        try {
            while (typeCursor.hasNext()) {
                ret = ((Type) typeCursor.next());
            }
        } finally {
            typeCursor.close();
        }

        return ret;
    }
}
