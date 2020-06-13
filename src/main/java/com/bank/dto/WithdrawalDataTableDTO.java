package com.bank.dto;

import com.bank.model.Withdrawal;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WithdrawalDataTableDTO {

    private Long length;
    private List<WithdrawalDTO> withdrawals = new ArrayList<>();

    public WithdrawalDataTableDTO() {}

    public WithdrawalDataTableDTO(Page<Withdrawal> withdrawals) {
        this.length = withdrawals.getTotalElements();
        this.withdrawals = withdrawals.stream().map(w-> new WithdrawalDTO(w)).collect(Collectors.toList());
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public List<WithdrawalDTO> getWithdrawals() {
        return withdrawals;
    }

    public void setWithdrawals(List<WithdrawalDTO> withdrawals) {
        this.withdrawals = withdrawals;
    }
}
