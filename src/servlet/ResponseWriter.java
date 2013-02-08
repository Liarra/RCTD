package servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: Buchina
 * Date: 08.02.2013
 * Time: 10:56:16
 * To change this template use File | Settings | File Templates.
 */
public class ResponseWriter {

    public void writeResponse(HttpServletRequest request, HttpServletResponse response, String page) throws IOException {
        OutputStream stream= response.getOutputStream();
         PrintWriter writer=new PrintWriter(new OutputStreamWriter(stream,"UTF-8"));
         writer.write(page);
         writer.close();
         stream.close();

    }
}
