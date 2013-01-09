package generators;

import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;

import java.io.File;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Buchina
 * Date: 09.01.2013
 * Time: 13:02:01
 * To change this template use File | Settings | File Templates.
 */
public class PageGenerator {
    String defString="<html>Sorry chief</html>";
    String basePagePath="C:\\Documents and Settings\\Buchina\\My Documents\\Develop\\own\\RCTD\\web\\html\\layout.html";

    public String getMainScreen(){
        return defString;
    }

    public String getAboutScreen(){
        return  defString;
    }

    public String getThankYouScreen(int donateId){
        return  defString;
    }

    private Document getBasePage() throws IOException {
        Document basePage=getHTMLFromFile(basePagePath);
        return basePage;
    }

    private void addMenu(){

    }

    private void addAboutBtn(){

    }

    private void addReturnButton(){
        
    }

    private Document getHTMLFromFile(String address) throws IOException {
        File input = new File(address);
        Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");

        return doc;
    }
}
