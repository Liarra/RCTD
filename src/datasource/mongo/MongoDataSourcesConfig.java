package datasource.mongo;

import com.mongodb.DB;
import com.mongodb.MongoClient;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: Nina
 * Date: 18.07.13
 * Time: 21:10
 */
public class MongoDataSourcesConfig {
    private String host = "localhost", db = "myDB", user = "admin", pass = "admin";
    private int port = 27017;

    public MongoDataSourcesConfig(String filename) {
        try {
            setFromProperties(load(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MongoDataSourcesConfig() {
        try {
            setFromProperties(load("mongoconfig.cfg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DB getDatabase() throws UnknownHostException {
        MongoClient mongoClient = new MongoClient(getHost(), getPort());

        DB database = mongoClient.getDB(getDBName());

        if (!getUserName().isEmpty()) {
            database.authenticate(getUserName(), getPass().toCharArray());
        }

        return database;
    }

    public String getUserName() {
        return user;
    }

    public String getPass() {
        return pass;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getDBName() {
        return db;
    }

    private Properties load(String filename) throws IOException, FileNotFoundException {
        Properties properties = new Properties();
        InputStream input = MongoDataSourcesConfig.class.getResourceAsStream(filename);
        try {
            properties.load(input);
            return properties;
        } finally {
            input.close();
        }
    }

    private void setFromProperties(Properties p) {
        String propertyHost = p.getProperty("host");
        String propertyDB = p.getProperty("db");
        Integer PropertyPort = (p.getProperty("port") == null) ? null : Integer.valueOf(p.getProperty("port"));

        if (propertyHost != null) host = propertyHost;
        if (propertyDB != null) db = propertyDB;
        if (PropertyPort != null) port = PropertyPort;
    }
}
