public class Transaction {
    private int receiver;
    private double amount;

    public Transaction(int receiver, double amount) {
        this.receiver = receiver;
        this.amount = amount;
    }

    public int getReceiver() {
        return receiver;
    }

    public double getAmount() {
        return amount;
    }
}
