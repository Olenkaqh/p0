import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LoanTest {
    private static final double DELTA = 1e-15;
    private Loan newLoan1;

    @Before
    public void setUp() {
       newLoan1 = new Loan (100000, 0, 1,30);
    }

    @Test
    public void testLoanConstructor() {
        Assert.assertEquals(100000, newLoan1.loanAmount, DELTA);
        Assert.assertEquals(1, newLoan1.interest, DELTA);
        Assert.assertEquals(30, newLoan1.time);
    }

    @Test
    public void testCalculateMonthlyPayments() {
        double result = Math.round(newLoan1.calculateMonthlyPayments() * 100.0 ) /100.0;
//        result = newLoan1.calculateMonthlyPayments();
        Assert.assertEquals(321.64,result, DELTA);
    }
    @Test
    public void testCalculateMonthlyInterestPayment(){
        double result = Math.round(newLoan1.calculateMonthlyInterestPayments() * 100.0 ) /100.0;
        Assert.assertEquals(83.33,result, DELTA);
    }

    @Test
    public void testCalculateTotalLoanPayment() {
       double result = Math.round(newLoan1.calculateTotalLoanPayment() * 100.0 ) /100.0;
       Assert.assertEquals(115790.23,result, DELTA);
    }

    @Test
    public void testCalculateTotalInterestPayment() {
        double result = Math.round(newLoan1.calculateTotalInterestPayment() * 100.0 ) /100.0;
        Assert.assertEquals(15790.23,result, DELTA);
    }

}
