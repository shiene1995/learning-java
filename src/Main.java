import javax.swing.*;
import java.sql.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {

        //new Login();
        //new Simple_Calculator();
        new Dashboard();

        echo(33); //I shorten the System.out.print();
        echo("Hello");
        echo(2.0);

        MySQL mySQLConnection = new MySQL("db_ciicc");
        ResultSet rs = mySQLConnection.selectSQL("1","id","tb_account","");
        //ResultSet rs = mySQLConnection.searchSQL("loss", new String[] {"doc_name","doc_used_in"},"tb_documents","");

        while (rs.next()){
            int id = rs.getInt("id");
            String Fname = rs.getString("Fname");
            String Lname = rs.getString("Lname");

            echo("ID: " + id + ", First name : " + Fname + ", Last name : " + Lname);
        }

        //int rs1 = mySQLConnection.updateSQL(new String[]{"sam1"},new String[] {"Password"},"tb_account","1");
        //int rs1 = mySQLConnection.insertSQL(new String[]{"Zyrinne","Salvador","admin","admin"},new String[] {"Fname","Lname","Username","Password"},"tb_account");
        //int rs1 = mySQLConnection.deleteSQL("11","tb_account");

        //if (rs1 > 0) {echo("SUCCESS");}

        rs.close();
        mySQLConnection.close(); //always closed the connection!

    }

    //Convert Print-Line to echo methods!
    static void echo(int data){System.out.println(data);}
    static void echo(String data){System.out.println(data);}
    static void echo(double data){System.out.println(data);}
}

