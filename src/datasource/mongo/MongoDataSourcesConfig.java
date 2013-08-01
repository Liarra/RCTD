package datasource.mongo;

import com.mongodb.DB;
import com.mongodb.MongoClient;

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

    private MongoClient mongoClient;
    private static MongoDataSourcesConfig config;

    public static MongoDataSourcesConfig createFromSource() throws IOException {
        return createFromSource("mongoconfig.cfg");
    }

    public static MongoDataSourcesConfig createFromSource(String filename) throws IOException {
        if (config == null) {
            MongoDataSourcesConfig ret = new MongoDataSourcesConfig(filename);
            config = ret;
            return config;
        }

        config.mongoClient.close();
        config.setFromProperties(load(filename));

        return config;
    }

    private MongoDataSourcesConfig(String filename) {
        try {
            setFromProperties(load(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DB getDatabase() throws UnknownHostException {
        DB database = mongoClient.getDB(getDBName());

        if (!getUserName().isEmpty()) {
            database.authenticate(getUserName(), getPass().toCharArray());
        }

        return database;
    }

    public void close() {

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

    private static Properties load(String filename) throws IOException {
        Properties properties = new Properties();
        InputStream input = MongoDataSourcesConfig.class.getResourceAsStream(filename);
        try {
            properties.load(input);
            return properties;
        } finally {
            input.close();
        }
    }

    private void setFromProperties(Properties p) throws UnknownHostException {
        String propertyHost = p.getProperty("host");
        String propertyDB = p.getProperty("db");
        String propertyUser = p.getProperty("user");
        String propertyPass = p.getProperty("pass");
        Integer PropertyPort = (p.getProperty("port") == null) ? null : Integer.valueOf(p.getProperty("port"));

        if (propertyHost != null) host = propertyHost;
        if (propertyDB != null) db = propertyDB;
        if (propertyUser != null) user = propertyUser;
        if (propertyPass != null) pass = propertyPass;
        if (PropertyPort != null) port = PropertyPort;

        if (mongoClient != null)
            mongoClient.close();
        mongoClient = new MongoClient(getHost(), getPort());
    }
}
