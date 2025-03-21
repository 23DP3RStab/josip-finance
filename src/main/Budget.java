package main;
import java.time.LocalDate;

public class Budget {
    public enum BudgetPeriod {
        DAILY,
        WEEKLY,
        MONTHLY,
        YEARLY
    }

    private int id;
    private BudgetPeriod period;
    private double limitAmount;
    private String name;
    private LocalDate startDate;

    public Budget(int id,BudgetPeriod period, double limitAmount, String name, LocalDate startDate) {
        this.id = id;
        this.limitAmount = limitAmount;
        this.period = period;
        this.name = name;
        this.startDate = startDate;
    }

    public int getID() {
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
    return "Budget{" +
            "id=" + id +
            ", period=" + period +
            ", limitAmount=" + limitAmount +
            ", name='" + name + '\'' +
            ", startDate=" + startDate +
            '}';
    }
}