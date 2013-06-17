package servlet;

import pagebuild.DonatesListComposer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * User: Buchina
 * Date: 11.01.2013
 * Time: 16:59:29
 */
public class DonatesServlet extends HttpServlet {
    private final ResponseWriter responseWriter = new ResponseWriter();

    protected void doPost(HttpServletRequest
                                  request, HttpServletResponse
                                  response) throws ServletException, IOException {
        doServe(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doServe(request, response);
    }

    private void doServe(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String page = "something went wrong";
        if (!request.getParameterMap().isEmpty()) {

            String donateTemplatePath = getServletConfig().getInitParameter("DonateTemplate");
            InputStream donateTemplate = getServletContext().getResourceAsStream(donateTemplatePath);

            String donatesPageTemplatePath = getServletConfig().getInitParameter("DonatesPage");
            InputStream donatesPageTemplate = getServletContext().getResourceAsStream(donatesPageTemplatePath);

            String typeIdS = request.getParameter("id");
            if (typeIdS == null) typeIdS = "-1";
            Long typeId = new Long(typeIdS);

            String userId = request.getParameter("viewer_id");

            DonatesListComposer gen = new DonatesListComposer(userId, donateTemplate, donatesPageTemplate);
            if (typeId == -1L)
                page = gen.getDonatesHTML();
            else {
                page = gen.getDonatesHTML(typeId);
            }
        }
        responseWriter.writeResponse(response, page);
    }
}
