package com.bank.services;

import com.bank.dto.DepositDTO;
import com.bank.dto.DepositDataTableDTO;
import com.bank.model.Deposit;
import com.bank.model.History;
import com.bank.model.Operation;
import com.bank.model.User;
import com.bank.repository.DepositRepository;
import com.bank.repository.HistoryRepository;
import com.bank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
public class DepositService {

    public DepositRepository depositRepository;
    public UserRepository userRepository;
    public HistoryRepository historyRepository;

    @Autowired
    public DepositService(DepositRepository depositRepository, UserRepository userRepository,
                          HistoryRepository historyRepository) {
        this.depositRepository = depositRepository;
        this.userRepository = userRepository;
        this.historyRepository = historyRepository;
    }

    @Transactional
    public DepositDataTableDTO getAllDeposits(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "creationDate"));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = this.userRepository.findByUsername(authentication.getName());
        Page<Deposit> deposit = this.depositRepository.findByUserId(pageable, user.getId());
        return new DepositDataTableDTO(deposit);
    }

    @Transactional
    public DepositDTO addDeposit(DepositDTO depositDTO) {
        Deposit deposit = new Deposit();
        History history = new History();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = this.userRepository.findByUsername(authentication.getName());
        deposit.setAmount(depositDTO.getAmount());
        deposit.setCreationDate(new Date().getTime());
        deposit.setUser(user);
        this.depositRepository.save(deposit);

        history.setCreationDate(new Date().getTime());
        history.setAmount(depositDTO.getAmount());
        history.setOperation(Operation.DEPOSIT);
        history.setUser(user);
        this.historyRepository.save(history);

        user.setBalance(user.getBalance() + depositDTO.getAmount());
        user.getHistories().add(history);
        this.userRepository.save(user);
        return new DepositDTO(deposit);
    }
}
