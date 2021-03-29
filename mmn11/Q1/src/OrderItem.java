/**
 * Represents a row of an item in the receipt.
 */
public class OrderItem {
    private final Product product;
    private int quantity;

    /**
     * Constructor of the class.
     *
     * @param product   The product bought.
     * @param quantity  The quantity of the product.
     */
    public OrderItem(Product product, int quantity)
    {
        this.quantity = quantity;
        this.product = new Product(product);
    }

    /**
     * Returns the quantity of the product.
     */
    public int getQuantity()
    {
        return this.quantity;
    }

    /**
     * Sets the quantity of the product.
     *
     * @param quantity The new quantity of the product.
     */
    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    /**
     * Returns the price of the product.
     */
    public double getPrice()
    {
        return this.product.getPrice();
    }

    /**
     * Returns a string representing the object.
     */
    public String toString()
    {
        return String.format("| %s   x%2d  %7.2f |", this.product, this.quantity, this.getPrice() * this.quantity);
    }
}
