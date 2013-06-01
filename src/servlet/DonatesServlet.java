package servlet;

import pagebuild.DonatesListComposer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

    @SuppressWarnings("FieldCanBeLocal")
    private final String cost = " <link rel=\"stylesheet\" type=\"text/css\" href=\"css/buttons.css\"/>\n" +
            "    <link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\"/>\n" +
            "    <link rel=\"stylesheet\" type=\"text/css\" href=\"css/welcome.css\"/>\n" +
            "    <link href='http://fonts.googleapis.com/css?family=Tenor+Sans&subset=latin,cyrillic' rel='stylesheet'\n" +
            "          type='text/css'>\n" +
            "    <link rel=\"stylesheet\" href=\"font/FontAwesome/css/font-awesome.min.css\">\n" +
            "    <script type=\"text/javascript\" src=\"js/ButtonsClick.js\"></script>\n" +
            "    <script type=\"text/javascript\" src=\"js/menu.js\"></script>\n" +
            "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">";

    private void doServe(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String page = "something went wrong";
        if (!request.getParameterMap().isEmpty()) {

            String typeIdS = request.getParameter("id");
            if (typeIdS == null) typeIdS = "-1";
            Long typeId = new Long(typeIdS);

            String userId = request.getParameter("viewer_id");


            DonatesListComposer gen = new DonatesListComposer(userId);
            if (typeId == -1L)
                page = gen.getDonatesHTML();
            else {
                page = gen.getDonatesHTML(typeId);
            }
        }
        responseWriter.writeResponse(response, cost + page);
    }
}
