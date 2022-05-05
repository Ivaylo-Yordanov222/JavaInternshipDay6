import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final String end = "END";
        ArrayList<Person> persons = new ArrayList<>();
        ArrayList<Product> products = new ArrayList<>();

        String[] personsRows = scanner.nextLine().split(";");
        fillPersons(personsRows, persons);
        String[] productsRows = scanner.nextLine().split(";");
        fillProducts(productsRows, products);

        String command;
        while (!((command = scanner.nextLine()).equals(end))){
            String[] commandRow = command.split(" ");
            String personName, productName;

            if(commandRow.length == 3){
                personName = commandRow[0] + " " + commandRow[1];
                productName = commandRow[2];
            }else{
                personName = commandRow[0];
                productName = commandRow[1];
            }

            Person person = findPerson(personName, persons);
            Product product = findProduct(productName,products);
            person.byeProduct(product);
        }
        scanner.close();
        printPersonsCarts(persons);
    }
    public static void printPersonsCarts(ArrayList<Person> persons){
        for (Person p: persons) {
            if(p.getProducts().size() > 0){
                System.out.print(p.getName()+"-");
                int i=0;
                for (Product pr: p.getProducts()){
                    int pL = p.getProducts().size();
                    if(pL - 1> i){
                        System.out.print(pr.getName() + ", ");
                    }else{
                        System.out.print(pr.getName());
                    }
                    i++;
                }
            }else{
                System.out.println(p.getName() + " - Nothing bought");
            }
            System.out.println();
        }
    }
    public static void fillPersons(String[] personsRows, ArrayList<Person> persons){
        for(int i = 0; i < personsRows.length; i++){
            String[] personRow = personsRows[i].split("=");
            String personName = personRow[0];
            if(personName.isBlank() || personName.isEmpty()){
                System.out.println("Name cat`t be empty.");
                return;
            }
            double personBalance = Double.parseDouble(personRow[1]);
            if(personBalance < 0){
                System.out.println("Balance can`t be negative!");
                return;
            }
            Person currentPerson = new Person(personName,personBalance);
            persons.add(currentPerson);
        }
    }
    public static void fillProducts(String[] productsRows, ArrayList<Product> products){
        for(int i = 0; i < productsRows.length; i++){
            String[] productsRow = productsRows[i].split("=");
            String productName = productsRow[0];
            if(productName.isBlank() || productName.isEmpty()){
                System.out.println("Name cat`t be empty.");
                return;
            }
            double productPrice = Double.parseDouble(productsRow[1]);
            if(productPrice < 0){
                System.out.println("Price can`t be negative!");
                return;
            }
            Product currentProduct = new Product(productName,productPrice);
            products.add(currentProduct);
        }
    }
    public static Person findPerson(String personName, ArrayList<Person> persons){
        Person personToFind = null;
        for (Person p : persons) {
            if (personName.equals(p.getName())) {
                personToFind = p;
                break;
            }
        }
        return personToFind;
    }
    public static Product findProduct(String productName, ArrayList<Product> products){
        Product productToFind = null;
        for (Product p : products) {
            if (productName.equals(p.getName())) {
                productToFind = p;
                break;
            }
        }
        return productToFind;
    }

}