package server;

import com.prime.generated.stubs.PrimeNumberDecompositionRequest;
import com.prime.generated.stubs.PrimeNumberDecompositionResponse;
import com.prime.generated.stubs.PrimeNumberServiceGrpc;
import io.grpc.stub.StreamObserver;

public class PrimeNumberServiceImpl extends PrimeNumberServiceGrpc.PrimeNumberServiceImplBase {

    @Override
    public void primeNumberDecomposition(PrimeNumberDecompositionRequest request, StreamObserver<PrimeNumberDecompositionResponse> responseObserver) {
        Long number = request.getNumber();
        Long divisor = 2L;
        System.out.println("Request recived to find the prime factors of the number: "+number);

        while (number > 1) {
            if (number % divisor == 0) {
                number = number / divisor;
                responseObserver.onNext(PrimeNumberDecompositionResponse.newBuilder()
                        .setPrimeFactor(divisor)
                        .build());
            } else {
                divisor = divisor + 1;
                //System.out.println(divisor);
            }
        }
        responseObserver.onCompleted();
    }
}
