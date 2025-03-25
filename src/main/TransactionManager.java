package main;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.UUID;

public class TransactionManager {

    public static ArrayList<Transaction> getTransactionList() throws Exception {
        BufferedReader reader = Helper.getReader("fake_transactions.csv");

        ArrayList<Transaction> transactionList = new ArrayList<>();
        String line;

        reader.readLine();
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");

            UUID id = UUID.fromString(parts[0]);
            LocalDate date = LocalDate.parse(parts[1]);
            Transaction.TransactionType type = Transaction.TransactionType.valueOf(parts[2].toUpperCase());
            String narrative = parts[3];
            String bankReference = parts[4];
            double amount = Double.valueOf(parts[5]);

            Transaction transaction = new Transaction(id, date, type, narrative, bankReference, amount);
            transactionList.add(transaction);
        }
        return transactionList;
    }

    public static void addPerson(Transaction transaction) throws Exception {
        BufferedWriter writer = Helper.getWriter("fake_transactions.csv", StandardOpenOption.APPEND);
    }
}
