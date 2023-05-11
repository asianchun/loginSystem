import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean isID = true;
        ArrayList<String> userID = new ArrayList<>();
        ArrayList<String> userPassword = new ArrayList<>();
        File file = new File("All_Users.txt");

        try {
            Scanner fileIn = new Scanner(file);

            while (fileIn.hasNextLine()){
                if (isID){
                    userID.add(fileIn.nextLine());
                    isID = false;
                } else {
                    userPassword.add(fileIn.nextLine());
                    isID = true;
                }
            }

            fileIn.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < userID.size(); i++){
            User user = new User(userID.get(i), userPassword.get(i));
        }
        Login loginPage = new Login(User.getUserInfo());
    }
}
