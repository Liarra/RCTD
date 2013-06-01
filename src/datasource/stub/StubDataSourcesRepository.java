package datasource.stub;

import datasource.*;
/**
 * Created with IntelliJ IDEA.
 * User: Buchina
 * Date: 30.01.2013
 * Time: 18:31:55
 */
public class StubDataSourcesRepository {
    public static final AdDataSource AdDataSourceInstance=new StubAdDataSource();
     public static DonateDataSource DonateDataSourceInstance=new StubDonateDataSource();
     public static TypeDataSource TypeDataSourceInstance=new StubTypeDataSource();
     public static final UserClicksDataSource UserClicksDataSourceInstance=new StubUserClicksDataSource();
}
