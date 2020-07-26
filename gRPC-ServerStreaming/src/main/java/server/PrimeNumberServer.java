package server;


import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class PrimeNumberServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Strating server");
        Server server = ServerBuilder.forPort(50052)
                .addService(new PrimeNumberServiceImpl())
                .build();

        server.start();
        System.out.println("Server started");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Received Shutdown Request");
            server.shutdown();
            System.out.println("Successfully stopped the server");
        }));

        server.awaitTermination();
    }

}
