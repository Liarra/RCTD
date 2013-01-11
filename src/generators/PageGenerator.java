package generators;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.Jsoup;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

import storedentities.Donate;
import storedentities.Type;

/**
 * Created by IntelliJ IDEA.
 * User: Buchina
 * Date: 09.01.2013
 * Time: 13:02:01
 * To change this template use File | Settings | File Templates.
 */
public class PageGenerator {
    String defString = "<html>Sorry chief, something went wrong</html>";
    String basePagePath = "C:\\Documents and Settings\\Buchina\\My Documents\\Develop\\own\\RCTD\\web\\html\\layout.html";

    public String getMainPage() {
        try {
            Document basePage = getBasePage();
            addMenu(basePage);
            addAllDonateHTMLs(basePage);
            return basePage.html();
        } catch (IOException e) {
            e.printStackTrace();
            return defString;
        }

    }

    public String getMainData(){
          return getDonates();
    }

    public String getAboutContent() {
        return defString;
    }

    public String getThankYouContent(int donateId) {
        return defString;
    }


    private Document getBasePage() throws IOException {
        Document basePage = getHTMLFromFile(basePagePath);
        return basePage;
    }

    private void addMenu(Document doc) {
        String categories = getCategories();
        Element menuContainer = doc.getElementById("menucell");

        menuContainer.append(categories);
    }

    private void addAllDonateHTMLs(Document doc) {
        String donateHTMLs = getDonates();
        Element menuContainer = doc.getElementById("content");
            menuContainer.append(donateHTMLs);
    }

    private void addAboutBtn() {

    }

    private void addReturnButton() {

    }

    private Document getHTMLFromFile(String address) throws IOException {
        File input = new File(address);
        Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");

        return doc;
    }

    private String getCategories() {
        //Забирать из БД
        ArrayList<Type> arr = new ArrayList<Type>();
        Type t1 = new Type();
        t1.setName("Все");
        t1.setId(-1L);
        arr.add(t1);
        Type t2 = new Type();
        t2.setName("Животные");
        t2.setId(1L);
        arr.add(t2);
        Type t3 = new Type();
        t3.setName("Дети");
        t3.setId(2L);
        arr.add(t3);
        Type t4 = new Type();
        t4.setId(3L);
        t4.setName("Дома престарелых");
        arr.add(t4);

        MenuGenerator menuGenerator = new MenuGenerator();

        return menuGenerator.generateMenu(arr);
//        return new String[]{"Все","Животные","Дети", "Дома престарелых", "Музеи"};
    }

    private String getDonates() {
        Donate d1 = new Donate();
        d1.setId(12L);
        d1.setName("TestDonate");
        d1.setPicURL("https://pp.userapi.com/c403716/v403716540/49dc/7VKcDbcGR5Y.jpg");
        List<Donate> testDonates = new ArrayList<Donate>();
        for (int i = 0; i < 20; i++)
            testDonates.add(d1);

        DonateGenerator gen = new DonateGenerator();

        return gen.generateDonatesHTML(testDonates);
    }
}
