import java.util.LinkedList;
import java.util.Scanner;

public class Main
{
    public enum Action {
        ADD,
        TOTAL,
        RECEIPT,
        CHECKOUT,
        CASH
    };

    public static final String[] ACTIONS = {
            String.format("%2d.\tAdd item", Action.ADD.ordinal() + 1),
            String.format("%2d.\tPrint current total", Action.TOTAL.ordinal() + 1),
            String.format("%2d.\tPrint current receipt", Action.RECEIPT.ordinal() + 1),
            String.format("%2d.\tCheckout", Action.CHECKOUT.ordinal() + 1),
            String.format("%2d.\tShow available cash", Action.CASH.ordinal() + 1)
    };


    public static void addItem(Scanner scanner, LinkedList<Product> products, CashRegister register)
    {
        // Prints products for user
        System.out.println("Available Products:");
        int i=1;
        for (Product product : products)
        {
            System.out.println(String.format("%2d %s", i++, product));
        };

        // Gets product from user - Assuming valid input
        System.out.print("Please choose a product: ");
        Product selectedProduct = products.get(scanner.nextInt() - 1);
        scanner.nextLine(); // Advances the scanner to the next line

        // Gets quantity from user - Assuming valid input
        System.out.print("Select quantity for product: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Advances the scanner to the next line

        register.addItem(selectedProduct, quantity);
    }

    public static void handleCheckout(Scanner scanner, CashRegister register)
    {
        double totalSum = register.totalOrderSum();
        double payment = 0;

        // Prints receipt
        System.out.println(register.getReceipt());

        // Gets payment from customer - Assuming valid input
        do
        {
            System.out.print("How much would you like to pay? ");
            payment = scanner.nextDouble();
            scanner.nextLine(); // Advances the scanner to the next line

            if (payment < totalSum)
            {
                System.out.println("That wouldn't be enough...");
            }
        } while (payment < totalSum);

        // Completes checkout and returns change
        double change = register.checkout(payment);
        System.out.println("Your change is: " + change);
        System.out.println("Thank you for your purchase! :)");
    }

    public static void serveCustomer(Scanner scanner, LinkedList<Product> products, CashRegister register)
    {
        Action selectedAction = Action.ADD;

        // Handles actions of user
        do
        {
            // Prints actions for user
            System.out.println("Actions:");
            for (String action : ACTIONS)
            {
                System.out.println(action);
            }

            // Gets selected action from user - Assuming valid input
            System.out.print("Please choose an action: ");
            selectedAction = Action.values()[scanner.nextInt() - 1];
            scanner.nextLine(); // Advances the scanner to the next line
            System.out.println();

            // Handles user action
            switch (selectedAction)
            {
                case ADD:
                    addItem(scanner, products, register);
                    break;
                case TOTAL:
                    System.out.println("The current total sum is " + register.totalOrderSum() + " .");
                    break;
                case RECEIPT:
                    System.out.println(register.getReceipt());
                    break;
                case CHECKOUT:
                    handleCheckout(scanner, register);
                    break;
                case CASH:
                    System.out.println("Current available cash in register is " + register.getAvailableCash() + " .");
                    break;
            }
            System.out.println("=====================================================");
        } while (selectedAction != Action.CHECKOUT);
    }

    public static CashRegister createRegister(Scanner scanner)
    {
        // Gets initial register's cash - Assuming valid input
        System.out.print("Insert initial available cash: ");
        double availableCash = scanner.nextDouble();
        scanner.nextLine(); // Advances the scanner to the next line

        // Create the cash register
        CashRegister cashRegister = new CashRegister(availableCash);
        return cashRegister;
    }

    public static LinkedList<Product> getProducts(Scanner scanner)
    {
        // Gets number of products - Assuming valid input
        System.out.print("Insert amount of products to list: ");
        int productsCount = scanner.nextInt();
        scanner.nextLine(); // Advances the scanner to the next line

        // Gets all products
        LinkedList<Product> products = new LinkedList<>();
        for (int i = 1; i <= productsCount; i++)
        {
            // Gets product - Assuming valid input
            System.out.print("Insert product #" + i + ": ");
            String name = scanner.nextLine();

            System.out.print("Insert product #" + i + " price: ");
            double price = scanner.nextDouble();
            scanner.nextLine(); // Advances the scanner to the next line

            products.add(new Product(name, price));
        }

        return products;
    }

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        // Get cash register & products
        CashRegister cashRegister = createRegister(scanner);
        LinkedList<Product> products = getProducts(scanner);

        // Serve customers
        while(true)
        {
            serveCustomer(scanner, products, cashRegister);
        }
    }
}
