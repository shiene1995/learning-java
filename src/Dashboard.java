import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Dashboard extends JFrame {
    private JPanel MainPanel;
    private JLabel logoLabel;
    private JButton CALCULATORButton;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton LOGOUTButton;
    private JTextField SEARCHFIELDTextField;
    private JButton searchButton;
    private JButton ADDButton;
    private JButton UPDATEButton;
    private JButton DELETEButton;
    private JTable table1;
    private JScrollPane TableScrollPane;
    static ResultSet rs;

    private ArrayList<Object[]> dataList = new ArrayList<>();
    private DefaultTableModel model = new DefaultTableModel();

    public Dashboard() {
        setContentPane(MainPanel); //this is how to call Jpanel
        setTitle("Dashboard: CIICC");
        setDefaultCloseOperation(EXIT_ON_CLOSE); //WHEN I CLICK CLOSE, THE PROGRAM WILL BE STOP AND NOT RUNNING IN BACKGROUND PROCESSING
        setResizable(false);
        setSize(1000,600);
        setLocationRelativeTo(null);
        setVisible(true);
        ImageIcon image = new ImageIcon("img/icon.png");
        setIconImage(image.getImage()); //SET WINDOWS ICON

        TableScrollPane.setSize(580, 506);
        table1.setRowHeight(25);

        logoLabel.setIcon(image);

        //refresh(); //refresh the showDataTable
        showDataTable("");
        //addData(new Object[]{"0", "First Name", "Last Name", "Username"});

        CALCULATORButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Simple_Calculator();
            }
        });
        LOGOUTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Login();
            }
        });

        ADDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddModal(Dashboard.this);
            }
        });

        UPDATEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table1.getSelectedRow() > -1) {
                    new UpdateModal((int) table1.getValueAt(table1.getSelectedRow(), 0), table1.getValueAt(table1.getSelectedRow(), 1).toString(), table1.getValueAt(table1.getSelectedRow(), 2).toString(), Dashboard.this);
                } else { alert("PLEASE SELECT IN TABLE!"); }
            }
        });
        DELETEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (table1.getSelectedRow() > 0) {
                        if (alertDelete("Are you sure you to delete this permanently?") == JOptionPane.YES_OPTION) {
                            System.out.println("YES");
                            MySQL mySQLConnection = new MySQL("db_ciicc");
                            int rs1 = mySQLConnection.deleteSQL(String.valueOf(table1.getValueAt(table1.getSelectedRow(), 0)), "tb_account");
                            if (rs1 > 0) {
                                System.out.println("SUCCESS");
                                mySQLConnection.close();
                                showDataTable("");
                            }
                        }
                    } else { alert("PLEASE SELECT IN TABLE!"); }
                } catch (SQLException ex) {throw new RuntimeException(ex);}
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDataTable(SEARCHFIELDTextField.getText());
            }
        });
        SEARCHFIELDTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchButton.doClick();
            }
        });
    }

    public void addData(Object[] data){

        // Assuming dataList is an ArrayList<Object[]>
        //dataList.add(new Object[]{5, "Fname", "Lname", "Username"});
        this.dataList.add(data);
        // This line adds only the LATEST entry as a new row to the table model
        this.model.addRow(dataList.getLast());

    }

    public void showDataTable(String searchData) {
        try {
            MySQL mySQLConnection = new MySQL("db_ciicc"); //DB Connection

            if (searchData.isEmpty()) { rs = mySQLConnection.selectSQL("tb_account");}
            else { rs = mySQLConnection.searchSQL(searchData, new String[] {"id","Fname","Lname", "Username"},"tb_account","");}

            // Define column names OR headers
            String[] columnNames = {"ID", "FIRST NAME", "LAST NAME", "USERNAME"};

            while (rs.next()) {
                //int id = rs.getInt("id");
                //String Fname = rs.getString("Fname");
                //String Lname = rs.getString("Lname");
                //String Username = rs.getString("Username");

                // Add the new data using the add() method
                dataList.add(new Object[]{rs.getInt("id"), rs.getString("Fname"), rs.getString("Lname"), rs.getString("Username")});
            }

            // To convert it back to a 2D array if needed:
            Object[][] data = dataList.toArray(new Object[0][]);

            model.setDataVector(data, columnNames);
            table1.setModel(model);

            rs.close();
            mySQLConnection.close();
            dataList.clear();

        } catch (SQLException ex) {throw new RuntimeException(ex);}
    }

    public void alert(String data){JOptionPane.showMessageDialog(this, data);} //ALERT LIKE IN JAVASCRIPT
    public int alertDelete(String data){return JOptionPane.showConfirmDialog(this, data, "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);} //ALERT LIKE IN JAVASCRIPT
}
