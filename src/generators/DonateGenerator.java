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
    private Template enabledTemplate, disabledTemplate;

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

                        "<a href=\"#\" class=\"btn_checked btn_donate\" onclick=\"clickDonateButton(${id})\">" + buttonLabel + "</a>\n" +
                        " </div>";

        String donateHTMLDisabledTemplate =
                "<div class=\"donate\" style=\"background-image:url(${donate_pic});\" " +
                        "onmouseover=\"mopen('${id}_hint')\"\n" +
                        "onmouseout=\"mclosetime()\">\n" +
                        "<div class=\"hint\" id=\"${id}_hint\">" +
                        "${donate_name} " +
                        "<div class=\"donate-home-link\"><a href=${donate_home} target=\"_blank\"><i class=\"icon-home\"></i></a></div>" +
                        "</div>\n" +
                        "<a href=\"#\" class=\"btn_unchecked btn_donate\">" + buttonLabel + "</a>\n" +
                        " </div>";


        enabledTemplate = new Template("enabledTemplate", new StringReader(donateHTMLEnabledTemplate), new Configuration());
        disabledTemplate = new Template("disabledTemplate", new StringReader(donateHTMLDisabledTemplate), new Configuration());
    }

    public String generateDonateHTML(Donate d, boolean enabled) throws IOException {
        String donateName = d.getName();
        String donatePic = d.getPicURL();
        String id = String.valueOf(d.getId());
        String donateHome = d.getDescription();

        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("donate_pic", donatePic);
        parameters.put("id", id);
        parameters.put("donate_name", donateName);
        parameters.put("donate_home", donateHome);

        String newDonateHTML;
        StringWriter stringWriter = new StringWriter();

        Template usedTemplate = enabled ? enabledTemplate : disabledTemplate;

        try {
            usedTemplate.process(parameters, stringWriter);
        } catch (TemplateException e) {
            throw new IOException("Unable to process template");
        }

        newDonateHTML = stringWriter.toString();
        return newDonateHTML;
    }
}
