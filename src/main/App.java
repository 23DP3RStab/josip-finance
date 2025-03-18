package main;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        
        ArrayList<Transaction> transactions = TransactionManager.getTransactionList();
    
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }
}