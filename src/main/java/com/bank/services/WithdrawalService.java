package com.bank.services;

import com.bank.dto.WithdrawalDTO;
import com.bank.dto.WithdrawalDataTableDTO;
import com.bank.model.*;
import com.bank.repository.HistoryRepository;
import com.bank.repository.UserRepository;
import com.bank.repository.WithdrawalRepository;
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
public class WithdrawalService {

    public WithdrawalRepository withdrawalRepository;
    public UserRepository userRepository;
    public HistoryRepository historyRepository;

    @Autowired
    public WithdrawalService(WithdrawalRepository withdrawalRepository, UserRepository userRepository,
                             HistoryRepository historyRepository) {
        this.withdrawalRepository = withdrawalRepository;
        this.userRepository = userRepository;
        this.historyRepository = historyRepository;
    }

    @Transactional
    public WithdrawalDataTableDTO getAllWithdrawals(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "creationDate"));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = this.userRepository.findByUsername(authentication.getName());
        Page<Withdrawal> withdrawal = this.withdrawalRepository.findByUserId(pageable, user.getId());
        return new WithdrawalDataTableDTO(withdrawal);
    }

    @Transactional
    public WithdrawalDTO withdrawMoney(WithdrawalDTO withdrawalDTO) {
        Withdrawal withdrawal = new Withdrawal();
        History history = new History();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = this.userRepository.findByUsername(authentication.getName());
        withdrawal.setAmount(withdrawalDTO.getAmount());
        withdrawal.setCreationDate(new Date().getTime());
        withdrawal.setUser(user);
        this.withdrawalRepository.save(withdrawal);

        history.setCreationDate(new Date().getTime());
        history.setAmount(withdrawalDTO.getAmount());
        history.setOperation(Operation.WITHDRAWAL);
        history.setUser(user);
        this.historyRepository.save(history);

        user.setBalance(user.getBalance() - withdrawalDTO.getAmount());
        user.getHistories().add(history);
        this.userRepository.save(user);
        return new WithdrawalDTO(withdrawal);
    }
}
