package pagebuild;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import generators.GA;
import generators.MenuGenerator;
import storedentities.Type;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
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

    public PageComposer(String viewer) {
        initDataSources();
        this.viewer_id = viewer;
    }

    public String getMainPage(ServletContext context) {
        try {
            InputStream layout = context.getResourceAsStream("/html/layout.html"),
                    welcome = context.getResourceAsStream("/html/welcome.html");

            Template t = new Template("Overall", new InputStreamReader(layout, "UTF-8"), new Configuration(), "UTF-8");
            Map<String, String> parameters = new HashMap<String, String>();
            parameters.put("menu", getMenu());
            parameters.put("welcome", getWelcomeScreenForNewUsers(welcome));
            parameters.put("donates", getAllDonateHTMLs());

            StringWriter stringWriter = new StringWriter();
            t.process(parameters, stringWriter);

            return stringWriter.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "<html>Sorry chief, something went wrong</html>";
        } catch (TemplateException e) {
            e.printStackTrace();
            return "<html>Sorry chief, something went wrong</html>";
        }
    }

    private String getWelcomeScreenForNewUsers(InputStream source) throws IOException, TemplateException {
        if (userClicksDataSource.existRecord(viewer_id)) return "";
        StringWriter stringWriter = new StringWriter();

        new Template("Welcome", new InputStreamReader(source, "UTF-8"), new Configuration(), "UTF-8").process(new HashMap(), stringWriter);
        return stringWriter.toString();
    }

    private String getMenu() {
        Collection<Type> types = typeDataSource.getAllTypes();
        MenuGenerator menuGenerator = new MenuGenerator();
        return menuGenerator.generateMenu(types);
    }

    private String getAllDonateHTMLs() {
        return GA.GACode + "<iframe id='myIframe' src='/RCTD/main?viewer_id=" + viewer_id + "' " +
                "width='800' height='440' style='display:none' " +
                "onload=\"processingComplete()\"" +
                " ></iframe>";
    }
}
