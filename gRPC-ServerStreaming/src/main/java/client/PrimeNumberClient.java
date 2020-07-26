package client;

import com.prime.generated.stubs.PrimeNumberDecompositionRequest;
import com.prime.generated.stubs.PrimeNumberServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class PrimeNumberClient {

    public static void main(String[] args) {
        System.out.println("Eastablishing connection with server");
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50052)
                .usePlaintext()
                .build();

        PrimeNumberServiceGrpc.PrimeNumberServiceBlockingStub stub=PrimeNumberServiceGrpc.newBlockingStub(channel);

        Integer number=56765433;
        System.out.println("Sending request to server");
        System.out.println("Prime factors of the number "+number+" are:");
        stub.primeNumberDecomposition(PrimeNumberDecompositionRequest.newBuilder().setNumber(number).build())
                .forEachRemaining(primeNumberDecompositionResponse -> {
                    System.out.println(primeNumberDecompositionResponse.getPrimeFactor());
                });
        System.out.println("Response received successfully");
        System.out.println("Shutting down channel");
        channel.shutdown();
    }
}
