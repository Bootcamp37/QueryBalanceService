package com.nttdata.bootcamp.QueryBalanceService.domain.repository;

import com.nttdata.bootcamp.QueryBalanceService.domain.dto.OperationActiveResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Slf4j
@Repository
@RequiredArgsConstructor
public class PaymentRepository {
    public static final String PAYMENT_SERVICE = "ms-payments";
    @Value("${message.path-paymentDomain}")
    public String urlPayment;
    @Value("${message.path-get-payment}")
    public String pathGet;
    @Autowired
    ReactiveCircuitBreakerFactory reactiveCircuitBreakerFactory;

    public Flux<OperationActiveResponse> getAll() {
        log.info("====> PaymentRepository: GetAll");
        log.info("====> OperationRepository: Llamada " + urlPayment + pathGet);
        WebClient webClientProduct = WebClient.builder().baseUrl(urlPayment).build();
        return webClientProduct.get()
                .uri(pathGet)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(OperationActiveResponse.class)
                .transform(it -> reactiveCircuitBreakerFactory.create(PAYMENT_SERVICE)
                        .run(it, throwable -> Flux.just(new OperationActiveResponse()))
                )
                .map(e -> {
                    e.setType("PAYMENT");
                    return e;
                });
    }
}
