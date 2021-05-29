public class BankAccount {
    private int number;
    private double balance;

    public BankAccount(int number, double balance)
    {
        this.number = number;
        this.balance = balance;
    }

    public int getNumber()
    {
        return this.number;
    }

    public synchronized double getBalance()
    {
        return this.balance;
    }

    public synchronized void transaction(double amount)
    {
        while (this.balance + amount < 0)
        {
            try {
                wait();
            } catch (InterruptedException e) {}
        }

        this.balance += amount;
        notifyAll();
    }
}
