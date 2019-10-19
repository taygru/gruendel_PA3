
class SavingsAccount {
    //declaring both values to be floating numbers
    public static double annualInterestRate;
    private double savingsBalance;

    public SavingsAccount(double amt) {
        this.savingsBalance = amt;
    }

    public void calculateMonthlyInterest() {
        double interest;
        interest = (this.savingsBalance * (annualInterestRate/100))/12;
        this.savingsBalance += interest;
    }

    public void showBalance() {
        System.out.printf("$%.2f", this.savingsBalance);
    }

    public static void modifyInterestRate(double newRate) {
        annualInterestRate = newRate;
    }
}

public class gruendel_p2{

    public static void main(String[] args){
        //setting the starting amount & setting interest rate to 4%
        SavingsAccount saver1 = new SavingsAccount(2000.0);
        SavingsAccount saver2 = new SavingsAccount(3000.0);
        SavingsAccount.modifyInterestRate(4.0);

        System.out.println("\t\t\t\t  saver1 \t saver2");

        for(int i=1; i<=12; ++i) {
            saver1.calculateMonthlyInterest();
            saver2.calculateMonthlyInterest();
            System.out.print("Month " + i + ": \t");

            System.out.print("\t");
            saver1.showBalance();
            System.out.print("\t");
            saver2.showBalance();
            System.out.println();
        }
        System.out.println();

        System.out.println("Setting the Interest Rate to 5%");
        System.out.println();

        //changing rate to 5%
        SavingsAccount.modifyInterestRate(5.0);

        saver1.calculateMonthlyInterest();
        saver2.calculateMonthlyInterest();

        System.out.print("Month 13: \t\t");
        saver1.showBalance();

        System.out.print("\t");

        saver2.showBalance();
        System.out.println();

    }
}
