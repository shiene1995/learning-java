import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends JFrame {
    static String passwordHash = "";
    static String saltHash = "";

    private JTextField textField1;
    private JButton enterButton;
    private JPanel swing_test_Panel; //I should give name for Jpanel to call in setContentPane
    private JPasswordField passwordField1;

    public Login(){
        setContentPane(swing_test_Panel); //this is how to call Jpanel
        setTitle("CIICC Apps");
        setDefaultCloseOperation(EXIT_ON_CLOSE); //WHEN I CLICK CLOSE, THE PROGRAM WILL BE STOP AND NOT RUNNING IN BACKGROUND PROCESSING
        setSize(400,400);
        setLocationRelativeTo(null);
        setVisible(true);

        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MySQL mySQLConnection = new MySQL("db_ciicc");

                String username = textField1.getText(); //this is how to get data in textfield
                char[] passwordChars = passwordField1.getPassword(); //this is how to get data in passwordField
                String password = new String(passwordChars); //converting character to String

                try {

                    ResultSet rs = mySQLConnection.selectSQL(username, "username", "tb_account", "");
                    while (rs.next()) {
                        Login.passwordHash = rs.getString("PasswordHash");
                        Login.saltHash = rs.getString("SaltHash");
                    }
                    mySQLConnection.closeConnection();

                } catch (SQLException ex) {throw new RuntimeException(ex);}

                boolean match = PasswordHashers.verifyPassword(password, Login.passwordHash, Login.saltHash);

                if (match)
                {
                    setVisible(false); // this is how to hide form
                    new Simple_Calculator(); // open new form
                }
                else
                {
                    alert("INVALID USERNAME OR PASSWORD!");
                    textField1.setText("");
                    passwordField1.setText("");
                }
            }
        });

        textField1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterButton.doClick();
            }
        });
        passwordField1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterButton.doClick();
            }
        });
    }
    public void alert(String data){JOptionPane.showMessageDialog(Login.this, data);} //ALERT LIKE IN JAVA
}