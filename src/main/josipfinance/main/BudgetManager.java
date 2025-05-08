package josipfinance.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class BudgetManager {

    public static ArrayList<Budget> getBudgetList(String filename) throws Exception {
        BufferedReader reader = Helper.getReader(filename);

        ArrayList<Budget> budgetList = new ArrayList<>();
        String line;

        reader.readLine();
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");

            UUID id = UUID.fromString(parts[0]);
            Budget.BudgetPeriod period = Budget.BudgetPeriod.valueOf(parts[1].toUpperCase());
            double limitAmount = Double.valueOf(parts[2]);
            String name = parts[3];
            LocalDate startDate = LocalDate.parse(parts[4]);

            Budget budget = new Budget(id, period, limitAmount, name, startDate);
            budgetList.add(budget);
        }
        return budgetList;
    }

    public static void addBudget(Budget budget, String filename) throws Exception {
        BufferedWriter writer = Helper.getWriter(filename, StandardOpenOption.APPEND);
        writer.write(budget.toString() + "\n");
        writer.close();
    }

    public static void deleteBudgetFromFile(UUID budgetID, String filename) throws Exception {
        BufferedReader reader = Helper.getReader(filename);
        ArrayList<String> lines = new ArrayList<>();
        String line;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length > 0 && !parts[0].equals(budgetID.toString())) {
                lines.add(line);
            }
        }
        reader.readLine();

        BufferedWriter writer = Helper.getWriter(filename, StandardOpenOption.TRUNCATE_EXISTING);
        for (String fileLine : lines) {
            writer.write(fileLine);
            writer.newLine();
        }
        writer.close();
    }
}
