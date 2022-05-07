public class Account {
    private final String name;
    private final long govId;
    private double balance;
    private final Credentials  credential;

    public Account(String name, long govId, Credentials credential) {
        this.name = name;
        this.govId = govId;
        this.credential = credential;
    }
    public Credentials getCredential(){
        return this.credential;
    }
    public String getName() {
        return name;
    }
    public String getUsername(){
        return this.credential.getUsername();
    }

    public long getGovId() {
        return govId;
    }

    public double getBalance() {
        return balance;
    }
    private void setBalance(double balance) {
        this.balance = balance;
    }

    public void deposit(double amount){
        this.setBalance(this.balance + amount);
    }
    public void withdraw(double amount){
        this.setBalance(this.getBalance() - amount);
    }
    public boolean hasAccess(String password){
        return this.credential.auth();
    }
}
