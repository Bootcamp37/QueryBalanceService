package com.nttdata.bootcamp.QueryBalanceService.domain.dto;

import com.nttdata.bootcamp.QueryBalanceService.domain.OperationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperationResponse {
    private String id;
    private String customerPassiveProductId;
    private OperationType operationType;
    private Double amount;
    private String operationDate;
}
