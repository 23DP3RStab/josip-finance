package main;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;
import main.Budget.BudgetPeriod;
import main.Category.CategoryType;
import main.Transaction.TransactionType;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.List;
import java.util.function.Predicate;


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
        String bannerlines = ("-".repeat(100));
        List<Transaction> transactions = TransactionManager.getTransactionList();
        List<Budget> budgets = BudgetManager.getBudgetList();
        List<Category> categories = CategoryManager.getCategoryList();
        HashMap<Integer, UUID> transactionMap = new HashMap<>();
        HashMap<Integer, UUID> budgetMap = new HashMap<>();

        while (true) {
            System.out.println();
            System.out.println(banner);
            System.out.println(bannerlines);
            System.out.println("Choose your option:  \n 'T' - VIEW YOUR TRANSACTIONS \n 'B' - VIEW YOUR BUDGET \n 'C' - VIEW YOUR CATEGORY \n 'X' - EXIT");
            String input = scanner.nextLine().toUpperCase();
            switch(input) {
                // TRANSACTION CASE
                case "T":
                    System.out.println(clearScreen);
                    displayTransactions(clearScreen, transactionMap, transactions);
                    System.out.println();
                    System.out.println("Choose your option: \n 'S' - SORT \n 'F' - FILTER  \n 'N' - NEW TRANSACTION \n 'D' - DELETE TRANSACTION");
                    System.out.println("--------------------------");
                    System.out.println(" 'Y' - RETURN TO MAIN \n 'X' - EXIT");
                    input = scanner.nextLine().toUpperCase();
                    switch(input) {
                        case "S":
                            System.out.print(clearScreen);
                            System.err.println("Sort by: \n 'D' - DATE \n 'T' - TYPE \n 'N' - NARRATIVE \n 'A' - AMOUNT \n 'C' - CATEGORY");
                            input = scanner.nextLine().toUpperCase();
                            switch(input) {
                                case "D":
                                    System.out.print(clearScreen);
                                    System.out.println("Sort by oldest to newest (A) or newest to oldest (D)?");
                                    input = scanner.nextLine().toUpperCase();
                                    switch(input) {
                                        case "A":
                                            System.out.print(clearScreen);
                                            System.out.println("Sorting by date from oldest to newest...");
                                            List<Transaction> sortedByDate = sortTransactions(transactions, Comparator.comparing(Transaction::getDate));
                                            displayTransactions(clearScreen, transactionMap, sortedByDate);
                                            input = scanner.nextLine();
                                            // deleteTransactions(scanner, clearScreen, transactionMap, sortedByDate);
                                            break;

                                        case "D":
                                            System.out.print(clearScreen);
                                            System.out.println("Sorting by date from newest to oldest...");
                                            List<Transaction> sortedByDateReversed = sortTransactions(transactions, Comparator.comparing(Transaction::getDate).reversed());
                                            displayTransactions(clearScreen, transactionMap, sortedByDateReversed);
                                            input = scanner.nextLine();
                                            // deleteTransactions(scanner, clearScreen, transactionMap, sortedByDateReversed);
                                            break;

                                        default:
                                            System.out.print(clearScreen);
                                            System.out.println("Invalid input. Please try again.");
                                    }
                                    break;

                                case "T":
                                    System.out.print(clearScreen);
                                    System.out.println("Sort by A-Z (A) or Z-A (D)?");
                                    String input5 = scanner.nextLine().toUpperCase();
                                    switch(input5) {
                                        case "A":
                                            System.out.print(clearScreen);
                                            System.out.println("Sorting by type from A-Z...");
                                            List<Transaction> sortedByType = sortTransactions(transactions, Comparator.comparing(Transaction::getType));
                                            displayTransactions(clearScreen, transactionMap, sortedByType);
                                            input = scanner.nextLine();
                                            // deleteTransactions(scanner, clearScreen, transactionMap, sortedByType);
                                            break;

                                        case "D":
                                            System.out.print(clearScreen);
                                            System.out.println("Sorting by type from Z-A...");
                                            List<Transaction> sortedByTypeReversed = sortTransactions(transactions, Comparator.comparing(Transaction::getType).reversed());
                                            displayTransactions(clearScreen, transactionMap, sortedByTypeReversed);
                                            input = scanner.nextLine();
                                            // deleteTransactions(scanner, clearScreen, transactionMap, sortedByTypeReversed);
                                            break;

                                        default:
                                            System.out.print(clearScreen);
                                            System.out.println("Invalid input. Please try again.");
                                    }                                    
                                    break;

                                case "N":
                                    System.out.print(clearScreen);
                                    System.out.println("Sort by A-Z (A) or Z-A (D)?");
                                    String input6 = scanner.nextLine().toUpperCase();
                                    switch(input6) {
                                        case "A":
                                            System.out.print(clearScreen);
                                            System.out.println("Sorting by narrative from A-Z...");
                                            List<Transaction> sortedByNarrative = sortTransactions(transactions, Comparator.comparing(Transaction::getNarrative));
                                            displayTransactions(clearScreen, transactionMap, sortedByNarrative);
                                            input = scanner.nextLine();
                                            // deleteTransactions(scanner, clearScreen, transactionMap, sortedByNarrative);
                                            break;
                                            
                                        case "D":
                                            System.out.print(clearScreen);
                                            System.out.println("Sorting by narrative from Z-A...");
                                            List<Transaction> sortedByNarrativeReversed = sortTransactions(transactions, Comparator.comparing(Transaction::getNarrative).reversed());
                                            displayTransactions(clearScreen, transactionMap, sortedByNarrativeReversed);
                                            input = scanner.nextLine();
                                            // deleteTransactions(scanner, clearScreen, transactionMap, sortedByNarrativeReversed);
                                            break;
                                            
                                        default:
                                            System.out.print(clearScreen);
                                            System.out.println("Invalid input. Please try again.");
                                    }                                  
                                    break;
                                        
                                case "A":
                                    System.out.print(clearScreen);
                                    System.out.println("Sort by ascending (A) or descending (D)?");
                                    String input8 = scanner.nextLine().toUpperCase();
                                    switch(input8) {
                                        case "A":
                                            System.out.print(clearScreen);
                                            System.out.println("Sorting by amount from lowest to highest...");
                                            List<Transaction> sortedByAmount = sortTransactions(transactions, Comparator.comparing(Transaction::getAmount));
                                            displayTransactions(clearScreen, transactionMap, sortedByAmount);
                                            input = scanner.nextLine();
                                            // deleteTransactions(scanner, clearScreen, transactionMap, sortedByAmount);
                                            break;

                                        case "D":
                                            System.out.print(clearScreen);
                                            System.out.println("Sorting by amount from highest to lowest...");
                                            List<Transaction> sortedByAmountReversed = sortTransactions(transactions, Comparator.comparing(Transaction::getAmount).reversed());
                                            displayTransactions(clearScreen, transactionMap, sortedByAmountReversed);
                                            input = scanner.nextLine();
                                            // deleteTransactions(scanner, clearScreen, transactionMap, sortedByAmountReversed);
                                            break;

                                        default:
                                            System.out.print(clearScreen);
                                            System.out.println("Invalid input. Please try again.");
                                    }
                                    break;

                                case "C":
                                    System.out.print(clearScreen);
                                    System.out.println("Sort by A-Z (A) or Z-A (D)?");
                                    String input9 = scanner.nextLine().toUpperCase();
                                    switch(input9) {
                                        case "A":
                                            System.out.print(clearScreen);
                                            System.out.println("Sorting by category from A-Z...");
                                            List<Transaction> sortedByCategory = sortTransactions(transactions, Comparator.comparing(Transaction::getCategory));
                                            displayTransactions(clearScreen, transactionMap, sortedByCategory);
                                            input = scanner.nextLine();
                                            // deleteTransactions(scanner, clearScreen, transactionMap, sortedByCategory);
                                            break;

                                        case "D":
                                            System.out.print(clearScreen);
                                            System.out.println("Sorting by category from Z-A...");
                                            List<Transaction> sortedByCategoryReversed = sortTransactions(transactions, Comparator.comparing(Transaction::getCategory).reversed());
                                            displayTransactions(clearScreen, transactionMap, sortedByCategoryReversed);
                                            input = scanner.nextLine();
                                            // deleteTransactions(scanner, clearScreen, transactionMap, sortedByCategoryReversed);
                                            break;

                                        default:
                                            System.out.print(clearScreen);
                                            System.out.println("Invalid input. Please try again.");
                                    }
                                    break;
                            }
                            break;

                        case "F":
                            System.out.print(clearScreen);
                            System.out.println("Filter by: \n 'D' - DATE \n 'T' - TYPE \n 'N' - NARRATIVE \n 'B' - BANK REFERENCE \n 'A' - AMOUNT \n 'C' - CATEGORY");
                            input = scanner.nextLine().toUpperCase();
                            switch(input) {
                                case "D":
                                    System.out.print(clearScreen);
                                    System.out.println("Enter date to filter (YYYY-MM-DD):");
                                    LocalDate filterDate = LocalDate.parse(scanner.nextLine());
                                    List<Transaction> filteredByDate = filterTransactions(transactions, transaction -> transaction.getDate().equals(filterDate));
                                    System.out.println(clearScreen);
                                    System.out.println("Filtering by date - [ " + filterDate.toString() + " ]");
                                    displayTransactions(clearScreen, transactionMap, filteredByDate);
                                    input = scanner.nextLine();
                                    System.out.println(clearScreen);
                                    break;

                                case "T":
                                    System.out.print(clearScreen);
                                    System.out.println("Enter type to filter (INCOMING, OUTGOING, PURCHASE):");
                                    TransactionType filterType = TransactionType.valueOf(scanner.nextLine().toUpperCase());
                                    List<Transaction> filteredByType = filterTransactions(transactions, transaction -> transaction.getType() == filterType);
                                    System.out.println(clearScreen);
                                    System.out.println("Filtering by type - [ " + filterType.toString() + " ]");
                                    displayTransactions(clearScreen, transactionMap, filteredByType);
                                    input = scanner.nextLine();
                                    System.out.println(clearScreen);
                                    break;

                                case "N":
                                    System.out.print(clearScreen);
                                    System.out.println("Enter narrative to filter:");
                                    String filterNarrative = scanner.nextLine().toLowerCase();
                                    List<Transaction> filteredByNarrative = filterTransactions(transactions, transaction -> transaction.getNarrative().toLowerCase().contains(filterNarrative));
                                    System.out.println(clearScreen);
                                    System.out.println("Filtering by narrative - [ " + filterNarrative + " ]");
                                    displayTransactions(clearScreen, transactionMap, filteredByNarrative);
                                    input = scanner.nextLine();
                                    System.out.println(clearScreen);
                                    break;

                                case "B":
                                    System.out.print(clearScreen);
                                    System.out.println("Enter bank reference to filter:");
                                    String filterBankReference = scanner.nextLine().toLowerCase();
                                    List<Transaction> filteredByBankReference = filterTransactions(transactions, transaction -> transaction.getBankReference().toLowerCase().contains(filterBankReference));
                                    System.out.println(clearScreen);
                                    System.out.println("Filtering by bank reference - [ " + filterBankReference + " ]");
                                    displayTransactions(clearScreen, transactionMap, filteredByBankReference);
                                    input = scanner.nextLine();
                                    System.out.println(clearScreen);
                                    break;

                                case "A":
                                    System.out.print(clearScreen);
                                    System.out.println("Enter amount to filter:");
                                    String sFilterAmount = scanner.nextLine();
                                    double filterAmount = 0.0;
                                    try {
                                        filterAmount = Double.parseDouble(sFilterAmount);
                                    } catch (NumberFormatException e) {
                                        System.out.println(clearScreen);
                                        System.out.println("Invalid input. Please enter a valid amount.");
                                        break;
                                    }
                                    final double finalFilterAmount = filterAmount;
                                    List<Transaction> filteredByAmount = filterTransactions(transactions, transaction -> transaction.getAmount() == finalFilterAmount);
                                    System.out.println(clearScreen);
                                    System.out.println("Filtering by amount - [ " + sFilterAmount + " ]");
                                    displayTransactions(clearScreen, transactionMap, filteredByAmount);
                                    input = scanner.nextLine();
                                    System.out.println(clearScreen);
                                    break;

                                case "C":
                                    System.out.print(clearScreen);
                                    System.out.println("Enter category to filter:");
                                    String filterCategory = scanner.nextLine();
                                    List<Transaction> filteredByCategory = filterTransactions(transactions, transaction -> transaction.getCategory().equalsIgnoreCase(filterCategory));
                                    System.out.println(clearScreen);
                                    System.out.println("Filtering by category - [ " + filterCategory + " ]");
                                    displayTransactions(clearScreen, transactionMap, filteredByCategory);
                                    input = scanner.nextLine();
                                    System.out.println(clearScreen);
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
                            try {
                                TransactionType.valueOf(type);
                            } catch (IllegalArgumentException e) {
                                System.out.println(clearScreen);
                                System.out.println("Invalid input. Enter a valid type value.");
                                break;
                            }
                            System.out.println("Enter transaction narrative:");
                            String narrative = scanner.nextLine();
                            System.out.println("Enter bank reference:");
                            String bankReference = scanner.nextLine();
                            System.out.println("Enter transaction amount:");
                            String sAmount = scanner.nextLine();
                            double amount = 0.0;
                            try {
                                amount = Double.parseDouble(sAmount);
                            } catch (NumberFormatException e) {
                                System.out.println(clearScreen);
                                System.out.println("Invalid input. Please enter a valid amount.");
                                break;
                            }
                            System.out.println("Enter transaction category:");
                            String category = scanner.nextLine();
                            if (type.isEmpty() || narrative.isEmpty() || bankReference.isEmpty() || bankReference.isEmpty() || category.isEmpty()) {
                                System.out.println(clearScreen);
                                System.out.println("Invalid input. Make sure to input everything correctly.");
                                break;
                            }
                            Transaction newTransaction = new Transaction(LocalDate.now(), TransactionType.valueOf(type), narrative, bankReference, amount, category);
                            transactions.add(newTransaction);
                            TransactionManager.addTransaction(newTransaction);
                            System.out.print(clearScreen);
                            System.out.println("Transaction added successfully!");
                            break;
                            
                        case "D":
                            deleteTransactions(scanner, clearScreen, transactionMap, transactions);
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
                // BUDGET CASE
                case "B":
                    System.out.print(clearScreen);
                    System.out.println("Budget:");
                    System.out.printf("%20s %20s %20s %20s\n", "Period", "Name", "Limit Amount", "Start Date");
                    System.out.println("-".repeat(120));
                    for (Budget budget : budgets) {
                        System.out.printf("%20s %20s %20s %20s\n",
                            budget.getPeriod(), budget.getName(),
                            budget.getLimitAmount(), budget.getStartDate());
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
                            budgets.add(newBudget);
                            BudgetManager.addBudget(newBudget);
                            System.out.print(clearScreen);
                            System.out.println("Budget added successfully!");
                            break;

                        case "D":
                            System.out.println();
                            System.out.println("Enter the budget number to delete: ");
                            int budgetNumberToDelete = scanner.nextInt();
                            scanner.nextLine();
                            if (budgetMap.containsKey(budgetNumberToDelete)) {
                                UUID budgetToDelete = budgetMap.get(budgetNumberToDelete);
                                budgets.removeIf(budget -> budget.getID().equals(budgetToDelete));
                                BudgetManager.deleteBudgetFromFile(budgetToDelete);
                                System.out.println(clearScreen);
                                System.out.println("Budget deleted successfully!");
                            } else {
                                System.out.println(clearScreen);
                                System.out.println("Invalid budget number. Please try again.");
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
                // CATEGORY CASE
                case "C":
                    System.out.print(clearScreen);
                    System.out.println("Categories:");
                    System.out.printf("%-25s %-12s\n","Name", "Type");
                    System.out.println("-".repeat(125));
                    for (Category category : categories) {
                        System.out.printf("%-25s %-12s\n",
                            category.getName(), category.getType());
                    }

                    System.out.println();
                    System.out.println("Choose your option: \n 'S' - SORT \n 'N' - NEW CATEGORY \n 'D' - DELETE CATEGORY");
                    System.out.println("--------------------------");
                    System.out.println("Choose your option: \n 'Y' - RETURN TO MAIN \n 'X' - EXIT");
                    String input4 = scanner.nextLine().toUpperCase();
                    switch(input4) {
                        case "N":
                            System.out.print(clearScreen);
                            System.out.println("New Category:");
                            System.out.println("Enter the category type (INCOME, EXPENSE): ");
                            String categorytype = scanner.nextLine().toUpperCase();
                            System.out.println("Enter category name:");
                            String categoryname = scanner.nextLine();
                            Category newCategory = new Category(categoryname, CategoryType.valueOf(categorytype));
                            categories.add(newCategory);
                            CategoryManager.addCategory(newCategory);
                            System.out.print(clearScreen);
                            System.out.println("Category added successfully!");
                            break;

                        case "D":
                            System.out.println();
                            System.out.println("Enter the category name to delete: ");
                            String categoryNameToDelete = scanner.nextLine();
                            if (!categories.isEmpty()) {
                                categories.removeIf(category -> category.getName().equals(categoryNameToDelete));
                                CategoryManager.deleteCategoryFromFile(categoryNameToDelete);
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
    
    public static void displayTransactions(String clearScreen, HashMap<Integer, UUID> transactionMap, List<Transaction> transactions) {
        System.out.println("Transactions:");
        System.out.printf("%-7s %-12s %-12s %-50s %-8s %7s %10s\n", "Nr.", "Date", "Type", "Narrative", "Bank Reference", "Amount", "Category");
        System.out.println("-".repeat(125));
        int transactionNumber = 1;
        transactionMap.clear();
        for (Transaction transaction : transactions) {
            transactionMap.put(transactionNumber, transaction.getID());
            System.out.printf("%-5s %-12s %-12s %-55s %-10s %-10.2f %-10s\n",
                transactionNumber++,
                transaction.getDate(), transaction.getType(), transaction.getNarrative(), 
                transaction.getBankReference(), transaction.getAmount(), transaction.getCategory());
        }
    }
    
    public static void deleteTransactions(Scanner scanner, String clearScreen, HashMap<Integer, UUID> transactionMap, List<Transaction> transactions) {
        System.out.println();
        System.out.println("Enter the transaction number to delete: ");
        int transactionNumberToDelete = scanner.nextInt();
        scanner.nextLine();
        if (transactionMap.containsKey(transactionNumberToDelete)) {
            UUID transactionToDelete = transactionMap.get(transactionNumberToDelete);
            transactions.removeIf(transaction -> transaction.getID().equals(transactionToDelete));
            try {
                TransactionManager.deleteTransactionFromFile(transactionToDelete);
                System.out.println(clearScreen);
                System.out.println("Transaction deleted successfully!");
            } catch (Exception e) {
                System.out.println("An error occurred while deleting the transaction: " + e.getMessage());
            }
        } else {
            System.out.println(clearScreen);
            System.out.println("Invalid transaction number. Please try again.");
        }
    }

    public static List<Transaction> sortTransactions(List<Transaction> transactions, Comparator<Transaction> comparator) {
        List<Transaction> sortedTransactions = new ArrayList<>(transactions);
        sortedTransactions.sort(comparator);
        return sortedTransactions;
    }

    public static List<Transaction> filterTransactions(List<Transaction> transactions, Predicate<Transaction> predicate) {
        return transactions.stream()
            .filter(predicate)
            .collect(Collectors.toList());
    }

    public static List<Budget> sortBudgets(List<Budget> budgets, Comparator<Budget> comparator) {
        List<Budget> sortedBudgets = new ArrayList<>(budgets);
        sortedBudgets.sort(comparator);
        return sortedBudgets;
    }

    public static List<Budget> filterBudgets(List<Budget> budgets, Predicate<Budget> predicate) {
        return budgets.stream()
            .filter(predicate)
            .collect(Collectors.toList());
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
}