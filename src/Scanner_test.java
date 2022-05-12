import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Scanner_test {
    public static void main(String[] args) throws IOException  {
        //List of users
        ArrayList<User> Users = new ArrayList<>();
        Scanner inp = new Scanner(System.in);
        //Initial user
        Users.add(new User("admin","Password1234!"));
        int answer;
        //Options
        String Username = login(Users);
            do {
                answer = 3;
                if(!Username.equals("")) {
                    System.out.println("Hello " + Username + ",\nEnter operation:\n1:create new user\n2:login as another user\n3:quit");
                    answer = inp.nextInt();
                    if (answer == 1) {
                        //New User
                        Users.add(newuser());
                    } else if (answer == 2) {
                        //Change Login
                        Username = login(Users);
                    } else if (answer != 3) {
                        //Error
                        System.out.println("Invalid Option");
                    }
                }
            }while(answer != 3);
        }
    public static User newuser(){
        User newUser = new User("",""); //new account credentials
        Scanner in = new Scanner(System.in);
        System.out.println("Input new Username");
        while(newUser.getUsername().equals("")) {
            newUser.setUsername(in.nextLine().toLowerCase());
        }
        boolean valid = false;
        while(!valid){
            System.out.println("Input new Password\nRequires:\n\bcapital letter\nlowercase letter\nsymbol\nnumber");
            char[] passtest = in.nextLine().toCharArray();
            boolean[] Tests = new boolean[4]; //Digit, lowercase, uppercase, symbol
            Arrays.fill(Tests,false);
            for (char c : passtest) {
                if (Character.isDigit(c)) {//digit test
                    Tests[0] = true;
                } else if (Character.isLowerCase(c)) {//lowercase test
                    Tests[1] = true;
                } else if (Character.isUpperCase(c)) {//uppercase test
                    Tests[2] = true;
                } else if (!Character.isLetterOrDigit(c) && !Character.isWhitespace(c)) {//symbol test
                    Tests[3] = true;
                }
            }
            if(Tests[0]&&Tests[1]&&Tests[2]&&Tests[3]){
                String password = "";
                for(char c:passtest){
                    password += c;
                }
                newUser.setPassword(password);
                valid = true;
            } else {
                System.out.println("Password does not meet requirements\n");
            }
        }
        return newUser;
    }
    public static String login(ArrayList<User> Users) throws IOException {
        int counter = 0;
        boolean valid = false;
        Scanner in = new Scanner(System.in);
        User ValidUser = new User("","");
        do{
            //enter username
            while (ValidUser.getUsername().equals("")) {
                System.out.println("Enter Username");
                ValidUser.setUsername(in.nextLine().toLowerCase());
            }
            counter+=1;
            //enter password
            System.out.println("Enter Password");
            BufferedReader passread = new BufferedReader(new InputStreamReader(System.in));
            //Scanner passread = new Scanner(System.in);
            ValidUser.setPassword(passread.readLine());
            //checks username password combo
            for(User i:Users) {
                if(ValidUser.getUsername().equals(i.getUsername())&&ValidUser.getPassword().equals(i.getPassword())){
                    valid = true;
                }
            }
            //loops if no correct login or counter gets to 3
        } while (!valid && counter < 3);
        //result
        if (valid){
            System.out.println("Welcome");
            return ValidUser.getUsername();
        } else{
            System.out.println("attempt limit reached");
            return "";
        }

    }
}
