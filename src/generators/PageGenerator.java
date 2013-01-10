package generators;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
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
    String defString="<html>Sorry chief, something went wrong</html>";
    String basePagePath="C:\\Documents and Settings\\Buchina\\My Documents\\Develop\\own\\RCTD\\web\\html\\layout.html";

    public String getMainScreen(){
        try{
            Document basePage=getBasePage();
            addMenu(basePage);
            return basePage.html();
        }catch(IOException e){
            e.printStackTrace();
             return defString;
        }

    }

    public String getAboutContent(){
        return  defString;
    }

    public String getThankYouContent(int donateId){
        return  defString;
    }



    private Document getBasePage() throws IOException {
        Document basePage=getHTMLFromFile(basePagePath);
        return basePage;
    }

    private void addMenu(Document doc){
        String[] categories=getCategories();
        Element menuContainer=doc.getElementById("buttonscell");

        for(int i=0;i<categories.length;i++){
            menuContainer.append("<a href=\"#\" class=\"btn_unchecked\">"+categories[i]+"</a>");
        }
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

    private String[] getCategories(){
        //Забирать из БД
        return new String[]{"Все","Животные","Дети", "Дома престарелых", "Музеи"};
    }
}
