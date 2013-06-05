package generators;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import storedentities.Type;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: NBuchina
 * Date: 05.06.13
 * Time: 12:41
 */
public class MenuGeneratorTest {
    private MenuGenerator menuGenerator;

    @Before
    public void setup() {
        menuGenerator = new MenuGenerator();
    }

    @After
    public void teardown() {
        menuGenerator = null;
    }

    private Collection<Type> getExampleTypes(int size) {
        ArrayList<Type> ret = new ArrayList<Type>();
        for (int i = 0; i < size; i++)
            ret.add(new Type(new Long(i++), "Type" + i));
        return ret;
    }

    @Test
    public void generateMenu_ContainsAllTheMenuItemsNames() {
        Collection<Type> exampleTypes = getExampleTypes(5);
        String menu = menuGenerator.generateMenu(exampleTypes);

        for (Type t : exampleTypes) {
            Assert.assertTrue(menu.contains(t.getName()));
        }
    }

    @Test
    public void generateMenu_ContainsAllTheMenuItemsIds() {
        Collection<Type> exampleTypes = getExampleTypes(5);
        String menu = menuGenerator.generateMenu(exampleTypes);

        for (Type t : exampleTypes) {
            Assert.assertTrue(menu.contains(t.getId().toString()));
        }
    }

    @Test
    public void generateMenu_ContainsEverythingMenuItem() {
        Long EMenuId = -1L;
        String EMenuText = "Все";
        Collection<Type> exampleTypes = getExampleTypes(5);
        String menu = menuGenerator.generateMenu(exampleTypes);

        Assert.assertTrue(menu.contains(EMenuText));
        Assert.assertTrue(menu.contains(EMenuId.toString()));
    }

    @Test
    public void generateMenu_ContainsOnlyOneEnabledItem() {
        Collection<Type> exampleTypes = getExampleTypes(5);
        String menu = menuGenerator.generateMenu(exampleTypes);

        Assert.assertTrue(menu.indexOf("btn_checked") == menu.lastIndexOf("btn_checked"));
    }
}
