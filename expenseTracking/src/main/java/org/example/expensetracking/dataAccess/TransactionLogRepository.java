package org.example.expensetracking.dataAccess;

import org.example.expensetracking.entities.TransactionLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionLogRepository extends JpaRepository<TransactionLog, Long> {
}
