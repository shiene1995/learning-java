import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public Dashboard(){
        setContentPane(MainPanel); //this is how to call Jpanel
        setTitle("Dashboard: CIICC");
        setDefaultCloseOperation(EXIT_ON_CLOSE); //WHEN I CLICK CLOSE, THE PROGRAM WILL BE STOP AND NOT RUNNING IN BACKGROUND PROCESSING
        setResizable(false);
        setSize(1000,600);
        setLocationRelativeTo(null);
        setVisible(true);
        ImageIcon image = new ImageIcon("img/icon.png");
        setIconImage(image.getImage()); //SET WINDOWS ICON

        TableScrollPane.add(table1); //TESTING
        add(TableScrollPane); //TESTING

        logoLabel.setIcon(image);

        CALCULATORButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Simple_Calculator();
            }
        });
        LOGOUTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Login();
            }
        });
    }
}
