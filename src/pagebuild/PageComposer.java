package pagebuild;

import generators.GA;
import generators.MenuGenerator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import storedentities.Type;

import javax.servlet.ServletContext;
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
    private Document page;

    public PageComposer(String viewer) {
        initDataSources();
        this.viewer_id = viewer;
    }

    public String getMainPage(ServletContext context) {
        try {
            InputStream layout = context.getResourceAsStream("/html/layout.html"),
                    welcome = context.getResourceAsStream("/html/welcome.html");

            page = getHTMLFromFile(layout);
            addWelcomeScreenForNewUsers(welcome);
            addMenu();
            addAllDonateHTMLs();
            return page.html();
        } catch (IOException e) {
            e.printStackTrace();
            return "<html>Sorry chief, something went wrong</html>";
        }
    }

    private Document getHTMLFromFile(InputStream source) throws IOException {
        return Jsoup.parse(source, "UTF-8", "http://example.com/");
    }

    private void addWelcomeScreenForNewUsers(InputStream source) throws IOException {
        if (userClicksDataSource.existRecord(viewer_id)) return;

        String welcomeScreen = Jsoup.parse(source, "UTF-8", "http://example.com/").html();
        Element body = page.getElementsByTag("body").first();
        body.append(welcomeScreen);
    }

    private void addMenu() {
        String categories = getCategoriesHTML();
        Element menuContainer = page.getElementById("menucell");
        menuContainer.append(categories);
    }

    private void addAllDonateHTMLs() {
        String donateHTMLs = GA.GACode + "<iframe id='myIframe' src='/RCTD/main?viewer_id=" + viewer_id + "' " +
                "width='800' height='440' style='display:compact' " +
                "onload=\"processingComplete()\"" +
                " ></iframe>";
        Element menuContainer = page.getElementById("content");
        menuContainer.append(donateHTMLs);
    }

    private String getCategoriesHTML() {
        Collection<Type> types = typeDataSource.getAllTypes();
        MenuGenerator menuGenerator = new MenuGenerator();
        return menuGenerator.generateMenu(types);
    }

}
