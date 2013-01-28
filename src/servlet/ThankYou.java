package servlet;


import storedentities.Ad;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;

import datasource.AdDataSource;
import datasource.stub.StubAdDataSource;

/**
 * Created by IntelliJ IDEA.
 * User: Buchina
 * Date: 11.01.2013
 * Time: 16:25:13
 * To change this template use File | Settings | File Templates.
 */
public class ThankYou extends HttpServlet {
    private String context;
    AdDataSource adDataSource=new StubAdDataSource();
    
    void doServe(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (!request.getParameterMap().isEmpty()) {
            try{context=getServletContext().getContextPath();   }

            catch(NoSuchMethodError er){
                context="/RCTD";
            }

            Long typeId = new Long(request.getParameter("id"));
            Ad ad= adDataSource.getAdbyId(1L);
            String adH =ad.getHTML();
            String adS=ad.getScript();


            String requestString=String.format("/jsp/ThankYou.jsp?id=%s&adS=%s&adH=%s",typeId,adS,adH);
            response.sendRedirect(context+requestString);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         doServe(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doServe(request,response);
    }
}
