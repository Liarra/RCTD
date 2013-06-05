package generators;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import storedentities.Donate;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: NBuchina
 * Date: 21.02.13
 * Time: 13:00
 */
public class DonateGeneratorTest {
    private DonateGenerator donateGenerator;

    @Before
    public void setUp() throws Exception {
        donateGenerator = new DonateGenerator();
    }

    @After
    public void tearDown() throws Exception {
        donateGenerator = null;
    }

    @Test(expected = IllegalArgumentException.class)
    public void GenerateDonateHTML_NullDonate_ExpectedIllegalArgument() throws IOException {
        donateGenerator.generateDonateHTML(null, true);
    }

    @Test
    public void GenerateDonateHTML_ExpectedNameOfTheDonateIncluded() throws IOException {
        String donateName = "Donate Name That Must Be here For Sure";
        Donate d = new Donate(1L, donateName);

        String result = donateGenerator.generateDonateHTML(d, true);
        Assert.assertTrue(result.contains(donateName));
    }

    @Test
    public void GenerateDonateHTML_ExpectedIdOfTheDonateIncluded() throws IOException {
        Long donateId = 9L;
        Donate d = new Donate(donateId, "SmNm");

        String result = donateGenerator.generateDonateHTML(d, true);
        Assert.assertTrue(result.contains(donateId.toString()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void GenerateDonateHTML_GivenDonateWithNoId_ExpectedIllegalArgumentException() throws IOException {
        Donate d = new Donate(1L, "Name");
        d.setId(null);

        donateGenerator.generateDonateHTML(d, true);
    }
@Test
    public void GenerateDonateHTML_ExpectedPictureOfTheDonateIncluded() throws IOException {
    String picURL="PictureUrlOlolo";
        Donate d=new Donate(6L,"123");
        d.setPicURL(picURL);

        String result=donateGenerator.generateDonateHTML(d,true);
        Assert.assertTrue(result.contains(picURL));
    }

    @Test
    public void GenerateDonateHTML_ExpectedHomeOfTheDonateIncluded() throws IOException {
        String home="HomeMageThing";
        Donate d=new Donate(6L,"123");
        d.setDescription(home);

        String result=donateGenerator.generateDonateHTML(d,true);
        Assert.assertTrue(result.contains(home));
    }
}
