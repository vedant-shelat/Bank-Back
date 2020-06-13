package com.bank.resources;

import com.bank.dto.DepositDataTableDTO;
import com.bank.dto.HistoryDataTableDTO;
import com.bank.services.HistoryService;
import com.bank.utility.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

@RestController
@RequestMapping("/history")
public class HistoryResources {

    public HistoryService historyService;

    @Autowired
    public HistoryResources(HistoryService historyService) {
        this.historyService = historyService;
    }

    @PostMapping()
    public HistoryDataTableDTO getAllUserHistory(@RequestBody Pager pager) {
        return this.historyService.getAllUserHistory(pager.getPage(), pager.getSize());
    }

    @GetMapping("/downloadStatement")
    public void downloadStatement(HttpServletResponse response) {
        byte[] buffer = this.historyService.downloadStatement();
        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachement;filename=users.csv");
        response.setContentLength(buffer.length);
        try (InputStream inputStream = new ByteArrayInputStream(buffer)){
            FileCopyUtils.copy(inputStream, response.getOutputStream());
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
