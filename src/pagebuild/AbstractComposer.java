package pagebuild;

import datasource.xml.XmlDataSourcesRepository;
import datasource.UserClicksDataSource;
import datasource.DonateDataSource;
import datasource.TypeDataSource;
import datasource.AdDataSource;

/**
 * Created by IntelliJ IDEA.
 * User: Buchina
 * Date: 08.02.2013
 * Time: 16:09:12
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractComposer {
    UserClicksDataSource userClicksDataSource;
    DonateDataSource donateDataSource;
    TypeDataSource typeDataSource;
    AdDataSource adDataSource;


    void initDataSources(){
       this.userClicksDataSource = datasource.stub.StubDataSourcesRepository.UserClicksDataSourceInstance;
       this.adDataSource=datasource.stub.StubDataSourcesRepository.AdDataSourceInstance;
       this.donateDataSource=new XmlDataSourcesRepository().XmlDonateDataSourceInstance;
       this.typeDataSource = new XmlDataSourcesRepository().XmlTypeDataSourceInstance;
    }

    public AbstractComposer(){
        initDataSources();
    }
}
