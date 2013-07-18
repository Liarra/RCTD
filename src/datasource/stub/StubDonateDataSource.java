package datasource.stub;

import datasource.DonateDataSource;
import datasource.storedentities.Donate;
import datasource.storedentities.Type;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * UserClickData: Buchina
 * Date: 25.01.2013
 * Time: 14:22:33
 */
public class StubDonateDataSource implements DonateDataSource {
    private final List<Donate> exampleDonateTypes = new ArrayList<Donate>();
    private final List<Donate> testDonates = new ArrayList<Donate>();

    public StubDonateDataSource() {

        generateExampleDonates();

        for (int i = 0; i < 5; i++) {
            testDonates.add(exampleDonateTypes.get(0));
            testDonates.add(exampleDonateTypes.get(1));
        }
    }

    private void generateExampleDonates() {
        Donate d1 = new Donate(0L,"TestDonate");
        d1.setPicURL("https://pp.userapi.com/c403716/v403716540/49dc/7VKcDbcGR5Y.jpg");
        exampleDonateTypes.add(d1);

        Donate d2 = new Donate(1L,"Котенька");
        d2.setPicURL("http://www.helpinghomelesscats.com/images/cat1.jpg");
        exampleDonateTypes.add(d2);
    }

    public Collection<Donate> getAllDonates() {
        return testDonates;
    }

    public Collection<Donate> getDonatesByType(Type t) {
        ArrayList<Donate> ret = new ArrayList<Donate>();
        if (t.getId() == -1) {
            return testDonates;
        }
        for (int i = 0; i < 5; i++) {
            if (t.getId() % 2 == 0) {
                ret.add(exampleDonateTypes.get(0));
            } else {
                ret.add(exampleDonateTypes.get(1));
            }
        }
        return ret;
    }

    public Collection<Donate> getDonatesByTypeId(Long typeId) {
        Collection<Donate> all = getAllDonates();
        Collection<Donate> ret = new ArrayList<Donate>();

        for (Donate d : all) {
            if (d.getType().getId().equals(typeId))
                ret.add(d);
        }

        return ret;
    }

    public Donate getDonateById(Long donateId) {
        return exampleDonateTypes.get(donateId.intValue());
    }
}
