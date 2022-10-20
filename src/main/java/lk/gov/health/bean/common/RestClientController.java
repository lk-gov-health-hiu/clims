/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package lk.gov.health.bean.common;

import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author Buddhika
 */
@Named
@SessionScoped
public class RestClientController implements Serializable {

    /**
     * Creates a new instance of RestClientController
     */
    public RestClientController() {
    }

    private String url;
    private String output;

    public void sendRestRequest()   {
        if (url == null) {
            url = "https://www.google.com/";
        }
        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {

            HttpGet request = new HttpGet(url);

            // add request headers
            request.addHeader("custom-key", "mkyong");
            request.addHeader(HttpHeaders.USER_AGENT, "Googlebot");

            CloseableHttpResponse response = httpClient.execute(request);

            try {

                // Get HttpResponse Status
                System.out.println(response.getProtocolVersion());              // HTTP/1.1
                System.out.println(response.getStatusLine().getStatusCode());   // 200
                System.out.println(response.getStatusLine().getReasonPhrase()); // OK
                System.out.println(response.getStatusLine().toString());        // HTTP/1.1 200 OK

                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    // return it as a String
                    String result = EntityUtils.toString(entity);
                    System.out.println(result);
                }

            } finally {
                response.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(RestClientController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                httpClient.close();
            } catch (IOException ex) {
                Logger.getLogger(RestClientController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

   
 
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }
}
