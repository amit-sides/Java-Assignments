
public class InterestChecking extends NoServiceChargeChecking{
	
	public static final double DEFAULT_INTEREST = 0.1;
	private double interest ;
	
	public InterestChecking(String accNo, String accOw, String accOwID, double accBa,double min) {
		super(accNo,accOw,accOwID,accBa,min);
		interest = DEFAULT_INTEREST;
	}

	public InterestChecking(String accNo, String accOw, String accOwID, double accBa,double min ,double interest) {
		super(accNo,accOw,accOwID,accBa,min);
		this.interest = interest;
	}
	
	public double getInterest() {
		return interest;
	}
	
	public void setInterest(double interest) {
		this.interest = interest;
	}
	
	public double calcInterest() {
		return this.getAccountBalance()*interest;
	}
	
	public void monthlyManagment(){
		this.deposit(this.calcInterest());
	}
	
	public String toString() {
		return super.toString() + "Account interest: " + interest + "\n"; 
	}
	
	
}
