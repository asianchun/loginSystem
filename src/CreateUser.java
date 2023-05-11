import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class CreateUser extends JFrame {

    private JButton submitButton;
    private JButton cancelButton;
    private JButton generateButton;
    private JLabel userID;
    private JLabel noMatch, lengthLabel, upperLabel, numberLabel;
    private JLabel userPassword;
    private JLabel confirmUserPassword;
    private JTextField idField;
    private JTextField passwordField;
    private JTextField confirmPasswordField;
    private Random randomGen;

    CreateUser(){
        randomGen = new Random();

        userID = new JLabel("userID:");
        userID.setBounds(50,100, 60,20);

        userPassword = new JLabel("password:");
        userPassword.setBounds(50, 140,60,20);

        confirmUserPassword = new JLabel("confirm:");
        confirmUserPassword.setBounds(50, 180,60,20);

        idField = new JTextField();
        idField.setBounds(120,100,200,20);

        passwordField = new JTextField();
        passwordField.setBounds(120,140,200,20);

        confirmPasswordField = new JTextField();
        confirmPasswordField.setBounds(120,180,200,20);

        noMatch = new JLabel();
        noMatch.setBounds(120, 260,280,20);
        noMatch.setFont(new Font(null,Font.BOLD, 15));
        noMatch.setForeground(Color.red);

        lengthLabel = new JLabel();
        lengthLabel.setBounds(120, 280,280,20);
        lengthLabel.setFont(new Font(null,Font.BOLD, 15));
        lengthLabel.setForeground(Color.red);

        upperLabel = new JLabel();
        upperLabel.setBounds(120, 300,280,20);
        upperLabel.setFont(new Font(null,Font.BOLD, 15));
        upperLabel.setForeground(Color.red);

        numberLabel = new JLabel();
        numberLabel.setBounds(120, 320,280,20);
        numberLabel.setFont(new Font(null,Font.BOLD, 15));
        numberLabel.setForeground(Color.red);

        submitButton = new JButton("Submit");
        submitButton.setBounds(120,220,100,20);
        submitButton.setFocusable(false);
        submitButton.addActionListener(
                (e) -> {
                    lengthLabel.setForeground(Color.red);
                    upperLabel.setForeground(Color.red);
                    numberLabel.setForeground(Color.red);

                    if (passwordField.getText().isEmpty() || confirmPasswordField.getText().isEmpty()
                            || idField.getText().isEmpty()) {
                        noMatch.setText("All fields must be filled");
                    } else {
                        if (passwordField.getText().equals(confirmPasswordField.getText())) {
                            if (passwordIsValid()){
                                User newUser = new User(idField.getText(), passwordField.getText());
                                addToFile();
                                this.dispose();
                                new Login(User.getUserInfo());
                            } else {
                                noMatch.setText("Password is not valid!");
                                lengthLabel.setText("Must be atleast 8 characters long");
                                upperLabel.setText("Must be atleast one uppercase letter");
                                numberLabel.setText("Must be atleast one number");
                            }
                        } else {
                            noMatch.setText("Passwords don't match");
                        }
                    }
                }
        );

        generateButton = new JButton("Generate");
        generateButton.setBounds(20,220,100,20);
        generateButton.setFocusable(false);
        generateButton.addActionListener(
                (e) -> {
                    int length = randomGen.nextInt(5) + 8;
                    String password = "";

                    for (int i = 0; i <= length; i++){
                        boolean number = randomGen.nextBoolean();

                        if (number){
                            int randomNumber = randomGen.nextInt(10);
                            password = password.concat(Integer.toString(randomNumber));
                        } else {
                            char randomLetter = (char) (randomGen.nextInt(26) + 97);
                            boolean upperCase = randomGen.nextBoolean();

                            if (upperCase){
                                randomLetter = Character.toUpperCase(randomLetter);
                            }

                            password = password.concat(Character.toString(randomLetter));
                        }
                    }

                    passwordField.setText(password);
                    confirmPasswordField.setText(password);
                }
        );

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(220,220,100,20);
        cancelButton.setFocusable(false);
        cancelButton.addActionListener(
                (e) -> {
                    this.dispose();
                    new Login(User.getUserInfo());
                }
        );

        this.add(userID);
        this.add(userPassword);
        this.add(confirmPasswordField);
        this.add(idField);
        this.add(passwordField);
        this.add(confirmUserPassword);
        this.add(submitButton);
        this.add(cancelButton);
        this.add(generateButton);
        this.add(noMatch);
        this.add(lengthLabel);
        this.add(upperLabel);
        this.add(numberLabel);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(420,420);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setVisible(true);
    }

    private void addToFile() {
        File file = new File("All_Users.txt");

        try {
            FileWriter fileWriter = new FileWriter(file);

            for (String i : User.getUserInfo().keySet()){
                fileWriter.append(i + "\n");
                fileWriter.append(User.getUserInfo().get(i) + "\n");
            }

            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean passwordIsValid(){
        boolean validUpper = false;
        boolean validNumber = false;
        boolean validLength = false;

        if (passwordField.getText().length() >= 8) {
            validLength = true;
            lengthLabel.setForeground(new Color(0x00b03e));
        }

        for (int i = 0; i < passwordField.getText().length(); i++){
            if (Character.isUpperCase(passwordField.getText().charAt(i))){
                validUpper = true;
                upperLabel.setForeground(new Color(0x00b03e));
            }

            if (!Character.isAlphabetic(passwordField.getText().charAt(i))){
                validNumber = true;
                numberLabel.setForeground(new Color(0x00b03e));
            }
        }

        if (validNumber && validUpper && validLength){
            return true;
        }
        else {
            return false;
        }
    }
}