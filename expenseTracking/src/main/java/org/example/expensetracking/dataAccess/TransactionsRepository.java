package org.example.expensetracking.dataAccess;

import org.example.expensetracking.entities.Transactions;
import org.example.expensetracking.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Integer> {

    @Query("SELECT SUM(t.amount) FROM Transactions t WHERE t.user.id = :userId")
    BigDecimal getTotalExpenseByUserId(@Param("userId") Long userId);

    List<Transactions> findByUser(Users user);

    @Query("SELECT DISTINCT t.user FROM Transactions t")
    List<Users> findAllUsers();

    @Query("SELECT SUM(t.amount) FROM Transactions t WHERE t.user = :user AND t.localDateTime >= :startDate AND t.localDateTime < :endDate")
    BigDecimal getTotalAmountForPeriod(@Param("user") Users user, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
