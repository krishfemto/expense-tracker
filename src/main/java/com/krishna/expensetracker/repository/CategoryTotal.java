package com.krishna.expensetracker.repository;

import java.math.BigDecimal;

/**
 * Projection used by the GROUP BY query in ExpenseRepository -
 * each row becomes one slice of the pie chart.
 */
public interface CategoryTotal {
    String getCategory();
    BigDecimal getTotal();
}
