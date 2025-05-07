package josipfinance.test;

import josipfinance.main.Budget;
import josipfinance.main.BudgetManager;
import josipfinance.main.Helper;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.nio.file.StandardOpenOption;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BudgetManagerTest {

    @Test
    void testGetBudgetListReadsCorrectly() throws Exception {
        String content = "id,period,limitAmount,name,startDate\n"
                       + "6da8a9b3-a675-4b04-9325-478e661c976c,MONTHLY,1000.0,Groceries,2024-01-01\n"
                       + "5f7574e4-0063-46ba-871d-c175f3c9c006,WEEKLY,250.0,Transport,2024-01-08";

        try (BufferedWriter writer = Helper.getWriter("test_budgets.csv", StandardOpenOption.CREATE)) {
            writer.write(content);
        }

        List<Budget> budgets = BudgetManager.getBudgetList("test_budgets.csv");
        assertEquals(2, budgets.size());

        Budget first = budgets.get(0);
        assertEquals("Groceries", first.getName());
        assertEquals(1000.0, first.getLimitAmount());
        assertEquals(Budget.BudgetPeriod.MONTHLY, first.getPeriod());

        Budget second = budgets.get(1);
        assertEquals("Transport", second.getName());
        assertEquals(Budget.BudgetPeriod.WEEKLY, second.getPeriod());
    }
}
