package generators;

import storedentities.Donate;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: Buchina
 * Date: 11.01.2013
 * Time: 15:31:17
 * To change this template use File | Settings | File Templates.
 */
public class DonateGenerator {
    String buttonLabel;
    String donateHTMLTemplate;

    public DonateGenerator() {
        try{
        buttonLabel = "Помочь";
            donateHTMLTemplate = "<div class=\"donate\" style=\"background:url(%s);\">\n" +
                "<div class=\"hint\">%s</div>\n" +
                "<a href=\"#\" class=\"btn_checked btn_donate\" onclick=\"clickDonateButton(%s)\">"+buttonLabel+"</a>\n" +
                " </div>";
        }catch (Exception e){}

    }

    public String generateDonateHTML(Donate d){
        String donateName=d.getName();
        String donatePic=d.getPicURL();
        String id=String.valueOf(d.getId());

        String newDonateHTML=String.format(donateHTMLTemplate,donatePic,donateName,id);
        return newDonateHTML;
    }

    public String generateDonatesHTML(Collection<Donate> d){
        String ret="";

        for(Donate donate:d){
            ret+=generateDonateHTML(donate);
        }

        return ret;
    }


}
