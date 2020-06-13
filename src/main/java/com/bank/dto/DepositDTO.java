package com.bank.dto;

import com.bank.model.Deposit;

public class DepositDTO {

    private Long id;
    private Long creationDate;
    private Double amount;
    private Long userId;

    public DepositDTO() {}

    public DepositDTO(Deposit deposit) {
        this.id = deposit.getId();
        this.creationDate = deposit.getCreationDate();
        this.amount = deposit.getAmount();
        if(deposit.getUser() != null) {
            this.userId = deposit.getUser().getId();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Long creationDate) {
        this.creationDate = creationDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
