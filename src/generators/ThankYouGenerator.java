package generators;

/**
 * Created with IntelliJ IDEA.
 * User: Buchina
 * Date: 08.02.2013
 * Time: 10:50:22
 */
public class ThankYouGenerator {

    public String generateThankYou(String backgroundImage, String adImage) {
        String ret;
        String head= " <head>" +
                "<link href=\"css/thankyou.css\" type=\"text/css\" rel=\"stylesheet\"/>" +
                "<link href=\"css/style.css\" type=\"text/css\" rel=\"stylesheet\"/>" +

                GA.GACode+
                "</head>";
        String template =

                        "<body><div id=\"ThankYouBar\" style=\"background-image:url(%s);\">\n" +
                        "    <div id=\"ThankYouString\">Спасибо!</div>\n" +
                        "</div>\n" +
                        "<div id='SponsorsText'>Наши спонсоры делают вашу помощь реальной:</div>" +
                        "\n" +
                        "<div id='SomeKindStuffHere'>%s</div>" +
                        "<div id='TellFriends' ><a href='#'onclick='parent.postToWall(1);'><i class=\"icon-bullhorn\"></i> Расскажи друзьям!</a></div></body>";
        ret = String.format(template, backgroundImage, adImage);
        return head+ ret;
    }
}