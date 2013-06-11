package generators;

import storedentities.Type;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Buchina
 * Date: 11.01.2013
 * Time: 16:09:39
 */
public class MenuGenerator {

    String generateMenuItem(Type t) {
        String menuTemplate = "<a href=\"#\" class=\"btn_unchecked\" id=\"%s\", onclick=\"clickMenu(%s,'%s'); proposeToInstall();\">%s</a>";
        String elementID = "menu" + t.getId();

        return String.format(menuTemplate, elementID, t.getId(), elementID, t.getName());
    }

    String generateAllMenuItem() {
        String elementID = "menuAll";
        String mainMenuTemplate = "<a href=\"#\" class=\"btn_checked\" id=\"%s\", onclick=\"clickMenu(%s,'%s'); proposeToInstall();\">%s</a>";
        return String.format(mainMenuTemplate, elementID, -1, elementID, "Все");
    }

    public String generateMenu(Collection<Type> types) {
        String menu = generateAllMenuItem();
        if(types==null)
            throw new IllegalArgumentException("types collection must not be null!");
        for (Type type : types)
            menu += generateMenuItem(type);

        return menu;
    }
}
