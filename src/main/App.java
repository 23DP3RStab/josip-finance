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
    final static String Violet = "\u001B[35;1m";
    final static String Reset = "\u001B[0m";
    final static String Red = "\u001B[31;1m";
    final static String Green = "\u001B[32;1m";
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String clearScreen = "\u001B[2J";
        String banner = ("      ██╗ ██████╗  ██████╗██╗██████╗     \u001B[35;1m███████╗██╗███╗   ██╗ █████╗ ███╗   ██╗ ██████╗███████╗\u001B[0m\r\n" + //
        "      ██║██╔═══██╗██╔════╝██║██╔══██╗    \u001B[35;1m██╔════╝██║████╗  ██║██╔══██╗████╗  ██║██╔════╝██╔════╝\u001B[0m\r\n" + //
        "      ██║██║   ██║█████╗  ██║██████╔╝    \u001B[35;1m█████╗  ██║██╔██╗ ██║███████║██╔██╗ ██║██║     █████╗  \u001B[0m\r\n" + //
        " ██   ██║██║   ██║    ██║ ██║██╔═══╝     \u001B[35;1m██╔══╝  ██║██║╚██╗██║██╔══██║██║╚██╗██║██║     ██╔══╝  \u001B[0m\r\n" + //
        " ╚█████╔╝╚██████╔╝██████║ ██║██║         \u001B[35;1m██║     ██║██║ ╚████║██║  ██║██║ ╚████║╚██████╗███████╗\u001B[0m\r\n" + //
        "  ╚════╝  ╚═════╝ ╚═════╝ ╚═╝╚═╝         \u001B[35;1m╚═╝     ╚═╝╚═╝  ╚═══╝╚═╝  ╚═╝╚═╝  ╚═══╝ ╚═════╝╚══════╝\u001B[0m\r\n" + //
        "\r\n" + //
        "                                   J O S I P   \u001B[35;1mF I N A N C E\r\n" + //
        "" +  "\u001B[0m");
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
            System.out.println(Violet + "Choose your option:  \n 'T' - VIEW YOUR TRANSACTIONS \n 'B' - VIEW YOUR BUDGET \n 'C' - VIEW YOUR CATEGORY \n 'X' - EXIT" +  Reset);
            String input = scanner.nextLine().toUpperCase();
            switch(input) {
                // TRANSACTION CASE
                case "T":
                    System.out.println(clearScreen);
                    Transaction.displayTransactions(transactionMap, transactions);
                    System.out.println();
                    System.out.println(Violet + "Choose your option: \n 'S' - SORT \n 'F' - FILTER  \n 'N' - NEW TRANSACTION \n 'D' - DELETE TRANSACTION" + Reset);
                    System.out.println(Violet + "--------------------------" + Reset);
                    System.out.println(Violet + " 'Y' - RETURN TO MAIN \n 'X' - EXIT" + Reset);
                    input = scanner.nextLine().toUpperCase();
                    switch(input) {
                        case "S":
                            System.out.print(clearScreen);
                            System.err.println(Violet + "Sort by: \n 'D' - DATE \n 'T' - TYPE \n 'N' - NARRATIVE \n 'A' - AMOUNT \n 'C' - CATEGORY" + Reset);
                            input = scanner.nextLine().toUpperCase();
                            switch(input) {
                                case "D":
                                    System.out.print(clearScreen);
                                    System.out.println(Violet + "Sort by oldest to newest (A) or newest to oldest (D)?" + Reset);
                                    input = scanner.nextLine().toUpperCase();
                                    switch(input) {
                                        case "A":
                                            System.out.print(clearScreen);
                                            System.out.println(Violet + "Sorting by date from oldest to newest..." + Reset);
                                            List<Transaction> sortedByDate = Transaction.sortTransactions(transactions, Comparator.comparing(Transaction::getDate));
                                            Transaction.displayTransactions(transactionMap, sortedByDate);
                                            input = scanner.nextLine();
                                            // deleteTransactions(scanner, clearScreen, transactionMap, sortedByDate);
                                            break;

                                        case "D":
                                            System.out.print(clearScreen);
                                            System.out.println(Violet+ "Sorting by date from newest to oldest..." + Reset);
                                            List<Transaction> sortedByDateReversed = Transaction.sortTransactions(transactions, Comparator.comparing(Transaction::getDate).reversed());
                                            Transaction.displayTransactions(transactionMap, sortedByDateReversed);
                                            input = scanner.nextLine();
                                            // deleteTransactions(scanner, clearScreen, transactionMap, sortedByDateReversed);
                                            break;

                                        default:
                                            System.out.print(clearScreen);
                                            System.out.println(Red + "Invalid input. Please try again." + Reset);
                                    }
                                    break;

                                case "T":
                                    System.out.print(clearScreen);
                                    System.out.println(Violet + "Sort by A-Z (A) or Z-A (D)?" + Reset);
                                    String input5 = scanner.nextLine().toUpperCase();
                                    switch(input5) {
                                        case "A":
                                            System.out.print(clearScreen);
                                            System.out.println(Violet+ "Sorting by type from A-Z..." + Reset);
                                            List<Transaction> sortedByType = Transaction.sortTransactions(transactions, Comparator.comparing(Transaction::getType));
                                            Transaction.displayTransactions(transactionMap, sortedByType);
                                            input = scanner.nextLine();
                                            // deleteTransactions(scanner, clearScreen, transactionMap, sortedByType);
                                            break;

                                        case "D":
                                            System.out.print(clearScreen);
                                            System.out.println(Violet + "Sorting by type from Z-A..." + Reset);
                                            List<Transaction> sortedByTypeReversed = Transaction.sortTransactions(transactions, Comparator.comparing(Transaction::getType).reversed());
                                            Transaction.displayTransactions(transactionMap, sortedByTypeReversed);
                                            input = scanner.nextLine();
                                            // deleteTransactions(scanner, clearScreen, transactionMap, sortedByTypeReversed);
                                            break;

                                        default:
                                            System.out.print(clearScreen);
                                            System.out.println(Red + "Invalid input. Please try again." + Reset);
                                    }                                    
                                    break;

                                case "N":
                                    System.out.print(clearScreen);
                                    System.out.println(Violet + "Sort by A-Z (A) or Z-A (D)?" + Reset);
                                    String input6 = scanner.nextLine().toUpperCase();
                                    switch(input6) {
                                        case "A":
                                            System.out.print(clearScreen);
                                            System.out.println(Violet + "Sorting by narrative from A-Z..." + Reset);
                                            List<Transaction> sortedByNarrative = Transaction.sortTransactions(transactions, Comparator.comparing(Transaction::getNarrative));
                                            Transaction.displayTransactions(transactionMap, sortedByNarrative);
                                            input = scanner.nextLine();
                                            // deleteTransactions(scanner, clearScreen, transactionMap, sortedByNarrative);
                                            break;
                                            
                                        case "D":
                                            System.out.print(clearScreen);
                                            System.out.println(Violet + "Sorting by narrative from Z-A..." + Reset);
                                            List<Transaction> sortedByNarrativeReversed = Transaction.sortTransactions(transactions, Comparator.comparing(Transaction::getNarrative).reversed());
                                            Transaction.displayTransactions(transactionMap, sortedByNarrativeReversed);
                                            input = scanner.nextLine();
                                            // deleteTransactions(scanner, clearScreen, transactionMap, sortedByNarrativeReversed);
                                            break;
                                            
                                        default:
                                            System.out.print(clearScreen);
                                            System.out.println(Red + "Invalid input. Please try again." + Reset);
                                    }                                  
                                    break;
                                        
                                case "A":
                                    System.out.print(clearScreen);
                                    System.out.println(Violet + "Sort by ascending (A) or descending (D)?" + Reset);
                                    String input8 = scanner.nextLine().toUpperCase();
                                    switch(input8) {
                                        case "A":
                                            System.out.print(clearScreen);
                                            System.out.println(Violet+ "Sorting by amount from lowest to highest..." + Reset);
                                            List<Transaction> sortedByAmount = Transaction.sortTransactions(transactions, Comparator.comparing(Transaction::getAmount));
                                            Transaction.displayTransactions(transactionMap, sortedByAmount);
                                            input = scanner.nextLine();
                                            // deleteTransactions(scanner, clearScreen, transactionMap, sortedByAmount);
                                            break;

                                        case "D":
                                            System.out.print(clearScreen);
                                            System.out.println(Violet+ "Sorting by amount from highest to lowest..." + Reset);
                                            List<Transaction> sortedByAmountReversed = Transaction.sortTransactions(transactions, Comparator.comparing(Transaction::getAmount).reversed());
                                            Transaction.displayTransactions(transactionMap, sortedByAmountReversed);
                                            input = scanner.nextLine();
                                            // deleteTransactions(scanner, clearScreen, transactionMap, sortedByAmountReversed);
                                            break;

                                        default:
                                            System.out.print(clearScreen);
                                            System.out.println(Red+"Invalid input. Please try again."+ Reset);
                                    }
                                    break;

                                case "C":
                                    System.out.print(clearScreen);
                                    System.out.println(Violet+"Sort by A-Z (A) or Z-A (D)?"+ Reset);
                                    String input9 = scanner.nextLine().toUpperCase();
                                    switch(input9) {
                                        case "A":
                                            System.out.print(clearScreen);
                                            System.out.println(Violet+"Sorting by category from A-Z..."+ Reset);
                                            List<Transaction> sortedByCategory = Transaction.sortTransactions(transactions, Comparator.comparing(Transaction::getCategory));
                                            Transaction.displayTransactions(transactionMap, sortedByCategory);
                                            input = scanner.nextLine();
                                            // deleteTransactions(scanner, clearScreen, transactionMap, sortedByCategory);
                                            break;

                                        case "D":
                                            System.out.print(clearScreen);
                                            System.out.println(Violet+"Sorting by category from Z-A..."+ Reset);
                                            List<Transaction> sortedByCategoryReversed = Transaction.sortTransactions(transactions, Comparator.comparing(Transaction::getCategory).reversed());
                                            Transaction.displayTransactions(transactionMap, sortedByCategoryReversed);
                                            input = scanner.nextLine();
                                            // deleteTransactions(scanner, clearScreen, transactionMap, sortedByCategoryReversed);
                                            break;

                                        default:
                                            System.out.print(clearScreen);
                                            System.out.println(Red+"Invalid input. Please try again."+ Reset);
                                    }
                                    break;
                            }
                            break;

                        case "F":
                            System.out.print(clearScreen);
                            System.out.println(Violet + "Filter by: \n 'D' - DATE \n 'T' - TYPE \n 'N' - NARRATIVE \n 'B' - BANK REFERENCE \n 'A' - AMOUNT \n 'C' - CATEGORY"+ Reset);
                            input = scanner.nextLine().toUpperCase();
                            switch(input) {
                                case "D":
                                    System.out.print(clearScreen);
                                    System.out.println(Violet+"Enter date to filter (YYYY-MM-DD):"+ Reset);
                                    LocalDate filterDate = LocalDate.parse(scanner.nextLine());
                                    List<Transaction> filteredByDate = Transaction.filterTransactions(transactions, transaction -> transaction.getDate().equals(filterDate));
                                    System.out.println(clearScreen);
                                    System.out.println(Violet+"Filtering by date - [ " + filterDate.toString() + " ]"+ Reset);
                                    Transaction.displayTransactions(transactionMap, filteredByDate);
                                    input = scanner.nextLine();
                                    System.out.println(clearScreen);
                                    break;

                                case "T":
                                    System.out.print(clearScreen);
                                    System.out.println(Violet+"Enter type to filter (INCOMING, OUTGOING, PURCHASE):"+ Reset);
                                    TransactionType filterType = TransactionType.valueOf(scanner.nextLine().toUpperCase());
                                    List<Transaction> filteredByType = Transaction.filterTransactions(transactions, transaction -> transaction.getType() == filterType);
                                    System.out.println(clearScreen);
                                    System.out.println(Violet+"Filtering by type - [ " + filterType.toString() + " ]"+ Reset);
                                    Transaction.displayTransactions(transactionMap, filteredByType);
                                    input = scanner.nextLine();
                                    System.out.println(clearScreen);
                                    break;

                                case "N":
                                    System.out.print(clearScreen);
                                    System.out.println(Violet+"Enter narrative to filter:"+Reset);
                                    String filterNarrative = scanner.nextLine().toLowerCase();
                                    List<Transaction> filteredByNarrative = Transaction.filterTransactions(transactions, transaction -> transaction.getNarrative().toLowerCase().contains(filterNarrative));
                                    System.out.println(clearScreen);
                                    System.out.println(Violet+"Filtering by narrative - [ " + filterNarrative + " ]"+Reset);
                                    Transaction.displayTransactions(transactionMap, filteredByNarrative);
                                    input = scanner.nextLine();
                                    System.out.println(clearScreen);
                                    break;

                                case "B":
                                    System.out.print(clearScreen);
                                    System.out.println(Violet+"Enter bank reference to filter:"+Reset);
                                    String filterBankReference = scanner.nextLine().toLowerCase();
                                    List<Transaction> filteredByBankReference = Transaction.filterTransactions(transactions, transaction -> transaction.getBankReference().toLowerCase().contains(filterBankReference));
                                    System.out.println(clearScreen);
                                    System.out.println(Violet+"Filtering by bank reference - [ " + filterBankReference + " ]"+Reset);
                                    Transaction.displayTransactions(transactionMap, filteredByBankReference);
                                    input = scanner.nextLine();
                                    System.out.println(clearScreen);
                                    break;

                                case "A":
                                    System.out.print(clearScreen);
                                    System.out.println(Violet+"Enter amount to filter:"+Reset);
                                    String sFilterAmount = scanner.nextLine();
                                    double filterAmount = 0.0;
                                    try {
                                        filterAmount = Double.parseDouble(sFilterAmount);
                                    } catch (NumberFormatException e) {
                                        System.out.println(clearScreen);
                                        System.out.println(Red+"Invalid input. Please enter a valid amount."+ Reset);
                                        break;
                                    }
                                    final double finalFilterAmount = filterAmount;
                                    List<Transaction> filteredByAmount = Transaction.filterTransactions(transactions, transaction -> transaction.getAmount() == finalFilterAmount);
                                    System.out.println(clearScreen);
                                    System.out.println(Violet+ "Filtering by amount - [ " + sFilterAmount + " ]" + Reset);
                                    Transaction.displayTransactions(transactionMap, filteredByAmount);
                                    input = scanner.nextLine();
                                    System.out.println(clearScreen);
                                    break;

                                case "C":
                                    System.out.print(clearScreen);
                                    System.out.println(Violet+"Enter category to filter:"+Reset);
                                    String filterCategory = scanner.nextLine();
                                    List<Transaction> filteredByCategory = Transaction.filterTransactions(transactions, transaction -> transaction.getCategory().equalsIgnoreCase(filterCategory));
                                    System.out.println(clearScreen);
                                    System.out.println(Violet+"Filtering by category - [ " + filterCategory + " ]"+Reset);
                                    Transaction.displayTransactions(transactionMap, filteredByCategory);
                                    input = scanner.nextLine();
                                    System.out.println(clearScreen);
                                    break;

                                default:
                                    System.out.print(clearScreen);
                                    System.out.println(Red+"Invalid input. Please try again."+ Reset);
                            }
                            break;
                        
                        case "N":
                            System.out.print(clearScreen);
                            System.out.println(Violet+"New Transaction:"+ Reset);
                            System.out.println(Violet+"Enter the transaction type (INCOMING, OUTGOING, PURCHASE): "+ Reset);
                            String type = scanner.nextLine().toUpperCase();
                            try {
                                TransactionType.valueOf(type);
                            } catch (IllegalArgumentException e) {
                                System.out.println(clearScreen);
                                System.out.println(Red+"Invalid input. Enter a valid type value."+ Reset);
                                break;
                            }
                            System.out.println(Violet+"Enter transaction narrative:"+ Reset);
                            String narrative = scanner.nextLine();
                            System.out.println(Violet+"Enter bank reference:"+ Reset);
                            String bankReference = scanner.nextLine();
                            System.out.println(Violet+"Enter transaction amount:"+ Reset);
                            String sAmount = scanner.nextLine();
                            double amount = 0.0;
                            try {
                                amount = Double.parseDouble(sAmount);
                            } catch (NumberFormatException e) {
                                System.out.println(clearScreen);
                                System.out.println(Red+"Invalid input. Please enter a valid amount."+ Reset);
                                break;
                            }
                            System.out.println(Violet+"Enter transaction category:"+ Reset);
                            String category = scanner.nextLine();
                            if (type.isEmpty() || narrative.isEmpty() || bankReference.isEmpty() || bankReference.isEmpty() || category.isEmpty()) {
                                System.out.println(clearScreen);
                                System.out.println(Red+"Invalid input. Make sure to input everything correctly."+ Reset);
                                break;
                            }
                            Transaction newTransaction = new Transaction(LocalDate.now(), TransactionType.valueOf(type), narrative, bankReference, amount, category);
                            transactions.add(newTransaction);
                            TransactionManager.addTransaction(newTransaction);
                            System.out.print(clearScreen);
                            System.out.println(Green+"Transaction added successfully!"+ Reset);
                            break;
                            
                        case "D":
                            Transaction.deleteTransactions(scanner, clearScreen, transactionMap, transactions);
                            break;

                        case "Y":
                            System.out.print(clearScreen);
                            System.out.println(Violet+"Returning to main menu..."+ Reset);
                            System.out.println();
                            break;

                        case "X":
                            System.out.print(clearScreen);
                            System.out.println(Violet+"Exiting..."+ Reset);
                            scanner.close();
                            return;

                        default:
                            System.out.print(clearScreen);
                            System.out.println(Red+"Invalid input. Please try again."+ Reset);
                            break;
                    }
                    break; 
                // BUDGET CASE
                case "B":
                    System.out.print(clearScreen);
                    Budget.displayBudgets(budgetMap, budgets);
                    System.out.println();
                    System.out.println(Violet+"Choose your option: \n 'S' - SORT \n 'F' - FILTER  \n 'N' - NEW BUDGET \n 'D' - DELETE BUDGET"+ Reset);
                    System.out.println(Violet+"--------------------------"+ Reset);
                    System.out.println(Violet+"Choose your option: \n 'Y' - RETURN TO MAIN \n 'X' - EXIT"+ Reset);
                    String input10 = scanner.nextLine().toUpperCase();
                    switch(input10) {
                        case "S":
                        System.out.print(clearScreen);
                        System.err.println(Violet+"Sort by: \n 'P' - PERIOD \n 'N' - NAME \n 'L' - LIMIT AMOUNT \n 'S' - START DATE"+ Reset);
                        input = scanner.nextLine().toUpperCase();
                        switch(input) {
                            case "P":
                                System.out.print(clearScreen);
                                System.out.println(Violet+"Sort by shortest to longest (A) or longest to shortest (D)?"+ Reset);
                                input = scanner.nextLine().toUpperCase();
                                switch(input) {
                                    case "A":
                                        System.out.print(clearScreen);
                                        System.out.println(Violet+"Sorting by period from shortest to longest..."+ Reset);
                                        List<Budget> sortedByPeriod = Budget.sortBudgets(budgets, Comparator.comparing(Budget::getPeriod));
                                        Budget.displayBudgets(budgetMap, sortedByPeriod);
                                        input = scanner.nextLine();
                                        // deleteBudgets(scanner, clearScreen, budgetMap, sortedByPeriod);
                                        break;

                                    case "D":
                                        System.out.print(clearScreen);
                                        System.out.println(Violet+"Sorting by period from longest to shortest..."+ Reset);
                                        List<Budget> sortedByPeriodReversed = Budget.sortBudgets(budgets, Comparator.comparing(Budget::getPeriod).reversed());
                                        Budget.displayBudgets(budgetMap, sortedByPeriodReversed);
                                        input = scanner.nextLine();
                                        // deleteBudgets(scanner, clearScreen, budgetMap, sortedByPeriodReversed);
                                        break;

                                    default:
                                        System.out.print(clearScreen);
                                        System.out.println(Red+"Invalid input. Please try again."+ Reset);
                                }
                                break;

                            case "N":
                                System.out.print(clearScreen);
                                System.out.println(Violet+"Sort by A-Z (A) or Z-A (D)?"+ Reset);
                                String input5 = scanner.nextLine().toUpperCase();
                                switch(input5) {
                                    case "A":
                                        System.out.print(clearScreen);
                                        System.out.println(Violet+"Sorting by name from A-Z..."+ Reset);
                                        List<Budget> sortedByName = Budget.sortBudgets(budgets, Comparator.comparing(Budget::getName));
                                        Budget.displayBudgets(budgetMap, sortedByName);
                                        input = scanner.nextLine();
                                        // deleteBudgets(scanner, clearScreen, budgetMap, sortedByName);
                                        break;

                                    case "D":
                                        System.out.print(clearScreen);
                                        System.out.println(Violet+"Sorting by name from Z-A..."+ Reset);
                                        List<Budget> sortedByNameReversed = Budget.sortBudgets(budgets, Comparator.comparing(Budget::getName).reversed());
                                        Budget.displayBudgets(budgetMap, sortedByNameReversed);
                                        input = scanner.nextLine();
                                        // deleteBudgets(scanner, clearScreen, budgetMap, sortedByNameReversed);
                                        break;

                                    default:
                                        System.out.print(clearScreen);
                                        System.out.println(Red+"Invalid input. Please try again."+ Reset);
                                }                                    
                                break;

                            case "L":
                                System.out.print(clearScreen);
                                System.out.println(Violet+"Sort by ascending (A) or descending (D)?"+ Reset);
                                String input6 = scanner.nextLine().toUpperCase();
                                switch(input6) {
                                    case "A":
                                        System.out.print(clearScreen);
                                        System.out.println(Violet+"Sorting by limit amount from lowest to highest..."+ Reset);
                                        List<Budget> sortedByLimitAmount = Budget.sortBudgets(budgets, Comparator.comparing(Budget::getLimitAmount));
                                        Budget.displayBudgets(budgetMap, sortedByLimitAmount);
                                        input = scanner.nextLine();
                                        // deleteBudgets(scanner, clearScreen, budgetMap, sortedByLimitAmount);
                                        break;
                                        
                                    case "D":
                                        System.out.print(clearScreen);
                                        System.out.println(Violet+"Sorting by limit amount from highest to lowest..."+ Reset);
                                        List<Budget> sortedByLimitAmountReversed = Budget.sortBudgets(budgets, Comparator.comparing(Budget::getLimitAmount).reversed());
                                        Budget.displayBudgets(budgetMap, sortedByLimitAmountReversed);
                                        input = scanner.nextLine();
                                        // deleteBudgets(scanner, clearScreen, budgetMap, sortedByLimitAmountReversed);
                                        break;
                                        
                                    default:
                                        System.out.print(clearScreen);
                                        System.out.println(Red+"Invalid input. Please try again."+ Reset);
                                }                                  
                                break;
                                    
                            case "S":
                                System.out.print(clearScreen);
                                System.out.println(Violet+"Sort by oldest to newest (A) or newest to oldest (D)?"+ Reset);
                                String input8 = scanner.nextLine().toUpperCase();
                                switch(input8) {
                                    case "A":
                                        System.out.print(clearScreen);
                                        System.out.println(Violet+"Sorting by date from oldest to newest..."+ Reset);
                                        List<Budget> sortedByStartDate = Budget.sortBudgets(budgets, Comparator.comparing(Budget::getStartDate));
                                        Budget.displayBudgets(budgetMap, sortedByStartDate);
                                        input = scanner.nextLine();
                                        // deleteBudgets(scanner, clearScreen, budgetMap, sortedByStartDate);
                                        break;

                                    case "D":
                                        System.out.print(clearScreen);
                                        System.out.println(Violet+"Sorting by date from newest to oldest..."+ Reset);
                                        List<Budget> sortedByStartDateReversed = Budget.sortBudgets(budgets, Comparator.comparing(Budget::getStartDate).reversed());
                                        Budget.displayBudgets(budgetMap, sortedByStartDateReversed);
                                        input = scanner.nextLine();
                                        // deleteBudgets(scanner, clearScreen, budgetMap, sortedByStartDateReversed);
                                        break;

                                    default:
                                        System.out.print(clearScreen);
                                        System.out.println(Red+"Invalid input. Please try again."+ Reset);
                                }
                                break;
                        }
                        break;

                        case "N":
                            System.out.print(clearScreen);
                            System.out.println(Violet+"New Budget:"+ Reset);
                            System.out.println(Violet+"Enter the period (DAILY, WEEKLY, MONTHLY, YEARLY): "+ Reset);
                            String period = scanner.nextLine().toUpperCase();
                            System.out.println(Violet+"Enter budget name:"+ Reset);
                            String name = scanner.nextLine();
                            System.out.println(Violet+"Enter budget limit amount:"+ Reset);
                            double limitAmount = scanner.nextDouble();
                            scanner.nextLine();
                            Budget newBudget = new Budget(BudgetPeriod.valueOf(period), limitAmount, name, LocalDate.now());
                            budgets.add(newBudget);
                            BudgetManager.addBudget(newBudget);
                            System.out.print(clearScreen);
                            System.out.println(Green+"Budget added successfully!"+ Reset);
                            break;

                        case "D":
                            System.out.println();
                            System.out.println(Violet+"Enter the budget number to delete: "+ Reset);
                            int budgetNumberToDelete = scanner.nextInt();
                            scanner.nextLine();
                            if (budgetMap.containsKey(budgetNumberToDelete)) {
                                UUID budgetToDelete = budgetMap.get(budgetNumberToDelete);
                                budgets.removeIf(budget -> budget.getID().equals(budgetToDelete));
                                BudgetManager.deleteBudgetFromFile(budgetToDelete);
                                System.out.println(clearScreen);
                                System.out.println(Green+"Budget deleted successfully!"+ Reset);
                            } else {
                                System.out.println(clearScreen);
                                System.out.println(Red+"Invalid budget number. Please try again."+ Reset);
                            }
                            break;

                        case "Y":
                            System.out.print(clearScreen);
                            System.out.println(Violet+"Returning to main menu..."+  Reset);
                            System.out.println();
                            break;

                        case "X":
                            System.out.print(clearScreen);
                            System.out.println(Violet+"Exiting..."+ Reset);
                            scanner.close();
                            return;

                        default:
                            System.out.print(clearScreen);
                            System.out.println(Red+"Invalid input. Please try again."+ Reset);
                            break;
                    }
                    break;
                // CATEGORY CASE
                case "C":
                    System.out.print(clearScreen);
                    System.out.println(Violet+"Categories:"+ Reset);
                    System.out.printf("%-25s %-12s\n","Name", "Type");
                    System.out.println("-".repeat(125));
                    for (Category category : categories) {
                        System.out.printf("%-25s %-12s\n",
                            category.getName(), category.getType());
                    }

                    System.out.println();
                    System.out.println(Violet+"Choose your option: \n 'S' - SORT \n 'N' - NEW CATEGORY \n 'D' - DELETE CATEGORY"+ Reset);
                    System.out.println(Violet+"--------------------------"+ Reset);
                    System.out.println(Violet+"Choose your option: \n 'Y' - RETURN TO MAIN \n 'X' - EXIT"+ Reset);
                    String input4 = scanner.nextLine().toUpperCase();
                    switch(input4) {
                        case "N":
                            System.out.print(clearScreen);
                            System.out.println(Violet+"New Category:"+ Reset);
                            System.out.println(Violet+"Enter the category type (INCOME, EXPENSE): "+ Reset);
                            String categorytype = scanner.nextLine().toUpperCase();
                            System.out.println(Violet+"Enter category name:"+ Reset);
                            String categoryname = scanner.nextLine();
                            Category newCategory = new Category(categoryname, CategoryType.valueOf(categorytype));
                            categories.add(newCategory);
                            CategoryManager.addCategory(newCategory);
                            System.out.print(clearScreen);
                            System.out.println(Green+"Category added successfully!"+ Reset);
                            break;

                        case "D":
                            System.out.println();
                            System.out.println(Violet+"Enter the category name to delete: "+ Reset);
                            String categoryNameToDelete = scanner.nextLine();
                            if (!categories.isEmpty()) {
                                categories.removeIf(category -> category.getName().equals(categoryNameToDelete));
                                CategoryManager.deleteCategoryFromFile(categoryNameToDelete);
                                System.out.println(clearScreen);
                                System.out.println(Green+"Category deleted successfully!"+ Reset);
                            } else {
                                System.out.println(clearScreen);
                                System.out.println(Red+"Invalid category number. Please try again."+ Reset);
                            }
                            break;

                        case "Y":
                            System.out.print(clearScreen);
                            System.out.println(Violet+"Returning to main menu..."+ Reset);
                            System.out.println();
                            break;

                        case "X":
                            System.out.print(clearScreen);
                            System.out.println(Violet+"Exiting..."+ Reset);
                            scanner.close();
                            return;
                            
                        default:
                            System.out.print(clearScreen);
                            System.out.println(Red+"Invalid input. Please try again."+ Reset);
                            break;
                    } 
                    break;

                case "X":
                    System.out.print(clearScreen);
                    System.out.println();
                    System.out.println(Violet+"Exiting..."+ Reset);
                    scanner.close();
                    return;
                
                default: 
                    System.out.print(clearScreen);
                    System.out.println();
                    System.out.println(Red+"Invalid input. Please try again."+ Reset);
                    System.out.println();
                    break;
            }
        }
    }
}