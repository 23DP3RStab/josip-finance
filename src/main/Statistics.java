package main;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Comparator;

public class Statistics {

    private List<Transaction> transactions;
    private List<Budget> budgets;

    public Statistics(List<Transaction> transactions, List<Budget> budgets) {
        this.transactions = transactions;
        this.budgets = budgets;
    }

    public void viewStatistics() {
        double totalIncome = transactions.stream()
                .filter(t -> t.getType() == Transaction.TransactionType.INCOMING)
                .mapToDouble(Transaction::getAmount)
                .sum();

        double totalExpenses = transactions.stream()
                .filter(t -> t.getType() == Transaction.TransactionType.OUTGOING || t.getType() == Transaction.TransactionType.PURCHASE)
                .mapToDouble(Transaction::getAmount)
                .sum();

        double netBalance = totalIncome + totalExpenses;

        System.out.println(App.VIOLET + "\n--- Financial Summary ---" + App.RESET);
        System.out.printf("Total Income:    %.2f\n", totalIncome);
        System.out.printf("Total Expenses:  %.2f\n", totalExpenses);
        System.out.printf("Net Balance:     %.2f\n", netBalance);

        System.out.println(App.VIOLET + "\n--- Category Spending ---" + App.RESET);
        Map<String, Double> categoryTotals = new HashMap<>();
        double totalNegative = 0;

        for (Transaction t : transactions) {
            if (t.getAmount() < 0) {
                categoryTotals.merge(t.getCategory(), t.getAmount(), Double::sum);
                totalNegative += t.getAmount();
            }
        }

        for (Map.Entry<String, Double> entry : categoryTotals.entrySet()) {
            double percent = (entry.getValue() / totalNegative) * 100;
            System.out.printf("%-15s: %8.2f (%5.1f%%)\n", entry.getKey(), entry.getValue(), percent);
        }

        System.out.println(App.VIOLET + "\n--- Budgets vs Actual ---" + App.RESET);
        for (Budget b : budgets) {
            String category = b.getName();
            double actual = categoryTotals.getOrDefault(category, 0.0);
            double limit = b.getLimitAmount();
            String status = actual <= -limit ? App.RED + "OVER" + App.RESET : App.GREEN + "OK" + App.RESET;

            System.out.printf("Budget: %-15s | Limit: %8.2f | Actual: %8.2f | Status: %s\n",
                    category, limit, -actual, status);
        }

        System.out.println(App.VIOLET + "\n--- Top 5 Expenses ---" + App.RESET);
        transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .sorted(Comparator.comparingDouble(Transaction::getAmount))
                .limit(5)
                .forEach(t -> System.out.printf("%s | %-14s | %8.2f | %s\n",
                        t.getDate(), t.getCategory(), t.getAmount(), t.getNarrative()));
    }
}
