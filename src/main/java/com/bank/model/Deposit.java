package com.bank.model;

import javax.persistence.Entity;

@Entity
public class Deposit extends AbstractEntity {

    private Double amount;

    public Deposit() {}

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
