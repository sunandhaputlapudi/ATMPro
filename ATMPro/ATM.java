import java.util.*;

public class ATM {

    private List<BankAccount> accounts;
    private Scanner sc = new Scanner(System.in);

    public ATM(List<BankAccount> accounts) {
        this.accounts = accounts;
    }

    public void start() {

        int choice;

        do {
            System.out.println("\n===== ATM SYSTEM =====");
            System.out.println("1. Create Account");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");

            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {

                case 1:
                    createAccount();
                    break;

                case 2:
                    login();
                    break;

                case 3:
                    System.out.println("Thank you for using ATM!");
                    break;

                default:
                    System.out.println("Invalid option!");
            }

        } while (choice != 3);
    }

    private void createAccount() {

        System.out.print("Enter new Account Number: ");
        String accNum = sc.nextLine();

        if (findAccount(accNum) != null) {
            System.out.println("Account already exists!");
            return;
        }

        System.out.print("Set PIN: ");
        String pin = sc.nextLine();

        System.out.print("Enter Initial Deposit: ");
        double balance = sc.nextDouble();
        sc.nextLine();

        BankAccount newAccount = new BankAccount(accNum, pin, balance);
        accounts.add(newAccount);
        FileUtil.saveAccounts(accounts);

        System.out.println("Account created successfully!");
    }

    private void login() {

        System.out.print("Enter Account Number: ");
        String accNum = sc.nextLine();

        System.out.print("Enter PIN: ");
        String pin = sc.nextLine();

        BankAccount account = findAccount(accNum);

        if (account != null && account.getPin().equals(pin)) {
            System.out.println("Login successful!");
            accountMenu(account);
        } else {
            System.out.println("Invalid credentials!");
        }
    }

    private void accountMenu(BankAccount account) {

        int choice;

        do {
            System.out.println("\n===== ACCOUNT MENU =====");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Logout");
            System.out.print("Choose option: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.println("Current Balance: " + account.getBalance());
                    break;

                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double deposit = sc.nextDouble();
                    sc.nextLine();
                    account.deposit(deposit);
                    FileUtil.saveAccounts(accounts);
                    break;

                case 3:
                    System.out.print("Enter amount to withdraw: ");
                    double withdraw = sc.nextDouble();
                    sc.nextLine();
                    if (account.withdraw(withdraw)) {
                        FileUtil.saveAccounts(accounts);
                    }
                    break;

                case 4:
                    System.out.println("Logged out successfully!");
                    break;

                default:
                    System.out.println("Invalid option!");
            }

        } while (choice != 4);
    }

    private BankAccount findAccount(String accNum) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accNum)) {
                return account;
            }
        }
        return null;
    }
}