package servlet;

import vk.UserReminderSender;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * UserClickData: NBuchina
 * Date: 04.06.13
 * Time: 14:35
 */
public class VKServlet extends HttpServlet {

    void doServe(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (!request.getParameterMap().isEmpty()) {

            String method = request.getParameter("method");

            UserReminderSender urs = new UserReminderSender();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doServe(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doServe(request, response);
    }


}
