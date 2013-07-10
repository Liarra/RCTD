package datasource.xml;

import datasource.DonateDataSource;
import datasource.TypeDataSource;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: Buchina
 * Date: 31.01.2013
 * Time: 13:08:23
 */
public class XmlDataSourcesRepository {

    public XmlDataSourcesRepository() {
        try {
            preloadData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private synchronized void preloadData() throws IOException {
        String filename = "donates.xml";
//        String filename = "betadonates.xml";
//        String filename = "testdonates.xml";
        InputStream xmlFile = XmlDataSourcesRepository.class.getResourceAsStream(filename);
        BufferedReader br = new BufferedReader(new InputStreamReader(xmlFile,"UTF-8"));
        String tmp;
        String fileContent = "";

        tmp = br.readLine(); // read first line of file.

        while (tmp != null) { // read a line until end of file.
            fileContent = fileContent + tmp; //Append the contents of the file to a string to be replaced and split out later.
            tmp = br.readLine();
        }

        br.close();

        InputStream storageFile = new ByteArrayInputStream(fileContent.getBytes("UTF-8"));
        XmlDonateDataSourceInstance = new XmlDonateDataSource(storageFile);
        XmlTypeDataSourceInstance = new XmlTypeDataSource(storageFile);
    }

    public DonateDataSource XmlDonateDataSourceInstance;
    public TypeDataSource XmlTypeDataSourceInstance;
}
