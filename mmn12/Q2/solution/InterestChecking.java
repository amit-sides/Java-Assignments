import java.util.Objects;

/**
 * Represents an interest savings bank account.
 */
public class InterestChecking extends NoServiceChargeChecking {
    private double interestRate;

    private static final double DEFAULT_INTEREST_RATE = 0.2;
    private static final double DEFAULT_MINIMUM_BALANCE = 100000;

    public InterestChecking(String bankId, String id, String owner, double initial_balance) {
        super(bankId, id, owner, initial_balance, DEFAULT_MINIMUM_BALANCE);
        this.interestRate = DEFAULT_INTEREST_RATE;
    }

    public InterestChecking(String bankId, String id, String owner, double initial_balance, double minimumBalance, double interestRate) {
        super(bankId, id, owner, initial_balance, minimumBalance);
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
        return "InterestChecking{" +
                "bankId='" + bankId + '\'' +
                ", id='" + id + '\'' +
                ", owner='" + owner + '\'' +
                ", balance=" + balance +
                ", minimumBalance=" + minimumBalance +
                ", interestRate=" + interestRate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        InterestChecking that = (InterestChecking) o;
        return Double.compare(that.interestRate, interestRate) == 0;
    }
}
