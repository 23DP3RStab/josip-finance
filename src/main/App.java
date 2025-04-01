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
        System.out.println("                      JOSIP FINANCE");
        System.out.println("------------------------------------------------------");
        while (true) {
            System.out.println("Choose your option:  \n '1' - VIEW YOUR TRANSACTIONS \n '2' - VIEW YOUR BUDGET \n '3' - VIEW YOUR CATEGORY \n 'X' - EXIT");
            String input = scanner.nextLine().toUpperCase();
            switch(input) {
            case "1": 
                System.out.println();
                // ArrayList<Transaction> transactions = TransactionManager.getTransactionList();
                // for (Transaction transaction : transactions) {
                //     System.out.println(transaction);
                // }

                Transaction transaction = new Transaction(LocalDate.parse("2025-05-02"), TransactionType.INCOMING, "sonders sent monke", "IDK525IDK", 123.25);
                System.out.println(transaction);
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

            case "2": 
            System.out.println();                   
                Budget budget = new Budget(BudgetPeriod.MONTHLY, 1000, "Sibder", LocalDate.parse("2025-01-20"));
                       System.out.println(budget);
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
           case "3": 
           System.out.println();
                Category transport = new Category("Transport", CategoryType.EXPENSE, 250);
                       System.out.println(transport);
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