package com.bank.repository;

import com.bank.model.History;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
    Page<History> findByUserId(Pageable pageable, Long id);
    List<History> findByUserId(Long id);
}
