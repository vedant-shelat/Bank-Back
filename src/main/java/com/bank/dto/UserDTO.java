package com.bank.dto;

import com.bank.model.Role;
import com.bank.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserDTO {

    private Long id;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password;
    private Role role;
    private Double balance;
    private List<HistoryDTO> histories = new ArrayList<>();

    public UserDTO() {}

    public UserDTO(User user) {
        this.id = user.getId();
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.balance = user.getBalance();
        if(user.getHistories() != null) {
            this.histories = user.getHistories().stream().map(h->  new HistoryDTO(h)).collect(Collectors.toList());
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<HistoryDTO> getHistories() {
        return histories;
    }

    public void setHistories(List<HistoryDTO> histories) {
        this.histories = histories;
    }
}
