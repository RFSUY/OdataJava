package com.example.Service;

import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TokenService {
    
    
    public String getBearerToken() throws ClientProtocolException, IOException {
        String url = "https://login.microsoftonline.com/rfsuy.com/oauth2/v2.0/token";
        String grantType = "client_credentials";
        String clientId = "b21fc105-683d-4a20-b14a-94f6128883c5";
        String clientSecret = "CaP8Q~0JICpqHRv7k08S6fEagLeTnYxXKEvIla0q";
        String scope = "https://usnconeboxax1aos.cloud.onebox.dynamics.com/.default";

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost request = new HttpPost(url);
        String body = "grant_type=" + grantType + "&client_id=" + clientId + "&client_secret=" + clientSecret
                + "&scope=" + scope;

        request.setEntity(new StringEntity(body));
        request.setHeader("Content-Type", "application/x-www-form-urlencoded");
        CloseableHttpResponse response = httpClient.execute(request);

        String responseString = EntityUtils.toString(response.getEntity());
        ObjectMapper mapper = new ObjectMapper();
        JsonNode responseJson = mapper.readTree(responseString);
        String accessToken = responseJson.get("access_token").asText();

        return accessToken;
    }

}
