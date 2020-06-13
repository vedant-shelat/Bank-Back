package com.bank.services;

import com.bank.dto.HistoryDataTableDTO;
import com.bank.model.History;
import com.bank.model.User;
import com.bank.repository.HistoryRepository;
import com.bank.repository.UserRepository;
import com.opencsv.CSVWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class HistoryService {

    public HistoryRepository historyRepository;
    public UserRepository userRepository;
    private static final String[] statementRow = {"Date", "Operation Type", "Amount"};

    @Autowired
    public HistoryService(HistoryRepository historyRepository,UserRepository userRepository) {
        this.historyRepository = historyRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public HistoryDataTableDTO getAllUserHistory(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "creationDate"));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = this.userRepository.findByUsername(authentication.getName());
        Page<History> history = this.historyRepository.findByUserId(pageable, user.getId());
        return new HistoryDataTableDTO(history);
    }

    @Transactional
    public byte[] downloadStatement() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy, HH:mm");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = this.userRepository.findByUsername(authentication.getName());
        List<History> histories = this.historyRepository.findByUserId(user.getId());
        List<String[]> rows = new ArrayList<>();
        rows.add(statementRow);
        for(History history: histories) {
            String[] row = new String[statementRow.length];
            Date date = new Date(history.getCreationDate());
            row[0] = sdf.format(date);
            row[1] = history.getOperation().toString();
            if(history.getOperation().toString().equals("DEPOSIT")) {
                row[2] = "+ " + history.getAmount().toString();
            } else {
                row[2] = "- " + history.getAmount().toString();
            }

            rows.add(row);
        }
        return this.writeRows(rows, user);
    }

    public byte[] writeRows(List<String[]> rows, User user) {
        try {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(buffer, StandardCharsets.ISO_8859_1);
            CSVWriter csvWriter = new CSVWriter(osw, ';', CSVWriter.DEFAULT_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);

            String balance = user.getBalance().toString();
            String title[] = {"Your Total Available Balance is " + balance };
            csvWriter.writeNext(title);

            for(String[] row: rows) {
                csvWriter.writeNext(row);
            }
            csvWriter.flush();
            csvWriter.close();
            return buffer.toByteArray();
        } catch(Exception e) {
            throw new RuntimeException("Error CSV File", e);
        }
    }
}
