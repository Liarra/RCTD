package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Buchina
 * Date: 11.01.2013
 * Time: 17:15:27
 */
public class HelpServlet extends HttpServlet {

    private final ResponseWriter resp = new ResponseWriter();

    void doServe(HttpServletRequest request, HttpServletResponse response) throws IOException {
        resp.writeResponse(response, "<h1>Здесь будет симпатичный FAQ :)</h1>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doServe(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doServe(request, response);
    }
}



