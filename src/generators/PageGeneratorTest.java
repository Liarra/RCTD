package generators;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.fail;

/**
 * Created by IntelliJ IDEA.
 * User: Buchina
 * Date: 09.01.2013
 * Time: 13:39:03
 * To change this template use File | Settings | File Templates.
 */
public class PageGeneratorTest {
    PageGenerator gen;
    @Before
    public void setUp() {
         gen=new PageGenerator();
        // Add your code here
    }

    @Test
    public void testGetMainScreenMenu() {
        String result=gen.getMainPage();
        if(!result.contains("Дома престарелых"))
            fail("Нету нужной менюшеи");
        System.out.println(result);
        // Add your code here
    }

    @Test
    public void testGetAboutScreen() {
        // Add your code here
    }

    @Test
    public void testGetThankYouScreen() {
        // Add your code here
    }
}
