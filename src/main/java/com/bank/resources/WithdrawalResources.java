package com.bank.resources;

import com.bank.dto.WithdrawalDTO;
import com.bank.dto.WithdrawalDataTableDTO;
import com.bank.services.WithdrawalService;
import com.bank.utility.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/withdrawal")
public class WithdrawalResources {

    public WithdrawalService withdrawalService;

    @Autowired
    public WithdrawalResources(WithdrawalService withdrawalService) {
        this.withdrawalService = withdrawalService;
    }

    @PostMapping()
    public WithdrawalDataTableDTO getAllWithdrawals(@RequestBody Pager pager) {
        return this.withdrawalService.getAllWithdrawals(pager.getPage(), pager.getSize());
    }

    @PostMapping("/withdrawMoney")
    public WithdrawalDTO withdrawMoney(@RequestBody WithdrawalDTO withdrawalDTO) {
        return this.withdrawalService.withdrawMoney(withdrawalDTO);
    }
}
