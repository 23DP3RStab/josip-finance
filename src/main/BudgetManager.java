package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;

public class BudgetManager {

    public static ArrayList<Budget> getBudgetList() throws Exception {
        BufferedReader reader = Helper.getReader("budgets.csv");

        ArrayList<Budget> budgetList = new ArrayList<>();
        String line;

        reader.readLine();
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");

            int id = Integer.valueOf(parts[0]);
            LocalDate startDate = LocalDate.parse(parts[1]);
            Budget.BudgetPeriod period = Budget.BudgetPeriod.valueOf(parts[2].toUpperCase());
            String name = parts[3];
            double limitAmount = Double.valueOf(parts[4]);

            Budget budget = new Budget(id, period, limitAmount, name, startDate);
            budgetList.add(budget);
        }
        return budgetList;
    }

    public static void addBudget(Budget budget) throws Exception {
        BufferedWriter writer = Helper.getWriter("budgets.csv", StandardOpenOption.APPEND);
    }
}
