package main;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
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

    private UUID id;
    private LocalDate startDate;
    private BudgetPeriod period;
    private String name;
    private double limitAmount;
    // private ArrayList<Category> categorySpending = new ArrayList<>();

    public Budget(UUID id,BudgetPeriod period, double limitAmount, String name, LocalDate startDate) {
        this.id = id;
        this.limitAmount = limitAmount;
        this.period = period;
        this.name = name;
        this.startDate = startDate;
    }

    public Budget(BudgetPeriod period, double limitAmount, String name, LocalDate startDate) {
        this.id = UUID.randomUUID();
        this.limitAmount = limitAmount;
        this.period = period;
        this.name = name;
        this.startDate = startDate;
    }

    public UUID getID() {
        return this.id;
    }
    
    public BudgetPeriod getPeriod() {
        return this.period;
    }

    public double getLimitAmount() {
        return this.limitAmount;
    }

    public String getName() {
        return this.name;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public void setPeriod(BudgetPeriod period) {
        this.period = period;
    }

    public void setLimitAmount(double limitAmount) {
        this.limitAmount = limitAmount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public static void displayBudgets(HashMap<Integer, UUID> budgetMap, List<Budget> budgets) {
        System.out.println("Budgets:");
        System.out.printf("%7s %20s %20s %20s %20s\n", "Nr.", "Period", "Name", "Limit Amount", "Start Date");
        System.out.println("-".repeat(120));
        int budgetNumber = 1;
        budgetMap.clear();
        for (Budget budget : budgets) {
            budgetMap.put(budgetNumber, budget.getID());
            System.out.printf("%5s %20s %20s %20s %20s\n",
                budgetNumber++, budget.getPeriod(), budget.getName(),
                budget.getLimitAmount(), budget.getStartDate());
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