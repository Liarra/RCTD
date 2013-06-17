package pagebuild;

import generators.GA;
import generators.MenuGenerator;
import storedentities.Type;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by IntelliJ IDEA.
 * User: Buchina
 * Date: 09.01.2013
 * Time: 13:02:01
 */
public class PageComposer extends AbstractComposer {
    private String viewer_id = "";
    private InputStream layoutTemplate;
    private InputStream welcomeTemplate;

    public PageComposer(String viewer, InputStream layoutTemplate, InputStream welcomeTemplate) {
        this.layoutTemplate = layoutTemplate;
        this.welcomeTemplate = welcomeTemplate;
        initDataSources();
        this.viewer_id = viewer;
    }

    public String getMainPage(ServletContext context) {
        try {

            Map<String, String> parameters = new HashMap<String, String>();
            parameters.put("menu", getMenu());
            parameters.put("welcome", getWelcomeScreenForNewUsers(welcomeTemplate));
            parameters.put("donates", getAllDonateHTMLs());

            return new FreeMarkerPageBuilder(layoutTemplate, parameters).process();
        } catch (IOException e) {
            e.printStackTrace();
            return "<html>Sorry chief, something went wrong</html>";
        }
    }

    private String getWelcomeScreenForNewUsers(InputStream source) throws IOException {
        if (userClicksDataSource.existRecord(viewer_id)) return "";
        return new FreeMarkerPageBuilder(source, new HashMap<String, String>()).process();
    }

    private String getMenu() {
        Collection<Type> types = typeDataSource.getAllTypes();
        MenuGenerator menuGenerator = new MenuGenerator();
        return menuGenerator.generateMenu(types);
    }

    private String getAllDonateHTMLs() {
        return GA.GACode + "<iframe id='myIframe' src='/RCTD/main?viewer_id=" + viewer_id + "' " +
                "width='800' height='440' " +
                "onload=\"processingComplete()\"" +
                " ></iframe>";
    }
}
