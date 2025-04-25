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
    // private double spendingLimit;

    // public Category(String name, CategoryType type, double spendingLimit) {
    //     this.name = name;
    //     this.type = type;
    //     this.spendingLimit = spendingLimit;
    // }

    public Category(String name, CategoryType type) {
        this.name = name;
        this.type = type;
    }
    
    public String getName() {
        return this.name;
    }

    public CategoryType getType() {
        return this.type;
    }

    // public double getSpendingLimit() {
    //     return this.spendingLimit;
    // }

    @Override
    public String toString() {
        return name + "," + type;
    }
}