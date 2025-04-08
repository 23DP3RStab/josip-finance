package main;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import main.Budget.BudgetPeriod;
import main.Category.CategoryType;
import main.Transaction.TransactionType;


// VSCode: F1 -> Java: Clean Workspace

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String clearScreen = "\u001B[2J";
        System.out.println("                      JOSIP FINANCE");
        System.out.println("------------------------------------------------------");
        ArrayList<Transaction> transactions = TransactionManager.getTransactionList();
        ArrayList<Budget> budget = BudgetManager.getBudgetList(); //IR KĻUDA!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // ArrayList<Category> transport = CategoryManager.getCategoryList(); //PAGAIDAM NEVAJAG
        while (true) {
            System.out.println("Choose your option:  \n 'T' - VIEW YOUR TRANSACTIONS \n 'B' - VIEW YOUR BUDGET \n 'C' - VIEW YOUR CATEGORY \n 'X' - EXIT");
            String input = scanner.nextLine().toUpperCase();
            switch(input) {
                case "T":
                    System.out.print(clearScreen);
                    System.out.println("Transactions:");
                    System.out.printf("%-12s %-12s %-50s %-8s %7s %15s\n", "Date", "Type", "Narrative", "Bank Reference", "Amount", "Category");
                    System.out.println("-".repeat(120));
                    for (Transaction transaction : transactions) {
                        System.out.printf("%-12s %-12s %-55s %-10s %-10.2f %-10s\n",
                            transaction.getDate(), transaction.getType(),
                            transaction.getNarrative(), transaction.getBankReference(), transaction.getAmount(), transaction.getCategory());
                    }

                    System.out.println();

                    System.out.println("Choose your option: \n 'Y' - RETURN TO MAIN \n 'X' - EXIT");
                    String input2 = scanner.nextLine().toUpperCase();
                    switch(input2) {
                        case "Y":
                            System.out.print(clearScreen);
                            System.out.println("Returning to main menu...");
                            break;
                        case "X":
                            System.out.print(clearScreen);
                            System.out.println("Exiting...");
                            scanner.close();
                            return;
                        default:
                            System.out.print(clearScreen);
                            System.out.println("Invalid input. Please try again.");
                            break;
                    } 
                    break; 

                case "B":
                //IR KĻUDA!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                    System.out.print(clearScreen);
                    System.out.println("Budget:");
                    System.out.printf("%12s %12s %50s %8s %7s %15s\n", "Period", "Limit Amount", "Name", "Start Date");
                    System.out.println("-".repeat(120));
                    for (Budget budgets : budget) {
                        System.out.printf("%12s %12s %50s %8s %7s %15s\n",
                            budgets.getPeriod(),
                            budgets.getLimitAmount(), budgets.getName(), budgets.getStartDate());
                    }

                    System.out.println();

                    System.out.println("Choose your option: \n 'Y' - RETURN TO MAIN \n 'X' - EXIT");
                    String input3 = scanner.nextLine().toUpperCase();
                    switch(input3) {
                        case "Y":
                            System.out.print(clearScreen);
                            System.out.println("Returning to main menu...");
                            break;
                        case "X":
                            System.out.print(clearScreen);
                            System.out.println("Exiting...");
                            scanner.close();
                            return;
                        default:
                            System.out.print(clearScreen);
                            System.out.println("Invalid input. Please try again.");
                            break;
                    } 
                    break;
                // case "C":
                //PAGAIDAM NEVAJAG
                 
                //     System.out.println();
                //     System.out.println("                  ID                       Period          Limit Amount                            Name                  Start Date           ");
                //     System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");
                //     // for (Category category : transport) {
                //     //     System.out.println(category);
                //     // }
                //     System.out.println();
                //     System.out.println("Choose your option: \n 'Y' - RETURN TO MAIN \n 'X' - EXIT");
                //     String input4 = scanner.nextLine().toUpperCase();
                //     switch(input4) {
                //         case "Y":
                //             System.out.println("Returning to main menu...");
                //             break;
                //         case "X":
                //             System.out.println("Exiting...");
                //             scanner.close();
                //             return;
                //         default:
                //             System.out.println("Invalid input. Please try again.");
                //             break;
                //     } 
                    // break;
                case "X":
                    System.out.print(clearScreen);
                    System.out.println();
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                
             
                default: 
                    System.out.print(clearScreen);
                    System.out.println();
                    System.out.println("Invalid input. Please try again.");
                    System.out.println();
                    break;
            }
        }
    }
}