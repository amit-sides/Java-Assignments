
public abstract class CheckingAccount extends BankAccount{
	
public CheckingAccount(String accNo, String accOw, String accOwID, double accBa){
		super(accNo,accOw,accOwID,accBa);
}	
	
public void writeCheck(double amount) {
	try {
		if (amount > getAccountBalance())
			throw new IllegalBalance();
		System.out.println("Written check for the amount of:" + amount);
	}
	catch (IllegalBalance e){
		System.out.println("Unsuficiant funds for check.");
	}
}
}
