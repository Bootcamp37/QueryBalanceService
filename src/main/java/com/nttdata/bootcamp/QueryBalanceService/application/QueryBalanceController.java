package com.nttdata.bootcamp.QueryBalanceService.application;

import com.nttdata.bootcamp.QueryBalanceService.domain.dto.CustomerActiveProductResponse;
import com.nttdata.bootcamp.QueryBalanceService.domain.dto.CustomerPassiveProductResponse;
import com.nttdata.bootcamp.QueryBalanceService.domain.dto.MovementsResponse;
import com.nttdata.bootcamp.QueryBalanceService.domain.dto.OperationResponse;
import com.nttdata.bootcamp.QueryBalanceService.infraestructure.IQueryBalanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("${message.path-balance}")
@RefreshScope
@Slf4j
public class QueryBalanceController {
    @Autowired
    private final IQueryBalanceService service;

    // Consulta de movimientos
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/passive/movement/{id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<OperationResponse> getMovementPassive(@PathVariable String id) {
        log.info("====> QueryBalanceController: GetMovementPassive");
        return service.getMovementPassive(id);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/active/movement/{id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<MovementsResponse> getMovementActive(@PathVariable String id) {
        log.info("====> QueryBalanceController: GetMovementActive");
        return service.getMovementActive(id);
    }

    // Consultas de saldo
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/passive/{id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<CustomerPassiveProductResponse> getBalancePassive(@PathVariable String id) {
        log.info("====> QueryBalanceController: GetBalancePassive");
        return service.getBalancePassive(id);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/active/{id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<CustomerActiveProductResponse> getBalanceActive(@PathVariable String id) {
        log.info("====> QueryBalanceController: GetBalanceActive");
        return service.getBalanceActive(id);
    }
}
