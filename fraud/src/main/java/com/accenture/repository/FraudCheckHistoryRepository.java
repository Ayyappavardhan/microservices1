package com.accenture.repository;

import com.accenture.entity.FraudCheckHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FraudCheckHistoryRepository  extends JpaRepository<FraudCheckHistory,Integer> {
    Optional<FraudCheckHistory> findByEmail(String email);
}
