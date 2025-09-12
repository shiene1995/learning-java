import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends JFrame {
    static String usernameDB = "";
    static String passwordHash = "";
    static String saltHash = "";

    private JTextField usernameInput;
    private JButton loginButton;
    private JPanel swing_test_Panel; //I should give name for Jpanel to call in setContentPane
    private JPasswordField passwordInput;

    public Login(){
        setContentPane(swing_test_Panel); //this is how to call Jpanel
        setTitle("CIICC Apps");
        setDefaultCloseOperation(EXIT_ON_CLOSE); //WHEN I CLICK CLOSE, THE PROGRAM WILL BE STOP AND NOT RUNNING IN BACKGROUND PROCESSING
        setResizable(false);
        setSize(400,400);
        setLocationRelativeTo(null);
        setVisible(true);
        ImageIcon image = new ImageIcon("img/icon.png");
        setIconImage(image.getImage());

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MySQL mySQLConnection = new MySQL("db_ciicc"); //CREATE CONNECTION TO MySQL

                char[] passwordChars = passwordInput.getPassword(); //this is how to get data in passwordField
                //String password = new String(passwordChars); //converting characters to String

                boolean usernameFilter = InputFilterUtils.isValidUsername(usernameInput.getText());
                //boolean passwordFilter = InputFilterUtils.isStrongPassword(new String(passwordChars)); //I should include this below in the future.

                if (usernameFilter) {

                    try {
                        ResultSet rs = mySQLConnection.selectSQL(usernameInput.getText(), "username", "tb_account", "");
                        if (rs.next()) {
                            usernameDB = rs.getString("username");
                            passwordHash = rs.getString("PasswordHash");
                            saltHash = rs.getString("SaltHash");

                            rs.close(); //Closed the ResultSet (For Select Query only)
                            mySQLConnection.close(); //Closing MySQL Connection and stmt

                            boolean VP = PasswordHashers.verifyPassword(new String(passwordChars), passwordHash, saltHash);
                            boolean VU = PasswordHashers.verifyUsername(usernameInput.getText(),usernameDB); //to case-sensitive the username
                            //example: Admin & admin are different

                            if (VP && VU) {
                                dispose(); // this is how to hide form
                                new Dashboard(); // open new form
                            } else { clearInput();}

                        } else {clearInput();}
                    } catch (SQLException ex) {throw new RuntimeException(ex);}

                } else {clearInput();}
            }
        });

        usernameInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginButton.doClick();
            }
        });
        passwordInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginButton.doClick();
            }
        });
    }
    public void alert(String data){JOptionPane.showMessageDialog(this, data);} //ALERT LIKE IN JAVASCRIPT
    public void clearInput(){alert("INVALID USERNAME OR PASSWORD!");usernameInput.setText("");passwordInput.setText("");}
}