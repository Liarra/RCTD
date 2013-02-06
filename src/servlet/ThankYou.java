package servlet;


import storedentities.Ad;
import storedentities.Donate;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;

import datasource.AdDataSource;
import datasource.UserClicksDataSource;
import datasource.DonateDataSource;
import datasource.stub.StubAdDataSource;
import datasource.stub.StubUserClicksDataSource;
import datasource.stub.StubDonateDataSource;
import static datasource.stub.StubDataSourcesRepository.*;
import static datasource.xml.XmlDataSourcesRepository.*;
import datasource.xml.XmlDataSourcesRepository;

/**
 * Created by IntelliJ IDEA.
 * User: Buchina
 * Date: 11.01.2013
 * Time: 16:25:13
 * To change this template use File | Settings | File Templates.
 */
public class ThankYou extends HttpServlet {
    private String context;
    AdDataSource adDataSource=AdDataSourceInstance;
    UserClicksDataSource userClicksDataSource=UserClicksDataSourceInstance;
    DonateDataSource donateDataSource=new XmlDataSourcesRepository().XmlDonateDataSourceInstance;
    
    void doServe(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (!request.getParameterMap().isEmpty()) {
            try{context=getServletContext().getContextPath();   }

            catch(NoSuchMethodError er){
                context="/RCTD";
            }
             Ad ad= adDataSource.getAdbyId(1L);
            String adH =ad.getHTML();
            String adS=ad.getScript();

            String userId=request.getParameter("viewer_id");
            Long typeId = new Long(request.getParameter("id"));
            submitClick(userId,typeId);

            String requestString=String.format("/jsp/ThankYou.jsp?id=%s&adS=%s&adH=%s",typeId,adS,adH);
            response.sendRedirect(context+requestString);
        }
    }

    private void submitClick(String user, Long donateId){
        Donate donate=donateDataSource.getDonateById(donateId);

        if(!userClicksDataSource.isAbleToClick(user,donate))
                return;
//            throw new IllegalArgumentException("This user cannot click on this donate anymore!");

        userClicksDataSource.click(user,donate);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         doServe(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doServe(request,response);
    }
}
