public class BankAccount {

    private String accountNumber;
    private String pin;
    private double balance;

    public BankAccount(String accountNumber, String pin, double balance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposit successful!");
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal successful!");
            return true;
        } else {
            System.out.println("Insufficient balance!");
            return false;
        }
    }

    public String toFileString() {
        return accountNumber + "," + pin + "," + balance;
    }
}