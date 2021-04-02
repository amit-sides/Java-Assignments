import java.util.Objects;

/**
 * Represents a savings bank account.
 */
public class SavingsAccount extends BankAccount {
    protected double interestRate;

    private static final double DEFAULT_INTEREST_RATE = 0.3;

    public SavingsAccount(String bankId, String id, String owner, double initial_balance) {
        super(bankId, id, owner, initial_balance);
        this.interestRate = DEFAULT_INTEREST_RATE;
    }

    public SavingsAccount(String bankId, String id, String owner, double initial_balance, double interestRate) {
        super(bankId, id, owner, initial_balance);
        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public double calculateInterest() {
        return this.balance * this.interestRate / 100.0;
    }

    @Override
    public void manage() {
        this.balance += this.calculateInterest();
    }

    @Override
    public String toString() {
        return "SavingsAccount{" +
                "bankId='" + bankId + '\'' +
                ", id='" + id + '\'' +
                ", owner='" + owner + '\'' +
                ", balance=" + balance +
                ", interestRate=" + interestRate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SavingsAccount that = (SavingsAccount) o;
        return Double.compare(that.interestRate, interestRate) == 0;
    }
}
