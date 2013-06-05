package generators;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import storedentities.Donate;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Buchina
 * Date: 11.01.2013
 * Time: 15:31:17
 */
public class DonateGenerator {
    private Template enabledTemplate;

    public DonateGenerator() throws IOException {
        String buttonLabel = "Помочь";
        String donateHTMLEnabledTemplate =
                "<div class=\"donate\" style=\"background-image:url(${donate_pic});\" " +
                        "onmouseover=\"mopen('${id}_hint')\"\n" +
                        "onmouseout=\"mclosetime()\">\n" +
                        "<div class=\"hint\" id=\"${id}_hint\">" +
                        "${donate_name} " +
                        "<div class=\"donate-home-link\"><a href=${donate_home} target=\"_blank\"><i class=\"icon-home\"></i></a></div>" +
                        "</div>\n" +

                        "<a class=\"${btn_className} btn_donate\" ${btn_onclickfunction}>" + buttonLabel + "</a>\n" +
                        " </div>";

        enabledTemplate = new Template("enabledTemplate", new StringReader(donateHTMLEnabledTemplate), new Configuration());
    }

    public String generateDonateHTML(Donate d, boolean enabled) throws IOException {
        if(d==null)throw new IllegalArgumentException("Donate must not be null!");
        if(d.getId()==null)throw new IllegalArgumentException("Donate must have an Id!");
        String donateName = d.getName();
        String donatePic = d.getPicURL();
        String id = String.valueOf(d.getId());
        String donateHome = d.getDescription();
        String btn_className = enabled ? "btn_checked" : "btn_unchecked";
        String btn_onclickFunction = enabled ? "onclick=\"clickDonateButton(" + id + ");\"" : "";

        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("id", id);
        parameters.put("donate_name", donateName);
        parameters.put("btn_className", btn_className);
        parameters.put("btn_onclickfunction", btn_onclickFunction);
        parameters.put("donate_home", donateHome==null?"":donateHome);
        parameters.put("donate_pic", donatePic==null?"":donatePic);

        String newDonateHTML;
        StringWriter stringWriter = new StringWriter();

        Template usedTemplate = enabledTemplate;

        try {
            usedTemplate.process(parameters, stringWriter);
        } catch (TemplateException e) {
            throw new IOException("Unable to process template");
        }

        newDonateHTML = stringWriter.toString();
        return newDonateHTML;
    }
}
