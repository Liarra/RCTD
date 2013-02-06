package datasource.xml;

import datasource.DonateDataSource;
import datasource.TypeDataSource;
import datasource.stub.StubDonateDataSource;
import datasource.stub.StubTypeDataSource;

import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: Buchina
 * Date: 31.01.2013
 * Time: 13:08:23
 * To change this template use File | Settings | File Templates.
 */
public class XmlDataSourcesRepository {
    static private String filename=  "testdonates.xml";
    static InputStream storageFile;
    public XmlDataSourcesRepository(){
        try {
            preloadData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private synchronized void preloadData() throws IOException {
        InputStream xmlFile=XmlDataSourcesRepository.class.getResourceAsStream(filename);
        BufferedReader br=new BufferedReader(new InputStreamReader(xmlFile));
         String tmp;
          String fileContent="";

         tmp = br.readLine(); // read first line of file.

            while (tmp != null) { // read a line until end of file.
                fileContent = fileContent + tmp; //Append the contents of the file to a string to be replaced and split out later.
                tmp = br.readLine();
            }

            br.close();

        storageFile=new ByteArrayInputStream(fileContent.getBytes());
        XmlDonateDataSourceInstance=new XmlDonatedataSource(storageFile);
        XmlTypeDataSourceInstance=new XmlTypeDataSource(storageFile);
    }
    public  DonateDataSource XmlDonateDataSourceInstance;
    public  TypeDataSource XmlTypeDataSourceInstance;
}
