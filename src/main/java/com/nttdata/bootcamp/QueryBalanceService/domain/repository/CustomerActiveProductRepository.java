package com.nttdata.bootcamp.QueryBalanceService.domain.repository;

import com.nttdata.bootcamp.QueryBalanceService.domain.dto.CustomerActiveProductResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CustomerActiveProductRepository {
    public Mono<CustomerActiveProductResponse> getById(String idCustomerPassiveProduct) {
        String urlProduct = "http://localhost:8082";
        WebClient webClientProduct = WebClient.builder().baseUrl(urlProduct).build();
        return webClientProduct.get()
                .uri("/api/v1/customerActivesProducts/{id}", idCustomerPassiveProduct)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(CustomerActiveProductResponse.class);
    }
}
