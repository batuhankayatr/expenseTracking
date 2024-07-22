package org.example.expensetracking.business;

import org.example.expensetracking.dataAccess.TransactionLogRepository;
import org.example.expensetracking.dataAccess.TransactionsRepository;
import org.example.expensetracking.entities.TransactionLog;
import org.example.expensetracking.entities.Transactions;
import org.example.expensetracking.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionsService {

    @Autowired
    private TransactionsRepository transactionsRepository;

    @Autowired
    private TransactionLogRepository transactionLogsRepository;

    public Transactions createTransaction(Transactions transaction) {
        Transactions createdTransaction = transactionsRepository.save(transaction);
        logTransaction(transaction.getUser(), "CREATE", "Transaction created: " + transaction.getDescription());
        return createdTransaction;
    }

    public Optional<Transactions> getTransactionById(Integer id) {
        return transactionsRepository.findById(id);
    }

    public List<Transactions> getAllTransactions() {
        return transactionsRepository.findAll();
    }

    public Transactions updateTransaction(Integer id, Transactions transactionDetails) {
        Transactions transaction = transactionsRepository.findById(id).orElseThrow(() -> new RuntimeException("Transaction not found"));
        transaction.setDescription(transactionDetails.getDescription());
        transaction.setAmount(transactionDetails.getAmount());
        transaction.setLocalDateTime(transactionDetails.getLocalDateTime());
        Transactions updatedTransaction = transactionsRepository.save(transaction);
        logTransaction(transaction.getUser(), "UPDATE", "Transaction updated: " + transaction.getDescription());
        return updatedTransaction;
    }

    public void deleteTransaction(Integer id) {
        Transactions transaction = transactionsRepository.findById(id).orElseThrow(() -> new RuntimeException("Transaction not found"));
        transactionsRepository.deleteById(id);
        logTransaction(transaction.getUser(), "DELETE", "Transaction deleted: " + transaction.getDescription());
    }

    public BigDecimal getTotalExpenseByUserId(Users user) {
        return transactionsRepository.getTotalExpenseByUserId(user.getId());
    }

    public List<Transactions> getTransactionsByUser(Users user) {
        return transactionsRepository.findByUser(user);
    }

    private void logTransaction(Users user, String action, String description) {
        TransactionLog log = new TransactionLog();
        log.setUser(user);
        log.setAction(action);
        log.setTimestamp(LocalDateTime.now());
        log.setDescription(description);
        transactionLogsRepository.save(log);
    }
}
