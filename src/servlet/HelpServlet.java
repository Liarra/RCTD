package servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * Created by IntelliJ IDEA.
 * User: Buchina
 * Date: 11.01.2013
 * Time: 17:15:27
 * To change this template use File | Settings | File Templates.
 */
public class HelpServlet extends HttpServlet {

    ResponseWriter resp=new ResponseWriter();
    void doServe(HttpServletRequest request, HttpServletResponse response) throws IOException {
         resp.writeResponse(request,response,"<h1>Здесь будет симпатичный FAQ :)</h1>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         doServe(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doServe(request,response);
    }
}



