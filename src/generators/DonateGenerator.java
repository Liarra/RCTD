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
    UserClicksDataSource userClicksDataSource;
    String userId;

    public DonateGenerator(UserClicksDataSource userClicksDataSource, String userId){
         this();
        this.userClicksDataSource=userClicksDataSource;
        this.userId=userId;
    }

    public DonateGenerator() {
        buttonLabel = "Помочь";
            donateHTMLEnabledTemplate = "<div class=\"donate\" style=\"background-image:url(%s);\">\n" +
                "<div class=\"hint\">%s</div>\n" +
                "<a href=\"#\" class=\"btn_checked btn_donate\" onclick=\"clickDonateButton(%s)\">"+buttonLabel+"</a>\n" +
                " </div>";

        donateHTMLDisabledTemplate = "<div class=\"donate\" style=\"background-image:url(%s);\">\n" +
                "<div class=\"hint\">%s</div>\n" +
                "<a href=\"#\" class=\"btn_unchecked btn_donate\">"+buttonLabel+"</a>\n" +
                " </div>";
    }

    public String generateDonateHTML(Donate d, boolean enabled){
        String donateName=d.getName();
        String donatePic=d.getPicURL();
        String id=String.valueOf(d.getId());

        String template=enabled?donateHTMLEnabledTemplate:donateHTMLDisabledTemplate;
        String newDonateHTML=String.format(template,donatePic,donateName,id);
        return newDonateHTML;
    }

    public String generateDonatesHTML(Collection<Donate> d){
        String ret="";

        if(userClicksDataSource==null)
        for(Donate donate:d){
            ret+=generateDonateHTML(donate,true);
        }

        else{
             for(Donate donate:d){
                 boolean isEnabled=userClicksDataSource.isAbleToClick(userId,donate);
                ret+=generateDonateHTML(donate,isEnabled);
        }
        }

        return ret;
    }


}
