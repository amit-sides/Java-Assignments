
public class SavingsAccount extends BankAccount {
	
	public static final double DEFAULT_INTEREST = 0.1;
	private double interest;
	
	public SavingsAccount(String accNo, String accOw, String accOwID, double accBa){
		super(accNo,accOw,accOwID,accBa);
		interest = DEFAULT_INTEREST;
	}	
	
	public SavingsAccount(String accNo, String accOw, String accOwID, double accBa, double interest){
		super(accNo,accOw,accOwID,accBa);
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
