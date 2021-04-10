
public class HighInterestSavings  extends SavingsAccount  {
	
	public static final double DEFAULT_HIGH_MINIMUM = 200;
	public static final double DEFAULT_HIGH_INTEREST = 0.2;
	private double highMinimum;
	
	public HighInterestSavings(String accNo, String accOw, String accOwID, double accBa){
		super(accNo,accOw,accOwID,accBa,DEFAULT_HIGH_INTEREST);
		highMinimum = DEFAULT_HIGH_MINIMUM;
	}	
	
	public HighInterestSavings(String accNo, String accOw, String accOwID, double accBa, double highMinimum){
		super(accNo,accOw,accOwID,accBa,DEFAULT_HIGH_INTEREST);
		this.highMinimum = highMinimum;
	}

	public double getHighMinimum() {
		return highMinimum;
	}
	
	public double minAllowed () {
		return highMinimum;
	}

	public void setHighMinimum(double highMinimum) {
		this.highMinimum = highMinimum;
	}
	
	public String toString() {
		return super.toString() + "Account minimum: " + highMinimum + "\n"; 
	}
	
}
