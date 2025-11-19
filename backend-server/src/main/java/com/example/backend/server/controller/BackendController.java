package com.example.backend.server.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class BackendController {

    @GetMapping("/b_server{serverId}")
    public String respond(@PathVariable int serverId) {
        return "Response from Backend Server " + serverId + "\n";
    }

}
