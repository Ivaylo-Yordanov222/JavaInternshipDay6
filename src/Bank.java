import java.util.ArrayList;

public class Bank {
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    private static final int LIMIT = 5;
    private final ArrayList<Account> accounts;
    private double assets;
    public Bank(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public double getAssets() {
        double assetsSum = 0;
        for (Account ac : accounts){
            assetsSum += ac.getBalance();
        }
        this.setAssets(assetsSum);
        return assets;
    }
    private void setAssets(double amount){
        this.assets = amount;
    }

    public void openAccount(String name, long govId, String username, String password){
        Credentials credential = new Credentials(username, password);
        Account newAccount = new Account(name, govId, credential);
        newAccount.getCredential().enroll();
        if(this.accounts.size() < LIMIT){
            this.getAccounts().add(newAccount);
            System.out.println(SUCCESS);
        }else{
            System.out.println(FAIL);
        }
    }
    public void closeAccount(String username, String password){
        Account searchedAccount = this.findAccount(username);

        if(searchedAccount != null && searchedAccount.hasAccess(password)){
            this.getAccounts().remove(searchedAccount);
            System.out.println(SUCCESS);
        }
        else{
            System.out.println(FAIL);
        }
    }
    public void deposit(String username, double amount){
        Account account = this.findAccount(username);
        if(account != null && amount > 0){
            account.deposit(amount);
            System.out.println(SUCCESS);
        }
        else{
            System.out.println(FAIL);
        }
    }
    public void withdraw(String username, String password, double amount){
        Account account = this.findAccount(username);
        if(account != null && account.hasAccess(password) && account.getBalance() > amount){
            account.withdraw(amount);
            System.out.println(SUCCESS);
        }
        else{
            System.out.println(FAIL);
        }
    }

    public void transfer(String username, String password, double amount, String recipientName){
        Account sender = this.findAccount(username);
        Account recipient = this.findAccount(recipientName);
        if(sender != null && recipient != null && sender.hasAccess(password) && amount < sender.getBalance()){
            sender.withdraw(amount);
            recipient.deposit(amount);
            System.out.println(SUCCESS);
        }
        else{
            System.out.println(FAIL);
        }
    }

    private Account findAccount(String username){
        Account searchedAccount = null;
        for (Account account: this.getAccounts()){
            if(account.getUsername().equals(username)){
                searchedAccount = account;
                break;
            }
        }
        return searchedAccount;
    }
}
