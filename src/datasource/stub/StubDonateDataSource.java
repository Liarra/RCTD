package datasource.stub;

import datasource.DonateDataSource;
import storedentities.Donate;
import storedentities.Type;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Buchina
 * Date: 25.01.2013
 * Time: 14:22:33
 * To change this template use File | Settings | File Templates.
 */
public class StubDonateDataSource implements DonateDataSource{
    List<Donate> exampleDonateTypes =new ArrayList<Donate>();
    List<Donate> testDonates = new ArrayList<Donate>();
    public StubDonateDataSource(){

          generateExampleDonates();

         for (int i = 0; i < 5; i++){
            testDonates.add(exampleDonateTypes.get(0));
            testDonates.add(exampleDonateTypes.get(1));
         }
    }

    private void generateExampleDonates(){
        Donate d1 = new Donate();
        d1.setId(0L);
        d1.setName("TestDonate");
        d1.setPicURL("https://pp.userapi.com/c403716/v403716540/49dc/7VKcDbcGR5Y.jpg");
        exampleDonateTypes.add(d1);

        Donate d2 = new Donate();
        d2.setId(1L);
        d2.setName("Котенька");
        d2.setPicURL("http://www.helpinghomelesscats.com/images/cat1.jpg");
        exampleDonateTypes.add(d2);
    }

    public Collection<Donate> getAllDonates() {
        return testDonates;
    }

    public Collection<Donate> getDonatesByType(Type t) {
        ArrayList<Donate> ret=new ArrayList<Donate>();
         if(t.getId()==-1){
            return testDonates;
        }
        for (int i = 0; i < 5; i++) {
         if(t.getId()%2==0){
             ret.add(exampleDonateTypes.get(0));
        }
        else{
              ret.add(exampleDonateTypes.get(1));
        }
        }
        return ret;
    }

    public Donate getDonateById(Long donateId) {
        return exampleDonateTypes.get(donateId.intValue());
    }
}
