import java.util.HashMap;
import java.util.Random;

public class BankTeller extends Thread {
    private static final Random RNG = new Random(); // common thread-safe random number generator
    private static final int SLEEP_TIME = 100; // milliseconds

    private TransactionsDatabase transactionsDatabase;
    private HashMap<Integer, BankAccount> bankAccounts;

    public BankTeller(TransactionsDatabase transactionsDatabase, HashMap<Integer, BankAccount> bankAccounts)
    {
        this.transactionsDatabase = transactionsDatabase;
        this.bankAccounts = bankAccounts;
    }

    public BankTeller(String name, TransactionsDatabase transactionsDatabase, HashMap<Integer, BankAccount> bankAccounts)
    {
        super(name);
        this.transactionsDatabase = transactionsDatabase;
        this.bankAccounts = bankAccounts;
    }

    @Override
    public void run() {
        BankAccount bankAccount = null;

        Transaction transaction = transactionsDatabase.retrieveTransaction();
        while (transaction != null)
        {
            bankAccount = bankAccounts.get(transaction.getReceiver());
            bankAccount.transaction(transaction.getAmount());
            System.out.println("Teller #" + this.getName() + " transffered " + transaction.getAmount() + " to " + transaction.getReceiver() + " - new balance: " + bankAccount.getBalance());
            try {
                sleep(RNG.nextInt(SLEEP_TIME + 1));
            } catch (InterruptedException e) { }
            transaction = transactionsDatabase.retrieveTransaction();
        }
    }
}
