package org.example.expensetracking.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Table(name = "expense_aggragate")
@Entity
public class ExpenseAggragate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    private BigDecimal totalAmount;
    private String period;
    private LocalDate startDate;
    private LocalDate endDate;
}
