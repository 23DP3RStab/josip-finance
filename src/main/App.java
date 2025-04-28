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
                    Transaction.displayTransactions(transactionMap, transactions);
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
                                            List<Transaction> sortedByDate = Transaction.sortTransactions(transactions, Comparator.comparing(Transaction::getDate));
                                            Transaction.displayTransactions(transactionMap, sortedByDate);
                                            input = scanner.nextLine();
                                            // deleteTransactions(scanner, clearScreen, transactionMap, sortedByDate);
                                            break;

                                        case "D":
                                            System.out.print(clearScreen);
                                            System.out.println("Sorting by date from newest to oldest...");
                                            List<Transaction> sortedByDateReversed = Transaction.sortTransactions(transactions, Comparator.comparing(Transaction::getDate).reversed());
                                            Transaction.displayTransactions(transactionMap, sortedByDateReversed);
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
                                            List<Transaction> sortedByType = Transaction.sortTransactions(transactions, Comparator.comparing(Transaction::getType));
                                            Transaction.displayTransactions(transactionMap, sortedByType);
                                            input = scanner.nextLine();
                                            // deleteTransactions(scanner, clearScreen, transactionMap, sortedByType);
                                            break;

                                        case "D":
                                            System.out.print(clearScreen);
                                            System.out.println("Sorting by type from Z-A...");
                                            List<Transaction> sortedByTypeReversed = Transaction.sortTransactions(transactions, Comparator.comparing(Transaction::getType).reversed());
                                            Transaction.displayTransactions(transactionMap, sortedByTypeReversed);
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
                                            List<Transaction> sortedByNarrative = Transaction.sortTransactions(transactions, Comparator.comparing(Transaction::getNarrative));
                                            Transaction.displayTransactions(transactionMap, sortedByNarrative);
                                            input = scanner.nextLine();
                                            // deleteTransactions(scanner, clearScreen, transactionMap, sortedByNarrative);
                                            break;
                                            
                                        case "D":
                                            System.out.print(clearScreen);
                                            System.out.println("Sorting by narrative from Z-A...");
                                            List<Transaction> sortedByNarrativeReversed = Transaction.sortTransactions(transactions, Comparator.comparing(Transaction::getNarrative).reversed());
                                            Transaction.displayTransactions(transactionMap, sortedByNarrativeReversed);
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
                                            List<Transaction> sortedByAmount = Transaction.sortTransactions(transactions, Comparator.comparing(Transaction::getAmount));
                                            Transaction.displayTransactions(transactionMap, sortedByAmount);
                                            input = scanner.nextLine();
                                            // deleteTransactions(scanner, clearScreen, transactionMap, sortedByAmount);
                                            break;

                                        case "D":
                                            System.out.print(clearScreen);
                                            System.out.println("Sorting by amount from highest to lowest...");
                                            List<Transaction> sortedByAmountReversed = Transaction.sortTransactions(transactions, Comparator.comparing(Transaction::getAmount).reversed());
                                            Transaction.displayTransactions(transactionMap, sortedByAmountReversed);
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
                                            List<Transaction> sortedByCategory = Transaction.sortTransactions(transactions, Comparator.comparing(Transaction::getCategory));
                                            Transaction.displayTransactions(transactionMap, sortedByCategory);
                                            input = scanner.nextLine();
                                            // deleteTransactions(scanner, clearScreen, transactionMap, sortedByCategory);
                                            break;

                                        case "D":
                                            System.out.print(clearScreen);
                                            System.out.println("Sorting by category from Z-A...");
                                            List<Transaction> sortedByCategoryReversed = Transaction.sortTransactions(transactions, Comparator.comparing(Transaction::getCategory).reversed());
                                            Transaction.displayTransactions(transactionMap, sortedByCategoryReversed);
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
                                    List<Transaction> filteredByDate = Transaction.filterTransactions(transactions, transaction -> transaction.getDate().equals(filterDate));
                                    System.out.println(clearScreen);
                                    System.out.println("Filtering by date - [ " + filterDate.toString() + " ]");
                                    Transaction.displayTransactions(transactionMap, filteredByDate);
                                    input = scanner.nextLine();
                                    System.out.println(clearScreen);
                                    break;

                                case "T":
                                    System.out.print(clearScreen);
                                    System.out.println("Enter type to filter (INCOMING, OUTGOING, PURCHASE):");
                                    TransactionType filterType = TransactionType.valueOf(scanner.nextLine().toUpperCase());
                                    List<Transaction> filteredByType = Transaction.filterTransactions(transactions, transaction -> transaction.getType() == filterType);
                                    System.out.println(clearScreen);
                                    System.out.println("Filtering by type - [ " + filterType.toString() + " ]");
                                    Transaction.displayTransactions(transactionMap, filteredByType);
                                    input = scanner.nextLine();
                                    System.out.println(clearScreen);
                                    break;

                                case "N":
                                    System.out.print(clearScreen);
                                    System.out.println("Enter narrative to filter:");
                                    String filterNarrative = scanner.nextLine().toLowerCase();
                                    List<Transaction> filteredByNarrative = Transaction.filterTransactions(transactions, transaction -> transaction.getNarrative().toLowerCase().contains(filterNarrative));
                                    System.out.println(clearScreen);
                                    System.out.println("Filtering by narrative - [ " + filterNarrative + " ]");
                                    Transaction.displayTransactions(transactionMap, filteredByNarrative);
                                    input = scanner.nextLine();
                                    System.out.println(clearScreen);
                                    break;

                                case "B":
                                    System.out.print(clearScreen);
                                    System.out.println("Enter bank reference to filter:");
                                    String filterBankReference = scanner.nextLine().toLowerCase();
                                    List<Transaction> filteredByBankReference = Transaction.filterTransactions(transactions, transaction -> transaction.getBankReference().toLowerCase().contains(filterBankReference));
                                    System.out.println(clearScreen);
                                    System.out.println("Filtering by bank reference - [ " + filterBankReference + " ]");
                                    Transaction.displayTransactions(transactionMap, filteredByBankReference);
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
                                    List<Transaction> filteredByAmount = Transaction.filterTransactions(transactions, transaction -> transaction.getAmount() == finalFilterAmount);
                                    System.out.println(clearScreen);
                                    System.out.println("Filtering by amount - [ " + sFilterAmount + " ]");
                                    Transaction.displayTransactions(transactionMap, filteredByAmount);
                                    input = scanner.nextLine();
                                    System.out.println(clearScreen);
                                    break;

                                case "C":
                                    System.out.print(clearScreen);
                                    System.out.println("Enter category to filter:");
                                    String filterCategory = scanner.nextLine();
                                    List<Transaction> filteredByCategory = Transaction.filterTransactions(transactions, transaction -> transaction.getCategory().equalsIgnoreCase(filterCategory));
                                    System.out.println(clearScreen);
                                    System.out.println("Filtering by category - [ " + filterCategory + " ]");
                                    Transaction.displayTransactions(transactionMap, filteredByCategory);
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
                            Transaction.deleteTransactions(scanner, clearScreen, transactionMap, transactions);
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
                    Budget.displayBudgets(budgetMap, budgets);
                    System.out.println();
                    System.out.println("Choose your option: \n 'S' - SORT \n 'F' - FILTER  \n 'N' - NEW BUDGET \n 'D' - DELETE BUDGET");
                    System.out.println("--------------------------");
                    System.out.println("Choose your option: \n 'Y' - RETURN TO MAIN \n 'X' - EXIT");
                    String input10 = scanner.nextLine().toUpperCase();
                    switch(input10) {
                        case "S":
                        System.out.print(clearScreen);
                        System.err.println("Sort by: \n 'P' - PERIOD \n 'N' - NAME \n 'L' - LIMIT AMOUNT \n 'S' - START DATE");
                        input = scanner.nextLine().toUpperCase();
                        switch(input) {
                            case "P":
                                System.out.print(clearScreen);
                                System.out.println("Sort by shortest to longest (A) or longest to shortest (D)?");
                                input = scanner.nextLine().toUpperCase();
                                switch(input) {
                                    case "A":
                                        System.out.print(clearScreen);
                                        System.out.println("Sorting by period from shortest to longest...");
                                        List<Budget> sortedByPeriod = Budget.sortBudgets(budgets, Comparator.comparing(Budget::getPeriod));
                                        Budget.displayBudgets(budgetMap, sortedByPeriod);
                                        input = scanner.nextLine();
                                        // deleteBudgets(scanner, clearScreen, budgetMap, sortedByPeriod);
                                        break;

                                    case "D":
                                        System.out.print(clearScreen);
                                        System.out.println("Sorting by period from longest to shortest...");
                                        List<Budget> sortedByPeriodReversed = Budget.sortBudgets(budgets, Comparator.comparing(Budget::getPeriod).reversed());
                                        Budget.displayBudgets(budgetMap, sortedByPeriodReversed);
                                        input = scanner.nextLine();
                                        // deleteBudgets(scanner, clearScreen, budgetMap, sortedByPeriodReversed);
                                        break;

                                    default:
                                        System.out.print(clearScreen);
                                        System.out.println("Invalid input. Please try again.");
                                }
                                break;

                            case "N":
                                System.out.print(clearScreen);
                                System.out.println("Sort by A-Z (A) or Z-A (D)?");
                                String input5 = scanner.nextLine().toUpperCase();
                                switch(input5) {
                                    case "A":
                                        System.out.print(clearScreen);
                                        System.out.println("Sorting by type from A-Z...");
                                        List<Transaction> sortedByType = sortTransactions(transactions, Comparator.comparing(Transaction::getType));
                                        displayTransactions(budgetMap, sortedByType);
                                        input = scanner.nextLine();
                                        // deleteTransactions(scanner, clearScreen, budgetMap, sortedByType);
                                        break;

                                    case "D":
                                        System.out.print(clearScreen);
                                        System.out.println("Sorting by type from Z-A...");
                                        List<Transaction> sortedByTypeReversed = sortTransactions(transactions, Comparator.comparing(Transaction::getType).reversed());
                                        displayTransactions(budgetMap, sortedByTypeReversed);
                                        input = scanner.nextLine();
                                        // deleteTransactions(scanner, clearScreen, budgetMap, sortedByTypeReversed);
                                        break;

                                    default:
                                        System.out.print(clearScreen);
                                        System.out.println("Invalid input. Please try again.");
                                }                                    
                                break;

                            case "L":
                                System.out.print(clearScreen);
                                System.out.println("Sort by A-Z (A) or Z-A (D)?");
                                String input6 = scanner.nextLine().toUpperCase();
                                switch(input6) {
                                    case "A":
                                        System.out.print(clearScreen);
                                        System.out.println("Sorting by narrative from A-Z...");
                                        List<Transaction> sortedByNarrative = sortTransactions(transactions, Comparator.comparing(Transaction::getNarrative));
                                        displayTransactions(budgetMap, sortedByNarrative);
                                        input = scanner.nextLine();
                                        // deleteTransactions(scanner, clearScreen, budgetMap, sortedByNarrative);
                                        break;
                                        
                                    case "D":
                                        System.out.print(clearScreen);
                                        System.out.println("Sorting by narrative from Z-A...");
                                        List<Transaction> sortedByNarrativeReversed = sortTransactions(transactions, Comparator.comparing(Transaction::getNarrative).reversed());
                                        displayTransactions(budgetMap, sortedByNarrativeReversed);
                                        input = scanner.nextLine();
                                        // deleteTransactions(scanner, clearScreen, budgetMap, sortedByNarrativeReversed);
                                        break;
                                        
                                    default:
                                        System.out.print(clearScreen);
                                        System.out.println("Invalid input. Please try again.");
                                }                                  
                                break;
                                    
                            case "S":
                                System.out.print(clearScreen);
                                System.out.println("Sort by ascending (A) or descending (D)?");
                                String input8 = scanner.nextLine().toUpperCase();
                                switch(input8) {
                                    case "A":
                                        System.out.print(clearScreen);
                                        System.out.println("Sorting by amount from lowest to highest...");
                                        List<Transaction> sortedByAmount = sortTransactions(transactions, Comparator.comparing(Transaction::getAmount));
                                        displayTransactions(budgetMap, sortedByAmount);
                                        input = scanner.nextLine();
                                        // deleteTransactions(scanner, clearScreen, budgetMap, sortedByAmount);
                                        break;

                                    case "D":
                                        System.out.print(clearScreen);
                                        System.out.println("Sorting by amount from highest to lowest...");
                                        List<Transaction> sortedByAmountReversed = sortTransactions(transactions, Comparator.comparing(Transaction::getAmount).reversed());
                                        displayTransactions(budgetMap, sortedByAmountReversed);
                                        input = scanner.nextLine();
                                        // deleteTransactions(scanner, clearScreen, budgetMap, sortedByAmountReversed);
                                        break;

                                    default:
                                        System.out.print(clearScreen);
                                        System.out.println("Invalid input. Please try again.");
                                }
                                break;
                        }
                        break;

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
}