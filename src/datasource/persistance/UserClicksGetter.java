package datasource.persistance;

import datasource.UserClicksDataSource;
import storedentities.Donate;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Buchina
 * Date: 08.02.2013
 * Time: 14:53:48
 */
public class UserClicksGetter implements UserClicksDataSource{
    public void click(String userId, Donate donate) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public Date getLastClickDate(String userId, Donate donate) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean isAbleToClick(String userId, Donate donate) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
