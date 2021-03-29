import java.util.LinkedList;
import java.util.Scanner;

/**
 * The main class that displays a textual interface to handle the cash register.
 */
public class Main
{
    /**
     * Each enum represents an action the user can choose from the menu.
     */
    public enum Action {
        ADD,
        TOTAL,
        RECEIPT,
        CHECKOUT,
        CASH
    }

    /**
     * The actions that will be presented to the user.
     */
    public static final String[] ACTIONS = {
            String.format("%2d.\tAdd item", Action.ADD.ordinal() + 1),
            String.format("%2d.\tPrint current total", Action.TOTAL.ordinal() + 1),
            String.format("%2d.\tPrint current receipt", Action.RECEIPT.ordinal() + 1),
            String.format("%2d.\tCheckout", Action.CHECKOUT.ordinal() + 1),
            String.format("%2d.\tShow available cash", Action.CASH.ordinal() + 1)
    };

    /**
     * Adds an item to the order according to the choice of the customer.
     *
     * @param scanner   A scanner object that will scan for user input.
     * @param products  The list of products available in the store.
     * @param register  The cash register that processes the order.
     */
    public static void addItem(Scanner scanner, LinkedList<Product> products, CashRegister register)
    {
        // Prints products for user
        System.out.println("Available Products:");
        int i = 1;
        for (Product product : products)
        {
            System.out.printf("%2d %s%n", i++, product);
        }

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

    /**
     * Handles the checkout process of the current customer.
     *
     * @param scanner   A scanner object that will scan for user input.
     * @param register  The cash register that processes the order.
     */
    public static void handleCheckout(Scanner scanner, CashRegister register)
    {
        double totalSum = register.totalOrderSum();
        double payment;

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

    /**
     * Serves the current customer - Displays available actions for it and performs the requested actions.
     *
     * @param scanner   A scanner object that will scan for user input.
     * @param products  The list of products available in the store.
     * @param register  The cash register that processes the order.
     */
    public static void serveCustomer(Scanner scanner, LinkedList<Product> products, CashRegister register)
    {
        Action selectedAction;

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

    /**
     * Creates the cash register and returns it.
     *
     * @param scanner   A scanner object that will scan for user input.
     */
    public static CashRegister createRegister(Scanner scanner)
    {
        // Gets initial register's cash - Assuming valid input
        System.out.print("Insert initial available cash: ");
        double availableCash = scanner.nextDouble();
        scanner.nextLine(); // Advances the scanner to the next line

        // Create the cash register
        return new CashRegister(availableCash);
    }

    /**
     * Asks the user to insert the available products in the store and returns a list of them.
     *
     * @param scanner   A scanner object that will scan for user input.
     */
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

    /**
     * The main function that executes logic of the cash register.
     * Will serve customers indefinitely.
     */
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
