import java.sql.SQLOutput;
import java.util.Scanner;
//import org.apache.log4j.*;

class MortgageCalculator {
    
    public static void main(String[] args){
//        final Logger logger = Logger.getLogger()
        //reads input from user
        Scanner input = new Scanner(System.in);

        System.out.println("House price: ");
        //takes input from user & stores it in variable housePrice
        int housePrice = input.nextInt();

        System.out.println("Down payment: ");

        //takes input from user & stores it in variable down payment
        int downPayment = input.nextInt();

        System.out.println("Interest rate: ");
        //takes input from user & stores it in variable interest
         double interest = input.nextFloat();

        System.out.println("Mortgage period (Years): ");
        //takes input from user & stores it in variable time
        int time = input.nextInt();

        System.out.println("You entered the following information: " + '\n' + "House price: $" + housePrice + '\n' + "Down payment: $"+ downPayment + '\n' + "Interest rate: " +interest+"%"+ '\n' + "Loan period: " +time +" years " + '\n' );

        //creates a new loan object
        Loan newLoan = new Loan(housePrice, downPayment,interest, time);

        //prints loan calculations
        System.out.println("Total monthly payment (includes principal & interest): $ " + newLoan.calculateMonthlyPayments());
                System.out.println(
                "\nMonthly interest Payment: $"+newLoan.calculateMonthlyInterestPayments() +
                "\nMonthly principal payment: $" + newLoan.calculateMonthlyPrincipalPayments() +
                "\nTotal Loan payment: $" + newLoan.calculateTotalLoanPayment() + "\nTotal interest: $ " + newLoan.calculateTotalInterestPayment());

        System.out.println("Do you want to see the amortization table ? (Y/N) ");
        char status = input.next().charAt(0);

        if(status == 'Y' ){
            //returns the amortized Schedule table
        newLoan.generateAmortizedPaymentsTable() ;
        }
        System.out.println("Do you want to see how long it will take to pay off your loan if you paid an extra amount every month ? (Y/N) ");
        char status2= input.next().charAt(0);
        if ( status2 == 'Y'){
            System.out.println("----- EXTRA PAYMENT ----  ");
            System.out.println("Extra payment per month: ");
            double extraAmount = input.nextDouble();
            System.out.println("You could pay off your mortgage in " + Math.floor(newLoan.calculateNewNumberOfPayments(extraAmount)/12)  +" years or " +  Math.floor(newLoan.calculateNewNumberOfPayments(extraAmount)) + " payments");
            newLoan.calculateNewNumberOfPayments(extraAmount) ;
        }

        //close scanner
        input.close();
    }
}