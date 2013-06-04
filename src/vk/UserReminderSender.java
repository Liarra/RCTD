package vk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created with IntelliJ IDEA.
 * User: NBuchina
 * Date: 03.06.13
 * Time: 18:44
 * To change this template use File | Settings | File Templates.
 */
public class UserReminderSender {
    private final String app_id = "3383812", app_secret = "rT04bTwGILFzeoVebUIz",
            access_token = "fe09ab9afe09ab9afecaf57edffe3a099effe09fe09ab9aafa5072d45729fb922376bf8";

    public static void main(String[] args) {
        System.out.println(new UserReminderSender().parseResponceForToken("{\"access_token\":\"533bacf01e11f55b536a565b57531ac114461ae8736d6506a3\"}"));
    }

    public void NotifyUser(String userId) {
        try {
            String access_token = getKey();
            String notification = "Привет! Сегодня вы можете сделать много добрых дел с помощью Доброй Кнопки!";
            String request = "https://api.vk.com/method/secure.sendNotification?" +
                    "uids=" + userId +
                    "&uid=" + app_id +
                    "&message=" + notification +
                    "&client_secret=" + app_secret +
                    "&access_token=" + access_token;

            sendURLRequest(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getKey() throws IOException {
        String result = sendURLRequest("https://oauth.vk.com/access_token?client_id=" + app_id + "&client_secret=" + app_secret + "&grant_type=client_credentials&scope=wall,notify");
        return parseResponceForToken(result);
    }

    private String sendURLRequest(String urlPath) throws IOException {
        String result = null;
        URL vkOauth = new URL(urlPath);
        URLConnection connection = vkOauth.openConnection();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        connection.getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null)
            result += inputLine + "\n";
        in.close();

        return result;
    }

    private String parseResponceForToken(String response) {
        int startResponce = response.indexOf(":\"");
        return response.substring(startResponce + 2, response.length() - 2);
    }
}
