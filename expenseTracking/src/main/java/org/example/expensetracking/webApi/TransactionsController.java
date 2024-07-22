package org.example.expensetracking.webApi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.expensetracking.business.TransactionsService;
import org.example.expensetracking.entities.Transactions;
import org.example.expensetracking.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@Tag(name = "Transactions", description = "Transactions API")
@AllArgsConstructor
@NoArgsConstructor
public class TransactionsController {

    @Autowired
    private TransactionsService transactionsService;

    @PostMapping("/create")
    @Operation(summary = "Create a new transaction", description = "Creates a new transaction")
    public ResponseEntity<Transactions> createTransaction(@RequestBody Transactions transaction) {
        Transactions createdTransaction = transactionsService.createTransaction(transaction);
        return ResponseEntity.ok(createdTransaction);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get transaction by ID", description = "Retrieves a transaction by its ID")
    public ResponseEntity<Transactions> getTransactionById(@PathVariable Integer id) {
        return transactionsService.getTransactionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    @Operation(summary = "Get all transactions", description = "Retrieves all transactions")
    public ResponseEntity<List<Transactions>> getAllTransactions() {
        List<Transactions> transactions = transactionsService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update a transaction", description = "Updates an existing transaction")
    public ResponseEntity<Transactions> updateTransaction(@PathVariable Integer id, @RequestBody Transactions transactionDetails) {
        Transactions updatedTransaction = transactionsService.updateTransaction(id, transactionDetails);
        return ResponseEntity.ok(updatedTransaction);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a transaction", description = "Deletes a transaction by its ID")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Integer id) {
        transactionsService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/totalExpense/{userId}")
    @Operation(summary = "Get total expense by user ID", description = "Calculates the total expense for a user")
    public ResponseEntity<BigDecimal> getTotalExpenseByUserId(@PathVariable long userId) {
        Users user = new Users();
        user.setId(userId); // User nesnesinin ID'sini ayarlayın
        BigDecimal totalExpense = transactionsService.getTotalExpenseByUserId(user);
        return ResponseEntity.ok(totalExpense);
    }

    @GetMapping("/userTransactions/{userId}")
    @Operation(summary = "Get transactions by user ID", description = "Retrieves transactions for a specific user")
    public ResponseEntity<List<Transactions>> getTransactionsByUserId(@PathVariable long userId) {
        Users user = new Users();
        user.setId(userId);
        List<Transactions> userTransactions = transactionsService.getTransactionsByUser(user);
        return ResponseEntity.ok(userTransactions);
    }
}
