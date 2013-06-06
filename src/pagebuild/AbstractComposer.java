package pagebuild;

import datasource.xml.XmlDataSourcesRepository;
import datasource.UserClicksDataSource;
import datasource.DonateDataSource;
import datasource.TypeDataSource;
import datasource.AdDataSource;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServlet;

/**
 * Created with IntelliJ IDEA.
 * User: Buchina
 * Date: 08.02.2013
 * Time: 16:09:12
 */
public abstract class AbstractComposer extends HttpServlet{
    protected UserClicksDataSource userClicksDataSource;
    protected DonateDataSource donateDataSource;
    protected TypeDataSource typeDataSource;
    protected AdDataSource adDataSource;


    void initDataSources(){
       this.userClicksDataSource = datasource.stub.StubDataSourcesRepository.UserClicksDataSourceInstance;
       this.adDataSource=datasource.stub.StubDataSourcesRepository.AdDataSourceInstance;
       this.donateDataSource=new XmlDataSourcesRepository().XmlDonateDataSourceInstance;
       this.typeDataSource = new XmlDataSourcesRepository().XmlTypeDataSourceInstance;
    }

    protected AbstractComposer(){
        initDataSources();
    }
}
