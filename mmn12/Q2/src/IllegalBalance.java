/**
 * Represents an exception for an illegal balance value
 */
public class IllegalBalance extends Exception {
    public IllegalBalance()
    {
        super();
    }

    public IllegalBalance(String errorMessage)
    {
        super(errorMessage);
    }
}
