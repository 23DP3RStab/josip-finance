import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.time.LocalDate;

public class TransactionManager {

    public static ArrayList<Transaction> getTransactionList() throws Exception {
        BufferedReader reader = Helper.getReader("fake_transactions.csv");

        ArrayList<Transaction> transactionList = new ArrayList<>();
        String line;

        reader.readLine();
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");

            LocalDate date = LocalDate.parse(parts[0]);
            Transaction.TransactionType type = Transaction.TransactionType.valueOf(parts[1].toUpperCase());
            String narrative = parts[2];
            String bankReference = parts[3];
            double amount = Double.valueOf(parts[4]);

            Transaction transaction = new Transaction(date, type, narrative, bankReference, amount);
            transactionList.add(transaction);
        }
        return transactionList;
    }

    public static void addPerson(Transaction transaction) throws Exception {
        BufferedWriter writer = Helper.getWriter("fake_transactions.csv", StandardOpenOption.APPEND);
    }
}
