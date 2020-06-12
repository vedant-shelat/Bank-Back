package com.bank.model;

import javax.persistence.Entity;

@Entity
public class Withdrawal extends AbstractEntity {

    private Double amount;

    public Withdrawal() {}

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
