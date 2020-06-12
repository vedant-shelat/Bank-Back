package com.bank.resources;

import com.bank.services.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/history")
public class HistoryResources {

    public HistoryService historyService;

    @Autowired
    public HistoryResources(HistoryService historyService) {
        this.historyService = historyService;
    }
}
