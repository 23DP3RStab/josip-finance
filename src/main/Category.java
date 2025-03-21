package main;

import java.math.BigDecimal;

public class Category {
    public enum CategoryType {
        INCOME,
        EXPENSE
    }
    
    private String name;
    private CategoryType type;
    private BigDecimal spendingLimit;


}
