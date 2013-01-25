package generators;

import storedentities.Type;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: Buchina
 * Date: 11.01.2013
 * Time: 16:09:39
 * To change this template use File | Settings | File Templates.
 */
public class MenuGenerator {
    String menuTemplate="<a href=\"#\" class=\"btn_unchecked\" id=\"%s\", onclick=\"clickMenu(%s,'%s')\">%s</a>";

    public String generateMenuItem(Type t){
        String elementID="menu"+t.getId();
        String newMenuItem=String.format(menuTemplate,elementID,t.getId(),elementID,t.getName());

        return newMenuItem;
    }

    public String generateAllMenuItem(){
        String elementID="menuAll";
        String mainMenuTemplate="<a href=\"#\" class=\"btn_checked\" id=\"%s\", onclick=\"clickMenu(%s,'%s')\">%s</a>";
        String newMenuItem=String.format(mainMenuTemplate,elementID,-1,elementID,"Все");
        return newMenuItem;
    }

    public String generateMenu(Collection<Type> types){
        String menu=generateAllMenuItem();

        for(Type type:types)
        menu+=generateMenuItem(type);

        return menu;
    }
}
