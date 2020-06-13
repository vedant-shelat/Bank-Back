package com.bank.repository;

import com.bank.model.Deposit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositRepository extends JpaRepository<Deposit, Long> {
    Page<Deposit> findByUserId(Pageable pageable, Long id);
}
