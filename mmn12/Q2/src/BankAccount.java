public abstract class BankAccount {
    protected String bankId;
    protected String id;
    protected String owner;
    protected double balance;

    public BankAccount(String bankId, String id, String owner, double initial_balance) {
        this.bankId = bankId;
        this.id = id;
        this.owner = owner;
        this.balance = initial_balance;
    }

    public void deposit(double money) {
        if (money > 0)
        {
            this.balance += money;
        }
    }

    public void withdraw(double money) throws IllegalBalance {
        if (money > this.balance)
        {
            throw new IllegalBalance("Sufficient balance for withdrawal");
        }

        this.balance -= money;
    }

    abstract void manage();

    @Override
    public String toString() {
        return "BankAccount{" +
                "bankId='" + bankId + '\'' +
                ", id='" + id + '\'' +
                ", owner='" + owner + '\'' +
                ", balance=" + balance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        // Check if object is an instance of BankAccount
        if (!(o instanceof BankAccount))
        {
            return false;
        }
        BankAccount other = (BankAccount)o;

        // Check details
        if (!bankId.equals(other.bankId))
        {
            return false;
        }
        if (!owner.equals(other.owner))
        {
            return false;
        }
        if (!id.equals(other.id))
        {
            return false;
        }
        return balance == other.balance;
    }
}
