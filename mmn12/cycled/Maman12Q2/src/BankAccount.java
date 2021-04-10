
public abstract class BankAccount {
	
	protected String accountNo;
	protected String accountOwner;
	protected String accountOwnerID;
	protected double accountBalance;
	
	public BankAccount(){
		accountNo = "";
		accountOwner = "";
		accountOwnerID = "";
		accountBalance = 0;
	}
	
	public BankAccount(String accNo, String accOw, String accOwID, double accBa){
		accountNo = accNo ;
		accountOwner = accOw ;
		accountOwnerID = accOwID ;
		accountBalance = accBa ;
	}

	
	public String getAccountNo() {
		return accountNo;
	}

	
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	
	public String getAccountOwner() {
		return accountOwner;
	}

	
	public void setAccountOwner(String accountOwner) {
		this.accountOwner = accountOwner;
	}

	
	public String getAccountOwnerID() {
		return accountOwnerID;
	}

	
	public void setAccountOwnerID(String accountOwnerID) {
		this.accountOwnerID = accountOwnerID;
	}
	
	
	public double getAccountBalance() {
		return accountBalance;
	}

	public boolean deposit(double d) {
		if ( d > 0)
		{
			this.accountBalance += d;
			return true;
		}
		return false;
	}
	
	public void withdraw(double w) {
		try {
			if (this.accountBalance - w < minAllowed ())
				throw new IllegalBalance();
			this.accountBalance -= w;
		}
		catch (IllegalBalance e){
			System.out.println("Unsuficiant funds for withdraw.");
		}
		
	}
	
	public double minAllowed () {
		return 0;
		}
	
	public abstract void monthlyManagment();
	
	public String toString() {
		return "Account status:\n-------------\n" + "Account number :" + accountNo +"\nAccount owner: " + accountOwner + 
				"\nAccount owner ID: " + accountOwnerID + "\n Account balance:" + accountBalance + "\n"; 
	}
	
	
	
}
