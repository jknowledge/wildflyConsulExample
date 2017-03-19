package de.jknowledge.swarm.example.service.discovery.server.jaxrs;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import java.net.URI;


/**
 * Created by christian on 19.03.17.
 */
@Path("helloworld")
public class HelloWorldEndpoint {

    @Context
    UriInfo uri;

    @GET
    public String sayHello() {
        URI serviceUri = uri.getBaseUri();
        return "Hello World - my current port is " + serviceUri.getPort();
    }
}
