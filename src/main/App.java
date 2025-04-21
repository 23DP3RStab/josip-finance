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
        String banner = ("      ██╗ ██████╗  ██████╗██╗██████╗     ███████╗██╗███╗   ██╗ █████╗ ███╗   ██╗ ██████╗███████╗\r\n" + //
        "      ██║██╔═══██╗██╔════╝██║██╔══██╗    ██╔════╝██║████╗  ██║██╔══██╗████╗  ██║██╔════╝██╔════╝\r\n" + //
        "      ██║██║   ██║█████╗  ██║██████╔╝    █████╗  ██║██╔██╗ ██║███████║██╔██╗ ██║██║     █████╗  \r\n" + //
        " ██   ██║██║   ██║    ██║ ██║██╔═══╝     ██╔══╝  ██║██║╚██╗██║██╔══██║██║╚██╗██║██║     ██╔══╝  \r\n" + //
        " ╚█████╔╝╚██████╔╝██████║ ██║██║         ██║     ██║██║ ╚████║██║  ██║██║ ╚████║╚██████╗███████╗\r\n" + //
        "  ╚════╝  ╚═════╝ ╚═════╝ ╚═╝╚═╝         ╚═╝     ╚═╝╚═╝  ╚═══╝╚═╝  ╚═╝╚═╝  ╚═══╝ ╚═════╝╚══════╝\r\n" + //
        "\r\n" + //
        "                                   J O S I P   F I N A N C E\r\n" + //
        "");
        System.out.println();
        String bannerlines = ("-".repeat(100));
        ArrayList<Transaction> transactions = TransactionManager.getTransactionList();
        ArrayList<Budget> budget = BudgetManager.getBudgetList();
        // ArrayList<Category> transport = CategoryManager.getCategoryList();
        while (true) {
            System.out.println(banner);
            System.out.println(bannerlines);
            System.out.println("Choose your option:  \n 'T' - VIEW YOUR TRANSACTIONS \n 'B' - VIEW YOUR BUDGET \n 'C' - VIEW YOUR CATEGORY \n 'X' - EXIT");
            String input = scanner.nextLine().toUpperCase();
            switch(input) {
                case "T":
                    System.out.print(clearScreen);
                    System.out.println("Transactions:");
                    System.out.printf("%-7s %-12s %-12s %-50s %-8s %7s %15s\n", "Nr.", "Date", "Type", "Narrative", "Bank Reference", "Amount", "Category");
                    System.out.println("-".repeat(125));
                    int transactionNumber = 1;
                    for (Transaction transaction : transactions) {
                        System.out.printf("%-5s %-12s %-12s %-55s %-10s %-10.2f %-10s\n",
                            transactionNumber++,
                            transaction.getDate(), transaction.getType(), transaction.getNarrative(), 
                            transaction.getBankReference(), transaction.getAmount(), transaction.getCategory());
                    }
                    System.out.println();
                    System.out.println("Choose your option: \n 'S' - SORT \n 'F' - FILTER  \n 'N' - NEW TRANSACTION \n 'D' - DELETE TRANSACTION");
                    System.out.println("--------------------------");
                    System.out.println(" 'Y' - RETURN TO MAIN \n 'X' - EXIT");
                    String input2 = scanner.nextLine().toUpperCase();
                    switch(input2) {
                        case "S":
                            System.out.print(clearScreen);
                            System.err.println("Sort by: \n 'D' - DATE \n 'T' - TYPE \n 'N' - NARRATIVE \n 'A' - AMOUNT \n 'C' - CATEGORY");
                            String input3 = scanner.nextLine().toUpperCase();
                            switch(input3) {
                                case "D":
                                System.out.print(clearScreen);
                                System.out.println("Sort by oldest to newest (ASC) or newest to oldest (DESC)?");
                                String input4 = scanner.nextLine().toUpperCase();
                                switch(input4) {
                                    case "ASC":
                                                System.out.print(clearScreen);
                                                System.out.println("Sorting by date from oldest to newest...");
                                                
                                                break;
                                            case "DESC":
                                                System.out.print(clearScreen);
                                                System.out.println("Sorting by date from newest to oldest...");
                                                
                                                break;
                                            default:
                                                System.out.print(clearScreen);
                                                System.out.println("Invalid input. Please try again.");
                                        }
                                    break;

                                case "T":
                                    System.out.print(clearScreen);
                                    System.out.println("Sort by A-Z (AZ) or Z-A (ZA)?");
                                    String input5 = scanner.nextLine().toUpperCase();
                                    switch(input5) {
                                        case "AZ":
                                            System.out.print(clearScreen);
                                            System.out.println("Sorting by type from A-Z...");
                                            
                                            break;
                                        case "ZA":
                                            System.out.print(clearScreen);
                                            System.out.println("Sorting by type from Z-A...");
                                            
                                            break;
                                        default:
                                            System.out.print(clearScreen);
                                            System.out.println("Invalid input. Please try again.");
                                    }                                    
                                    break;

                                case "N":
                                    System.out.print(clearScreen);
                                    System.out.println("Sort by A-Z (AZ) or Z-A (ZA)?");
                                    String input6 = scanner.nextLine().toUpperCase();
                                    switch(input6) {
                                        case "AZ":
                                            System.out.print(clearScreen);
                                            System.out.println("Sorting by narrative from A-Z...");
                                            
                                            break;
                                        case "ZA":
                                            System.out.print(clearScreen);
                                            System.out.println("Sorting by narrative from Z-A...");
                                            
                                            break;
                                        default:
                                            System.out.print(clearScreen);
                                            System.out.println("Invalid input. Please try again.");
                                    }                                  
                                    break;

                                case "A":
                                    System.out.print(clearScreen);
                                    System.out.println("Sort by ascending (ASC) or descending (DESC)?");
                                    String input8 = scanner.nextLine().toUpperCase();
                                    switch(input8) {
                                        case "ASC":
                                            System.out.print(clearScreen);
                                            System.out.println("Sorting by amount from lowest to highest...");
                                            
                                            break;
                                        case "DESC":
                                            System.out.print(clearScreen);
                                            System.out.println("Sorting by amount from highest to lowest...");
                                            
                                            break;
                                        default:
                                            System.out.print(clearScreen);
                                            System.out.println("Invalid input. Please try again.");
                                    }
                                    
                                    break;
                                case "C":
                                    System.out.print(clearScreen);
                                    System.out.println("Sort by A-Z (AZ) or Z-A (ZA)?");
                                    String input9 = scanner.nextLine().toUpperCase();
                                    switch(input9) {
                                        case "AZ":
                                            System.out.print(clearScreen);
                                            System.out.println("Sorting by category from A-Z...");
                                            break;

                                        case "ZA":
                                            System.out.print(clearScreen);
                                            System.out.println("Sorting by category from Z-A...");
                                            break;

                                        default:
                                            System.out.print(clearScreen);
                                            System.out.println("Invalid input. Please try again.");
                            }
                        }
                        break; 

                        case "F":
                            System.out.print(clearScreen);
                            System.out.println("Filter by: \n 'D' - DATE \n 'T' - TYPE \n 'N' - NARRATIVE \n 'B' - BANK REFERENCE \n 'A' - AMOUNT \n 'C' - CATEGORY");
                            String inputfilterTrans = scanner.nextLine().toUpperCase();
                            switch(inputfilterTrans) {
                                case "D":
                                    System.out.print(clearScreen);
                                    System.out.println("Filtering by date...");
                                    
                                    break;
                                case "T":
                                    System.out.print(clearScreen);
                                    System.out.println("Filtering by type...");
                                    
                                    break;
                                case "N":
                                    System.out.print(clearScreen);
                                    System.out.println("Filtering by narrative...");
                                    
                                    break;
                                case "B":
                                    System.out.print(clearScreen);
                                    System.out.println("Filtering by bank reference...");
                                    
                                    break;
                                case "A":
                                    System.out.print(clearScreen);
                                    System.out.println("Filtering by amount...");
                                    
                                    break;
                                case "C":
                                    System.out.print(clearScreen);
                                    System.out.println("Filtering by category...");
                                    
                                    break;
                                default:
                                    System.out.print(clearScreen);
                                    System.out.println("Invalid input. Please try again.");
                            }
                            break;
                        
                        case "N":
                            System.out.print(clearScreen);
                            System.out.println("New Transaction:");
                            System.out.println("Enter the transaction type (INCOMING, OUTGOING, PURCHASE): ");
                            String type = scanner.nextLine().toUpperCase();
                            System.out.println("Enter transaction narrative:");
                            String narrative = scanner.nextLine();
                            System.out.println("Enter bank reference:");
                            String bankReference = scanner.nextLine();
                            System.out.println("Enter transaction category:");
                            String category = scanner.nextLine();
                            System.out.println("Enter transaction amount:");
                            double amount = scanner.nextDouble();
                            scanner.nextLine();
                            Transaction newTransaction = new Transaction(LocalDate.now(), TransactionType.valueOf(type), narrative, bankReference, amount, category);
                            transactions.add(newTransaction);
                            TransactionManager.addTransaction(newTransaction);
                            System.out.print(clearScreen);
                            System.out.println("Transaction added successfully!");
                            break;
                            
                        case "D":
                            System.out.println();
                            System.out.println("Enter the transaction number to delete: ");
                            int transactionNumberToDelete = scanner.nextInt();
                            scanner.nextLine();
                            if (transactionNumberToDelete > 0 && transactionNumberToDelete <= transactions.size()) {
                                Transaction transactionToDelete = transactions.get(transactionNumberToDelete - 1);
                                transactions.remove(transactionNumberToDelete - 1);
                                TransactionManager.updateTransactionInFile(transactionToDelete);
                                System.out.println(clearScreen);
                                System.out.println("Transaction deleted successfully!");
                            } else {
                                System.out.println(clearScreen);
                                System.out.println("Invalid transaction number. Please try again.");
                            }
                            break;

                        case "Y":
                            System.out.print(clearScreen);
                            System.out.println("Returning to main menu...");
                            System.out.println();
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
                    System.out.print(clearScreen);
                    System.out.println("Budget:");
                    System.out.printf("%20s %20s %20s %20s\n", "Period", "Name", "Limit Amount", "Start Date");
                    System.out.println("-".repeat(120));
                    for (Budget budgets : budget) {
                        System.out.printf("%20s %20s %20s %20s\n",
                            budgets.getPeriod(), budgets.getName(),
                            budgets.getLimitAmount(), budgets.getStartDate());
                    }

                    System.out.println();
                    System.out.println("Choose your option: \n 'S' - SORT \n 'F' - FILTER  \n 'N' - NEW BUDGET \n 'D' - DELETE BUDGET");
                    System.out.println("--------------------------");
                    System.out.println("Choose your option: \n 'Y' - RETURN TO MAIN \n 'X' - EXIT");
                    String input10 = scanner.nextLine().toUpperCase();
                    switch(input10) {
                        case "N":
                        System.out.print(clearScreen);
                        System.out.println("New Budget:");
                        System.out.println("Enter the period (DAILY, WEEKLY, MONTHLY, YEARLY): ");
                        String period = scanner.nextLine().toUpperCase();
                        System.out.println("Enter budget name:");
                        String name = scanner.nextLine();
                        System.out.println("Enter budget limit amount:");
                        double limitAmount = scanner.nextDouble();
                        scanner.nextLine();
                        Budget newBudget = new Budget(BudgetPeriod.valueOf(period), limitAmount, name, LocalDate.now());
                        budget.add(newBudget);
                        BudgetManager.addBudget(newBudget);
                        System.out.print(clearScreen);
                        System.out.println("Budget added successfully!");
                        break;

                        // case "D":

                        case "Y":
                            System.out.print(clearScreen);
                            System.out.println("Returning to main menu...");
                            System.out.println();
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
                    
                case "C":
                System.out.print(clearScreen);
                System.out.println("Budget:");
                System.out.printf("%20s %20s %20s %20s\n", "Period", "Name", "Limit Amount", "Start Date");
                System.out.println("-".repeat(120));
                for (Budget budgets : budget) {
                    System.out.printf("%20s %20s %20s %20s\n",
                        budgets.getPeriod(), budgets.getName(),
                        budgets.getLimitAmount(), budgets.getStartDate());
                }

                    System.out.println();
                    System.out.println("Choose your option: \n 'S' - SORT \n 'F' - FILTER  \n 'N' - NEW CATEGORY \n 'D' - DELETE CATEGORY");
                    System.out.println("--------------------------");
                    System.out.println("Choose your option: \n 'Y' - RETURN TO MAIN \n 'X' - EXIT");
                    String input4 = scanner.nextLine().toUpperCase();
                    switch(input4) {
                        // case "N":
                        // System.out.print(clearScreen);
                        // System.out.println("New Category:");
                        // System.out.println("Enter the category type (INCOME, EXPENSE): ");
                        // String categorytype = scanner.nextLine().toUpperCase();
                        // System.out.println("Enter category name:");
                        // String categoryname = scanner.nextLine();
                        // Category newCategory = new Category(categoryname, CategoryType.valueOf(categorytype));
                        // transport.add(newCategory);
                        // CategoryManager.addCategory(newCategory);
                        // System.out.print(clearScreen);
                        // System.out.println("Category added successfully!");
                        // break;

                        case "Y":
                            System.out.print(clearScreen);
                            System.out.println("Returning to main menu...");
                            System.out.println();
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