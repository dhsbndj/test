package com.itcast.test;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Test {
    public static String getToken() {
        BufferedReader br = null;
        StringBuffer sb = new StringBuffer();

        String authHost = "https://aip.baidubce.com/oauth/2.0/token?";

        String clientId = "";
        String clientSecret = "";
        String getAccessTokenUrl =
                "grant_type=client_credentials"
                        + "&client_id=" + clientId
                        + "&client_secret=" + clientSecret;
        try {
            URL url = new URL(getAccessTokenUrl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("POST");
            connection.connect();
            br = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = JSONObject.fromObject(sb.toString());
        String token = jsonObject.getString("access_token");
        return token;
    }

    public static void main(String[] args) {
        String tonken = getToken();
        System.out.println(tonken);
        //开发人员
    }

}
