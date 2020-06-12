package com.bank.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class User extends AbstractEntity {

    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private Double balance = 0.0;
    @OneToMany(fetch = FetchType.LAZY)
    private List<History> histories;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Deposit> deposits;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Withdrawal> withdrawals;

    public User() {}

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public List<History> getHistories() {
        return histories;
    }

    public void setHistories(List<History> histories) {
        this.histories = histories;
    }

    public List<Deposit> getDeposits() {
        return deposits;
    }

    public void setDeposits(List<Deposit> deposits) {
        this.deposits = deposits;
    }

    public List<Withdrawal> getWithdrawals() {
        return withdrawals;
    }

    public void setWithdrawals(List<Withdrawal> withdrawals) {
        this.withdrawals = withdrawals;
    }
}
