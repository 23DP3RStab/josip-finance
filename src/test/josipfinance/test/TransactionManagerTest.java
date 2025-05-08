package josipfinance.test;

import josipfinance.main.Transaction;
import josipfinance.main.TransactionManager;
import josipfinance.main.Helper;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TransactionManagerTest {

    @Test
    void testGetTransactionListParsesCorrectly() throws Exception {
        String content = "id,date,type,narrative,bankReference,amount,category\n"
                       + "f2b6823a-9ac7-4561-a830-5ef770dfedc2,2024-01-01,INCOMING,Salary,REF001,3000.0,Income\n"
                       + "9d16cc37-c57d-42a6-9f65-e497f9bf2bdd,2024-01-05,PURCHASE,Books,REF002,45.5,Education";

        try (BufferedWriter writer = Helper.getWriter("test_transactions.csv", StandardOpenOption.CREATE)) {
            writer.write(content);
        }

        ArrayList<Transaction> transactions = TransactionManager.getTransactionList("test_transactions.csv");
        assertEquals(2, transactions.size());

        Transaction first = transactions.get(0);
        assertEquals("Salary", first.getNarrative());
        assertEquals(3000.0, first.getAmount());
        assertEquals(Transaction.TransactionType.INCOMING, first.getType());

        Transaction second = transactions.get(1);
        assertEquals("Books", second.getNarrative());
        assertEquals(Transaction.TransactionType.PURCHASE, second.getType());
        assertEquals("Education", second.getCategory());
    }
}
