import java.util.ArrayList;
public class Person {
    private final String name;
    private double balance;
    private final ArrayList<Product> products;

    public Person(String name, double balance) {
        this.name = name;
        this.balance = balance;
        products = new ArrayList<>();
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void byeProduct(Product product){
        if(this.balance < product.getPrice()){
            System.out.println(this.getName() + " can`t afford " + product.getName());
        }else{
            this.setBalance(this.getBalance() - product.getPrice());
            System.out.println(this.getName() + " bought "+ product.getName());
            this.products.add(product);
        }
    }
}
