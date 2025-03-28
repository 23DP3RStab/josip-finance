package main;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

import main.Budget.BudgetPeriod;
import main.Category.CategoryType;
import main.Transaction.TransactionType;

public class App {
    public static void main(String[] args) throws Exception {
        
        ArrayList<Transaction> transactions = TransactionManager.getTransactionList();
    
        // for (Transaction transaction : transactions) {
        //     System.out.println(transaction);
        // }

        Transaction transaction = new Transaction(LocalDate.parse("2025-05-02"), TransactionType.INCOMING, "sonders sent monke", "IDK525IDK", 123.25);
        System.out.println(transaction);

        Budget budget = new Budget(BudgetPeriod.MONTHLY, 1000, "Sibder", LocalDate.parse("2025-01-20"));
        System.out.println(budget);

        BigDecimal limit = new BigDecimal("250.0");
        
        Category transport = new Category("Transport", CategoryType.EXPENSE, limit);
        System.out.println(transport);

        
    }
}