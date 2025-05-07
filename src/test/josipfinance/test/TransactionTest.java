package josipfinance.test;

import josipfinance.main.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

    private Transaction transaction;

    @BeforeEach
    void setUp() {
        transaction = new Transaction(UUID.randomUUID(), LocalDate.of(2024, 1, 10), Transaction.TransactionType.OUTGOING, "Rent payment", "XYZ123", 1200.0, "Housing");
    }

    @Test
    void testTransactionCreation() {
        assertEquals(LocalDate.of(2024, 1, 10), transaction.getDate());
        assertEquals(Transaction.TransactionType.OUTGOING, transaction.getType());
        assertEquals("Rent payment", transaction.getNarrative());
        assertEquals("XYZ123", transaction.getBankReference());
        assertEquals(1200.0, transaction.getAmount());
        assertEquals("Housing", transaction.getCategory());
        assertNotNull(transaction.getID());
    }

    @Test
    void testSettersAndGetters() {
        transaction.setNarrative("Groceries");
        transaction.setBankReference("ABC999");
        transaction.setAmount(85.5);
        transaction.setType(Transaction.TransactionType.PURCHASE);
        transaction.setDate(LocalDate.of(2024, 1, 15));
        transaction.setCategory("Food");

        assertEquals("Groceries", transaction.getNarrative());
        assertEquals("ABC999", transaction.getBankReference());
        assertEquals(85.5, transaction.getAmount());
        assertEquals(Transaction.TransactionType.PURCHASE, transaction.getType());
        assertEquals(LocalDate.of(2024, 1, 15), transaction.getDate());
        assertEquals("Food", transaction.getCategory());
    }

    @Test
    void testTransactionTypeEnum() {
        assertEquals(Transaction.TransactionType.INCOMING, Transaction.TransactionType.valueOf("INCOMING"));
        assertThrows(IllegalArgumentException.class, () -> {
            Transaction.TransactionType.valueOf("INVALID");
        });
    }
}
