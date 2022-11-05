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
public class ShoppingRepository {
    public static final String SHOPPING_SERVICE = "ms-shopping";
    @Value("${message.path-shoppingDomain}")
    public String urlShopping;
    @Value("${message.path-get-shopping}")
    public String pathGet;
    @Autowired
    ReactiveCircuitBreakerFactory reactiveCircuitBreakerFactory;

    public Flux<OperationActiveResponse> getAll() {
        log.info("====> ShoppingRepository: GetAll");
        log.info("====> OperationRepository: Llamada " + urlShopping + pathGet);
        WebClient webClientProduct = WebClient.builder().baseUrl(urlShopping).build();
        return webClientProduct.get()
                .uri(pathGet)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(OperationActiveResponse.class)
                .transform(it -> reactiveCircuitBreakerFactory.create(SHOPPING_SERVICE)
                        .run(it, throwable -> Flux.just(new OperationActiveResponse()))
                )
                .map(e -> {
                    e.setType("SHOPPING");
                    return e;
                });
    }
}
