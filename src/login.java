import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class login extends JFrame {
    private final String username = "admin";
    private final String password = "admin";

    private JTextField textField1;
    private JTextField textField2;
    private JButton enterButton;
    private JPanel swing_test_Panel; //I should give name for Jpanel to call in setContentPane

    public login(){
        setContentPane(swing_test_Panel); //this is how to call Jpanel
        setTitle("CIICC Apps");
        setDefaultCloseOperation(EXIT_ON_CLOSE); //WHEN I CLICK CLOSE, THE PROGRAM WILL BE STOP AND NOT RUNNING IN BACKGROUND PROCESSING
        setSize(400,400);
        setLocationRelativeTo(null);
        setVisible(true);

        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = textField1.getText(); //this is how to get data in textfield
                String password = textField2.getText(); //this is how to get data in textfield

                if (username.equals(login.this.username) && password.equals(login.this.password))
                {
                    setVisible(false); // this is how to hide form
                    new Simple_Calculator(); // open new form
                }
                else
                {
                    alert("INVALID USERNAME OR PASSWORD!");
                    textField1.setText("");
                    textField2.setText("");
                }
            }
        });

        textField1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterButton.doClick();
            }
        });
        textField2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterButton.doClick();
            }
        });
    }
    public void alert(String data){JOptionPane.showMessageDialog(login.this, data);} //ALERT LIKE IN JAVA
}