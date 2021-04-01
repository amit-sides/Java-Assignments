
public class NoServiceChargeChecking extends CheckingAccount{
	
	private final double DEFAULT_WITHDRAW_MINIMUM = 200;
	private double minimum;
	
	public NoServiceChargeChecking(String accNo, String accOw, String accOwID, double accBa) {
		super(accNo,accOw,accOwID,accBa);
		minimum = DEFAULT_WITHDRAW_MINIMUM;
	}
	
	public NoServiceChargeChecking(String accNo, String accOw, String accOwID, double accBa,double min) {
		super(accNo,accOw,accOwID,accBa);
		minimum = min;
	}
	
	public double minAllowed () {
		return minimum;
		}

	public double getMinimum() {
		return minimum;
	}

	public void setMinimum(double minimum) {
		this.minimum = minimum;
	}
	
	public void monthlyManagment(){
	}
	
	public String toString() {
		return super.toString() + "Minimum withdraw :" + minimum + "\n";
	}
	
}
