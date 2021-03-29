import java.util.LinkedList;

/**
 * Implements a cash register that can process orders.
 */
public class CashRegister {
    private double availableCash;
    private LinkedList<OrderItem> orderItems;

    /**
     * Default constructor for CashRegister.
     */
    public CashRegister()
    {
        this.availableCash = 0;
        this.startNewOrder();
    }

    /**
     * Constructor for CashRegister that initializes available cash.
     *
     * @param cash  The value to initialize availableCash with.
     */
    public CashRegister(double cash)
    {
        this.availableCash = cash;
        this.startNewOrder();
    }

    /**
     * Starts a new order.
     */
    private void startNewOrder()
    {
        this.orderItems = new LinkedList<>();
    }

    /**
     * Adds an item (product + quantity) to the current order.
     *
     * @param product   The product to add.
     * @param quantity  The quantity of the product.
     */
    public void addItem(Product product, int quantity)
    {
        OrderItem orderItem = new OrderItem(product, quantity);
        this.orderItems.add(orderItem);
    }

    /**
     * Returns a string representing the current receipt of the current order.
     */
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

    /**
     * Calculates and returns the current total sum of the order.
     */
    public double totalOrderSum()
    {
        double sum = 0;
        for (OrderItem item : this.orderItems)
        {
            sum += item.getPrice() * item.getQuantity();
        }
        return sum;
    }

    /**
     * Checkout function - The function receives the payment amount given by the customer and returns the change.
     *
     * @param payment   The payment given by the customer.
     */
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

    /**
     * Returns the available cash in the register.
     */
    public double getAvailableCash()
    {
        return this.availableCash;
    }
}
