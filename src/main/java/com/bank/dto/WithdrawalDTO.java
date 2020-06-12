package com.bank.dto;

import com.bank.model.Withdrawal;

public class WithdrawalDTO {

    private Long id;
    private Long creationDate;
    private Double amount;

    public WithdrawalDTO() {}

    public WithdrawalDTO(Withdrawal withdrawal) {
        this.id = withdrawal.getId();
        this.creationDate = withdrawal.getCreationDate();
        this.amount = withdrawal.getAmount();
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
}
