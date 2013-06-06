package pagebuild;

import generators.DonateGenerator;
import generators.GA;
import storedentities.Donate;

import java.io.IOException;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Buchina
 * Date: 08.02.2013
 * Time: 15:02:24
 */
public class DonatesListComposer extends AbstractComposer {
    private DonateGenerator gen;
    private String userId;

    public DonatesListComposer(String userId) throws IOException {
        initDataSources();
        gen = new DonateGenerator();
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
                ret += gen.generateDonateHTML(donate, true);
            }

        else {
            for (Donate donate : d) {
                boolean isEnabled = userClicksDataSource.isAbleToClick(userId, donate);
                ret += gen.generateDonateHTML(donate, isEnabled);
            }
        }

        return ret + "</body>";
    }
}
