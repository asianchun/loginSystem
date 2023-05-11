import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class Login extends JFrame implements ActionListener {

    private JLabel userID;
    private JLabel incorrectLogin;
    private JLabel userPassword;
    private JTextField idField;
    private JPasswordField passwordField; //Makes the text dotted to the user
    private JButton loginButton;
    private JButton signUpButton;
    private HashMap<String, String> userInfo;

    Login(HashMap<String, String> userInfo){
        this.userInfo = userInfo;

        userID = new JLabel("userID:");
        userID.setBounds(50,140, 60,20);

        userPassword = new JLabel("password:");
        userPassword.setBounds(50, 180,60,20);

        idField = new JTextField();
        idField.setBounds(120,140,200,20);

        passwordField = new JPasswordField();
        passwordField.setBounds(120,180,200,20);

        loginButton = new JButton("Login");
        loginButton.setFocusable(false);
        loginButton.setBounds(120,220,100,20);
        loginButton.addActionListener(this);

        incorrectLogin = new JLabel();
        incorrectLogin.setBounds(120,260,280,20);
        incorrectLogin.setFont(new Font(null,Font.BOLD, 15));
        incorrectLogin.setForeground(Color.red);

        signUpButton = new JButton("Sign Up");
        signUpButton.setFocusable(false);
        signUpButton.setBounds(220,220,100,20);
        signUpButton.addActionListener(this);

        this.add(userID);
        this.add(userPassword);
        this.add(idField);
        this.add(passwordField);
        this.add(incorrectLogin);
        this.add(loginButton);
        this.add(signUpButton);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(420,420);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton){
            String id = idField.getText();
            String password = String.valueOf(passwordField.getPassword()); //Getting text from JPasswordField

            if (userInfo.containsKey(id)){
                if (userInfo.get(id).equals(password)){
                    new NextPage(id);
                    this.dispose();
                }
                else {
                    incorrectLogin.setText("Incorrect Password");
                }
            }
            else {
                incorrectLogin.setText("ID provided not found");
            }
        }
        else if (e.getSource() == signUpButton){
            new CreateUser();
            this.dispose();
        }
    }
}
