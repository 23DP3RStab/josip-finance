package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.math.BigDecimal;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import main.Category.CategoryType;

public class CategoryManager {

    public static ArrayList<Category> getCategoryList() throws Exception {
        BufferedReader reader = Helper.getReader("categories.csv");

        ArrayList<Category> categoryList = new ArrayList<>();
        String line;

        reader.readLine();
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");

            String name = parts[0];
            CategoryType type = CategoryType.valueOf(parts[1]);

            Category category = new Category(name, type);
            categoryList.add(category);
        }
        return categoryList;
    }

    public static void addCategory(Category category) throws Exception {
        BufferedWriter writer = Helper.getWriter("categories.csv", StandardOpenOption.APPEND);
        writer.write(category.toString() + "\n");
        writer.close();
    }
}
