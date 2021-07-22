import java.util.Scanner;
import java.lang.Math;

class MortgageCalculator {
    
    public static void main(String[] args){
        int housePrice;
        int downpayment;
        float interest;
        int time;
      

        /*creates an object of Scanner class 
        * this object can be use to take input from user */       
        Scanner input = new Scanner(System.in);
        
        System.out.println("House price: ");
        //takes input from user & stores it in variable housePrice
        housePrice = input.nextInt();
       
        System.out.println("Downpayment: ");

        //takes input from user & stores it in variable downpayment
        downpayment = input.nextInt();

        System.out.println("Interest rate: ");
        //takes input from user & stores it in variable interest
        interest = input.nextFloat();

        System.out.println("Mortgage period (Years): ");
        //takes input from user & stores it in variable downpayment
        time = input.nextInt();

        //close scanner
        input.close();

        //For testing purposes
        System.out.println("You entered the following information: " + '\n' + "House price: $" + housePrice + '\n' + "Downpayment: $"+ downpayment + '\n' + "Interest rate: " +interest+"%"+ '\n' + "Loan period: " +time +" years " + '\n' );
        
        //converts interest to decimal
        float interestInDecimals = interest / 100 ;
        // calculates final loan principal
        double loanPrincipal = housePrice - downpayment;
        int timeInMonths = time * 12;
        /** calculates the monthly payment for a loan and stores it in variable result
        * includes principal and interest only
        * formula: P x (r / n) x (1 + r / n)^n(t)] / (1 + r / n)^n(t) - 1 
        * formular found in https://www.thebalance.com/calculate-mortgage-315668
        */  
       double result =  (loanPrincipal * (interestInDecimals / 12) * Math.pow((1+ (interestInDecimals / 12)), timeInMonths))/ ((Math.pow((1+ (interestInDecimals / 12)),360)) - 1);
       // rounds results to the nearest two decimal places.
       double roundedResult = Math.round(result * 100.0)/100.0;
       
        System.out.println("You will be paying $" + roundedResult);
    }
}