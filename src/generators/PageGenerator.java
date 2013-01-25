package generators;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.Jsoup;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import storedentities.Donate;
import storedentities.Type;
import datasource.TypeDataSource;
import datasource.DonateDataSource;
import datasource.stub.StubTypeDataSource;
import datasource.stub.StubDonateDataSource;

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
    TypeDataSource typeDataSource=new StubTypeDataSource();
    DonateDataSource donateDataSource=new StubDonateDataSource();

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

    public String getAboutContent() {
        return defString;
    }

     private Document getBasePage() throws IOException {
        Document basePage = getHTMLFromFile(basePagePath);
        return basePage;
    }

    private void addMenu(Document doc) {
        String categories = getCategoriesHTML();
        Element menuContainer = doc.getElementById("menucell");
        menuContainer.append(categories);
    }

    private void addAllDonateHTMLs(Document doc) {
        String donateHTMLs = getDonatesHTML();
        Element menuContainer = doc.getElementById("content");
            menuContainer.append(donateHTMLs);
    }


    private Document getHTMLFromFile(String address) throws IOException {
        File input = new File(address);
        Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");

        return doc;
    }

    private String getCategoriesHTML() {
          Collection<Type> types=typeDataSource.getAllTypes();
        MenuGenerator menuGenerator = new MenuGenerator();
        return menuGenerator.generateMenu(types);
    }

    public String getDonatesHTML() {
        Collection<Donate> testDonates=donateDataSource.getAllDonates();

        DonateGenerator gen = new DonateGenerator();
        return gen.generateDonatesHTML(testDonates);
    }

    public String getDonatesHTML(Long typeID){
         Type type=typeDataSource.getTypeById(typeID);
        Collection<Donate> donates=donateDataSource.getDonatesByType(type);

        DonateGenerator gen = new DonateGenerator();
        return gen.generateDonatesHTML(donates);
    }

    public String getDonatePicAddress(Long donateID){
        Donate don=donateDataSource.getDonateById(donateID);
        return don.getPicURL();
    }
}
