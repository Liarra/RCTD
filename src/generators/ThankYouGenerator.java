package generators;

/**
 * Created by IntelliJ IDEA.
 * User: Buchina
 * Date: 08.02.2013
 * Time: 10:50:22
 * To change this template use File | Settings | File Templates.
 */
public class ThankYouGenerator {
    public String template="<link href=\"css/thankyou.css\" type=\"text/css\" rel=\"stylesheet\"/>\n" +
            "<div id=\"ThankYouBar\" style=\"background-image:url(%s);\">\n" +
            "    <div id=\"ThankYouString\">Спасибо!</div>\n" +
            "</div>\n" +
            "\n" +
            "<div id=\"AdPics\">%s</div>";

    public String generateThankYou(String backgroundImage,String adImage){
        String ret;

        ret=String.format(template,backgroundImage,adImage);
        return ret;
    }
}
