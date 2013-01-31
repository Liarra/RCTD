package datasource.xml;

import datasource.DonateDataSource;
import datasource.TypeDataSource;
import datasource.stub.StubDonateDataSource;
import datasource.stub.StubTypeDataSource;

import java.io.InputStream;

/**
 * Created by IntelliJ IDEA.
 * User: Buchina
 * Date: 31.01.2013
 * Time: 13:08:23
 * To change this template use File | Settings | File Templates.
 */
public class XmlDataSourcesRepository {
    static InputStream storageFile=XmlDataSourcesRepository.class.getResourceAsStream("testdonates.xml");
    public static DonateDataSource XmlDonateDataSourceInstance=new XmlDonatedataSource(storageFile);
     public static TypeDataSource XmlTypeDataSourceInstance=new XmlTypeDataSource(storageFile);
}
