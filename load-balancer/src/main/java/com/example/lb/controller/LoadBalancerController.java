package com.example.lb.controller;

import com.example.lb.service.LoadBalancerService;
import com.example.lb.config.LBConfig;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoadBalancerController {

    private final LoadBalancerService service;
    private final LBConfig config;

    public LoadBalancerController(LoadBalancerService service, LBConfig config) {
        this.service = service;
        this.config = config;

        // background scaling thread
        new Thread(() -> {
            while (true) {
                try { Thread.sleep(3000); } catch (Exception ignored) {}
                service.scaleIfNeeded();
            }
        }).start();
    }

    @GetMapping("/health")
    public Object health() {
        return config.backendServers;
    }

    @GetMapping("/")
    public String handleRequest() {
        return service.forwardRequest();
    }

    // ✅ THIS FIXES YOUR 404
    @GetMapping("/lb")
    public String loadBalance() {
        return service.forwardRequest();
    }
}
