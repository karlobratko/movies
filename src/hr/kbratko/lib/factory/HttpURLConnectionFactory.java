/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.lib.factory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author kbratko
 */
public final class HttpURLConnectionFactory {

  private static final int TIMEOUT = 10000;
  private static final String REQUEST_METHOD = "GET";
  private static final String USER_AGENT = "User-Agent";
  private static final String MOZILLA = "Mozilla/5.0";

  private HttpURLConnectionFactory() {
  }

  public static HttpURLConnection getHttpURLConnection(String path)
    throws MalformedURLException, IOException {
    URL url = new URL(path);
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setConnectTimeout(TIMEOUT);
    connection.setReadTimeout(TIMEOUT);
    connection.setRequestMethod(REQUEST_METHOD);
    connection.addRequestProperty(USER_AGENT, MOZILLA);
    return connection;
  }

}
