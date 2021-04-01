
import java.util.Random;

public class TesterMmn12Q2b {
	
	public static void main ( String[] args) {
	// create an array of different bank accounts
	BankAccount[] accountsArray = { new ServiceChargeChecking("0001","yosi","031265489",9000) ,
									new NoServiceChargeChecking("0002","david","031265321",6000),
									new NoServiceChargeChecking("0003","moshe","031265345",89000,500),
									new InterestChecking("0004","eli","033455489",90000,500),
									new InterestChecking("0005","avi","343265489",100000,500,0.15),
									new SavingsAccount("0006","itzik","031234589",3000),
									new SavingsAccount("0007","ariel","789265489",5000,0.17),
									new HighInterestSavings("0008","tomer","031789489",23000),
									new HighInterestSavings("0009","kobi","037985489",68000,500)
								  };
	
	// print the details of the accounts in the array
	for ( BankAccount accounts : accountsArray){
		System.out.println(accounts);
	}
	
	Random r = new Random();
	
	// test the use of the accounts as instructed in the question
	for ( BankAccount accounts : accountsArray){
		accounts.deposit(r.nextInt(5000));
		System.out.println(accounts);
		accounts.withdraw(r.nextInt(500));
		System.out.println(accounts);
		accounts.monthlyManagment();
		System.out.println(accounts);
	}
	
	
	}
}
