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


import static datasource.stub.StubDataSourcesRepository.*;
import static datasource.xml.XmlDataSourcesRepository.*;
import datasource.xml.XmlDataSourcesRepository;
import datasource.TypeDataSource;
import datasource.DonateDataSource;


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
        String page="something went wrong";
        if (!request.getParameterMap().isEmpty()) {
            String typeIdS=request.getParameter("id");
            if(typeIdS==null)typeIdS="-1";
            Long typeId = new Long(typeIdS);
            String userId=request.getParameter("viewer_id");

            XmlDataSourcesRepository rep=new XmlDataSourcesRepository();
         TypeDataSource XmlTypeDataSourceInstance=rep.XmlTypeDataSourceInstance;
         DonateDataSource XmlDonateDataSourceInstance=rep.XmlDonateDataSourceInstance;

            PageGenerator gen=new PageGenerator(XmlTypeDataSourceInstance, XmlDonateDataSourceInstance,UserClicksDataSourceInstance, userId);
            if (typeId == -1L)
                page = gen.getDonatesHTML();
            else
                page = gen.getDonatesHTML(typeId);
        } else {
//            page = gen.getDonatesHTML();
        }

        OutputStream stream = response.getOutputStream();
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(stream, "UTF-8"));

        writer.print(page);
        writer.close();
        stream.close();
    }
}
