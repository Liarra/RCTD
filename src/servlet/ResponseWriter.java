package servlet;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * UserClickData: Buchina
 * Date: 08.02.2013
 * Time: 10:56:16
 */
class ResponseWriter {

    public void writeResponse(HttpServletResponse response, String page) throws IOException {
        response.setContentType("text/html");
        OutputStream stream = response.getOutputStream();
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(stream, "UTF-8"));
        writer.write(page);
        writer.close();
        stream.close();

    }
}
