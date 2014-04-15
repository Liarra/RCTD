package pagebuild;

import datasource.*;
import datasource.mongo.MongoDataSourcesConfig;
import datasource.mongo.MongoTypeDataSource;
import datasource.mongo.MongoUserClicksDataSource;
import datasource.mongo.MongoUserReminderDataSource;
import datasource.xml.XmlDataSourcesRepository;

import javax.servlet.http.HttpServlet;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * UserClickData: Buchina
 * Date: 08.02.2013
 * Time: 16:09:12
 */
public abstract class AbstractComposer{
    protected UserClicksDataSource userClicksDataSource;
    protected DonateDataSource donateDataSource;
    protected TypeDataSource typeDataSource;
    protected AdDataSource adDataSource;
    protected UserReminderDataSource userReminderDataSource;


    void initDataSources(){
//        MongoDataSourcesConfig mongoConfig= null;
//        try {
//            mongoConfig = MongoDataSourcesConfig.createFromSource("default.cfg");
////            mongoConfig = MongoDataSourcesConfig.createFromSource();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        this.adDataSource=datasource.stub.StubDataSourcesRepository.AdDataSourceInstance;
       this.donateDataSource=new XmlDataSourcesRepository().XmlDonateDataSourceInstance;
       this.typeDataSource = new XmlDataSourcesRepository().XmlTypeDataSourceInstance;
//       this.typeDataSource = new MongoTypeDataSource(mongoConfig);
        this.userClicksDataSource = datasource.stub.StubDataSourcesRepository.USER_DATA_SOURCE_INSTANCE;
//        this.userClicksDataSource = new MongoUserClicksDataSource(mongoConfig);
//        this.userReminderDataSource=new MongoUserReminderDataSource(mongoConfig);

    }

    protected AbstractComposer(){
        initDataSources();
    }
}
