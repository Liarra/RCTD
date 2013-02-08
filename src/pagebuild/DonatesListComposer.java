package pagebuild;

import generators.DonateGenerator;
import storedentities.Donate;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: Buchina
 * Date: 08.02.2013
 * Time: 15:02:24
 * To change this template use File | Settings | File Templates.
 */
public class DonatesListComposer extends AbstractComposer{
    DonateGenerator gen;
    String userId;

    public DonatesListComposer(String userId) {
        initDataSources();
        gen=new DonateGenerator(userId);
        this.userId=userId;
   }

    public String getDonatesHTML() {
        Collection<Donate> testDonates=donateDataSource.getAllDonates();
         return generateDonatesHTML(testDonates);
    }

    public String getDonatesHTML(Long typeID){
        Collection<Donate> donates=donateDataSource.getDonatesByTypeId(typeID);
        return generateDonatesHTML(donates);
    }


     private String generateDonatesHTML(Collection<Donate> d){
        String ret="";

        if(userClicksDataSource==null)
        for(Donate donate:d){
            ret+=gen.generateDonateHTML(donate,true);
        }

        else{
             for(Donate donate:d){
                 boolean isEnabled=userClicksDataSource.isAbleToClick(userId,donate);
                 ret+=gen.generateDonateHTML(donate,isEnabled);
        }
        }

        return ret;
    }

}
