public abstract class CheckingAccount extends BankAccount {

    public CheckingAccount(String bankId, String id, String owner, double initial_balance) {
        super(bankId, id, owner, initial_balance);
    }

    abstract void writeCheck(double price) throws IllegalBalance;

    @Override
    public String toString() {
        return "CheckingAccount{" +
                "bankId='" + bankId + '\'' +
                ", id='" + id + '\'' +
                ", owner='" + owner + '\'' +
                ", balance=" + balance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}
