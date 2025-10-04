package com.example.demo.model;

import com.example.demo.covertDateFormat.YearMonthConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.YearMonth;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private Users user;
    private String category;
    private BigDecimal limitAmount;

    @Convert(converter = YearMonthConverter.class)
    private YearMonth month;

    @Transient
    private BigDecimal spent;

}
















