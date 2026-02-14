import java.io.*;
import java.util.*;

public class FileUtil {

    private static final String FILE_NAME = "accounts.txt";

    public static List<BankAccount> loadAccounts() {
        List<BankAccount> accounts = new ArrayList<>();

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            // File does not exist → create empty file
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating accounts.txt file!");
                e.printStackTrace();
            }
        }

        // Read existing accounts
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue; // skip empty lines
                String[] data = line.split(",");
                if (data.length != 3) continue; // skip invalid lines

                accounts.add(new BankAccount(
                        data[0],
                        data[1],
                        Double.parseDouble(data[2])
                ));
            }
        } catch (IOException e) {
            System.out.println("Error reading accounts.txt file!");
            e.printStackTrace();
        }

        return accounts;
    }
    public static void saveAccounts(List<BankAccount> accounts) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (BankAccount account : accounts) {
                bw.write(account.toFileString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to accounts.txt file!");
            e.printStackTrace();
        }
    }
}