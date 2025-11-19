package com.example.lb.model;

public class BackendServer {
    public int id;
    public int load;
    public String port;

    public BackendServer(int id, int load, String port) {
        this.id = id;
        this.load = load;
        this.port = port;
    }
}
