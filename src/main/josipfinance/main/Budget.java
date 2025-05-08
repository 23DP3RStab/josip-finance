package josipfinance.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Budget {
    public enum BudgetPeriod {
        DAILY,
        WEEKLY,
        MONTHLY,
        YEARLY
    }

    private final UUID id;
    private LocalDate startDate;
    private BudgetPeriod period;
    private String name;
    private double limitAmount;

    public Budget(UUID id, BudgetPeriod period, double limitAmount, String name, LocalDate startDate) {
        this.id = id;
        this.limitAmount = limitAmount;
        this.period = period;
        this.name = name;
        this.startDate = startDate;
    }

    public Budget(BudgetPeriod period, double limitAmount, String name, LocalDate startDate) {
        this(UUID.randomUUID(), period, limitAmount, name, startDate);
    }

    public UUID getID() { return this.id; }
    public BudgetPeriod getPeriod() { return this.period; }
    public double getLimitAmount() { return this.limitAmount; }
    public String getName() { return this.name; }
    public LocalDate getStartDate() { return this.startDate; }

    public void setPeriod(BudgetPeriod period) { this.period = period; }
    public void setLimitAmount(double limitAmount) {
        if (limitAmount < 0) {
            throw new IllegalArgumentException("Limit amout can't be negative.");
        }
        this.limitAmount = limitAmount; 
    }
    public void setName(String name) { this.name = name; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public static void displayBudgets(HashMap<Integer, UUID> budgetMap, List<Budget> budgets) {
        System.out.println(App.VIOLET + "Budgets:" + App.RESET);
        System.out.printf("%5s %15s %17s %20s %13s\n", "Nr.", "Period", "Name", "Limit Amount", "Start Date");
        System.out.println("-".repeat(120));
        int budgetNumber = 1;
        budgetMap.clear();
        for (Budget budget : budgets) {
            budgetMap.put(budgetNumber, budget.getID());
            System.out.printf("%5s %15s %20s %15s %15s\n",
                budgetNumber++, budget.getPeriod(), budget.getName(),
                budget.getLimitAmount(), budget.getStartDate());
        }
    }

    public static void deleteBudget(Scanner scanner, HashMap<Integer, UUID> budgetMap, List<Budget> budgets) {
        System.out.println();
        System.out.println(App.VIOLET + "Enter the budget number to delete: " + App.RESET);
        int budgetNumberToDelete = 0;
        try {
            budgetNumberToDelete = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println(App.CLEAR_SCREEN);
            System.out.println(App.RED + "Invalid budget number. Please try again." + App.RESET);
        }
        scanner.nextLine();
        if (budgetMap.containsKey(budgetNumberToDelete)) {
            UUID budgetToDelete = budgetMap.get(budgetNumberToDelete);
            budgets.removeIf(budget -> budget.getID().equals(budgetToDelete));
            try {
                BudgetManager.deleteBudgetFromFile(budgetToDelete, "budgets.csv");
                System.out.println(App.CLEAR_SCREEN);
                System.out.println(App.GREEN + "Budget deleted successfully!" + App.RESET);
            } catch (Exception e) {
                System.out.println(App.RED + "An error occurred while deleting the budget: " + e.getMessage() + App.RESET);
            }
        } else {
            System.out.println(App.CLEAR_SCREEN);
            System.out.println(App.RED + "Invalid budget number. Please try again." + App.RESET);
        }
    }

    public static List<Budget> sortBudgets(List<Budget> budgets, Comparator<Budget> comparator) {
        List<Budget> sortedBudgets = new ArrayList<>(budgets);
        sortedBudgets.sort(comparator);
        return sortedBudgets;
    }

    public static List<Budget> filterBudgets(List<Budget> budgets, Predicate<Budget> predicate) {
        return budgets.stream()
            .filter(predicate)
            .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return id + "," + period + "," + limitAmount + "," + name + "," + startDate;
    }
}