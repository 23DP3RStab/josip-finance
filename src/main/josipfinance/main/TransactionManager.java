package josipfinance.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.UUID;

public class TransactionManager {

    public static ArrayList<Transaction> getTransactionList(String filename) throws Exception {
        BufferedReader reader = Helper.getReader(filename);

        ArrayList<Transaction> transactionList = new ArrayList<>();
        String line;

        reader.readLine();
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            boolean needToUpdate = false;

            UUID id;
            if (parts[0].isEmpty()) {
                id = UUID.randomUUID();
            } else {
                try {
                    id = UUID.fromString(parts[0]);
                } catch (IllegalArgumentException e) {
                    System.out.println("[ERROR] Wrong UUID format.");
                    System.out.println("[INFO] Generating new UUID.");
                    id = UUID.randomUUID();
                    needToUpdate = true;
                }
            }
            
            LocalDate date = LocalDate.now();
            if (!parts[1].isEmpty()) {
                try {
                    date = LocalDate.parse(parts[1]);
                } catch (DateTimeParseException e) {
                    date = LocalDate.now();
                }
            }

            double amount = parts[5].isEmpty() ? 0.0 : Double.parseDouble(parts[5]);

            Transaction.TransactionType type;
            if (parts[2].isEmpty()) {
                type = amount < 0 ? Transaction.TransactionType.OUTGOING : Transaction.TransactionType.INCOMING;
            } else {
                type = Transaction.TransactionType.valueOf(parts[2].toUpperCase());
            }

            String narrative = parts[3].isEmpty() ? "No narrative provided" : parts[3];
            String bankReference = parts[4].isEmpty() ? "No reference" : parts[4];
            String category;
            if (type == Transaction.TransactionType.OUTGOING || type == Transaction.TransactionType.PURCHASE) {
                category = parts[6].isEmpty() ? "Miscellanious" : parts[6];
            } else {
                category = parts[6].isEmpty() ? "Miscellanious income" : parts[6];
            }

            Transaction transaction = new Transaction(id, date, type, narrative, bankReference, amount, category);
            transactionList.add(transaction);

            if (needToUpdate) {
                updateTransactionInFile(transaction, filename);
            }
        }
        return transactionList;
    }

    public static void updateTransactionInFile(Transaction transaction, String filename) throws Exception {
        BufferedReader reader = Helper.getReader(filename);
        ArrayList<String> lines = new ArrayList<>();
        String line;

        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        reader.close();

        BufferedWriter writer = Helper.getWriter(filename, StandardOpenOption.TRUNCATE_EXISTING);
        for (String fileLine : lines) {
            String[] parts = fileLine.split(",");
            if (parts.length > 0 && !parts[0].isEmpty() && parts[0].equals(transaction.getID().toString())) {
                writer.write(transaction.getID() + "," + transaction.getDate() + "," + transaction.getType() + ","
                        + transaction.getNarrative() + "," + transaction.getBankReference() + "," + transaction.getAmount() + "," + transaction.getCategory());
            } else {
                writer.write(fileLine);
            }
            writer.newLine();
        }
        writer.close();
    }

    public static void addTransaction(Transaction transaction, String filename) throws Exception {
        BufferedWriter writer = Helper.getWriter(filename, StandardOpenOption.APPEND);
        writer.write(transaction.toString() + "\n");
        writer.close();
    }

    public static void deleteTransactionFromFile(UUID transactionID, String filename) throws Exception {
        BufferedReader reader = Helper.getReader(filename);
        ArrayList<String> lines = new ArrayList<>();
        String line;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length > 0 && !parts[0].equals(transactionID.toString())) {
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