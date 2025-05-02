package main;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;
import main.Budget.BudgetPeriod;
import main.Category.CategoryType;
import main.Transaction.TransactionType;
import java.util.UUID;
import java.util.List;


// VSCode: F1 -> Java: Clean Workspace

public class App {

    final static String VIOLET = "\u001B[35;1m";
    final static String RESET = "\u001B[0m";
    final static String RED = "\u001B[31;1m";
    final static String GREEN = "\u001B[32;1m";
    final static String CLEAR_SCREEN = "\u001B[2J";
    final static String BANNER = ("      ██╗ ██████╗  ██████╗██╗██████╗     \u001B[35;1m███████╗██╗███╗   ██╗ █████╗ ███╗   ██╗ ██████╗███████╗\u001B[0m\r\n" + //
    "      ██║██╔═══██╗██╔════╝██║██╔══██╗    \u001B[35;1m██╔════╝██║████╗  ██║██╔══██╗████╗  ██║██╔════╝██╔════╝\u001B[0m\r\n" + //
    "      ██║██║   ██║█████╗  ██║██████╔╝    \u001B[35;1m█████╗  ██║██╔██╗ ██║███████║██╔██╗ ██║██║     █████╗  \u001B[0m\r\n" + //
    " ██   ██║██║   ██║    ██║ ██║██╔═══╝     \u001B[35;1m██╔══╝  ██║██║╚██╗██║██╔══██║██║╚██╗██║██║     ██╔══╝  \u001B[0m\r\n" + //
    " ╚█████╔╝╚██████╔╝██████║ ██║██║         \u001B[35;1m██║     ██║██║ ╚████║██║  ██║██║ ╚████║╚██████╗███████╗\u001B[0m\r\n" + //
    "  ╚════╝  ╚═════╝ ╚═════╝ ╚═╝╚═╝         \u001B[35;1m╚═╝     ╚═╝╚═╝  ╚═══╝╚═╝  ╚═╝╚═╝  ╚═══╝ ╚═════╝╚══════╝\u001B[0m\r\n" + //
    "\r\n" + //
    "                                   J O S I P   \u001B[35;1mF I N A N C E\r\n" + //
    "" +  "\u001B[0m");
    final static String BANNER_LINES = ("-".repeat(100));

    public static void main(String[] args) throws Exception {
        final Scanner scanner = new Scanner(System.in);
        
        List<Transaction> transactions = TransactionManager.getTransactionList();
        List<Budget> budgets = BudgetManager.getBudgetList();
        List<Category> categories = CategoryManager.getCategoryList();
        HashMap<Integer, UUID> transactionMap = new HashMap<>();
        HashMap<Integer, UUID> budgetMap = new HashMap<>();
        
        while (true) {
            System.out.println();
            System.out.println(BANNER);
            System.out.println(BANNER_LINES);
            System.out.println(VIOLET + "Choose your option:  \n 'T' - VIEW YOUR TRANSACTIONS \n 'B' - VIEW YOUR BUDGET \n 'C' - VIEW YOUR CATEGORY \n 'X' - EXIT" +  RESET);
            String input = scanner.nextLine().toUpperCase();
            switch(input) {
                // TRANSACTION CASE
                case "T":
                    System.out.println(CLEAR_SCREEN);
                    Transaction.displayTransactions(transactionMap, transactions);
                    System.out.println();
                    System.out.println(VIOLET + "Choose your option: \n 'S' - SORT \n 'F' - FILTER  \n 'N' - NEW TRANSACTION \n 'D' - DELETE TRANSACTION" + RESET);
                    System.out.println(VIOLET + "--------------------------" + RESET);
                    System.out.println(VIOLET + " 'Y' - RETURN TO MAIN \n 'X' - EXIT" + RESET);
                    input = scanner.nextLine().toUpperCase();
                    switch(input) {
                        case "S":
                            System.out.print(CLEAR_SCREEN);
                            System.err.println(VIOLET + "Sort by: \n 'D' - DATE \n 'T' - TYPE \n 'N' - NARRATIVE \n 'A' - AMOUNT \n 'C' - CATEGORY" + RESET);
                            input = scanner.nextLine().toUpperCase();
                            switch(input) {
                                case "D":
                                    System.out.print(CLEAR_SCREEN);
                                    System.out.println(VIOLET + "Sort by oldest to newest (A) or newest to oldest (D)?" + RESET);
                                    input = scanner.nextLine().toUpperCase();
                                    switch(input) {
                                        case "A":
                                            System.out.print(CLEAR_SCREEN);
                                            System.out.println(VIOLET + "Sorting by date from oldest to newest..." + RESET);
                                            List<Transaction> sortedByDate = Transaction.sortTransactions(transactions, Comparator.comparing(Transaction::getDate));
                                            Transaction.displayTransactions(transactionMap, sortedByDate);
                                            // input = scanner.nextLine();
                                            // deleteTransactions(scanner, CLEAR_SCREEN, transactionMap, sortedByDate);
                                            handleSortingAndFiltering(scanner);
                                            break;

                                        case "D":
                                            System.out.print(CLEAR_SCREEN);
                                            System.out.println(VIOLET + "Sorting by date from newest to oldest..." + RESET);
                                            List<Transaction> sortedByDateReversed = Transaction.sortTransactions(transactions, Comparator.comparing(Transaction::getDate).reversed());
                                            Transaction.displayTransactions(transactionMap, sortedByDateReversed);
                                            // input = scanner.nextLine();
                                            // deleteTransactions(scanner, CLEAR_SCREEN, transactionMap, sortedByDateReversed);
                                            handleSortingAndFiltering(scanner);
                                            break;

                                        default:
                                            System.out.print(CLEAR_SCREEN);
                                            System.out.println(RED + "Invalid input. Please try again." + RESET);
                                    }
                                    break;

                                case "T":
                                    System.out.print(CLEAR_SCREEN);
                                    System.out.println(VIOLET + "Sort by A-Z (A) or Z-A (D)?" + RESET);
                                    String input5 = scanner.nextLine().toUpperCase();
                                    switch(input5) {
                                        case "A":
                                            System.out.print(CLEAR_SCREEN);
                                            System.out.println(VIOLET + "Sorting by type from A-Z..." + RESET);
                                            List<Transaction> sortedByType = Transaction.sortTransactions(transactions, Comparator.comparing(Transaction::getType));
                                            Transaction.displayTransactions(transactionMap, sortedByType);
                                            // input = scanner.nextLine();
                                            // deleteTransactions(scanner, CLEAR_SCREEN, transactionMap, sortedByType);
                                            handleSortingAndFiltering(scanner);
                                            break;

                                        case "D":
                                            System.out.print(CLEAR_SCREEN);
                                            System.out.println(VIOLET + "Sorting by type from Z-A..." + RESET);
                                            List<Transaction> sortedByTypeReversed = Transaction.sortTransactions(transactions, Comparator.comparing(Transaction::getType).reversed());
                                            Transaction.displayTransactions(transactionMap, sortedByTypeReversed);
                                            // input = scanner.nextLine();
                                            // deleteTransactions(scanner, CLEAR_SCREEN, transactionMap, sortedByTypeReversed);
                                            handleSortingAndFiltering(scanner);
                                            break;

                                        default:
                                            System.out.print(CLEAR_SCREEN);
                                            System.out.println(RED + "Invalid input. Please try again." + RESET);
                                    }                                    
                                    break;

                                case "N":
                                    System.out.print(CLEAR_SCREEN);
                                    System.out.println(VIOLET + "Sort by A-Z (A) or Z-A (D)?" + RESET);
                                    String input6 = scanner.nextLine().toUpperCase();
                                    switch(input6) {
                                        case "A":
                                            System.out.print(CLEAR_SCREEN);
                                            System.out.println(VIOLET + "Sorting by narrative from A-Z..." + RESET);
                                            List<Transaction> sortedByNarrative = Transaction.sortTransactions(transactions, Comparator.comparing(Transaction::getNarrative));
                                            Transaction.displayTransactions(transactionMap, sortedByNarrative);
                                            // input = scanner.nextLine();
                                            // deleteTransactions(scanner, CLEAR_SCREEN, transactionMap, sortedByNarrative);
                                            handleSortingAndFiltering(scanner);
                                            break;
                                            
                                        case "D":
                                            System.out.print(CLEAR_SCREEN);
                                            System.out.println(VIOLET + "Sorting by narrative from Z-A..." + RESET);
                                            List<Transaction> sortedByNarrativeReversed = Transaction.sortTransactions(transactions, Comparator.comparing(Transaction::getNarrative).reversed());
                                            Transaction.displayTransactions(transactionMap, sortedByNarrativeReversed);
                                            // input = scanner.nextLine();
                                            // deleteTransactions(scanner, CLEAR_SCREEN, transactionMap, sortedByNarrativeReversed);
                                            handleSortingAndFiltering(scanner);
                                            break;
                                            
                                        default:
                                            System.out.print(CLEAR_SCREEN);
                                            System.out.println(RED + "Invalid input. Please try again." + RESET);
                                    }                                  
                                    break;
                                        
                                case "A":
                                    System.out.print(CLEAR_SCREEN);
                                    System.out.println(VIOLET + "Sort by ascending (A) or descending (D)?" + RESET);
                                    String input8 = scanner.nextLine().toUpperCase();
                                    switch(input8) {
                                        case "A":
                                            System.out.print(CLEAR_SCREEN);
                                            System.out.println(VIOLET + "Sorting by amount from lowest to highest..." + RESET);
                                            List<Transaction> sortedByAmount = Transaction.sortTransactions(transactions, Comparator.comparing(Transaction::getAmount));
                                            Transaction.displayTransactions(transactionMap, sortedByAmount);
                                            // input = scanner.nextLine();
                                            // deleteTransactions(scanner, CLEAR_SCREEN, transactionMap, sortedByAmount);
                                            handleSortingAndFiltering(scanner);
                                            break;

                                        case "D":
                                            System.out.print(CLEAR_SCREEN);
                                            System.out.println(VIOLET + "Sorting by amount from highest to lowest..." + RESET);
                                            List<Transaction> sortedByAmountReversed = Transaction.sortTransactions(transactions, Comparator.comparing(Transaction::getAmount).reversed());
                                            Transaction.displayTransactions(transactionMap, sortedByAmountReversed);
                                            // input = scanner.nextLine();
                                            // deleteTransactions(scanner, CLEAR_SCREEN, transactionMap, sortedByAmountReversed);
                                            handleSortingAndFiltering(scanner);
                                            break;

                                        default:
                                            System.out.print(CLEAR_SCREEN);
                                            System.out.println(RED+"Invalid input. Please try again."+ RESET);
                                    }
                                    break;

                                case "C":
                                    System.out.print(CLEAR_SCREEN);
                                    System.out.println(VIOLET + "Sort by A-Z (A) or Z-A (D)?"+ RESET);
                                    String input9 = scanner.nextLine().toUpperCase();
                                    switch(input9) {
                                        case "A":
                                            System.out.print(CLEAR_SCREEN);
                                            System.out.println(VIOLET + "Sorting by category from A-Z..."+ RESET);
                                            List<Transaction> sortedByCategory = Transaction.sortTransactions(transactions, Comparator.comparing(Transaction::getCategory));
                                            Transaction.displayTransactions(transactionMap, sortedByCategory);
                                            // input = scanner.nextLine();
                                            // deleteTransactions(scanner, CLEAR_SCREEN, transactionMap, sortedByCategory);
                                            handleSortingAndFiltering(scanner);
                                            break;

                                        case "D":
                                            System.out.print(CLEAR_SCREEN);
                                            System.out.println(VIOLET + "Sorting by category from Z-A..."+ RESET);
                                            List<Transaction> sortedByCategoryReversed = Transaction.sortTransactions(transactions, Comparator.comparing(Transaction::getCategory).reversed());
                                            Transaction.displayTransactions(transactionMap, sortedByCategoryReversed);
                                            // input = scanner.nextLine();
                                            // deleteTransactions(scanner, CLEAR_SCREEN, transactionMap, sortedByCategoryReversed);
                                            handleSortingAndFiltering(scanner);
                                            break;

                                        default:
                                            System.out.print(CLEAR_SCREEN);
                                            System.out.println(RED+"Invalid input. Please try again."+ RESET);
                                    }
                                    break;
                            }
                            break;

                        case "F":
                            System.out.print(CLEAR_SCREEN);
                            System.out.println(VIOLET + "Filter by: \n 'D' - DATE \n 'T' - TYPE \n 'N' - NARRATIVE \n 'B' - BANK REFERENCE \n 'A' - AMOUNT \n 'C' - CATEGORY"+ RESET);
                            input = scanner.nextLine().toUpperCase();
                            switch(input) {
                                case "D":
                                    System.out.print(CLEAR_SCREEN);
                                    System.out.println(VIOLET + "Enter date to filter (YYYY-MM-DD):"+ RESET);
                                    LocalDate filterDate = LocalDate.parse(scanner.nextLine());
                                    List<Transaction> filteREDByDate = Transaction.filterTransactions(transactions, transaction -> transaction.getDate().equals(filterDate));
                                    System.out.println(CLEAR_SCREEN);
                                    System.out.println(VIOLET + "Filtering by date - [ " + filterDate.toString() + " ]"+ RESET);
                                    Transaction.displayTransactions(transactionMap, filteREDByDate);
                                    // input = scanner.nextLine();
                                    handleSortingAndFiltering(scanner);
                                    System.out.println(CLEAR_SCREEN);
                                    break;

                                case "T":
                                    System.out.print(CLEAR_SCREEN);
                                    System.out.println(VIOLET + "Enter type to filter (INCOMING, OUTGOING, PURCHASE):"+ RESET);
                                    TransactionType filterType = TransactionType.valueOf(scanner.nextLine().toUpperCase());
                                    List<Transaction> filteREDByType = Transaction.filterTransactions(transactions, transaction -> transaction.getType() == filterType);
                                    System.out.println(CLEAR_SCREEN);
                                    System.out.println(VIOLET + "Filtering by type - [ " + filterType.toString() + " ]"+ RESET);
                                    Transaction.displayTransactions(transactionMap, filteREDByType);
                                    // input = scanner.nextLine();
                                    handleSortingAndFiltering(scanner);
                                    System.out.println(CLEAR_SCREEN);
                                    break;

                                case "N":
                                    System.out.print(CLEAR_SCREEN);
                                    System.out.println(VIOLET + "Enter narrative to filter:"+RESET);
                                    String filterNarrative = scanner.nextLine().toLowerCase();
                                    List<Transaction> filteREDByNarrative = Transaction.filterTransactions(transactions, transaction -> transaction.getNarrative().toLowerCase().contains(filterNarrative));
                                    System.out.println(CLEAR_SCREEN);
                                    System.out.println(VIOLET + "Filtering by narrative - [ " + filterNarrative + " ]"+RESET);
                                    Transaction.displayTransactions(transactionMap, filteREDByNarrative);
                                    // input = scanner.nextLine();
                                    handleSortingAndFiltering(scanner);
                                    System.out.println(CLEAR_SCREEN);
                                    break;

                                case "B":
                                    System.out.print(CLEAR_SCREEN);
                                    System.out.println(VIOLET + "Enter bank reference to filter:"+RESET);
                                    String filterBankReference = scanner.nextLine().toLowerCase();
                                    List<Transaction> filteREDByBankReference = Transaction.filterTransactions(transactions, transaction -> transaction.getBankReference().toLowerCase().contains(filterBankReference));
                                    System.out.println(CLEAR_SCREEN);
                                    System.out.println(VIOLET + "Filtering by bank reference - [ " + filterBankReference + " ]"+RESET);
                                    Transaction.displayTransactions(transactionMap, filteREDByBankReference);
                                    // input = scanner.nextLine();
                                    handleSortingAndFiltering(scanner);
                                    System.out.println(CLEAR_SCREEN);
                                    break;

                                case "A":
                                    System.out.print(CLEAR_SCREEN);
                                    System.out.println(VIOLET + "Enter amount to filter:"+RESET);
                                    String sFilterAmount = scanner.nextLine();
                                    double filterAmount = 0.0;
                                    try {
                                        filterAmount = Double.parseDouble(sFilterAmount);
                                    } catch (NumberFormatException e) {
                                        System.out.println(CLEAR_SCREEN);
                                        System.out.println(RED+"Invalid input. Please enter a valid amount."+ RESET);
                                        break;
                                    }
                                    final double finalFilterAmount = filterAmount;
                                    List<Transaction> filteREDByAmount = Transaction.filterTransactions(transactions, transaction -> transaction.getAmount() == finalFilterAmount);
                                    System.out.println(CLEAR_SCREEN);
                                    System.out.println(VIOLET + "Filtering by amount - [ " + sFilterAmount + " ]" + RESET);
                                    Transaction.displayTransactions(transactionMap, filteREDByAmount);
                                    // input = scanner.nextLine();
                                    handleSortingAndFiltering(scanner);
                                    System.out.println(CLEAR_SCREEN);
                                    break;

                                case "C":
                                    System.out.print(CLEAR_SCREEN);
                                    System.out.println(VIOLET + "Enter category to filter:"+RESET);
                                    String filterCategory = scanner.nextLine();
                                    List<Transaction> filteREDByCategory = Transaction.filterTransactions(transactions, transaction -> transaction.getCategory().equalsIgnoreCase(filterCategory));
                                    System.out.println(CLEAR_SCREEN);
                                    System.out.println(VIOLET + "Filtering by category - [ " + filterCategory + " ]"+RESET);
                                    Transaction.displayTransactions(transactionMap, filteREDByCategory);
                                    // input = scanner.nextLine();
                                    handleSortingAndFiltering(scanner);
                                    System.out.println(CLEAR_SCREEN);
                                    break;

                                default:
                                    System.out.print(CLEAR_SCREEN);
                                    System.out.println(RED+"Invalid input. Please try again."+ RESET);
                            }
                            break;
                        
                        case "N":
                            System.out.print(CLEAR_SCREEN);
                            System.out.println(VIOLET + "New Transaction:"+ RESET);
                            System.out.println(VIOLET + "Enter the transaction type (INCOMING, OUTGOING, PURCHASE): "+ RESET);
                            String type = scanner.nextLine().toUpperCase();
                            try {
                                TransactionType.valueOf(type);
                            } catch (IllegalArgumentException e) {
                                System.out.println(CLEAR_SCREEN);
                                System.out.println(RED+"Invalid input. Enter a valid type value."+ RESET);
                                break;
                            }
                            System.out.println(VIOLET + "Enter transaction narrative:"+ RESET);
                            String narrative = scanner.nextLine();
                            System.out.println(VIOLET + "Enter bank reference:"+ RESET);
                            String bankReference = scanner.nextLine();
                            System.out.println(VIOLET + "Enter transaction amount:"+ RESET);
                            String sAmount = scanner.nextLine();
                            double amount = 0.0;
                            try {
                                amount = Double.parseDouble(sAmount);
                            } catch (NumberFormatException e) {
                                System.out.println(CLEAR_SCREEN);
                                System.out.println(RED+"Invalid input. Please enter a valid amount."+ RESET);
                                break;
                            }
                            System.out.println(VIOLET + "Enter transaction category:"+ RESET);
                            String category = scanner.nextLine();
                            if (type.isEmpty() || narrative.isEmpty() || bankReference.isEmpty() || bankReference.isEmpty() || category.isEmpty()) {
                                System.out.println(CLEAR_SCREEN);
                                System.out.println(RED+"Invalid input. Make sure to input everything correctly."+ RESET);
                                break;
                            }
                            Transaction newTransaction = new Transaction(LocalDate.now(), TransactionType.valueOf(type), narrative, bankReference, amount, category);
                            transactions.add(newTransaction);
                            TransactionManager.addTransaction(newTransaction);
                            System.out.print(CLEAR_SCREEN);
                            System.out.println(GREEN+"Transaction added successfully!"+ RESET);
                            break;
                            
                        case "D":
                            Transaction.deleteTransactions(scanner, transactionMap, transactions);
                            break;

                        case "Y":
                            System.out.print(CLEAR_SCREEN);
                            System.out.println(VIOLET + "Returning to main menu..."+ RESET);
                            System.out.println();
                            break;

                        case "X":
                            System.out.print(CLEAR_SCREEN);
                            System.out.println(VIOLET + "Exiting..."+ RESET);
                            scanner.close();
                            return;

                        default:
                            System.out.print(CLEAR_SCREEN);
                            System.out.println(RED+"Invalid input. Please try again."+ RESET);
                            break;
                    }
                    break; 
                // BUDGET CASE
                case "B":
                    System.out.print(CLEAR_SCREEN);
                    Budget.displayBudgets(budgetMap, budgets);
                    System.out.println();
                    System.out.println(VIOLET + "Choose your option: \n 'S' - SORT \n 'F' - FILTER  \n 'N' - NEW BUDGET \n 'D' - DELETE BUDGET"+ RESET);
                    System.out.println(VIOLET + "--------------------------"+ RESET);
                    System.out.println(VIOLET + " 'Y' - RETURN TO MAIN \n 'X' - EXIT"+ RESET);
                    String input10 = scanner.nextLine().toUpperCase();
                    switch(input10) {
                        case "S":
                        System.out.print(CLEAR_SCREEN);
                        System.err.println(VIOLET + "Sort by: \n 'P' - PERIOD \n 'N' - NAME \n 'L' - LIMIT AMOUNT \n 'S' - START DATE"+ RESET);
                        input = scanner.nextLine().toUpperCase();
                        switch(input) {
                            case "P":
                                System.out.print(CLEAR_SCREEN);
                                System.out.println(VIOLET + "Sort by shortest to longest (A) or longest to shortest (D)?"+ RESET);
                                input = scanner.nextLine().toUpperCase();
                                switch(input) {
                                    case "A":
                                        System.out.print(CLEAR_SCREEN);
                                        System.out.println(VIOLET + "Sorting by period from shortest to longest..."+ RESET);
                                        List<Budget> sortedByPeriod = Budget.sortBudgets(budgets, Comparator.comparing(Budget::getPeriod));
                                        Budget.displayBudgets(budgetMap, sortedByPeriod);
                                        // input = scanner.nextLine();
                                        // deleteBudgets(scanner, CLEAR_SCREEN, budgetMap, sortedByPeriod);
                                        handleSortingAndFiltering(scanner);
                                        break;

                                    case "D":
                                        System.out.print(CLEAR_SCREEN);
                                        System.out.println(VIOLET + "Sorting by period from longest to shortest..."+ RESET);
                                        List<Budget> sortedByPeriodReversed = Budget.sortBudgets(budgets, Comparator.comparing(Budget::getPeriod).reversed());
                                        Budget.displayBudgets(budgetMap, sortedByPeriodReversed);
                                        // input = scanner.nextLine();
                                        // deleteBudgets(scanner, CLEAR_SCREEN, budgetMap, sortedByPeriodReversed);
                                        handleSortingAndFiltering(scanner);
                                        break;

                                    default:
                                        System.out.print(CLEAR_SCREEN);
                                        System.out.println(RED+"Invalid input. Please try again."+ RESET);
                                }
                                break;

                            case "N":
                                System.out.print(CLEAR_SCREEN);
                                System.out.println(VIOLET + "Sort by A-Z (A) or Z-A (D)?"+ RESET);
                                String input5 = scanner.nextLine().toUpperCase();
                                switch(input5) {
                                    case "A":
                                        System.out.print(CLEAR_SCREEN);
                                        System.out.println(VIOLET + "Sorting by name from A-Z..."+ RESET);
                                        List<Budget> sortedByName = Budget.sortBudgets(budgets, Comparator.comparing(Budget::getName));
                                        Budget.displayBudgets(budgetMap, sortedByName);
                                        // input = scanner.nextLine();
                                        // deleteBudgets(scanner, CLEAR_SCREEN, budgetMap, sortedByName);
                                        handleSortingAndFiltering(scanner);
                                        break;

                                    case "D":
                                        System.out.print(CLEAR_SCREEN);
                                        System.out.println(VIOLET + "Sorting by name from Z-A..."+ RESET);
                                        List<Budget> sortedByNameReversed = Budget.sortBudgets(budgets, Comparator.comparing(Budget::getName).reversed());
                                        Budget.displayBudgets(budgetMap, sortedByNameReversed);
                                        // input = scanner.nextLine();
                                        // deleteBudgets(scanner, CLEAR_SCREEN, budgetMap, sortedByNameReversed);
                                        handleSortingAndFiltering(scanner);
                                        break;

                                    default:
                                        System.out.print(CLEAR_SCREEN);
                                        System.out.println(RED+"Invalid input. Please try again."+ RESET);
                                }                                    
                                break;

                            case "L":
                                System.out.print(CLEAR_SCREEN);
                                System.out.println(VIOLET + "Sort by ascending (A) or descending (D)?"+ RESET);
                                String input6 = scanner.nextLine().toUpperCase();
                                switch(input6) {
                                    case "A":
                                        System.out.print(CLEAR_SCREEN);
                                        System.out.println(VIOLET + "Sorting by limit amount from lowest to highest..."+ RESET);
                                        List<Budget> sortedByLimitAmount = Budget.sortBudgets(budgets, Comparator.comparing(Budget::getLimitAmount));
                                        Budget.displayBudgets(budgetMap, sortedByLimitAmount);
                                        // input = scanner.nextLine();
                                        // deleteBudgets(scanner, CLEAR_SCREEN, budgetMap, sortedByLimitAmount);
                                        handleSortingAndFiltering(scanner);
                                        break;
                                        
                                    case "D":
                                        System.out.print(CLEAR_SCREEN);
                                        System.out.println(VIOLET + "Sorting by limit amount from highest to lowest..."+ RESET);
                                        List<Budget> sortedByLimitAmountReversed = Budget.sortBudgets(budgets, Comparator.comparing(Budget::getLimitAmount).reversed());
                                        Budget.displayBudgets(budgetMap, sortedByLimitAmountReversed);
                                        // input = scanner.nextLine();
                                        // deleteBudgets(scanner, CLEAR_SCREEN, budgetMap, sortedByLimitAmountReversed);
                                        handleSortingAndFiltering(scanner);
                                        break;
                                        
                                    default:
                                        System.out.print(CLEAR_SCREEN);
                                        System.out.println(RED+"Invalid input. Please try again."+ RESET);
                                }                                  
                                break;
                                    
                            case "S":
                                System.out.print(CLEAR_SCREEN);
                                System.out.println(VIOLET + "Sort by oldest to newest (A) or newest to oldest (D)?"+ RESET);
                                String input8 = scanner.nextLine().toUpperCase();
                                switch(input8) {
                                    case "A":
                                        System.out.print(CLEAR_SCREEN);
                                        System.out.println(VIOLET + "Sorting by date from oldest to newest..."+ RESET);
                                        List<Budget> sortedByStartDate = Budget.sortBudgets(budgets, Comparator.comparing(Budget::getStartDate));
                                        Budget.displayBudgets(budgetMap, sortedByStartDate);
                                        // input = scanner.nextLine();
                                        // deleteBudgets(scanner, CLEAR_SCREEN, budgetMap, sortedByStartDate);
                                        handleSortingAndFiltering(scanner);
                                        break;

                                    case "D":
                                        System.out.print(CLEAR_SCREEN);
                                        System.out.println(VIOLET + "Sorting by date from newest to oldest..."+ RESET);
                                        List<Budget> sortedByStartDateReversed = Budget.sortBudgets(budgets, Comparator.comparing(Budget::getStartDate).reversed());
                                        Budget.displayBudgets(budgetMap, sortedByStartDateReversed);
                                        // input = scanner.nextLine();
                                        // deleteBudgets(scanner, CLEAR_SCREEN, budgetMap, sortedByStartDateReversed);
                                        handleSortingAndFiltering(scanner);
                                        break;

                                    default:
                                        System.out.print(CLEAR_SCREEN);
                                        System.out.println(RED+"Invalid input. Please try again."+ RESET);
                                }
                                break;
                        }
                        break;

                        case "N":
                            System.out.print(CLEAR_SCREEN);
                            System.out.println(VIOLET + "New Budget:"+ RESET);
                            System.out.println(VIOLET + "Enter the period (DAILY, WEEKLY, MONTHLY, YEARLY): "+ RESET);
                            String period = scanner.nextLine().toUpperCase();
                            System.out.println(VIOLET + "Enter budget name:"+ RESET);
                            String name = scanner.nextLine();
                            System.out.println(VIOLET + "Enter budget limit amount:"+ RESET);
                            double limitAmount = scanner.nextDouble();
                            scanner.nextLine();
                            Budget newBudget = new Budget(BudgetPeriod.valueOf(period), limitAmount, name, LocalDate.now());
                            budgets.add(newBudget);
                            BudgetManager.addBudget(newBudget);
                            System.out.print(CLEAR_SCREEN);
                            System.out.println(GREEN+"Budget added successfully!"+ RESET);
                            break;

                        case "D":
                            System.out.println();
                            System.out.println(VIOLET + "Enter the budget number to delete: "+ RESET);
                            int budgetNumberToDelete = scanner.nextInt();
                            scanner.nextLine();
                            if (budgetMap.containsKey(budgetNumberToDelete)) {
                                UUID budgetToDelete = budgetMap.get(budgetNumberToDelete);
                                budgets.removeIf(budget -> budget.getID().equals(budgetToDelete));
                                BudgetManager.deleteBudgetFromFile(budgetToDelete);
                                System.out.println(CLEAR_SCREEN);
                                System.out.println(GREEN+"Budget deleted successfully!"+ RESET);
                            } else {
                                System.out.println(CLEAR_SCREEN);
                                System.out.println(RED+"Invalid budget number. Please try again."+ RESET);
                            }
                            break;

                        case "Y":
                            System.out.print(CLEAR_SCREEN);
                            System.out.println(VIOLET + "Returning to main menu..."+  RESET);
                            System.out.println();
                            break;

                        case "X":
                            System.out.print(CLEAR_SCREEN);
                            System.out.println(VIOLET + "Exiting..."+ RESET);
                            scanner.close();
                            return;

                        default:
                            System.out.print(CLEAR_SCREEN);
                            System.out.println(RED+"Invalid input. Please try again."+ RESET);
                            break;
                    }
                    break;
                // CATEGORY CASE
                case "C":
                    System.out.print(CLEAR_SCREEN);
                    System.out.println(VIOLET + "Categories:"+ RESET);
                    System.out.printf("%-25s %-12s\n","Name", "Type");
                    System.out.println("-".repeat(125));
                    for (Category category : categories) {
                        System.out.printf("%-25s %-12s\n",
                            category.getName(), category.getType());
                    }

                    System.out.println();
                    System.out.println(VIOLET + "Choose your option: \n 'S' - SORT \n 'N' - NEW CATEGORY \n 'D' - DELETE CATEGORY"+ RESET);
                    System.out.println(VIOLET + "--------------------------"+ RESET);
                    System.out.println(VIOLET + " 'Y' - RETURN TO MAIN \n 'X' - EXIT"+ RESET);
                    String input4 = scanner.nextLine().toUpperCase();
                    switch(input4) {
                        case "N":
                            System.out.print(CLEAR_SCREEN);
                            System.out.println(VIOLET + "New Category:"+ RESET);
                            System.out.println(VIOLET + "Enter the category type (INCOME, EXPENSE): "+ RESET);
                            String categorytype = scanner.nextLine().toUpperCase();
                            System.out.println(VIOLET + "Enter category name:"+ RESET);
                            String categoryname = scanner.nextLine();
                            Category newCategory = new Category(categoryname, CategoryType.valueOf(categorytype));
                            categories.add(newCategory);
                            CategoryManager.addCategory(newCategory);
                            System.out.print(CLEAR_SCREEN);
                            System.out.println(GREEN+"Category added successfully!"+ RESET);
                            break;

                        case "D":
                            System.out.println();
                            System.out.println(VIOLET + "Enter the category name to delete: "+ RESET);
                            String categoryNameToDelete = scanner.nextLine();
                            if (!categories.isEmpty()) {
                                categories.removeIf(category -> category.getName().equals(categoryNameToDelete));
                                CategoryManager.deleteCategoryFromFile(categoryNameToDelete);
                                System.out.println(CLEAR_SCREEN);
                                System.out.println(GREEN+"Category deleted successfully!"+ RESET);
                            } else {
                                System.out.println(CLEAR_SCREEN);
                                System.out.println(RED+"Invalid category number. Please try again."+ RESET);
                            }
                            break;

                        case "Y":
                            System.out.print(CLEAR_SCREEN);
                            System.out.println(VIOLET + "Returning to main menu..."+ RESET);
                            System.out.println();
                            break;

                        case "X":
                            System.out.print(CLEAR_SCREEN);
                            System.out.println(VIOLET + "Exiting..."+ RESET);
                            scanner.close();
                            return;
                            
                        default:
                            System.out.print(CLEAR_SCREEN);
                            System.out.println(RED+"Invalid input. Please try again."+ RESET);
                            break;
                    } 
                    break;

                case "X":
                    System.out.print(CLEAR_SCREEN);
                    System.out.println();
                    System.out.println(VIOLET + "Exiting..."+ RESET);
                    scanner.close();
                    return;
                
                default: 
                    System.out.print(CLEAR_SCREEN);
                    System.out.println();
                    System.out.println(RED+"Invalid input. Please try again."+ RESET);
                    System.out.println();
                    break;
            }
        }
    }
    public static void handleSortingAndFiltering(Scanner scanner) {
        System.out.println();
        System.out.println(VIOLET + "Choose your option: \n 'Y' - RETURN TO MAIN \n 'X' - EXIT"+ RESET);
        String inpouting = scanner.nextLine().toUpperCase();
    
        switch (inpouting) {
            case "Y":
                System.out.print(CLEAR_SCREEN);
                System.out.println(VIOLET + "Returning to main menu..." + RESET);
                System.out.println();
                break;
    
            case "X":
                System.out.print(CLEAR_SCREEN);
                System.out.println(VIOLET + "Exiting..." + RESET);
                System.exit(0);
                return;
    
            default:
                System.out.print(CLEAR_SCREEN);
                System.out.println(RED + "Invalid input. Please try again." + RESET);
                break;
        }
    }
}

