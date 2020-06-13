package com.bank.resources;

import com.bank.dto.DepositDTO;
import com.bank.dto.DepositDataTableDTO;
import com.bank.services.DepositService;
import com.bank.utility.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping()
    public DepositDataTableDTO getAllDeposits(@RequestBody Pager pager) {
        return this.depositService.getAllDeposits(pager.getPage(), pager.getSize());
    }

    @PostMapping("/addDeposit")
    public DepositDTO addDeposit(@RequestBody DepositDTO depositDTO) {
        return this.depositService.addDeposit(depositDTO);
    }
}
