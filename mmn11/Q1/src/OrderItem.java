public class OrderItem {
    private Product product;
    private int quantity;

    public OrderItem(Product product, int quantity)
    {
        this.quantity = quantity;
        this.product = new Product(product);
    }

    public int getQuantity()
    {
        return quantity;
    }

    public double getPrice()
    {
        return product.getPrice();
    }

    public String toString()
    {
        return String.format("| %s   x%2d  %7.2f |", this.product, this.quantity, this.getPrice() * this.quantity);
    }
}
