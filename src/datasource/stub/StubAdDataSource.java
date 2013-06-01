package datasource.stub;

import datasource.AdDataSource;
import storedentities.Ad;

/**
 * Created with IntelliJ IDEA.
 * User: Buchina
 * Date: 28.01.2013
 * Time: 16:57:42
 */
public class StubAdDataSource implements AdDataSource{
    public Ad getAdbyId(Long id) {
        Ad ad=new Ad();
        ad.setHTML("<img src='img/1.jpg' />");
        ad.setScript("alert(\"Ok ad\");");
        return ad;
    }
}
