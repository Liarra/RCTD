package servlet;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Buchina
 * Date: 11.01.2013
 * Time: 16:25:13
 * To change this template use File | Settings | File Templates.
 */
public class ThankYou extends HttpServlet {
    private String context;
    
    void doServe(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (!request.getParameterMap().isEmpty()) {
            Long typeId = new Long(request.getParameter("id"));
            
            context=getServletContext().getContextPath();
            response.sendRedirect(context+"/jsp/ThankYou.jsp?id="+typeId);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         doServe(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doServe(request,response);
    }
}
