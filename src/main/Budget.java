package main;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class Budget {
    public enum BudgetPeriod {
        DAILY,
        WEEKLY,
        MONTHLY,
        YEARLY
    }

    private UUID id;
    private LocalDate startDate;
    private BudgetPeriod period;
    private String name;
    private double limitAmount;
    // private ArrayList<Category> categorySpending = new ArrayList<>();

    public Budget(UUID id,BudgetPeriod period, double limitAmount, String name, LocalDate startDate) {
        this.id = id;
        this.limitAmount = limitAmount;
        this.period = period;
        this.name = name;
        this.startDate = startDate;
    }

    public Budget(BudgetPeriod period, double limitAmount, String name, LocalDate startDate) {
        this.id = UUID.randomUUID();
        this.limitAmount = limitAmount;
        this.period = period;
        this.name = name;
        this.startDate = startDate;
    }

    public UUID getID() {
        return this.id;
    }
    
    public BudgetPeriod getPeriod() {
        return this.period;
    }

    public double getLimitAmount() {
        return this.limitAmount;
    }

    public String getName() {
        return this.name;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public void setPeriod(BudgetPeriod period) {
        this.period = period;
    }

    public void setLimitAmount(double limitAmount) {
        this.limitAmount = limitAmount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return id + "\t" + period + "\t" + limitAmount + "\t" + name + "\t" + startDate;
    }
}