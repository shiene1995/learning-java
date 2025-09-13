import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Base64;


public class AddModal extends JFrame {

    private JPanel addModalPane;
    private JTextField fName;
    private JTextField lName;
    private JTextField usernameInput;
    private JPasswordField passwordInput1;
    private JPasswordField passwordInput2;
    private JButton SAVEButton;
    ResultSet rs;
    private Dashboard dashboard;

    public AddModal(Dashboard dashboard){
        setContentPane(addModalPane); //this is how to call Jpanel
        setTitle("Add");
        setResizable(false);
        setSize(400,550);
        setLocationRelativeTo(null);
        setVisible(true);
        ImageIcon image = new ImageIcon("img/icon.png");
        setIconImage(image.getImage()); //SET WINDOWS ICON
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        this.dashboard = dashboard; // Store the reference

        SAVEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Dashboard dboard = new Dashboard("data");
                //dboard.addData(new Object[]{"1", "First Name", "Last Name", "Username"});

                try {
                    if (fName.getText().isEmpty() || lName.getText().isEmpty() || usernameInput.getText().isEmpty() || new StringBuilder().append(passwordInput1.getPassword()).isEmpty() || new StringBuilder().append(passwordInput2.getPassword()).isEmpty())
                    {
                        alert("PLEASE FILL ALL THE BLANK!");
                    }
                    else {
                        MySQL mySQLConnection = new MySQL("db_ciicc");

                        if (InputFilterUtils.isValidUsername(usernameInput.getText())) {
                            rs = mySQLConnection.selectSQL(usernameInput.getText(), "username", "tb_account", "");
                            if (rs.next()) {
                                alert("INVALID USERNAME!");
                            } else {
                                if (Arrays.equals(passwordInput1.getPassword(), passwordInput2.getPassword())) {
                                    char[] passwordChars = passwordInput1.getPassword();
                                    byte[] salt = PasswordHashers.generateSalt();

                                    int rs1 = mySQLConnection.insertSQL(new String[]{fName.getText(), lName.getText(), usernameInput.getText(), PasswordHashers.hashPassword(passwordChars, salt), Base64.getEncoder().encodeToString(salt)}, new String[]{"Fname", "Lname", "Username", "PasswordHash", "SaltHash"}, "tb_account");
                                    if (rs1 > 0) {
                                        alert("NEW DATA INPUT SUCCESS!");
                                        rs.close(); //Closed the ResultSet (For Select Query only)
                                        mySQLConnection.close(); //Closing MySQL Connection and stmt
                                        dispose();
                                        dashboard.showDataTable("");
                                    }
                                }
                                else { alert("PASSWORD CANNOT MATCH!");}
                            }
                        }
                        else { alert("INVALID USERNAME!"); }
                    }
                } catch (SQLException ex) {throw new RuntimeException(ex);}
            }
        });

        fName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {SAVEButton.doClick();}});
        lName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {SAVEButton.doClick();}});
        usernameInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {SAVEButton.doClick();}});
        passwordInput1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {SAVEButton.doClick();}});
        passwordInput2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {SAVEButton.doClick();}});
    }
    public void alert(String data){JOptionPane.showMessageDialog(this, data);} //ALERT LIKE IN JAVASCRIPT
}
