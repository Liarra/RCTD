package util;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: Nina
 * Date: 09.06.13
 * Time: 0:52
 */
public class TextFilesReader extends FileReader {
    File f=null;
    public TextFilesReader(File file) throws FileNotFoundException {
        super(file);
        f=file;
    }

    public String ohPleaseReadItAllAlready() throws IOException {
        String ret=null;
        BufferedReader br=new BufferedReader(new FileReader(f));
        String line=null;

        while((line=br.readLine())!=null){
             ret+=line+"\n";
        }

        br.close();
        return ret;
    }
}
