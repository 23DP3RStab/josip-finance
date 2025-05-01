package main;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Transaction {
    public enum TransactionType {
        INCOMING,
        OUTGOING,
        PURCHASE
    }

    private UUID id;
    private LocalDate date;
    private TransactionType type;
    private String narrative;
    private String bankReference;
    private double amount;
    private String category;

    public Transaction(UUID id, LocalDate date, TransactionType type, String narrative, String bankReference, double amount, String category) {
        this.id = id;
        this.date = date;
        this.type = type;
        this.narrative = narrative;
        this.bankReference = bankReference;
        this.amount = amount;
        this.category = category;
    }

    public Transaction(LocalDate date, TransactionType type, String narrative, String bankReference, double amount, String category) {
        this(UUID.randomUUID(), date, type, narrative, bankReference, amount, category);
    }

    public UUID getID() {
        return this.id;
    }

    public TransactionType getType() {
        return this.type;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public String getNarrative() {
        return this.narrative;
    }

    public String getBankReference() {
        return this.bankReference;
    }

    public double getAmount() {
        return this.amount;
    }

    public String getCategory() {
        return this.category;
    }

    public static void displayTransactions(HashMap<Integer, UUID> transactionMap, List<Transaction> transactions) {
        System.out.println(App.VIOLET + "Transactions:" + App.RESET);
        System.out.printf("%-7s %-12s %-12s %-50s %-8s %7s %10s\n", "Nr.", "Date", "Type", "Narrative", "Bank Reference", "Amount", "Category");
        System.out.println("-".repeat(125));
        int transactionNumber = 1;
        transactionMap.clear();
        for (Transaction transaction : transactions) {
            transactionMap.put(transactionNumber, transaction.getID());
            System.out.printf("%-5s %-12s %-12s %-55s %-10s %-10.2f %-10s\n",
                transactionNumber++,
                transaction.getDate(), transaction.getType(), transaction.getNarrative(), 
                transaction.getBankReference(), transaction.getAmount(), transaction.getCategory());
        }
    }
    
    public static void deleteTransactions(Scanner scanner, HashMap<Integer, UUID> transactionMap, List<Transaction> transactions) {
        System.out.println();
        System.out.println(App.VIOLET + "Enter the transaction number to delete: " + App.RESET);
        int transactionNumberToDelete = scanner.nextInt();
        scanner.nextLine();
        if (transactionMap.containsKey(transactionNumberToDelete)) {
            UUID transactionToDelete = transactionMap.get(transactionNumberToDelete);
            transactions.removeIf(transaction -> transaction.getID().equals(transactionToDelete));
            try {
                TransactionManager.deleteTransactionFromFile(transactionToDelete);
                System.out.println(App.CLEAR_SCREEN);
                System.out.println(App.GREEN + "Transaction deleted successfully!" + App.RESET);
            } catch (Exception e) {
                System.out.println(App.RED + "An error occurred while deleting the transaction: " + e.getMessage() + App.RESET);
            }
        } else {
            System.out.println(App.CLEAR_SCREEN);
            System.out.println(App.RED + "Invalid transaction number. Please try again." + App.RESET);
        }
    }

    public static List<Transaction> sortTransactions(List<Transaction> transactions, Comparator<Transaction> comparator) {
        List<Transaction> sortedTransactions = new ArrayList<>(transactions);
        sortedTransactions.sort(comparator);
        return sortedTransactions;
    }

    public static List<Transaction> filterTransactions(List<Transaction> transactions, Predicate<Transaction> predicate) {
        return transactions.stream()
            .filter(predicate)
            .collect(Collectors.toList());
    }
    
    @Override
    public String toString() {
        return id + "," + date + "," + type + "," + narrative + "," + bankReference + "," + amount + "," + category;
    }
}