package servlet;


import pagebuild.ThankYouComposer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Buchina
 * Date: 11.01.2013
 * Time: 16:25:13
 * To change this template use File | Settings | File Templates.
 */
public class ThankYouServlet extends HttpServlet {

    ResponseWriter responseWriter=new ResponseWriter();
    
    void doServe(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (!request.getParameterMap().isEmpty()) {

            String userId=request.getParameter("viewer_id");
            Long typeId = new Long(request.getParameter("id"));
             ThankYouComposer c=new ThankYouComposer(typeId);
            c.submitClick(userId);

            String ThankYouHTML=c.composeThankYouPage();
            responseWriter.writeResponse(request,response,ThankYouHTML);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         doServe(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doServe(request,response);
    }
}
