package main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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

    public static List<Category> sortCategories(List<Category> categories, Comparator<Category> comparator) {
        List<Category> sortedCategories = new ArrayList<>(categories);
        sortedCategories.sort(comparator);
        return sortedCategories;
    }

    public static List<Category> filterCategories(List<Category> categories, Predicate<Category> predicate) {
        return categories.stream()
            .filter(predicate)
            .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return name + "," + type;
    }
}