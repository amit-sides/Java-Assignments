import java.util.Scanner;

public class Main {

    public enum Action {
        PLUS,
        MINUS,
        DERIVATIVE,
    };

    public static final String[] ACTIONS = {
            String.format("%2d.\tSum 2 polynoms", Action.PLUS.ordinal() + 1),
            String.format("%2d.\tSubtract 2 polynoms", Action.MINUS.ordinal() + 1),
            String.format("%2d.\tDerivative a polynom", Action.DERIVATIVE.ordinal() + 1),
    };

    public static Polynom scanPolynom(Scanner scanner)
    {
        // Gets number of terms
        System.out.print("Enter number of terms: ");
        int terms_count = scanner.nextInt();
        scanner.nextLine(); // Advances the scanner to the next line

        int[] powers = new int[terms_count];
        double[] coefficients = new double[terms_count];
        for (int i = 0; i < terms_count; i++)
        {
            // Get power
            System.out.print("Insert term's power #" + (i + 1) + " : ");
            powers[i] = scanner.nextInt();
            scanner.nextLine(); // Advances the scanner to the next line

            System.out.print("Insert term's coefficient #" + (i + 1) + " : ");
            coefficients[i] = scanner.nextDouble();
            scanner.nextLine(); // Advances the scanner to the next line
        }
        System.out.println();

        try
        {
            return new Polynom(powers, coefficients);
        }
        catch (Exception e)
        {
            // Should not occur...
            return null;
        }

    }

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        // Get action
        System.out.println("Actions:");
        for (String action : ACTIONS)
        {
            System.out.println(action);
        }

        // Gets selected action from user - Assuming valid input
        System.out.print("Please choose an action: ");
        Action selectedAction;
        try
        {
            selectedAction = Action.values()[scanner.nextInt() - 1];
        } catch (ArrayIndexOutOfBoundsException e)
        {
            System.out.println("Error: Invalid action!");
            return;
        }
        scanner.nextLine(); // Advances the scanner to the next line
        System.out.println();

        // Get first polynom
        System.out.println("Enter first polynom:");
        Polynom p1 = scanPolynom(scanner);

        // Get second polynom, if needed
        Polynom p2 = new Polynom();
        if (selectedAction != Action.DERIVATIVE)
        {
            System.out.println("Enter second polynom:");
             p2 = scanPolynom(scanner);
        }

        System.out.println("===============================================================");

        // Handle user action
        Polynom result = new Polynom();
        switch (selectedAction)
        {
            case PLUS:
                System.out.println(p1);
                System.out.println("+");
                System.out.println(p2);
                result = p1.plus(p2);
                break;
            case MINUS:
                System.out.println(p1);
                System.out.println("-");
                System.out.println(p2);
                result = p1.minus(p2);
                break;
            case DERIVATIVE:
                System.out.println("Derivative of " + p1 + "\t:");
                result = p1.derivative();
                break;
            default:
                System.out.println("Error: Invalid action!");
                return;
        }
        System.out.println("-------------------------------------------------------");
        System.out.println(result);
    }
}
