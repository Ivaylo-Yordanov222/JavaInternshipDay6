import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final String end = "end";
        String commandLine, command;
        double value1, value2;
        while(!((commandLine = sc.nextLine()).equals(end))){
            String[] splitCommand = commandLine.split(" ");
            command = splitCommand[0].toLowerCase();
            if(command.equals(end))
            {
                return;
            }
            value1 = Double.parseDouble(splitCommand[1]);
            value2 = Double.parseDouble(splitCommand[2]);
            executeCommand(command, value1,value2);
        }
    }
    private static void executeCommand(String command, double value1, double value2) {
        double result = 0;
        switch (command){
            case "sum":
                result = Calculator.sum(value1,value2);
                break;
            case "sub":
                result = Calculator.subtract(value1,value2);
                break;
            case "mul":
                result = Calculator.multiply(value1,value2);
                break;
            case "div":
                result = Calculator.divide(value1,value2);
                break;
            case "per":
                result = Calculator.percentage(value1,value2);
                break;
        }
        System.out.printf("%.3f", result);
        System.out.println();
    }
}