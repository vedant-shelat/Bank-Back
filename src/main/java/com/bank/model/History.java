package com.bank.model;

import javax.persistence.*;

@Entity
public class History extends AbstractEntity {

    @Enumerated(EnumType.STRING)
    private Operation operation;
    private Double amount;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="fk_user_id")
    private User user;

    public History() {}

    public History(Long id, Operation operation, Double amount, User user) {
        super(id);
        this.operation = operation;
        this.amount = amount;
        this.user = user;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

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
