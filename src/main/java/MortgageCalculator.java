import java.util.Scanner;

class MortgageCalculator {
    
    public static void main(String[] args){
        int housePrice;
        int downPayment;
        float interest;
        int time;


        /*creates an object of Scanner class
        * this object can be use to take input from user */
        Scanner input = new Scanner(System.in);

        System.out.println("House price: ");
        //takes input from user & stores it in variable housePrice
        housePrice = input.nextInt();

        System.out.println("Down payment: ");

        //takes input from user & stores it in variable down payment
        downPayment = input.nextInt();

        System.out.println("Interest rate: ");
        //takes input from user & stores it in variable interest
        interest = input.nextFloat();

        System.out.println("Mortgage period (Years): ");
        //takes input from user & stores it in variable time
        time = input.nextInt();

        //close scanner
        input.close();

        System.out.println("You entered the following information: " + '\n' + "House price: $" + housePrice + '\n' + "Down payment: $"+ downPayment + '\n' + "Interest rate: " +interest+"%"+ '\n' + "Loan period: " +time +" years " + '\n' );

        //creates a new loan object
        Loan newLoan = new Loan(housePrice, downPayment,interest, time);
        //prints loan calculations
        System.out.println("Total Monthly Payment (includes principal & interest): $ " + newLoan.calculateMonthlyPayments()
                + "\nMonthly interest Payment: $"+newLoan.calculateMonthlyInterestPayments() +
                "\nMonthly principal payment: $" + newLoan.calculateMonthlyPrincipalPayments() +
                "\nTotal Loan payment: $" + newLoan.calculateTotalLoanPayment() + "\nTotal interest: $ " + newLoan.calculateTotalInterestPayment());

        //returns the amortized Schedule table
        newLoan.generateAmortizedPaymentsTable() ;
    }
}