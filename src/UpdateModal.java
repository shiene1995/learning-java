import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class UpdateModal extends JFrame {

    private JPanel UpdateModalPanel;
    private JTextField FnameInput;
    private JTextField LnameInput;
    private JButton UPDATEButton;
    static String FName, LName;
    static int id;
    private Dashboard dashboard;

    public UpdateModal(Dashboard dashboard){
        setContentPane(UpdateModalPanel); //this is how to call Jpanel
        setTitle("Add");
        setResizable(false);
        setSize(400,350);
        setLocationRelativeTo(null);
        setVisible(true);
        ImageIcon image = new ImageIcon("img/icon.png");
        setIconImage(image.getImage()); //SET WINDOWS ICON
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        this.dashboard = dashboard;

        FnameInput.setText(FName);
        LnameInput.setText(LName);

        UPDATEButton.addActionListener(new ActionListener() { @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (FnameInput.getText().isEmpty() || LnameInput.getText().isEmpty())
                    {
                        alert("PLEASE FILL ALL THE BLANK!");
                    }
                    else
                    {
                        MySQL mySQLConnection = new MySQL("db_ciicc");

                        int rs1 = mySQLConnection.updateSQL(new String[]{FnameInput.getText(), LnameInput.getText()},new String[] {"Fname", "Lname"},"tb_account",String.valueOf(UpdateModal.id));
                        if (rs1 > 0) {
                            alert("UPDATE SUCCESS!");
                            mySQLConnection.close(); //Closing MySQL Connection and stmt
                            dispose();
                            dashboard.showDataTable("");
                        }
                    }
                } catch (SQLException ex) {throw new RuntimeException(ex);}
            }});
    }

    public UpdateModal(int id, String Fname, String Lname, Dashboard dashboard){
        FName = Fname;
        LName = Lname;
        UpdateModal.id = id;
        System.out.println("ID : "+id + " " + Fname + " "+ Lname);
        new UpdateModal(dashboard);
    }
    public void alert(String data){JOptionPane.showMessageDialog(this, data);} //ALERT LIKE IN JAVASCRIPT
}
