/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package lk.gov.health.bean.common;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

/**
 *
 * @author buddh
 */
@Named
@SessionScoped
public class ApiRequestController implements Serializable {

    private String uri;
    private String out;

    /**
     * Creates a new instance of ApiRequestController
     */
    public ApiRequestController() {
    }

    public void execureUri1() {
        Map m = new HashMap();
        m.put("un", "username");
        out = executePost(uri, m);
    }

    public void execureUri2() {
        Map m = new HashMap();
        m.put("un", "username");
        out = biaQurantineData(uri);
    }

    public String biaQurantineData(String uri) {
        URL apiUrl;

        try {
            // Creates a new url object with the ApirPort api URL
            apiUrl = new URL(uri);
            try {
                // Create a new HTTP connection
                HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
                // Set the request method to POST
                connection.setRequestMethod("POST");
                // Set content type to application/json
                connection.setRequestProperty("content-type", "application/json");
                // set connection to return output
                connection.setDoOutput(true);
                // create a json string for request body
                //TODO: Replace the arrival date with the fromDate formatted to below format
                String jsonString = new JSONObject()
                        .put("username", "health2")
                        .put("password", "healthuser2")
                        .put("arrivalDate", "2021-11-24")
                        .toString();
                // set request body
                try (OutputStream os = connection.getOutputStream()) {
                    byte[] input = jsonString.getBytes("utf-8");
                    os.write(input, 0, input.length);
                }

                //reading the response
                String res;
                BufferedInputStream bis = new BufferedInputStream(connection.getInputStream());
                ByteArrayOutputStream buf = new ByteArrayOutputStream();
                int resultLines = bis.read();
                while (resultLines != -1) {
                    buf.write((byte) resultLines);
                    resultLines = bis.read();
                }
                res = buf.toString();
                System.out.println(res);
                return res;
                // try(BufferedReader br = new BufferedReader(
                //   new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                //     StringBuilder response = new StringBuilder();
                //     String responseLine = null;
                //     while ((responseLine = br.readLine()) != null) {
                //         System.out.println(responseLine);
                //         response.append(responseLine.trim());
                //     }
                //     // System.out.println(response.toString());

                //     this.biaData = response.toString();
                //     return "/national/bia";
                // }
            } catch (IOException e) {
                // TODO: Auto-generated catch block
                e.printStackTrace();
                return e.getMessage();
            }
        } catch (MalformedURLException e) {
            // TODO: Auto-generated catch block
            e.printStackTrace();
            return e.getMessage();
        }

    }

    public String executePost(String targetURL, Map<String, Object> parameters) {
        if (targetURL == null || targetURL.trim().equals("")) {
            return "No URI";
        }
        HttpURLConnection connection = null;
        if (parameters != null && !parameters.isEmpty()) {
            targetURL += "?";
            Set s = parameters.entrySet();
            Iterator it = s.iterator();
            while (it.hasNext()) {
                Map.Entry m = (Map.Entry) it.next();
                Object pVal = m.getValue();
                String pPara = (String) m.getKey();
                targetURL += pPara + "=" + pVal.toString() + "&";
            }
            targetURL += "last=true";
        }
        try {
            //Create connection
            URL url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            connection.setUseCaches(false);
            connection.setDoOutput(true);
            //Send request
            DataOutputStream wr = new DataOutputStream(
                    connection.getOutputStream());
            wr.writeBytes(targetURL);
            wr.flush();
            wr.close();

            //Get Response  
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            return response.toString();
        } catch (Exception e) {
            return "Exception > " +  e.getMessage();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getOut() {
        return out;
    }

    public void setOut(String out) {
        this.out = out;
    }

}
