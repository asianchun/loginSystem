import javax.swing.*;
import java.awt.*;

public class NextPage extends JFrame {

    private JLabel label;
    private JButton returnButton;

    NextPage(String username){

        returnButton = new JButton("Return");
        returnButton.setFocusable(false);
        returnButton.addActionListener(
                (e) -> {
                    new Login(User.getUserInfo());
                    this.dispose();
                }
        );

        label = new JLabel();
        label.setText("Welcome to the secret, " + username + "!");
        label.setFont(new Font("Consolas", Font.PLAIN, 20));

        this.add(label);
        this.add(returnButton);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(420,420);
        this.setLocationRelativeTo(null);
        this.setLayout(new FlowLayout());
        this.setVisible(true);
    }
}
