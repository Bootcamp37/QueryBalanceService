package com.nttdata.bootcamp.QueryBalanceService.domain.repository;

import com.nttdata.bootcamp.QueryBalanceService.domain.dto.OperationResponse;
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
public class OperationRepository {
    public static final String OPERATION_SERVICE = "ms-operations";
    @Value("${message.path-operationDomain}")
    public String urlOperation;
    @Value("${message.path-get-operation}")
    public String pathGet;
    @Autowired
    ReactiveCircuitBreakerFactory reactiveCircuitBreakerFactory;

    public Flux<OperationResponse> getAll() {
        log.info("====> OperationRepository: GetAll");
        log.info("====> OperationRepository: Llamada " + urlOperation + pathGet);
        WebClient webClientProduct = WebClient.builder().baseUrl(urlOperation).build();
        return webClientProduct.get()
                .uri(pathGet)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(OperationResponse.class)
                .transform(it -> reactiveCircuitBreakerFactory.create(OPERATION_SERVICE)
                        .run(it, throwable -> Flux.just(new OperationResponse()))
                );
    }
}
