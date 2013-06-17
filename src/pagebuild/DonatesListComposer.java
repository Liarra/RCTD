package pagebuild;

import storedentities.Donate;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Buchina
 * Date: 08.02.2013
 * Time: 15:02:24
 */
public class DonatesListComposer extends AbstractComposer {
    private String userId;
    private InputStream donateTemplate;

    public DonatesListComposer(String userId, InputStream donateTemplate) throws IOException {
        this.donateTemplate = donateTemplate;
        initDataSources();
        this.userId = userId;
    }

    public String getDonatesHTML() throws IOException {
        Collection<Donate> testDonates = donateDataSource.getAllDonates();
        return generateDonatesHTML(testDonates);
    }

    public String getDonatesHTML(Long typeID) throws IOException {
        Collection<Donate> donates = donateDataSource.getDonatesByTypeId(typeID);
        return generateDonatesHTML(donates);
    }

    private String generateDonatesHTML(Collection<Donate> d) throws IOException {
        String ret = "<body id='innerBody'>";

        if (userClicksDataSource == null)
            for (Donate donate : d) {
                ret += getDonateHTML(donate, true);
            }

        else {
            for (Donate donate : d) {
                boolean isEnabled = userClicksDataSource.isAbleToClick(userId, donate);
                ret += getDonateHTML(donate, isEnabled);
            }
        }

        return ret + "</body>";
    }

    private String getDonateHTML(Donate d, boolean enabled) throws IOException {
        if (d == null) throw new IllegalArgumentException("Donate must not be null!");
        if (d.getId() == null) throw new IllegalArgumentException("Donate must have an Id!");
        String donateName = d.getName();
        String donatePic = d.getPicURL();
        String id = String.valueOf(d.getId());
        String donateHome = d.getDescription();
        String btn_className = enabled ? "btn_checked" : "btn_unchecked";
        String btn_onclickFunction = enabled ? "onclick=\"clickDonateButton(" + id + ");\"" : "";

        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("id", id);
        parameters.put("donate_name", donateName);
        parameters.put("btn_className", btn_className);
        parameters.put("btn_onclickfunction", btn_onclickFunction);
        parameters.put("donate_home", donateHome == null ? "" : donateHome);
        parameters.put("donate_pic", donatePic == null ? "" : donatePic);

        return new FreeMarkerPageBuilder(donateTemplate, parameters).process();
    }
}
