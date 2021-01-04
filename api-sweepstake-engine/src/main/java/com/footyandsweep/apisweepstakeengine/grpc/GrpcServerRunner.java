package com.footyandsweep.apisweepstakeengine.grpc;

import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class GrpcServerRunner implements CommandLineRunner, DisposableBean {

    private final ConfigurableApplicationContext applicationContext;
    private Server server;

    public GrpcServerRunner(@Autowired ConfigurableApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void run(String... args) throws Exception {
        /* Creating certificate files */
        File cert = new File("~/etc/ssl/server.crt");
        File key = new File("~/etc/ssl/server.key");

        BindableService service = applicationContext.getBean("sweepstakeController", BindableService.class);
        server = ServerBuilder.forPort(9090).useTransportSecurity(cert, key).addService(service).build();

        runSever();
    }

    private void runSever() {
        Thread thread = new Thread(() -> {
            try {
                server.awaitTermination();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.setDaemon(false);
        thread.start();
    }

    @Override
    public void destroy() {
        server.shutdown();
    }
}