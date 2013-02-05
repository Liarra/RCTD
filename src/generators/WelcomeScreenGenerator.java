package generators;

/**
 * Created by IntelliJ IDEA.
 * User: Buchina
 * Date: 05.02.2013
 * Time: 15:48:37
 * To change this template use File | Settings | File Templates.
 */
public class WelcomeScreenGenerator {
    String layout="<div id=\"dialog-overlay\"></div>\n" +
            "<div id=\"dialog-box\">\n" +
            "    <div class=\"dialog-content\">\n" +
            "        <div id=\"dialog-message\">%s</div>\n" +
            "        <a href=\"#\" class=\"btn_checked\" id=\"welcome_btn\" onclick=\"clickWelcome()\">%s</a>\n" +
            "    </div>\n" +
            "</div>";
    String message="Привет всем, это мишки Гамми!";
    String button_text="Понятно!";

    public String getWelcomeScreen(){
        String ret=String.format(layout,message,button_text);

        return ret;
    }
}
