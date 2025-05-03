package main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.Scanner;

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

    public static void displayCategories(List<Category> categories) {
        System.out.println(App.VIOLET + "Categories:" + App.RESET);
        System.out.printf("%-25s %-12s\n","Name", "Type");
        System.out.println("-".repeat(125));
        for (Category category : categories) {
            System.out.printf("%-25s %-12s\n",
                category.getName(), category.getType());
        }
    }

    public static void deleteCategory(Scanner scanner, List<Category> categories) {
        System.out.println();
        System.out.println(App.VIOLET + "Enter the category name to delete: " + App.RESET);
        String categoryNameToDelete = scanner.nextLine();

        boolean categoryExists = categories.stream().anyMatch(category -> category.getName().equalsIgnoreCase(categoryNameToDelete));

        if (categoryExists) {
            categories.removeIf(category -> category.getName().equals(categoryNameToDelete));
            try {
                CategoryManager.deleteCategoryFromFile(categoryNameToDelete);
                System.out.println(App.CLEAR_SCREEN);
                System.out.println(App.GREEN + "Category deleted successfully!" + App.RESET);
            } catch (Exception e) {
                System.out.println(App.RED + "An error occurred while deleting the budget: " + e.getMessage() + App.RESET);
            }
        } else {
            System.out.println(App.CLEAR_SCREEN);
            System.out.println(App.RED + "Invalid category name. Please try again." + App.RESET);
        }
    }

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