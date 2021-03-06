package com.bank.dto;

import com.bank.model.History;
import com.bank.model.Operation;

public class HistoryDTO {

    private Long id;
    private Long creationDate;
    private Operation operation;
    private Double amount;
    private Long userId;

    public HistoryDTO() {}

    public HistoryDTO(History history) {
        this.id = history.getId();
        this.creationDate = history.getCreationDate();
        this.operation = history.getOperation();
        this.amount = history.getAmount();
        if(history.getUser() != null) {
            this.userId = history.getUser().getId();
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
