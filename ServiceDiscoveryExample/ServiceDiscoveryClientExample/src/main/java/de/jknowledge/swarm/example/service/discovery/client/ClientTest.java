package de.jknowledge.swarm.example.service.discovery.client;

import com.netflix.client.ClientFactory;
import com.netflix.client.http.HttpRequest;
import com.netflix.client.http.HttpResponse;
import com.netflix.config.ConfigurationManager;
import com.netflix.niws.client.http.RestClient;

import java.net.URI;


/**
 * Created by christian on 19.03.17.
 */
public class ClientTest {

    public static void main(String[] args) throws Exception {
        ConfigurationManager.loadPropertiesFromResources("ribbon.properties");
        RestClient client = (RestClient) ClientFactory.getNamedClient("client");

        HttpRequest request = HttpRequest.newBuilder().uri(new URI("/helloworld")).build();
        for (int i = 0; i < 20; i++)  {
            HttpResponse response = client.executeWithLoadBalancer(request);
            String result = response.getEntity(String.class);
            System.out.println("response: " + result);
            System.out.println("status code for " + response.getRequestedURI() + "  :" + response.getStatus());
        }
    }

}
