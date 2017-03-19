package de.jknowledge.swarm.example.service.discovery.client;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.health.model.HealthService;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ServerList;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by christian on 19.03.17.
 */
public class ConsulServerList implements ServerList {

    public List getInitialListOfServers() {
        return this.getServerList();
    }

    public List getUpdatedListOfServers() {
        return this.getServerList();
    }

    private List<Server> getServerList() {
        List<Server> servers = new LinkedList<Server>();
        ConsulClient consulClient = new ConsulClient();
        List<HealthService> nodes = consulClient.getHealthServices("restExampleService", false, null).getValue();
        System.out.println("found " + nodes.size() + " nodes");
        for(HealthService nodeObj : nodes) {
            System.out.println(nodeObj.getService().getId() + " - "+  nodeObj.getService().getAddress() + ":" + nodeObj.getService().getPort());
            String host = nodeObj.getService().getAddress();
            if(host.equals("0:0:0:0:0:0:0:0")) {
                host = "localhost";
            }
            servers.add(new Server(host, nodeObj.getService().getPort()));
        }
        return servers;
    }
}
