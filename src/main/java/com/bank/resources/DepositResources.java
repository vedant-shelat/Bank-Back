package com.bank.resources;

import com.bank.services.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deposit")
public class DepositResources {

    public DepositService depositService;

    @Autowired
    public DepositResources(DepositService depositService) {
        this.depositService = depositService;
    }
}
