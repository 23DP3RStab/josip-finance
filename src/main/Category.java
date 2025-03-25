package main;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Category {
    public enum CategoryType {
        INCOME,
        EXPENSE
    }
    
    private String name;
    private CategoryType type;
    private BigDecimal spendingLimit;
    private ArrayList<Transaction> transactions;

    public Category(String name, CategoryType type, BigDecimal spendingLimit) {
        this.name = name;
        this.type = type;
        this.spendingLimit = spendingLimit;
    }

    public Category(String name, CategoryType type) {
        this.name = name;
        this.type = type;
    }
    
    public String getName() {
        return this.name;
    }

    public CategoryType getCategoryType() {
        return this.type;
    }

    public BigDecimal getSpendingLimit() {
        return this.spendingLimit;
    }

    @Override
    public String toString() {
    return "Category{" +
            "name=" + name +
            ", type=" + type +
            ", spendigLimit=" + spendingLimit +
            '}';
    }
}