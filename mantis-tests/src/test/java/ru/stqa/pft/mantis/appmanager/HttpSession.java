package ru.stqa.pft.mantis.appmanager;


import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class HttpSession {
    private CloseableHttpClient httpclient;
    private ApplicationManager app;

    public HttpSession(ApplicationManager app) {
        this.app = app;
        httpclient = HttpClients.custom().setRedirectStrategy(new LaxRedirectStrategy()).build();
    }

    // метод для выполнения логина
    public boolean login(String username, String password) throws IOException {
        HttpPost post = new HttpPost(app.getProperty("web.baseUrl") + "login_page.php");
        List<NameValuePair> params = new ArrayList<NameValuePair>();

        // формирование параметров для тела запроса
        params.add(new BasicNameValuePair("username", username));
        params.add(new BasicNameValuePair("password", password));
        params.add(new BasicNameValuePair("secure_session", "on"));
        params.add(new BasicNameValuePair("return", "my_view_page.php"));
        post.setEntity(new UrlEncodedFormEntity(params));

        // передача параметров тела в запрос
        CloseableHttpResponse response = httpclient.execute(post);
        String body = geTextFrom(response);

        // проверка что пользователь вошел
        return body.contains(String.format("<span id=\"logged-in-user\">%s</span>", username));
    }

    private String geTextFrom(CloseableHttpResponse response) throws IOException {
        try {
            return EntityUtils.toString(response.getEntity());
        } finally {
            response.close();
        }
    }
    // проверка пользователя который вошел
    public boolean isLoggedInAs(String username) throws IOException {
        HttpGet get = new HttpGet(app.getProperty("web.baseUrl") + "my_view_page.php");
        CloseableHttpResponse response = httpclient.execute(get);
        String body = geTextFrom(response);
        return body.contains(String.format("<span id=\"logged-in-user\">%s</span>", username));
    }

}
