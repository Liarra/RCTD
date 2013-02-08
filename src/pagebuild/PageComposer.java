package pagebuild;

import generators.MenuGenerator;
import generators.WelcomeScreenGenerator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import storedentities.Type;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: Buchina
 * Date: 09.01.2013
 * Time: 13:02:01
 * To change this template use File | Settings | File Templates.
 */
public class PageComposer extends AbstractComposer{
    String defString = "<html>Sorry chief, something went wrong</html>";
    String basePagePath = "/html/layout.html";
    String viewer_id="";

    public PageComposer(String viewer) {
        initDataSources();
        this.viewer_id=viewer;
    }


    public String getAboutContent() {
        return defString;
    }

    public String getMainPage(InputStream context) {
        try {
            Document basePage = getHTMLFromFile(context);
            addWelcomeScreenForNewUsers(basePage);
            addMenu(basePage);
            addAllDonateHTMLs(basePage);
            return basePage.html();
        } catch (IOException e) {
            e.printStackTrace();
            return defString;
        }

    }

    private void addWelcomeScreenForNewUsers(Document doc) {
         String welcomeScreen=new WelcomeScreenGenerator().getWelcomeScreen();
            Element body = doc.getElementsByTag("body").first();
        body.append(welcomeScreen);

    }

    private void addMenu(Document doc) {
        String categories = getCategoriesHTML();
        Element menuContainer = doc.getElementById("menucell");
        menuContainer.append(categories);
    }

    private void addAllDonateHTMLs(Document doc) {
        String donateHTMLs = new DonatesListComposer(viewer_id).getDonatesHTML();
        Element menuContainer = doc.getElementById("content");
        menuContainer.append(donateHTMLs);
    }

    private Document getHTMLFromFile(InputStream source) throws IOException {
        Document doc=Jsoup.parse(source, "UTF-8", "http://example.com/");
        return doc;
    }

    private String getCategoriesHTML() {
        Collection<Type> types=typeDataSource.getAllTypes();
        MenuGenerator menuGenerator = new MenuGenerator();
        return menuGenerator.generateMenu(types);
    }

}
