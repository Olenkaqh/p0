import java.lang.Math;

public class Loan {
    // data members
    int loanAmount;
    double interest;
    int time;

    // constructor
    public Loan(int housePrice, int downPayment, double interest, int time) {
        this.loanAmount = housePrice - downPayment;
        this.interest = interest;
        this.time = time;
    }

    public double calculateMonthlyPayments() {
        //converts interest to decimal
        double interestInDecimals = interest / 100;

        //monthly interest rate
        double monthlyInterestRate = interestInDecimals / 12;

        //total number of monthly payments
        int numOfPayments = this.time * 12;

        /* calculates the monthly payment for a loan and stores it in variable result
         * includes principal and interest only
         * Amortized formula: M= P[r(1+r)^n/((1+r)^n)-1)]
         * formula found in https://www.bankrate.com/calculators/mortgages/amortization-calculator.aspx
         */
        double result = this.loanAmount *(( (monthlyInterestRate) * Math.pow((1 + monthlyInterestRate), numOfPayments)) / ((Math.pow((1 + monthlyInterestRate), numOfPayments)) - 1));
        // rounds results to the nearest two decimal places.
        //return Math.round(result * 100.0) / 100.0;
        return result;
    }

    public double calculateMonthlyInterestPayments() {
        double interestInDecimals = this.interest / 100;
        double monthlyInterestRate = interestInDecimals / 12;
        //Interest Payment = P * (r/n)
        double result = this.loanAmount * monthlyInterestRate;
//        return Math.round(result * 100.0) / 100.0;
        return result;
    }

    public double calculateMonthlyPrincipalPayments() {
        double result = calculateMonthlyPayments() - calculateMonthlyInterestPayments();
//        return Math.round(result * 100.0) / 100.0;
        return result;
    }

    public double calculateTotalLoanPayment() {
         /*  N = Number of periods (number of monthly mortgage payments)
            M = Monthly payment amount, calculated from last segment
            P = Principal amount (the total amount borrowed, minus any down payments) */
        //C = N * M
        double result = this.calculateMonthlyPayments() * this.time * 12;
        return result;
    }

    public double calculateTotalInterestPayment() {
        double result = this.calculateTotalLoanPayment() - this.loanAmount;
//        return Math.round(result * 100.0) / 100.0;
        return result;
    }

    // prints the amortized table for monthly payments
    public void generateAmortizedPaymentsTable() {
        //prints the table header
        System.out.println("---------------------------------------------------------");
        System.out.format("%5s %10s %10s %10s %15s", "Month","Monthly Payment", "Principal", "Interest", "Total balance");
        System.out.println("\n-------------------------------------------------------");

        double monthlyPayment = this.calculateMonthlyPayments();
        double monthlyPrincipal = this.calculateMonthlyPrincipalPayments();
        double monthlyInterest = this.calculateMonthlyInterestPayments();
        double totalBalance = this.loanAmount -monthlyPrincipal;
        int months  = this.time* 12;

        //prints the loan information for each month;
        for (int i = 1; i <= months; i++) {
            System.out.format("%5s %10s %10s %10s %15s", i,Math.round(monthlyPayment* 100.0) /100.0, Math.round(monthlyPrincipal* 100.0) /100.0, Math.round(monthlyInterest* 100.0) /100.0, Math.round(totalBalance* 100.0) /100.0);
            monthlyInterest = totalBalance * (this.interest / 1200);
            monthlyPrincipal =monthlyPayment - monthlyInterest;
            totalBalance  =  totalBalance - monthlyPrincipal;

            System.out.println('\n');
        }

    }
}


