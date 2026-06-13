package com.krishna.expensetracker.repository;

import com.krishna.expensetracker.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findAllByOrderByDateDesc();

    /**
     * Groups all expenses by category and sums the amounts.
     * Used to build the pie chart on the dashboard.
     */
    @Query("SELECT e.category AS category, SUM(e.amount) AS total " +
           "FROM Expense e GROUP BY e.category ORDER BY total DESC")
    List<CategoryTotal> sumAmountsByCategory();
}
