/**
 * Represents a product in the store.
 */
public class Product {
    private final String name;
    private final double price;

    /**
     * Constructor of the class.
     *
     * @param name  Name of the product.
     * @param price Price of the product.
     */
    public Product(String name, double price)
    {
        this.name = name;
        this.price = price;
    }

    /**
     * Copy Constructor of the class.
     *
     * @param other Another object of the class that will be copied.
     */
    public Product(Product other)
    {
        this.name = other.name;
        this.price = other.price;
    }

    /**
     * Returns the price of a single product.
     */
    public double getPrice()
    {
        return this.price;
    }

    /**
     * Returns a string representing the object
     */
    public String toString()
    {
        return String.format("%-20s  %7.2f", this.name, this.price);
    }
}
