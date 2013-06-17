package pagebuild;

import storedentities.Type;

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
    private InputStream menuTemplate;

    public PageComposer(String viewer, InputStream layoutTemplate, InputStream welcomeTemplate, InputStream menuTemplate) {
        this.layoutTemplate = layoutTemplate;
        this.welcomeTemplate = welcomeTemplate;
        this.menuTemplate = menuTemplate;
        initDataSources();
        this.viewer_id = viewer;
    }

    public String getMainPage() {
        try {
            Map<String, String> parameters = new HashMap<String, String>();
            parameters.put("menu", getMenu());
            parameters.put("welcome", getWelcomeScreenForNewUsers(welcomeTemplate));

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

    private String getMenu() throws IOException {
        Collection<Type> types = typeDataSource.getAllTypes();
        HashMap<String, Collection> parameters = new HashMap<String, Collection>();
        parameters.put("types", types);

        return new FreeMarkerPageBuilder(menuTemplate, parameters).process();
    }
}
