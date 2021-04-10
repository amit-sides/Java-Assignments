import java.util.Objects;

/**
 * Represents a bank account without monthly fees
 */
public class NoServiceChargeChecking extends CheckingAccount{
    protected double minimumBalance;

    private static final double DEFAULT_MINIMUM_BALANCE = 10000;

    public NoServiceChargeChecking(String bankId, String id, String owner, double initial_balance) {
        super(bankId, id, owner, initial_balance);
        this.minimumBalance = DEFAULT_MINIMUM_BALANCE;
    }

    public NoServiceChargeChecking(String bankId, String id, String owner, double initial_balance, double minimumBalance) {
        super(bankId, id, owner, initial_balance);
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
    public void manage() {
    }

    @Override
    public String toString() {
        return "NoServiceChargeChecking{" +
                "bankId='" + bankId + '\'' +
                ", id='" + id + '\'' +
                ", owner='" + owner + '\'' +
                ", balance=" + balance +
                ", minimumBalance=" + minimumBalance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        NoServiceChargeChecking that = (NoServiceChargeChecking) o;
        return Double.compare(that.minimumBalance, minimumBalance) == 0;
    }
}
