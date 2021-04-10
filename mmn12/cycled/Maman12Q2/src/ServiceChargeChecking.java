
public class ServiceChargeChecking extends CheckingAccount{

	public static final double  DEFAULT_COMMISSION = 20;
	private double commission;
	
	public ServiceChargeChecking(String accNo, String accOw, String accOwID, double accBa) {
		super(accNo,accOw,accOwID,accBa);
		commission = DEFAULT_COMMISSION;
	}
	
	public ServiceChargeChecking(String accNo, String accOw, String accOwID, double accBa,double com) {
		super(accNo,accOw,accOwID,accBa);
		commission = com;
	}

	public double getCommission() {
		return commission;
	}

	public void setCommission(double commision) {
		this.commission = commision;
	}
		
	public void monthlyManagment(){
		this.withdraw(commission);
	}
	
	public String toString() {
		return super.toString() + "Account commission :" + commission + "\n";
	}
}
