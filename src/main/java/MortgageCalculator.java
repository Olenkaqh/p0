import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;


class MortgageCalculator {

    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args){
        Logger log = LoggerFactory.getLogger(MortgageCalculator.class);

        log.info("Starting calculator...");

        System.out.println("House price: ");
        //takes input from user & stores it in variable housePrice
        double housePrice = input.nextDouble();

        System.out.println("Down payment: ");

        //takes input from user & stores it in variable down payment
        double downPayment = input.nextDouble();

        System.out.println("Interest rate: ");
        //takes input from user & stores it in variable interest
         double interest = input.nextDouble();

        System.out.println("Mortgage period (Years): ");
        //takes input from user & stores it in variable time
        int time = input.nextInt();

//        System.out.println("You entered the following information: " + '\n' + "House price: $" + housePrice + '\n' + "Down payment: $"+ downPayment + '\n' + "Interest rate: " +interest+"%"+ '\n' + "Loan period: " +time +" years " + '\n' );

        //creates a new loan object
        Loan newLoan = new Loan(housePrice, downPayment,interest, time);

        //adds new loan to record.txt
        Helper helper = new Helper();
        helper.addToRecord(newLoan);

        Locale locale = new Locale("en", "US");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);

        //prints loan calculations
        System.out.println("----------------------------------------------------------");
        System.out.println("Total monthly payment (includes principal & interest): " + currencyFormatter.format(Math.round(newLoan.calculateMonthlyPayments()*100.0)/100.0));
                System.out.println(
                "\nMonthly interest Payment: "+ currencyFormatter.format(Math.round(newLoan.calculateMonthlyInterestPayments() *100.0)/100.0) +
                "\nMonthly principal payment: " +currencyFormatter.format( Math.round(newLoan.calculateMonthlyPrincipalPayments() *100.0)/100.0)+
                "\nTotal Loan payment: " + currencyFormatter.format(Math.round(newLoan.calculateTotalLoanPayment() *100.0)/100.0)+ "\nTotal interest: " + currencyFormatter.format(Math.round(newLoan.calculateTotalInterestPayment()*100.0)/100.0));
        System.out.println("----------------------------------------------------------");
        System.out.println("Do you want to see the amortization table ? (Y/N) ");
        char status = input.next().charAt(0);
        if(status == 'Y' ) {
            System.out.println("Do you want the monthly or annual table? (m :month or a: annual)");
            char status1 = input.next().charAt(0);
            while(status1 != 'm' && status1 != 'a'){
                System.out.println("Please enter the right command.");
                status1 = input.next().charAt(0);
            }
            //returns the amortized Schedule table for the respective period
            newLoan.generateAmortizedPaymentsTable(status1);
        }

        System.out.println("Do you want to see how long it will take to pay off your loan if you paid an extra amount every month ? (Y/N) ");
        char status2= input.next().charAt(0);
        if ( status2 == 'Y'){
            System.out.println("----- EXTRA PAYMENT ----  ");
            System.out.println("Extra payment per month: ");
            double extraAmount = input.nextDouble();
            System.out.println("You could pay off your mortgage in " + Math.round(newLoan.calculateNewNumberOfPayments(extraAmount)/12)  +" years or " +  Math.round(newLoan.calculateNewNumberOfPayments(extraAmount)) + " monthly payments");
            newLoan.calculateNewNumberOfPayments(extraAmount) ;
        }

        //close scanner
        input.close();
    }
}