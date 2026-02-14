import java.util.*;

public class Main {

    public static void main(String[] args) {

        List<BankAccount> accounts = FileUtil.loadAccounts();

        ATM atm = new ATM(accounts);
        atm.start();
    }
}