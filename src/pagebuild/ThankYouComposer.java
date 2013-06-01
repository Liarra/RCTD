package pagebuild;

import generators.ThankYouGenerator;
import storedentities.Ad;
import storedentities.Donate;

/**
 * Created with IntelliJ IDEA.
 * User: Buchina
 * Date: 08.02.2013
 * Time: 16:00:39
 */
public class ThankYouComposer extends AbstractComposer{

    public ThankYouComposer(Long donateId) {
        initDataSources();
        this.donateId = donateId;
    }

    private final Long donateId;
      public String composeThankYouPage(){
          Ad ad= adDataSource.getAdbyId(1L);
            String adH =ad.getHTML();

          Donate donate=donateDataSource.getDonateById(donateId);
          return new ThankYouGenerator().generateThankYou(donate.getPicURL(),adH);
      }

    public void submitClick(String user){
        Donate donate=donateDataSource.getDonateById(donateId);

        if(!userClicksDataSource.isAbleToClick(user,donate))
                return;

        userClicksDataSource.click(user,donate);

    }
}
