package com.nttdata.bootcamp.QueryBalanceService.infraestructure;

import com.nttdata.bootcamp.QueryBalanceService.domain.dto.CustomerActiveProductResponse;
import com.nttdata.bootcamp.QueryBalanceService.domain.dto.CustomerPassiveProductResponse;
import com.nttdata.bootcamp.QueryBalanceService.domain.dto.MovementResponse;
import com.nttdata.bootcamp.QueryBalanceService.domain.dto.OperationResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IQueryBalanceService {
    Mono<CustomerPassiveProductResponse> getBalancePassive(String customerProductId);
    Mono<CustomerActiveProductResponse> getBalanceActive(String customerProductId);
    Flux<OperationResponse> getMovementPassive(String customerProductId);
    Flux<MovementResponse> getMovementActive(String customerProductId);
}
