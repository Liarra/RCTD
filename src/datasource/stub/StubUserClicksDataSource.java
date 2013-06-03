package datasource.stub;

import storedentities.UserLastDonate;
import storedentities.Donate;

import java.util.*;

import datasource.UserClicksDataSource;

/**
 * Created with IntelliJ IDEA.
 * User: Buchina
 * Date: 30.01.2013
 * Time: 17:10:28
 */
public class StubUserClicksDataSource implements UserClicksDataSource{
    private final List<UserLastDonate> lastDonates =new ArrayList<UserLastDonate>();

    public void click(String userId, Donate donate) {
        UserLastDonate don=getLastUserDonate(userId,donate);
        if(don!=null)
        don.setDonateDate(new Date());

        else{
            UserLastDonate newDonate=new UserLastDonate();
            newDonate.setDonateDate(new Date());
            newDonate.setUser_id(userId);
            newDonate.setDonate(donate);

            lastDonates.add(newDonate);
        }
    }

    public Date getLastClickDate(String userId, Donate donate) {
        UserLastDonate lastdonate=getLastUserDonate(userId,donate);
        if(lastdonate ==null)return new Date(0);
        return lastdonate.getDonateDate();
    }

    public boolean isAbleToClick(String userId, Donate donate) {
        Date now=new Date();
        Date d=getLastClickDate(userId,donate);
        Calendar then_click=Calendar.getInstance();
        then_click.setTime(d);

        Calendar now_click=Calendar.getInstance();
        now_click.setTime(now);
        
        if(then_click.get(Calendar.DAY_OF_YEAR)==now_click.get(Calendar.DAY_OF_YEAR)){
            if(then_click.get(Calendar.YEAR)==now_click.get(Calendar.YEAR))
                return false;
        }

        return true;
    }

    @Override
    public boolean existRecord(String user) {
        for(UserLastDonate d: lastDonates){
            if(d.getUser_id().equals(user))
                return true;
        }
        return false;
    }

    private UserLastDonate getLastUserDonate(String user, Donate donate){
        for(UserLastDonate d: lastDonates){
              if(d.getUser_id().equals(user)&&d.getDonate().getId().equals(donate.getId()))
                  return d;
        }

        return null;
    }
}
