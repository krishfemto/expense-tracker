package com.krishna.expensetracker.controller;

import com.krishna.expensetracker.dto.ExpenseForm;
import com.krishna.expensetracker.repository.CategoryTotal;
import com.krishna.expensetracker.service.ExpenseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class WebController {

    private final ExpenseService service;

    @GetMapping("/")
    public String home(Model model) {
        addCommonAttributes(model);
        model.addAttribute("expenseForm", new ExpenseForm());
        return "index";
    }

    @PostMapping("/expenses")
    public String addExpense(@Valid @ModelAttribute("expenseForm") ExpenseForm form,
                              BindingResult bindingResult,
                              Model model) {

        if (bindingResult.hasErrors()) {
            addCommonAttributes(model);
            return "index";
        }

        service.addExpense(form);
        return "redirect:/";
    }

    @PostMapping("/expenses/{id}/delete")
    public String deleteExpense(@PathVariable Long id) {
        service.deleteExpense(id);
        return "redirect:/";
    }

    private void addCommonAttributes(Model model) {
        model.addAttribute("expenses", service.getAllExpenses());
        model.addAttribute("categories", service.getAvailableCategories());
        model.addAttribute("totalSpending", service.getTotalSpending());

        List<CategoryTotal> categoryTotals = service.getCategoryTotals();
        model.addAttribute("chartLabels",
                categoryTotals.stream().map(CategoryTotal::getCategory).collect(Collectors.toList()));
        model.addAttribute("chartValues",
                categoryTotals.stream().map(CategoryTotal::getTotal).collect(Collectors.toList()));
    }
}
