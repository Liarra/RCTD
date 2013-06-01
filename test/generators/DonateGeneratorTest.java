package generators;

import org.junit.After;
import org.junit.Before;
import org.junit.*;

/**
 * Created by IntelliJ IDEA.
 * User: NBuchina
 * Date: 21.02.13
 * Time: 13:00
 * To change this template use File | Settings | File Templates.
 */
public class DonateGeneratorTest {
    private DonateGenerator donateGenerator;
    @Before
    public void setUp() throws Exception {
         donateGenerator=new DonateGenerator();
    }

    @After
    public void tearDown() throws Exception {
           donateGenerator=null;
    }

    @Test
    public void GenerateDonateHTML_NullDonete_ExpectedNPE(){

    }
}
