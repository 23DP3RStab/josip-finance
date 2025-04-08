package main;
import java.time.LocalDate;
import java.util.UUID;

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
    
    @Override
    public String toString() {
        return id + "\t" + date + "\t" + type + "\t" + narrative + "\t" + bankReference + "\t" + amount;
    }
}