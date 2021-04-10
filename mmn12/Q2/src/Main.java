import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Random rand = new Random();
        int seed = rand.nextInt();
        System.out.println("Using seed: " + seed);
        rand.setSeed(seed);

        // Create bank accounts
        BankAccount[] accountsArray = {
                new ServiceChargeChecking("BU11", "11", "Abraham", 74340),
                new SavingsAccount("BU32", "32", "Benny", 200000),
                new InterestChecking("BU99", "99", "Charles", 66666, 666, 0.66),
                new NoServiceChargeChecking("BU23", "23", "Dan", 1234),
                new HighInterestSavings("BU74", "74", "Elon", 455012),
                new InterestChecking("BU65", "65", "Frank", 55000),
                new SavingsAccount("BU95", "95", "Gabriel", 154000, 0.22),
                new HighInterestSavings("BU03", "03", "Hellen", 350666),
                new NoServiceChargeChecking("BU14", "14", "Iris", 56463, 1000),
        };

        // Print accounts
        for ( BankAccount accounts : accountsArray) {
            System.out.println(accounts);
        }

        // Perform actions on accounts...
        double x = 0;
        for ( BankAccount account : accountsArray) {
            System.out.println("=================================================");
            System.out.println("Performing actions...");
            System.out.println(account);
            x = rand.nextDouble() * 8000;
            System.out.println("Deposit: " + x);
            account.deposit(x);
            System.out.println(account);
            x = rand.nextDouble() * 1000;
            System.out.println("Withdraw:" + x);
            try {
                account.withdraw(x);
            } catch (IllegalBalance e)
            {
                System.out.println("IllegalBalance Exception: failed to withdraw " + x);
            }
            System.out.println(account);
            System.out.println("Manage:");
            account.manage();
            System.out.println(account);
        }
    }
}
