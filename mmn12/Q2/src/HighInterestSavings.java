import java.util.Objects;

/**
 * Represents a high interest savings bank account.
 */
public class HighInterestSavings extends SavingsAccount {
    private double minimumBalance;

    private static final double DEFAULT_MINIMUM_BALANCE = 10000;
    private static final double DEFAULT_INTEREST_RATE = 0.5;

    public HighInterestSavings(String bankId, String id, String owner, double initial_balance) {
        super(bankId, id, owner, initial_balance, DEFAULT_INTEREST_RATE);
        this.minimumBalance = DEFAULT_MINIMUM_BALANCE;
    }

    public HighInterestSavings(String bankId, String id, String owner, double initial_balance, double interestRate, double minimumBalance) {
        super(bankId, id, owner, initial_balance, interestRate);
        this.minimumBalance = minimumBalance;
    }

    public double getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(double minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    @Override
    public void withdraw(double price) throws IllegalBalance
    {
        if (this.balance - price < this.minimumBalance)
        {
            throw new IllegalBalance("Sufficient balance for withdrawal");
        }
        this.balance -= price;
    }

    @Override
    public String toString() {
        return "HighInterestSavings{" +
                "bankId='" + bankId + '\'' +
                ", id='" + id + '\'' +
                ", owner='" + owner + '\'' +
                ", balance=" + balance +
                ", interestRate=" + interestRate +
                ", minimumBalance=" + minimumBalance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        HighInterestSavings that = (HighInterestSavings) o;
        return Double.compare(that.minimumBalance, minimumBalance) == 0;
    }
}
