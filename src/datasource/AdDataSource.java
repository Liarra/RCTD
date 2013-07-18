package datasource;

import datasource.storedentities.Ad;

/**
 * Created with IntelliJ IDEA.
 * UserClickData: Buchina
 * Date: 25.01.2013
 * Time: 16:00:38
 */
public interface AdDataSource {
    public Ad getAdById(Long id);
}
