import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.LinkedList;

public class TransactionsDatabase {
    private LinkedList<Transaction> transactions;

    public TransactionsDatabase(Transaction[] transactions)
    {
        this.transactions = new LinkedList<>();
        this.transactions.addAll(Arrays.asList(transactions));
    }

    public synchronized Transaction retrieveTransaction()
    {
        try {
            return this.transactions.remove();
        } catch (NoSuchElementException e)
        {
            return null;
        }
    }
}
