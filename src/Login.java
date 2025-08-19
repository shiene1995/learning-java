import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends JFrame {
    static String usernameDB = "";
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
                MySQL mySQLConnection = new MySQL("db_ciicc"); //CREATE CONNECTION TO MySQL

                String username = textField1.getText(); //this is how to get data in textfield
                char[] passwordChars = passwordField1.getPassword(); //this is how to get data in passwordField
                String password = new String(passwordChars); //converting characters to String

                boolean usernameFilter = InputFilterUtils.isValidUsername(username);
                boolean passwordFilter = InputFilterUtils.isStrongPassword(password); //I should include this below in the future.

                if (usernameFilter) {

                    try {
                        ResultSet rs = mySQLConnection.selectSQL(username, "username", "tb_account", "");
                        if (rs.next()) {
                            usernameDB = rs.getString("username");
                            passwordHash = rs.getString("PasswordHash");
                            saltHash = rs.getString("SaltHash");

                            rs.close(); //Closed the ResultSet (For Select Query only)
                            mySQLConnection.close(); //Closing MySQL Connection and stmt

                            boolean VP = PasswordHashers.verifyPassword(password, passwordHash, saltHash);
                            boolean VU = PasswordHashers.verifyUsername(username,usernameDB); //to case-sensitive the username
                            //example: Admin & admin are different

                            if (VP && VU) {
                                setVisible(false); // this is how to hide form
                                new Simple_Calculator(); // open new form
                            } else { clearInput();}

                        } else {clearInput();}
                    } catch (SQLException ex) {throw new RuntimeException(ex);}

                } else {clearInput();}
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
    public void alert(String data){JOptionPane.showMessageDialog(this, data);} //ALERT LIKE IN JAVASCRIPT
    public void clearInput(){alert("INVALID USERNAME OR PASSWORD!");textField1.setText("");passwordField1.setText("");}
}