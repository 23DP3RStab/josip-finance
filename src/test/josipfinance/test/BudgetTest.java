package josipfinance.test;

import josipfinance.main.Budget;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BudgetTest {

    private Budget budget;

    @BeforeEach
    void setUp() {
        budget = new Budget(Budget.BudgetPeriod.MONTHLY, 500.0, "Groceries", LocalDate.of(2024, 1, 1));
    }

    @Test
    void testBudgetCreation() {
        assertEquals("Groceries", budget.getName());
        assertEquals(LocalDate.of(2024, 1, 1), budget.getStartDate());
        assertEquals(Budget.BudgetPeriod.MONTHLY, budget.getPeriod());
        assertEquals(500.0, budget.getLimitAmount());
        assertNotNull(budget.getID());
    }

    @Test
    void testSettersAndGetters() {
        budget.setName("Utilities");
        budget.setLimitAmount(300.0);
        budget.setPeriod(Budget.BudgetPeriod.WEEKLY);
        budget.setStartDate(LocalDate.of(2024, 2, 1));

        assertEquals("Utilities", budget.getName());
        assertEquals(300.0, budget.getLimitAmount());
        assertEquals(Budget.BudgetPeriod.WEEKLY, budget.getPeriod());
        assertEquals(LocalDate.of(2024, 2, 1), budget.getStartDate());
    }

    @Test
    void testBudgetPeriodEnum() {
        assertEquals(Budget.BudgetPeriod.DAILY, Budget.BudgetPeriod.valueOf("DAILY"));
        assertEquals(Budget.BudgetPeriod.YEARLY, Budget.BudgetPeriod.valueOf("YEARLY"));
    }
}
