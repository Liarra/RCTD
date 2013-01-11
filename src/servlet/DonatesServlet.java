package servlet;

import generators.PageGenerator;

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
 * Time: 16:59:29
 * To change this template use File | Settings | File Templates.
 */
public class DonatesServlet extends HttpServlet

{


    protected void doPost(HttpServletRequest
            request, HttpServletResponse
            response) throws ServletException, IOException {
        doServe(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doServe(request, response);
    }

    private void doServe(HttpServletRequest request, HttpServletResponse response) throws IOException {
        OutputStream stream = response.getOutputStream();
        PrintWriter writer = new PrintWriter(stream);
        String page = new PageGenerator().getMainData();
        writer.print(page);
        writer.close();
        stream.close();
    }
}
