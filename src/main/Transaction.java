package main;
import java.time.LocalDate;

public class Transaction {
    public enum TransactionType {
        INCOMING,
        OUTGOING,
        PURCHASE
    }

    private int id;
    private TransactionType type;
    private LocalDate date;
    private String narrative;
    private String bankReference;
    private double amount;

    public Transaction(int id,LocalDate date, TransactionType type, String narrative, String bankReference, double amount) {
        this.id = id;
        this.date = date;
        this.type = type;
        this.narrative = narrative;
        this.bankReference = bankReference;
        this.amount = amount;
    }

    public int getID() {
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
    
    @Override
    public String toString() {
        return "Transaction{" +
                "date=" + date +
                ", type=" + type +
                ", narrative='" + narrative + '\'' +
                ", bankReference='" + bankReference + '\'' +
                ", amount=" + amount +
                '}';
    }
}
