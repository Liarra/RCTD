package datasource.stub;

import datasource.*;
/**
 * Created by IntelliJ IDEA.
 * User: Buchina
 * Date: 30.01.2013
 * Time: 18:31:55
 * To change this template use File | Settings | File Templates.
 */
public class StubDataSourcesRepository {
    public static AdDataSource AdDataSourceInstance=new StubAdDataSource();
     public static DonateDataSource DonateDataSourceInstance=new StubDonateDataSource();
     public static TypeDataSource TypeDataSourceInstance=new StubTypeDataSource();
     public static UserClicksDataSource UserClicksDataSourceInstance=new StubUserClicksDataSource();
}
