import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Random rand = new Random();
        int seed = rand.nextInt();
        System.out.println("Using seed: " + seed);
        rand.setSeed(seed);

        // Create bank accounts
        BankAccount[] accountsArray = { new ServiceChargeChecking("BU11","Abraham","11",74340) ,
                new SavingsAccount("BU32","Benny","32",200000),
                new InterestChecking("BU99", "Charles","99",66666,666,0.66),
                new NoServiceChargeChecking("BU23", "Dan","23",1234),
                new HighInterestSavings("BU74","Elon","74",455012),
                new InterestChecking("BU65","Frank","65",55000),
                new SavingsAccount("BU95","Gabriel","95",154000,0.22),
                new HighInterestSavings("BU03","Hellen","03",350666),
                new NoServiceChargeChecking("BU14","Iris","14",56463,1000),
        };

        // Print accounts
        for ( BankAccount accounts : accountsArray){
            System.out.println(accounts);
        }

        // Perform actions on accounts...
        double x = 0;
        for ( BankAccount account : accountsArray){
            System.out.println("=================================================");
            System.out.println("Performing actions...");
            System.out.println(account);
            x = rand.nextDouble() * 8000;
            System.out.println("Deposit: " + x);
            account.deposit(x);
            System.out.println(account);
            x = rand.nextDouble() * 1000;
            System.out.println("Withdraw:" + x);
            try{
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
