public class Transaction {
    private enum TransactionType {
        INCOMING,
        OUTGOING,
        PURCHASE
    }

    private TransactionType type;

    public Transaction () {

    }
}
