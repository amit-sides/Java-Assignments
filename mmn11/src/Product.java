public class Product {
    private final String name;
    private final double price;

    public Product(String name, double price)
    {
        this.name = name;
        this.price = price;
    }

    public Product(Product other)
    {
        this.name = other.name;
        this.price = other.price;
    }

    public double getPrice()
    {
        return this.price;
    }

    public String toString()
    {
        return String.format("%-20s  %7.2f", this.name, this.price);
    }
}
