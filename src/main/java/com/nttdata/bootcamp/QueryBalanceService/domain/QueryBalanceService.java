package com.nttdata.bootcamp.QueryBalanceService.domain;

import com.nttdata.bootcamp.QueryBalanceService.domain.dto.CustomerActiveProductResponse;
import com.nttdata.bootcamp.QueryBalanceService.domain.dto.CustomerPassiveProductResponse;
import com.nttdata.bootcamp.QueryBalanceService.domain.dto.MovementsResponse;
import com.nttdata.bootcamp.QueryBalanceService.domain.dto.OperationResponse;
import com.nttdata.bootcamp.QueryBalanceService.domain.repository.*;
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
    @Autowired
    private final ShoppingRepository shoppingRepository;
    @Autowired
    private final PaymentRepository paymentRepository;

    @Override
    public Mono<CustomerPassiveProductResponse> getBalancePassive(String customerProductId) {
        log.info("====> QueryBalanceService: GetBalancePassive");
        return customerPassiveProductRepository.getById(customerProductId)
                .switchIfEmpty(Mono.error(RuntimeException::new));
    }

    @Override
    public Mono<CustomerActiveProductResponse> getBalanceActive(String customerProductId) {
        log.info("====> QueryBalanceService: GetBalanceActive");
        return customerActiveProductRepository.getById(customerProductId)
                .switchIfEmpty(Mono.error(RuntimeException::new));
    }

    @Override
    public Flux<OperationResponse> getMovementPassive(String customerProductId) {
        log.info("====> QueryBalanceService: GetMovementPassive");
        return operationRepository.getAll()
                .filter(p -> p.getCustomerPassiveProductId().equals(customerProductId))
                .map(e -> {
                    log.info("Resultados ===> " + e.toString());
                    return e;
                })
                .switchIfEmpty(Mono.error(RuntimeException::new));
    }

    @Override
    public Flux<MovementsResponse> getMovementActive(String customerProductId) {
        log.info("====> QueryBalanceService: GetMovementActive");
        return shoppingRepository.getAll()
                .filter(e -> e.getCustomerActiveProductId().equals(customerProductId))
                .mergeWith(paymentRepository.getAll()
                        .filter(e -> e.getCustomerActiveProductId().equals(customerProductId)))
                .sort((e1, e2) -> e1.getDate().compareTo(e2.getDate()));
    }

    @Override
    public Flux<Void> getCommission(String start, String end) {
        log.info("Start ==> " + start);
        log.info("End   ==> " + end);

        // Obtener todos los productos pasivos
        // Obtener todas las operaciones
        return null;
    }
}
