import java.util.LinkedList;

public class CashRegister {
    private double availableCash;
    private LinkedList<OrderItem> orderItems;

    public CashRegister()
    {
        this.availableCash = 0;
        this.startNewOrder();
    }

    public CashRegister(double cash)
    {
        this.availableCash = cash;
        this.startNewOrder();
    }

    private void startNewOrder()
    {
        this.orderItems = new LinkedList<>();
    }

    public void addItem(Product product, int quantity)
    {
        OrderItem orderItem = new OrderItem(product, quantity);
        this.orderItems.add(orderItem);
    }

    public String getReceipt()
    {
        StringBuilder output = new StringBuilder();
        output.append("┌----------------------------------------------┐\n");
        for (OrderItem item : this.orderItems)
        {
            output.append(item).append("\n");
        }
        output.append("├----------------------------------------------┤\n");
        output.append(String.format("| %-35s  %7.2f |\n", "Total Sum", this.totalOrderSum()));
        output.append("└----------------------------------------------┘\n");
        return output.toString();
    }
    
    public double totalOrderSum()
    {
        double sum = 0;
        for (OrderItem item : this.orderItems)
        {
            sum += item.getPrice() * item.getQuantity();
        }
        return sum;
    }

    public double checkout(double payment) throws IllegalArgumentException
    {
        double totalSum = this.totalOrderSum();

        // Validates payment amount
        if (totalSum > payment)
        {
            throw new IllegalArgumentException("The payment is not sufficient! Given: " + payment + ", needed: " + totalSum);
        }

        // Calculates change and updates available cash
        double change = payment - totalSum;
        this.availableCash += payment - change;

        // Resets the order
        this.startNewOrder();

        return change;
    }

    public double getAvailableCash()
    {
        return availableCash;
    }
}
