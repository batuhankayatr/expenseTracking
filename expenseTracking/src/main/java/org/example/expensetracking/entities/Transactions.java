package org.example.expensetracking.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "transactions")
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String description;
    private BigDecimal amount;
    private LocalDateTime localDateTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;
}
