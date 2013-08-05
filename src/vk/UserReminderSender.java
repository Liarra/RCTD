package vk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created with IntelliJ IDEA.
 * UserClickData: NBuchina
 * Date: 03.06.13
 * Time: 18:44
 */
public class UserReminderSender {
    private final String app_id = "3383812", app_secret = "rT04bTwGILFzeoVebUIz";

    public void NotifyUser(String userId) {
        try {
            String access_token = getKey();
            System.out.println(access_token);
            String notification = "Привет! Сегодня ты можешь сделать доброе дело, просто кликнув мышкой на кнопку. Не упусти шанс помочь!";
            notification = URLEncoder.encode(notification, "UTF-8");
            String request = "https://api.vk.com/method/secure.sendNotification?" +
                    "uids=" + userId +
                    "&message=" + notification +
                    "&client_secret=" + app_secret +
                    "&access_token=" + access_token;

            System.out.println(sendURLRequest(request));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getKey() throws IOException {
        String result = sendURLRequest(
                "https://oauth.vk.com/access_token?client_id=" + app_id
                        + "&client_secret=" + app_secret
                        + "&grant_type=client_credentials");  //&scope=wall,notify
        return parseResponseForToken(result);
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

    private String parseResponseForToken(String response) {
        int startResponse = response.indexOf(":\"");
        response = response.substring(startResponse + 2, response.length() - 2);
        response = response.substring(0, response.indexOf('"'));
        return response;
    }
}
