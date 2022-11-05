package com.nttdata.bootcamp.QueryBalanceService.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovementsResponse {
    private String id;
    private String customerActiveProductId;
    private Double amount;
    private String date;
    private String type;
}
