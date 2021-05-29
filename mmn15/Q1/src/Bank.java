import javax.swing.text.html.parser.TagElement;
import java.util.Arrays;
import java.util.HashMap;

public class Bank {
    public static final int TELLERS_COUNT = 10;

    public static void main(String[] args) {
        BankAccount bankAccounts[] = {
            new BankAccount(0, 12313.5),
            new BankAccount(1, 543),
            new BankAccount(2, 54444),
            new BankAccount(3, 50),
            new BankAccount(4, 36666),
        };
        HashMap<Integer, BankAccount> bankAccountsHashMap = new HashMap<>();
        for (BankAccount bankAccount : bankAccounts)
        {
            bankAccountsHashMap.put(bankAccount.getNumber(), bankAccount);
        }

        Transaction transactions[] = {
            new Transaction(2, 500),
            new Transaction(1, 800),
            new Transaction(4, 300),
            new Transaction(1, -200),
            new Transaction(3, 500),
            new Transaction(0, -400),
            new Transaction(0, -666),
            new Transaction(3, 444),
            new Transaction(4, 123),
            new Transaction(1, -888),
            new Transaction(4, -1000),
            new Transaction(3, -900),
            new Transaction(3, -500),
            new Transaction(3, 1000),
            new Transaction(2, 400),
            new Transaction(1, 333),
            new Transaction(0, 444),
            new Transaction(0, 564),
            new Transaction(1, -843),
            new Transaction(2, 941),
            new Transaction(0, -842),
            new Transaction(2, -327),
            new Transaction(2, 842),
            new Transaction(4, 732),
            new Transaction(4, 134),
            new Transaction(3, 432),
            new Transaction(1, 1000),
        };
        TransactionsDatabase transactionsDatabase = new TransactionsDatabase(transactions);

        BankTeller bankTellers[] = new BankTeller[TELLERS_COUNT];
        for (int i = 0; i < TELLERS_COUNT; i++) {
            bankTellers[i] = new BankTeller(i + "", transactionsDatabase, bankAccountsHashMap);
        }

        for (int i = 0; i < TELLERS_COUNT; i++) {
            bankTellers[i].start();
        }

        for (int i = 0; i < TELLERS_COUNT; i++) {
            try {
                bankTellers[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
