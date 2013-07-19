package pagebuild;

import datasource.mongo.MongoDataSourcesConfig;
import datasource.mongo.MongoTypeDataSource;
import datasource.mongo.MongoUserClicksDataSource;
import datasource.xml.XmlDataSourcesRepository;
import datasource.UserClicksDataSource;
import datasource.DonateDataSource;
import datasource.TypeDataSource;
import datasource.AdDataSource;

import javax.servlet.http.HttpServlet;

/**
 * Created with IntelliJ IDEA.
 * UserClickData: Buchina
 * Date: 08.02.2013
 * Time: 16:09:12
 */
public abstract class AbstractComposer extends HttpServlet{
    protected UserClicksDataSource userClicksDataSource;
    protected DonateDataSource donateDataSource;
    protected TypeDataSource typeDataSource;
    protected AdDataSource adDataSource;


    void initDataSources(){
        MongoDataSourcesConfig mongoConfig=new MongoDataSourcesConfig();

       this.adDataSource=datasource.stub.StubDataSourcesRepository.AdDataSourceInstance;
       this.donateDataSource=new XmlDataSourcesRepository().XmlDonateDataSourceInstance;
       this.typeDataSource = new XmlDataSourcesRepository().XmlTypeDataSourceInstance;
//       this.typeDataSource = new MongoTypeDataSource(mongoConfig);
//        this.userClicksDataSource = datasource.stub.StubDataSourcesRepository.USER_DATA_SOURCE_INSTANCE;
        this.userClicksDataSource = new MongoUserClicksDataSource(mongoConfig);

    }

    protected AbstractComposer(){
        initDataSources();
    }
}
