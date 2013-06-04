package generators;

/**
 * Created with IntelliJ IDEA.
 * User: Buchina
 * Date: 08.02.2013
 * Time: 10:50:22
 */
public class ThankYouGenerator {

    public String generateThankYou(String backgroundImage,String adImage){
        String ret;

        String template =
                "<link href=\"css/thankyou.css\" type=\"text/css\" rel=\"stylesheet\"/>\n" +

                "<div id=\"ThankYouBar\" style=\"background-image:url(%s);\">\n" +
                "    <div id=\"ThankYouString\">Спасибо!</div>\n" +
                "</div>\n" +
                "\n" +
                "<div id=\"AdPics\">%s</div>" +
                        "<a href=# onclick='parent.postToWall(1)'>123</a>";
        ret=String.format(template,backgroundImage,adImage);
        return ret;
    }
}
