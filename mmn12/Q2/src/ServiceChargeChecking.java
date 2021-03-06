import java.util.Objects;

/**
 * Represents a checking bank account with monthly fees.
 */
public class ServiceChargeChecking extends CheckingAccount {
    private double monthlyFees;

    private static final double DEFAULT_FEES = 6;

    public ServiceChargeChecking(String bankId, String id, String owner, double initial_balance) {
        super(bankId, id, owner, initial_balance);
        this.monthlyFees = DEFAULT_FEES;
    }

    public ServiceChargeChecking(String bankId, String id, String owner, double initial_balance, double monthlyFees) {
        super(bankId, id, owner, initial_balance);
        this.monthlyFees = monthlyFees;
    }

    public double getMonthlyFees() {
        return monthlyFees;
    }

    public void setMonthlyFees(double monthlyFees) {
        this.monthlyFees = monthlyFees;
    }

    @Override
    void manage() {
        // Since this account has no minimum balance, we will allow negative balance (debt to the bank)
        this.balance -= this.monthlyFees;
    }

    @Override
    public String toString() {
        return "ServiceChargeChecking{" +
                "bankId='" + bankId + '\'' +
                ", id='" + id + '\'' +
                ", owner='" + owner + '\'' +
                ", balance=" + balance +
                ", monthlyFees=" + monthlyFees +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ServiceChargeChecking that = (ServiceChargeChecking) o;
        return Double.compare(that.monthlyFees, monthlyFees) == 0;
    }
}
