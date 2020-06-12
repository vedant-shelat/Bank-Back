package com.bank.resources;

import com.bank.services.WithdrawalService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
