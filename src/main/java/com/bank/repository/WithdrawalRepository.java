package com.bank.repository;

import com.bank.model.Deposit;
import com.bank.model.Withdrawal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WithdrawalRepository extends JpaRepository<Withdrawal, Long> {
    Page<Withdrawal> findByUserId(Pageable pageable, Long id);
}
