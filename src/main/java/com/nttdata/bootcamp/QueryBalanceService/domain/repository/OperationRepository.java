package com.nttdata.bootcamp.QueryBalanceService.domain.repository;

import com.nttdata.bootcamp.QueryBalanceService.domain.dto.CustomerPassiveProductResponse;
import com.nttdata.bootcamp.QueryBalanceService.domain.dto.OperationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
@Slf4j
@Repository
@RequiredArgsConstructor
public class OperationRepository {
    public Flux<OperationResponse> getAll() {
        String urlProduct = "http://localhost:8083";
        WebClient webClientProduct = WebClient.builder().baseUrl(urlProduct).build();
        return webClientProduct.get()
                .uri("/api/v1/operations")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(OperationResponse.class);
    }
}
