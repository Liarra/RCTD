package datasource;

import storedentities.Ad;

/**
 * Created with IntelliJ IDEA.
 * User: Buchina
 * Date: 25.01.2013
 * Time: 16:00:38
 */
public interface AdDataSource {
    public Ad getAdbyId(Long id);
}
