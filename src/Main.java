import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static ArrayList<Account> accounts = new ArrayList<>();
    public static Bank bank = new Bank(accounts);

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final String end = "END";
        String commandLine;
        while(!((commandLine = sc.nextLine()).equals(end))){
            String[] splitCommand = commandLine.split(" ");
            executeCommand(splitCommand);
        }
        sc.close();
        System.out.println(bank.getAssets());
        for (Account account: bank.getAccounts()){
            System.out.println(account.getName() + ", " + account.getGovId() + ", "
                    + String.format("%.2f", account.getBalance()));
        }
    }
    public static void executeCommand(String[] splitCommand){
        String command;
        String username, password, name, recipientName;
        long govId;
        double amount;
        command = splitCommand[0];
        switch (command){
            case "OPEN":
                username = splitCommand[1];
                password = splitCommand[2];
                name = splitCommand[3];
                govId = Long.parseLong(splitCommand[4]);
                System.out.print(command + " ");
                bank.openAccount(name, govId, username, password);
                break;
            case "CLOSE":
                username = splitCommand[1];
                password = splitCommand[2];
                System.out.print(command + " ");
                bank.closeAccount(username, password);
                break;
            case "DEPOSIT":
                username = splitCommand[1];
                amount = Double.parseDouble(splitCommand[2]);
                System.out.print(command + " ");
                bank.deposit(username, amount);
                break;
            case "WITHDRAW":
                username = splitCommand[1];
                password = splitCommand[2];
                amount = Double.parseDouble(splitCommand[3]);
                System.out.print(command + " ");
                bank.withdraw(username, password, amount);
                break;
            case "TRANSFER":
                username = splitCommand[1];
                password = splitCommand[2];
                amount = Double.parseDouble(splitCommand[3]);
                recipientName = splitCommand[4];
                System.out.print(command + " ");
                bank.transfer(username, password, amount, recipientName);
                break;
        }
    }
}