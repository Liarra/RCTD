package datasource.stub;

import datasource.AdDataSource;
import datasource.storedentities.Ad;

/**
 * Created with IntelliJ IDEA.
 * UserClickData: Buchina
 * Date: 28.01.2013
 * Time: 16:57:42
 */
public class StubAdDataSource implements AdDataSource{
    public Ad getAdById(Long id) {
        Ad ad=new Ad();
//        ad.setHTML("<img src='img/1.jpg' />");
ad.setHTML("<div style=\"background: #cd5c5c; color: #ffffff; padding:15px;\">\n" +
        "    Привет! Спасибо, что зашли! <br/>\n" +
        "    Как видите, сейчас мы работаем в режиме тестирования. Скоро в приложении появятся настоящие добрые дела и ваши клики\n" +
        "    превратятся в реальные пожертвования!<br/>\n" +
        "    А пока вы можете зайти в нашу группу, оставить отзыв, сообщить об ошибке или подсказать потрясающую идею.\n" +
        "    Оставайтесь с нами! <3 \n" +
        "</div>");
        ad.setScript("alert(\"Ok ad\");");
        return ad;
    }
}
