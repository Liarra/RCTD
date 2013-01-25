package servlet;

import generators.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;

/**
 * Created by IntelliJ IDEA.
 * User: Buchina
 * Date: 09.01.2013
 * Time: 13:45:08
 * To change this template use File | Settings | File Templates.
 */
public class PageServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          doServe( request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doServe( request, response);
    }

     private void doServe(HttpServletRequest request, HttpServletResponse response) throws IOException {
         OutputStream stream= response.getOutputStream();
         PrintWriter writer=new PrintWriter(new OutputStreamWriter(stream,"UTF-8"));
         String page= new PageGenerator().getMainPage();
         writer.write(page);
         writer.close();
         stream.close();
    }
}
