package pagebuild;

import storedentities.Ad;
import storedentities.Donate;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Buchina
 * Date: 08.02.2013
 * Time: 16:00:39
 */
public class ThankYouComposer extends AbstractComposer {
    private final Long donateId;
    private InputStream template;

    public ThankYouComposer(Long donateId, InputStream template) {
        initDataSources();
        this.template = template;
        this.donateId = donateId;
    }

    public String composeThankYouPage() {
        Ad ad = adDataSource.getAdbyId(1L);
        String adH = ad.getHTML();

        Donate donate = donateDataSource.getDonateById(donateId);

        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("bg_image", donate.getPicURL());
        parameters.put("ad", adH);

        return composePageFromTemplateInputStream(parameters);
    }

    private String composePageFromTemplateInputStream(Map<String, String> parameters) {
        try {
            return new FreeMarkerPageBuilder(template, parameters).process();
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }

    public void submitClick(String user) {
        Donate donate = donateDataSource.getDonateById(donateId);

        if (!userClicksDataSource.isAbleToClick(user, donate))
            return;

        userClicksDataSource.click(user, donate);
    }
}
