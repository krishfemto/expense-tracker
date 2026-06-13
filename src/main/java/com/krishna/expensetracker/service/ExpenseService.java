package com.krishna.expensetracker.service;

import com.krishna.expensetracker.dto.ExpenseForm;
import com.krishna.expensetracker.entity.Expense;
import com.krishna.expensetracker.repository.CategoryTotal;
import com.krishna.expensetracker.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository repository;

    public List<Expense> getAllExpenses() {
        return repository.findAllByOrderByDateDesc();
    }

    public Expense addExpense(ExpenseForm form) {
        Expense expense = Expense.builder()
                .description(form.getDescription())
                .amount(form.getAmount())
                .category(form.getCategory())
                .date(form.getDate())
                .build();
        return repository.save(expense);
    }

    public void deleteExpense(Long id) {
        repository.deleteById(id);
    }

    public List<CategoryTotal> getCategoryTotals() {
        return repository.sumAmountsByCategory();
    }

    public BigDecimal getTotalSpending() {
        return getAllExpenses().stream()
                .map(Expense::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /** Fixed list shown in the category dropdown. */
    public List<String> getAvailableCategories() {
        return List.of("Food", "Transport", "Shopping", "Bills", "Entertainment", "Health", "Other");
    }
}
