package com.nttdata.bootcamp.QueryBalanceService.application;

import com.nttdata.bootcamp.QueryBalanceService.domain.dto.CustomerActiveProductResponse;
import com.nttdata.bootcamp.QueryBalanceService.domain.dto.CustomerPassiveProductResponse;
import com.nttdata.bootcamp.QueryBalanceService.domain.dto.MovementResponse;
import com.nttdata.bootcamp.QueryBalanceService.domain.dto.OperationResponse;
import com.nttdata.bootcamp.QueryBalanceService.infraestructure.IQueryBalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@RequiredArgsConstructor
@RestController
@RequestMapping("${message.path-balance}")
@RefreshScope
public class QueryBalanceController {
    @Autowired
    private final IQueryBalanceService service;

    // Consulta de movimientos
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/passive/movement/{id}")
    public Flux<OperationResponse> getMovementPassive(@PathVariable String id){
        return service.getMovementPassive(id);
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/active/movement/{id}")
    public Flux<MovementResponse> getMovementActive(@PathVariable String id){
        return service.getMovementActive(id);
    }
    // Consultas de saldo
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/passive/{id}")
    public Mono<CustomerPassiveProductResponse> getBalancePassive(@PathVariable String id){
        return service.getBalancePassive(id);
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/active/{id}")
    public Mono<CustomerActiveProductResponse> getBalanceActive(@PathVariable String id){
        return service.getBalanceActive(id);
    }
}
