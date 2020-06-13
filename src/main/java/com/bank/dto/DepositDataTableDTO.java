package com.bank.dto;

import com.bank.model.Deposit;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DepositDataTableDTO {

    private Long length;
    private List<DepositDTO> deposits = new ArrayList<>();

    public DepositDataTableDTO() {}

    public DepositDataTableDTO(Page<Deposit> deposits) {
        this.length = deposits.getTotalElements();
        this.deposits = deposits.stream().map(d-> new DepositDTO(d)).collect(Collectors.toList());
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public List<DepositDTO> getDeposits() {
        return deposits;
    }

    public void setDeposits(List<DepositDTO> deposits) {
        this.deposits = deposits;
    }
}
