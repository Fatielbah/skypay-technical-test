package bankingService;
import java.time.LocalDate;

public class AccountTest {
    
    public static void main(String[] args) {
    	testDeposit();
        testWithdraw();
        testInsufficientFunds();
        testNegativeDeposit();
        testNegativeWithdrawal();
        testAcceptanceScenario();
        testMultipleTransactions();
        
    }
    
    private static void testDeposit() {
        System.out.println("Test 1: Deposit");
        Account account = new Account();
        account.deposit(1000);
        
        assert account.getBalance() == 1000 : "Balance should be 1000";
        assert account.getTransactionCount() == 1 : "Should have 1 transaction";
        System.out.println("Deposit test passed\n");
    }
    
    private static void testWithdraw() {
        System.out.println("Test 2: Withdraw");
        Account account = new Account();
        account.deposit(1000);
        account.withdraw(500);
        
        assert account.getBalance() == 500 : "Balance should be 500";
        assert account.getTransactionCount() == 2 : "Should have 2 transactions";
        System.out.println("Withdraw test passed\n");
    }
    
    private static void testInsufficientFunds() {
        System.out.println("Test 3: Insufficient Funds Exception");
        Account account = new Account();
        account.deposit(500);
        
        try {
            account.withdraw(1000);
            assert false : "Should have thrown exception";
        } catch (IllegalArgumentException e) {
            assert e.getMessage().contains("Insufficient funds") : "Wrong exception message";
            System.out.println("Correctly threw exception: " + e.getMessage() + "\n");
        }
    }
    
    private static void testNegativeDeposit() {
        System.out.println("Test 4: Negative Deposit Exception");
        Account account = new Account();
        
        try {
            account.deposit(-100);
            assert false : "Should have thrown exception";
        } catch (IllegalArgumentException e) {
            assert e.getMessage().contains("must be positive") : "Wrong exception message";
            System.out.println("Correctly threw exception: " + e.getMessage() + "\n");
        }
    }
    
    private static void testNegativeWithdrawal() {
        System.out.println("Test 5: Negative Withdrawal Exception");
        Account account = new Account();
        account.deposit(1000);
        
        try {
            account.withdraw(-100);
            assert false : "Should have thrown exception";
        } catch (IllegalArgumentException e) {
            assert e.getMessage().contains("must be positive") : "Wrong exception message";
            System.out.println("Correctly threw exception: " + e.getMessage() + "\n");
        }
    }
    
    private static void testAcceptanceScenario() {
        System.out.println("Test 6: Acceptance Scenario ");
        Account account = new Account();
        
        LocalDate date1 = LocalDate.of(2012, 1, 10);
        account.depositWithDate(1000, date1);
        
        LocalDate date2 = LocalDate.of(2012, 1, 13);
        account.depositWithDate(2000, date2);
        
        LocalDate date3 = LocalDate.of(2012, 1, 14);
        account.withdrawWithDate(500, date3);
        
        System.out.println("Expected output:");
        account.printStatement();
        
        assert account.getBalance() == 2500 : "Final balance should be 2500";
        System.out.println("Acceptance scenario passed\n");
    }
    
    private static void testMultipleTransactions() {
        System.out.println("Test 7: Multiple Transactions Performance");
        Account account = new Account();
        
        long startTime = System.nanoTime();
        
        for (int i = 0; i < 10000; i++) {
            account.deposit(100);
        }
        
        for (int i = 0; i < 5000; i++) {
            account.withdraw(50);
        }
        
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000; 
        
        assert account.getBalance() == 750000 : "Balance calculation incorrect";
        assert account.getTransactionCount() == 15000 : "Transaction count incorrect";
        
        System.out.println("Handled 15,000 transactions in " + duration + "ms\n");
    }
}
