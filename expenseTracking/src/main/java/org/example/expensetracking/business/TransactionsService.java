package org.example.expensetracking.business;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.expensetracking.dataAccess.TransactionsRepository;
import org.example.expensetracking.entities.Transactions;
import org.example.expensetracking.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionsService {

    @Autowired
    private TransactionsRepository transactionsRepository;

    public Transactions createTransaction(Transactions transaction) {
        return transactionsRepository.save(transaction);
    }

    public Optional<Transactions> getTransactionById(Integer id) {
        return transactionsRepository.findById(id);
    }

    public List<Transactions> getAllTransactions() {
        return transactionsRepository.findAll();
    }

    public Transactions updateTransaction(Integer id, Transactions transactionDetails) {
        Transactions transaction = transactionsRepository.findById(id).orElseThrow();
        transaction.setDescription(transactionDetails.getDescription());
        transaction.setAmount(transactionDetails.getAmount());
        transaction.setLocalDateTime(transactionDetails.getLocalDateTime());
        return transactionsRepository.save(transaction);
    }

    public void deleteTransaction(Integer id) {
        transactionsRepository.deleteById(id);
    }

    public BigDecimal getTotalExpenseByUserId(Users user) {
        return transactionsRepository.getTotalExpenseByUserId(user.getId());
    }

    public List<Transactions> getTransactionsByUser(Users user) {
        return transactionsRepository.findByUser(user);
    }
}
