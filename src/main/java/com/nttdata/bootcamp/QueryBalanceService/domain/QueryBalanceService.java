package com.nttdata.bootcamp.QueryBalanceService.domain;

import com.nttdata.bootcamp.QueryBalanceService.domain.dto.CustomerActiveProductResponse;
import com.nttdata.bootcamp.QueryBalanceService.domain.dto.CustomerPassiveProductResponse;
import com.nttdata.bootcamp.QueryBalanceService.domain.dto.MovementResponse;
import com.nttdata.bootcamp.QueryBalanceService.domain.dto.OperationResponse;
import com.nttdata.bootcamp.QueryBalanceService.domain.repository.CustomerActiveProductRepository;
import com.nttdata.bootcamp.QueryBalanceService.domain.repository.CustomerPassiveProductRepository;
import com.nttdata.bootcamp.QueryBalanceService.domain.repository.OperationRepository;
import com.nttdata.bootcamp.QueryBalanceService.infraestructure.IQueryBalanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
@RequiredArgsConstructor
@Slf4j
public class QueryBalanceService implements IQueryBalanceService {
    @Autowired
    private final CustomerActiveProductRepository customerActiveProductRepository;
    @Autowired
    private final CustomerPassiveProductRepository customerPassiveProductRepository;
    @Autowired
    private final OperationRepository operationRepository;
    @Override
    public Mono<CustomerPassiveProductResponse> getBalancePassive(String customerProductId) {
        log.debug("====> QueryBalanceService: GetBalancePassive");
        return customerPassiveProductRepository.getById(customerProductId)
                .switchIfEmpty(Mono.error(RuntimeException::new));
    }

    @Override
    public Mono<CustomerActiveProductResponse> getBalanceActive(String customerProductId) {
        log.debug("====> QueryBalanceService: GetBalanceActive");
        return customerActiveProductRepository.getById(customerProductId)
                .switchIfEmpty(Mono.error(RuntimeException::new));
    }

    @Override
    public Flux<OperationResponse> getMovementPassive(String customerProductId) {
        log.debug("====> QueryBalanceService: GetMovementPassive");
        return operationRepository.getAll()
                .filter(p -> p.getCustomerPassiveProductId().equals(customerProductId))
                .switchIfEmpty(Mono.error(RuntimeException::new));
    }

    @Override
    public Flux<MovementResponse> getMovementActive(String customerProductId) {
        log.debug("====> QueryBalanceService: GetMovementActive");
        return null;
    }
}
