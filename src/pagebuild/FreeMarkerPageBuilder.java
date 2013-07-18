package pagebuild;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * UserClickData: NBuchina
 * Date: 17.06.13
 * Time: 14:59
 */
public class FreeMarkerPageBuilder {
    public FreeMarkerPageBuilder(InputStream templateInputStream, Map parameters) {
        this.templateInputStream = templateInputStream;
        this.parameters = parameters;
    }

    InputStream templateInputStream;
    Map parameters;

    public String process() throws IOException {
        templateInputStream.reset();
        Template t = new Template("Overall", new InputStreamReader(templateInputStream, "UTF-8"), new Configuration(), "UTF-8");

        StringWriter stringWriter = new StringWriter();
        try {
            t.process(parameters, stringWriter);
        } catch (TemplateException e) {
            throw new IOException("Template exception", e);
        }

        return stringWriter.toString();
    }
}
