package pagebuild;

import generators.GA;
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
 */
public class PageComposer extends AbstractComposer {
    private String viewer_id = "";

    public PageComposer(String viewer) {
        initDataSources();
        this.viewer_id = viewer;
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
            return "<html>Sorry chief, something went wrong</html>";
        }
    }

    private void addWelcomeScreenForNewUsers(Document doc) {
        if (userClicksDataSource.existRecord(viewer_id)) return;

        String welcomeScreen = new WelcomeScreenGenerator().getWelcomeScreen();
        Element body = doc.getElementsByTag("body").first();
        body.append(welcomeScreen);
    }

    private void addMenu(Document doc) {
        String categories = getCategoriesHTML();
        Element menuContainer = doc.getElementById("menucell");
        menuContainer.append(categories);
    }

    private void addAllDonateHTMLs(Document doc) {
        String donateHTMLs = GA.GACode+"<iframe id='myIframe' src='/RCTD/main?viewer_id=" + viewer_id + "' " +
                "width='800' height='440' style='border: transparent 0px;'" +
                "onload=\"processingComplete()\"" +
                " ></iframe>";
        Element menuContainer = doc.getElementById("content");
        menuContainer.append(donateHTMLs);
    }

    private Document getHTMLFromFile(InputStream source) throws IOException {
        return Jsoup.parse(source, "UTF-8", "http://example.com/");
    }

    private String getCategoriesHTML() {
        Collection<Type> types = typeDataSource.getAllTypes();
        MenuGenerator menuGenerator = new MenuGenerator();
        return menuGenerator.generateMenu(types);
    }

}
