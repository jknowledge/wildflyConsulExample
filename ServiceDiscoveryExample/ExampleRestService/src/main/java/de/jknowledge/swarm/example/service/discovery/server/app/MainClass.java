package de.jknowledge.swarm.example.service.discovery.server.app;

import de.jknowledge.swarm.example.service.discovery.server.jaxrs.HelloWorldEndpoint;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.jaxrs.JAXRSArchive;
import org.wildfly.swarm.topology.TopologyArchive;

/**
 * Created by christian on 19.03.17.
 */
public class MainClass {


    public static void main(String[] arg) throws Exception {

        // Instantiate the container
        Swarm swarm = new Swarm();

        // Create one or more deployments
        JAXRSArchive deployment = ShrinkWrap.create(JAXRSArchive.class);

        // Add resource to deployment
        deployment.addClass(HelloWorldEndpoint.class);
        deployment.as(TopologyArchive.class).advertise("restExampleService");

        swarm.start();
        swarm.deploy(deployment);

    }

}
