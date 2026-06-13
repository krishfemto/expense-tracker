package com.krishna.expensetracker.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Bound to the "add expense" form fields.
 */
@Data
public class ExpenseForm {

    @NotBlank(message = "Please enter a description")
    private String description;

    @NotNull(message = "Please enter an amount")
    @DecimalMin(value = "0.01", message = "Amount must be greater than zero")
    private BigDecimal amount;

    @NotBlank(message = "Please select a category")
    private String category;

    @NotNull(message = "Please select a date")
    private LocalDate date = LocalDate.now();
}
