package com.bank.services;

import com.bank.model.*;
import com.bank.repository.DepositRepository;
import com.bank.repository.HistoryRepository;
import com.bank.repository.UserRepository;
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
public class DepositServiceTest {

    @Mock
    public DepositRepository depositRepository;
    @Mock
    public UserRepository userRepository;
    @Mock
    public HistoryRepository historyRepository;
    @InjectMocks
    public DepositService depositService;

    @Before
    public void setUp() {
        depositService = new DepositService(depositRepository, userRepository, historyRepository);
    }

    @Test()
    public void addDepositTest() {
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
        Deposit deposit = new Deposit(1L, 10.0, user);
        History history = new History(1L, Operation.DEPOSIT,10.0, user);

        when(this.depositRepository.save(deposit)).thenReturn(deposit);

        history.setUser(user);
        when(this.historyRepository.save(history)).thenReturn(history);

        user.setBalance(10.0);
        user.getHistories().add(history);
        when(this.userRepository.save(user)).thenReturn(user);
        Assert.assertEquals(1L, deposit.getId().longValue());
    }
}
