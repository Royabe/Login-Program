import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class Scanner_test {
    public static void main(String[] args) throws IOException  {
        //List of users
        String[] name = new String[10];
        String[] pass = new String[10];
        Scanner inp = new Scanner(System.in);
        //Initial user
        name[0]="admin";
        pass[0]="Password1234!";
        int numuser = 1; //number of users
        int answer;
        //Options
        String Username = login(name,pass,numuser);
            do {
                answer = 3;
                if(!Username.equals("")) {
                    System.out.println("Hello " + Username + ",\nEnter operation:\n1:create new user\n2:login as another user\n3:quit");
                    answer = inp.nextInt();
                    if (answer == 1) {
                        //New User
                        numuser += 1;
                        String[] temp = newuser();
                        name[numuser-1] = temp[0];
                        pass[numuser-1] = temp[1];
                    } else if (answer == 2) {
                        //Change Login
                        Username = login(name, pass, numuser);
                    } else if (answer != 3) {
                        //Error
                        System.out.println("Invalid Option");
                    }
                }
            }while(answer != 3);
        }
    public static String[] newuser(){
        String[] creds = new String[2]; //new account credentials
        Arrays.fill(creds,"");//initialises array
        Scanner in = new Scanner(System.in);
        System.out.println("Input new Username");
        while(creds[0].equals("")) {
            creds[0] = in.nextLine().toLowerCase();
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
                valid = true;
            } else {
                System.out.println("Password does not meet requirements\n");
            }
        }
        return creds;
    }
    public static String login(String[] cname, String[] cpass, int numuser) throws IOException {
        int counter = 0;
        int index = 1;
        boolean valid = false;
        Scanner in = new Scanner(System.in);
        String password;
        do{
            String name = "";

            //enter username
            while (name.equals("")) {
                System.out.println("Enter Username");
                name = in.nextLine().toLowerCase();
            }
            counter+=1;
            //enter password
            System.out.println("Enter Password");
            BufferedReader passread = new BufferedReader(new InputStreamReader(System.in));
            password = passread.readLine();
            //checks username password combo
            for(int i = 0; i < numuser+1;i++) {
                if (name.equals(cname[i]) && password.equals(cpass[i])) {
                    valid = true;
                    index = i;
                }
            }
            //loops if no correct login or counter gets to 3
        } while (!valid && counter < 3);
        //result
        if (valid){
            System.out.println("Welcome");
            return cname[index];
        } else{
            System.out.println("attempt limit reached");
            return "";
        }

    }
}
//Admin
//Password1234!
//3 attempts
//Username <> ""
//Username is not case-sensitive
//Send src file only via email