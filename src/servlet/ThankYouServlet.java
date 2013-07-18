package servlet;


import pagebuild.ThankYouComposer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * UserClickData: Buchina
 * Date: 11.01.2013
 * Time: 16:25:13
 */
public class ThankYouServlet extends HttpServlet {

    private final ResponseWriter responseWriter = new ResponseWriter();

    void doServe(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String thankYouTemplatePath = getServletConfig().getInitParameter("ThankYouTemplate");
        InputStream thankYouTemplate = getServletContext().getResourceAsStream(thankYouTemplatePath);

        if (!request.getParameterMap().isEmpty()) {

            String userId = request.getParameter("viewer_id");
            Long typeId = new Long(request.getParameter("id"));
            ThankYouComposer c = new ThankYouComposer(typeId, thankYouTemplate);
            c.submitClick(userId);

            String ThankYouHTML = c.composeThankYouPage();
            responseWriter.writeResponse(response, ThankYouHTML);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doServe(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doServe(request, response);
    }
}
