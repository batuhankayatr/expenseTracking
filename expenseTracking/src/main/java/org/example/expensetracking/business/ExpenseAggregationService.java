package org.example.expensetracking.business;

import org.example.expensetracking.dataAccess.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
@Service
public class ExpenseAggregationService {
    @Autowired
    private TransactionsRepository transactionsRepository;

    @Scheduled(cron = "0 0 0 * * *") // Her gün gece yarısı çalışır
    public void aggregateDailyExpenses() {
        LocalDateTime endDate = LocalDateTime.now();
        LocalDateTime startDate = endDate.minus(1, ChronoUnit.DAYS);
        aggregateExpenses(startDate, endDate, "daily");
    }

    @Scheduled(cron = "0 0 0 * * MON") // Her hafta başında çalışır
    public void aggregateWeeklyExpenses() {
        LocalDateTime endDate = LocalDateTime.now();
        LocalDateTime startDate = endDate.minus(1, ChronoUnit.WEEKS);
        aggregateExpenses(startDate, endDate, "weekly");
    }

    @Scheduled(cron = "0 0 0 1 * *") // Her ayın başında çalışır
    public void aggregateMonthlyExpenses() {
        LocalDateTime endDate = LocalDateTime.now();
        LocalDateTime startDate = endDate.minus(1, ChronoUnit.MONTHS);
        aggregateExpenses(startDate, endDate, "monthly");
    }

    private void aggregateExpenses(LocalDateTime startDate, LocalDateTime endDate, String period) {
        List<Object[]> results = transactionsRepository.getTotalExpenseForAllUsersAndPeriod(startDate, endDate);
        for (Object[] result : results) {
            Long userId = (Long) result[0];
            BigDecimal totalAmount = (BigDecimal) result[1];

            System.out.printf("User ID: %d, Total %s Expense: %s%n", userId, period, totalAmount);
        }
    }
}
