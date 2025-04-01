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

            UUID id;
            if (parts[0].isEmpty()) {
                id = UUID.randomUUID();
            } else {
                try {
                    id = UUID.fromString(parts[0]);
                } catch (Exception)

            }

            UUID id = parts[0].isEmpty() ? UUID.randomUUID() : UUID.fromString(parts[0]);
            LocalDate date = parts[1].isEmpty() ? LocalDate.now() : LocalDate.parse(parts[1]);
            double amount = parts[5].isEmpty() ? 0.0 : Double.parseDouble(parts[5]);

            Transaction.TransactionType type;
            if (parts[2].isEmpty()) {
                type = amount < 0 ? Transaction.TransactionType.OUTGOING : Transaction.TransactionType.INCOMING;
            } else {
                type = Transaction.TransactionType.valueOf(parts[2].toUpperCase());
            }

            String narrative = parts[3].isEmpty() ? "No narrative provided" : parts[3];
            String bankReference = parts[4].isEmpty() ? "No reference" : parts[4];

            Transaction transaction = new Transaction(id, date, type, narrative, bankReference, amount);
            transactionList.add(transaction);

            if (parts[0].isEmpty() || parts[2].isEmpty()) {
                updateTransactionInFile(transaction);
            }
        }
        return transactionList;
    }

    private static void updateTransactionInFile(Transaction transaction) throws Exception {
        BufferedReader reader = Helper.getReader("fake_transactions.csv");
        ArrayList<String> lines = new ArrayList<>();
        String line;

        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        reader.close();

        BufferedWriter writer = Helper.getWriter("fake_transactions.csv", StandardOpenOption.TRUNCATE_EXISTING);
        for (String fileLine : lines) {
            String[] parts = fileLine.split(",");
            if (parts.length > 0 && !parts[0].isEmpty() && parts[0].equals(transaction.getID().toString())) {
                writer.write(transaction.getID() + "," + transaction.getDate() + "," + transaction.getType() + ","
                        + transaction.getNarrative() + "," + transaction.getBankReference() + "," + transaction.getAmount());
            } else {
                writer.write(fileLine);
            }
            writer.newLine();
        }
        writer.close();
    }

    public static void addTransaction(Transaction transaction) throws Exception {
        BufferedWriter writer = Helper.getWriter("fake_transactions.csv", StandardOpenOption.APPEND);
    }
}
