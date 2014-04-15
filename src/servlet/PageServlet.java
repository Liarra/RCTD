package servlet;

import pagebuild.PageComposer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;


/**
 * Created by IntelliJ IDEA.
 * UserClickData: Buchina
 * Date: 09.01.2013
 * Time: 13:45:08
 */
public class PageServlet extends HttpServlet {
    private final ResponseWriter responseWriter = new ResponseWriter();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doServe(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doServe(request, response);
    }

    private void doServe(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String welcomeTemplatePath = getServletConfig().getInitParameter("Welcome");
        String layoutTemplatePath = getServletConfig().getInitParameter("Layout");
        String menuTemplatePath = getServletConfig().getInitParameter("Menu");

        String donateTemplatePath = getServletConfig().getInitParameter("DonateTemplate");
        String donatesPageTemplatePath = getServletConfig().getInitParameter("DonatesPage");



        InputStream welcomeTemplate = getServletContext().getResourceAsStream(welcomeTemplatePath);
        InputStream layoutTemplate = getServletContext().getResourceAsStream(layoutTemplatePath);
        InputStream menuTemplate = getServletContext().getResourceAsStream(menuTemplatePath);
        InputStream donateTemplate = getServletContext().getResourceAsStream(donateTemplatePath);
        InputStream donatesPageTemplate = getServletContext().getResourceAsStream(donatesPageTemplatePath);

        String viewer_id = request.getParameter("viewer_id");

        String typeIdS = request.getParameter("id");
        if (typeIdS == null) typeIdS = "-1";
        Long typeId = new Long(typeIdS);

        PageComposer pageComposer = new PageComposer(viewer_id,layoutTemplate,welcomeTemplate,menuTemplate,donateTemplate,donatesPageTemplate);
        String page = pageComposer.getMainPage();

        responseWriter.writeResponse(response, page);
    }
}
