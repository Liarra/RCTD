package pagebuild;

import generators.ThankYouGenerator;
import storedentities.Ad;
import storedentities.Donate;

/**
 * Created by IntelliJ IDEA.
 * User: Buchina
 * Date: 08.02.2013
 * Time: 16:00:39
 * To change this template use File | Settings | File Templates.
 */
public class ThankYouComposer extends AbstractComposer{

    public ThankYouComposer(Long donateId) {
        initDataSources();
        this.donateId = donateId;
    }

    Long donateId;
      public String composeThankYouPage(){
          Ad ad= adDataSource.getAdbyId(1L);
            String adH =ad.getHTML();
            String adS=ad.getScript();

          Donate donate=donateDataSource.getDonateById(donateId);
            String ThankYouHTML=new ThankYouGenerator().generateThankYou(donate.getPicURL(),adH);

          return ThankYouHTML;
      }

    public void submitClick(String user){
        Donate donate=donateDataSource.getDonateById(donateId);

        if(!userClicksDataSource.isAbleToClick(user,donate))
                return;

        userClicksDataSource.click(user,donate);

    }
}
