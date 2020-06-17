package com.bank.services;

import com.bank.model.*;
import com.bank.repository.HistoryRepository;
import com.bank.repository.UserRepository;
import com.bank.repository.WithdrawalRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WithdrawalServiceTest {

    @Mock
    public WithdrawalRepository withdrawalRepository;
    @Mock
    public UserRepository userRepository;
    @Mock
    public HistoryRepository historyRepository;
    @InjectMocks
    public WithdrawalService withdrawalService;

    @Before
    public void setUp() {
        withdrawalService = new WithdrawalService(withdrawalRepository, userRepository, historyRepository);
    }

    @Test()
    public void withdrawMoneyTest() {
        User user = new User();
        user.setId(1L);
        user.setCreationDate(new Date().getTime());
        user.setEmail("test@yopmail.com");
        user.setPassword("p");
        user.setFirstname("John");
        user.setLastname("Doe");
        user.setUsername("John");
        user.setRole(Role.ROLE_USER);
        when(this.userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));
        Withdrawal withdrawal = new Withdrawal(1L, 10.0, user);
        History history = new History(1L, Operation.WITHDRAWAL,10.0, user);

        when(this.withdrawalRepository.save(withdrawal)).thenReturn(withdrawal);

        history.setUser(user);
        when(this.historyRepository.save(history)).thenReturn(history);

        user.setBalance(10.0);
        user.getHistories().add(history);
        when(this.userRepository.save(user)).thenReturn(user);
        Assert.assertEquals(1L, withdrawal.getId().longValue());
    }
}
