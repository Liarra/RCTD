package generators;

import storedentities.Donate;

import java.util.Collection;

import datasource.UserClicksDataSource;

/**
 * Created by IntelliJ IDEA.
 * User: Buchina
 * Date: 11.01.2013
 * Time: 15:31:17
 * To change this template use File | Settings | File Templates.
 */
public class DonateGenerator {
    String buttonLabel;
    String donateHTMLEnabledTemplate, donateHTMLDisabledTemplate;
    String userId;

    public DonateGenerator(String userId){
        this();
        this.userId=userId;
    }

    public DonateGenerator() {
        buttonLabel = "Помочь";
            donateHTMLEnabledTemplate =
                    "<div class=\"donate\" style=\"background-image:url(%s);\" " +
                    "onmouseover=\"mopen('%s_hint')\"\n" +
                    "onmouseout=\"mclosetime()\">\n" +
                "<div class=\"hint\" id=\"%s_hint\">" +
                    "%s " +
                    "<div class=\"donate-home-link\"><a href=%s target=\"_blank\"><i class=\"icon-home\"></i></a></div>" +
                    "</div>\n" +
                "<a href=\"#\" class=\"btn_checked btn_donate\" onclick=\"clickDonateButton(%s)\">"+buttonLabel+"</a>\n" +
                " </div>";

        donateHTMLDisabledTemplate =
                "<div class=\"donate\" style=\"background-image:url(%s);\"" +
                        "onmouseover=\"mopen('%s_hint')\"\n" +
                        "onmouseout=\"mclosetime()\">\n" +
                "<div class=\"hint\" id=\"%s_hint\">" +
                "%s" +
                 "<div class=\"donate-home-link\"><a href=%s target=\"_blank\"><i class=\"icon-home\"></i></a></div>" +
                "</div>\n" +
                "<a href=\"#\" class=\"btn_unchecked btn_donate\">"+buttonLabel+"</a>\n" +
                " </div>";
    }

    public String generateDonateHTML(Donate d, boolean enabled){
        String donateName=d.getName();
        String donatePic=d.getPicURL();
        String id=String.valueOf(d.getId());
        String donateHome=d.getDescription();

        String template=enabled?donateHTMLEnabledTemplate:donateHTMLDisabledTemplate;
        String newDonateHTML=String.format(template,donatePic,id,id,donateName,donateHome,id);
        return newDonateHTML;
    }




}
