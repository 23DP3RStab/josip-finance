package main;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import main.Budget.BudgetPeriod;
import main.Category.CategoryType;
import main.Transaction.TransactionType;


public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String clearScreen = "\u001B[2J";
        System.out.println("                      JOSIP FINANCE");
        System.out.println("------------------------------------------------------");
        // ArrayList<Transaction> transactions = TransactionManager.getTransactionList();
        // ArrayList<Budget> budget = BudgetManager.getBudgetList();
        // ArrayList<Category> transport = CategoryManager.getCategoryList();
        while (true) {
            System.out.println("Choose your option:  \n 'T' - VIEW YOUR TRANSACTIONS \n 'B' - VIEW YOUR BUDGET \n 'C' - VIEW YOUR CATEGORY \n 'X' - EXIT");
            String input = scanner.nextLine().toUpperCase();
            switch(input) {
                case "T": 
                System.out.print(clearScreen);
                System.out.println("                  ID                       Date          Type                            Narrative                  Bank Reference     Amount ");
                System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");
                // for (Transaction transaction : transactions) {
                //     System.out.println(transaction);
                // }

                    System.out.println();

                    System.out.println("Choose your option: \n 'Y' - RETURN TO MAIN \n 'X' - EXIT");
                    String input2 = scanner.nextLine().toUpperCase();
                    switch(input2) {
                        case "Y":
                            System.out.println("Returning to main menu...");
                            break;
                        case "X":
                            System.out.println("Exiting...");
                            scanner.close();
                            return;
                        default:
                            System.out.println("Invalid input. Please try again.");
                            break;
                    } 
                    break; 

                case "B": 
                    System.out.print(clearScreen);
                    System.out.println("                  ID                       Period          Limit Amount                            Name                  Start Date           ");
                    System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");                 
                    // for (Budget budgets : budget) {
                    //     System.out.println(budgets);
                    // }
                    System.out.println();

                    System.out.println("Choose your option: \n 'Y' - RETURN TO MAIN \n 'X' - EXIT");
                    String input3 = scanner.nextLine().toUpperCase();
                    switch(input3) {
                        case "Y":
                            System.out.println("Returning to main menu...");
                            break;
                        case "X":
                            System.out.println("Exiting...");
                            scanner.close();
                            return;
                        default:
                            System.out.println("Invalid input. Please try again.");
                            break;
                    } 
                    break;
                case "C": 
                    System.out.println();
                    System.out.println("                  ID                       Period          Limit Amount                            Name                  Start Date           ");
                    System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");
                    // for (Category category : transport) {
                    //     System.out.println(category);
                    // }
                    System.out.println();
                    System.out.println("Choose your option: \n 'Y' - RETURN TO MAIN \n 'X' - EXIT");
                    String input4 = scanner.nextLine().toUpperCase();
                    switch(input4) {
                        case "Y":
                            System.out.println("Returning to main menu...");
                            break;
                        case "X":
                            System.out.println("Exiting...");
                            scanner.close();
                            return;
                        default:
                            System.out.println("Invalid input. Please try again.");
                            break;
                    } 
                    break;
                case "X":
                    System.out.println();
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                
             
                default: 
                    System.out.println();
                    System.out.println("Invalid input. Please try again.");
                    System.out.println();
                    break;
            }
        }
    }
}