package servlet;

import pagebuild.PageComposer;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;


/**
 * Created by IntelliJ IDEA.
 * User: Buchina
 * Date: 09.01.2013
 * Time: 13:45:08
 * To change this template use File | Settings | File Templates.
 */
public class PageServlet extends HttpServlet {
    String layout;
    ResponseWriter responseWriter=new ResponseWriter();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          doServe( request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doServe( request, response);
    }

     private void doServe(HttpServletRequest request, HttpServletResponse response) throws IOException {
         layout=getServletConfig().getInitParameter("layout");
         String viewer_id=request.getParameter("viewer_id");

         PageComposer pageComposer =new PageComposer(viewer_id);
         String page= pageComposer.getMainPage(getServletContext().getResourceAsStream(layout));
         
         responseWriter.writeResponse(request,response,page);
    }
}
