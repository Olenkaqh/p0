import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Scanner;


class MortgageCalculator {

    private static FileWriter file;
    private static Scanner input = new Scanner(System.in);
//    static Logger logger = Logger.getLogger(MortgageCalculator.class.getName());
    public static void main(String[] args){
        Logger log = LoggerFactory.getLogger(MortgageCalculator.class);
        log.info("Starting calculator...");

//        logger.debug("Hello Log!");
        System.out.println("House price: ");
        //takes input from user & stores it in variable housePrice
        int housePrice = input.nextInt();

        System.out.println("Down payment: ");

        //takes input from user & stores it in variable down payment
        int downPayment = input.nextInt();

        System.out.println("Interest rate: ");
        //takes input from user & stores it in variable interest
         double interest = input.nextDouble();

        System.out.println("Mortgage period (Years): ");
        //takes input from user & stores it in variable time
        int time = input.nextInt();

        System.out.println("You entered the following information: " + '\n' + "House price: $" + housePrice + '\n' + "Down payment: $"+ downPayment + '\n' + "Interest rate: " +interest+"%"+ '\n' + "Loan period: " +time +" years " + '\n' );

        //creates a new loan object
        Loan newLoan = new Loan(housePrice, downPayment,interest, time);


        Gson gson = new Gson();
        //converts java objects to JSON strings
        String json = gson.toJson(newLoan);

        try {
            file = new FileWriter("/Users/olenka/Desktop/p0/Record.txt", true);
            file.write('\n');
            file.write(json); // writes json to file
            log.info("String has been successfully copied to file...");
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try {
                file.flush();
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        //prints loan calculations
        System.out.println("Total monthly payment (includes principal & interest): $ " + newLoan.calculateMonthlyPayments());
                System.out.println(
                "\nMonthly interest Payment: $"+newLoan.calculateMonthlyInterestPayments() +
                "\nMonthly principal payment: $" + newLoan.calculateMonthlyPrincipalPayments() +
                "\nTotal Loan payment: $" + newLoan.calculateTotalLoanPayment() + "\nTotal interest: $ " + newLoan.calculateTotalInterestPayment());

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
            System.out.println("You could pay off your mortgage in " + Math.floor(newLoan.calculateNewNumberOfPayments(extraAmount)/12)  +" years or " +  Math.floor(newLoan.calculateNewNumberOfPayments(extraAmount)) + " payments");
            newLoan.calculateNewNumberOfPayments(extraAmount) ;
        }

        //close scanner
        input.close();
    }
}