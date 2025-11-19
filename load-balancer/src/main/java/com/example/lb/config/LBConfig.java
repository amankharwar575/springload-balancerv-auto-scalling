package com.example.lb.config;

import com.example.lb.model.BackendServer;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class LBConfig {

    public List<BackendServer> backendServers = new ArrayList<>();
    public String healthEndpoint = "/health";
    public String routingAlgo = "round_robin";

    public LBConfig() {
        backendServers.add(new BackendServer(1, 0, "8001"));
        backendServers.add(new BackendServer(2, 0, "8002"));
        backendServers.add(new BackendServer(3, 0, "8003"));
        backendServers.add(new BackendServer(4, 0, "8004"));
    }
}
