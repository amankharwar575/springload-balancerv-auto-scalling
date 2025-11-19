package com.example.lb.service;

import com.example.lb.config.LBConfig;
import com.example.lb.model.BackendServer;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

@Service
public class LoadBalancerService {

    private final LBConfig config;
    private int next = 0;

    public LoadBalancerService(LBConfig config) {
        this.config = config;
    }

    private synchronized BackendServer getNextServer() {

        BackendServer server;

        switch (config.routingAlgo) {
            case "round_robin":
                server = config.backendServers.get(next);
                next = (next + 1) % config.backendServers.size();
                break;

            case "random_selection":
                int idx = new Random().nextInt(config.backendServers.size());
                server = config.backendServers.get(idx);
                break;

            default:
                server = config.backendServers.get(0);
        }

        server.load++;
        return server;
    }

    public String forwardRequest() {
        BackendServer server = getNextServer();

        try {
            String url = "http://localhost:" + server.port + "/b_server" + server.id;
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("GET");

            InputStream in = conn.getInputStream();
            return new String(in.readAllBytes());

        } catch (Exception e) {
            return "Backend unreachable";
        }
    }

    public void scaleIfNeeded() {
        for (BackendServer s : config.backendServers) {
            if (s.load > 5) {
                int newId = config.backendServers.size() + 1;
                String port = "800" + newId;

                config.backendServers.add(new BackendServer(newId, 0, port));
                System.out.println("Added new backend server on port " + port);
            }
        }
    }

    public LBConfig getConfig() {
        return config;
    }
}
