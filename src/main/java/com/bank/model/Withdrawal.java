package com.bank.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Withdrawal extends AbstractEntity {

    private Double amount;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="fk_user_id")
    private User user;

    public Withdrawal() {}

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
