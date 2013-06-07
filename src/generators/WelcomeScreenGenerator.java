package generators;

/**
 * Created with IntelliJ IDEA.
 * User: Buchina
 * Date: 05.02.2013
 * Time: 15:48:37
 */
@SuppressWarnings("FieldCanBeLocal")
public class WelcomeScreenGenerator {
    private final String layout =
            "<div id=\"dialog-overlay\"></div>\n" +
            "<div id=\"dialog-box\">\n" +
            "    <div class=\"dialog-content\">\n" +
            "        <div id=\"dialog-message\">%s</div>\n" +
            "        <a href=\"#\" class=\"btn_checked\" id=\"welcome_btn\" onclick=\"clickWelcome()\">%s</a>\n" +
            "    </div>\n" +
            "</div>";

    private final String message = "<h1>Добро пожаловать!</h1>" +
            "Добрая кнопка - это приложение для генерации благотворительных пожертвований. Просто выберите, кому вы хотите помочь, и нажмите на кнопку!" +
            "<div style=\"padding-left:10px;\">" +
            "<h2><i class=\"icon-money\"> </i>Это абсолютно БЕСПЛАТНО!</h2> " +
            "За вас платят владельцы рекламных объявлений, размещаемых в приложении." +
            "<h2><i class=\"icon-time\"></i> Не требует времени и сил</h2>" +
            "На одну и ту же кнопку можно нажимать раз в день." +
            "<h2><i class=\"icon-heart\"></i> Простой способ помочь</h2> " +
            "Никаких проблем с денежными переводами и платёжными системами!" +
            "</div>" +
            "<br />Начните с выбора подходящей вам категории (меню сверху) или узнайте больше из нашего FAQ (кнопка с вопросительным знаком [?])";

    public String getWelcomeScreen() {
        String button_text = "Понятно!";
        return String.format(layout, message, button_text);
    }
}
