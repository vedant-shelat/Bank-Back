package com.bank.dto;

import com.bank.model.Deposit;

public class DepositDTO {

    private Long id;
    private Long creationDate;
    private Double amount;

    public DepositDTO(Deposit deposit) {
        this.id = deposit.getId();
        this.creationDate = deposit.getCreationDate();
        this.amount = deposit.getAmount();
    }
}
