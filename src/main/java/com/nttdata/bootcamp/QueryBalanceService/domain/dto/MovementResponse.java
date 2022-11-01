package com.nttdata.bootcamp.QueryBalanceService.domain.dto;

import com.nttdata.bootcamp.QueryBalanceService.domain.OperationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovementResponse {
    private String id;
    private String customerActiveProductId;
    private Double amount;
    private String shoppingDate;
    private OperationType operationType;
}
