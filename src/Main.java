import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final String end = "end";
        String commandLine, command;
        String username, password, newPassword = null;
        while(!((commandLine = sc.nextLine()).equals(end))){
            String[] splitCommand = commandLine.split(" ");
            command = splitCommand[0].toLowerCase();
            if(command.equals(end))
            {
                return;
            }
            username = splitCommand[1];
            password = splitCommand[2];
            if(splitCommand.length == 4){
                newPassword = splitCommand[3];
            }
            executeCommand(command,username,password,newPassword);
        }
    }
    private static void executeCommand(String command, String username, String password, String newPassword) {
        Credentials currentCredentials = new Credentials(username,password);
        boolean result = false;
        switch (command){
            case "enroll":
                result = currentCredentials.enroll();
                break;
            case "auth":
                result = currentCredentials.auth();
                break;
            case "chpass":
                result = currentCredentials.changePassword(newPassword);
                break;
        }
        System.out.println(command.toUpperCase() + " " + (result?"success":"fail"));
    }
}