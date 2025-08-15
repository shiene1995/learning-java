import javax.swing.*;
import java.sql.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws SQLException {

        //new Simple_Calculator();
        new login();

        echo(33); //I shorten the System.out.print();
        echo("Hello");
        echo(2.0);

        mySQL mySQLConnection = new mySQL("db_notary");
        ResultSet rs = mySQLConnection.selectSQL("","","tb_testing","");
        //ResultSet rs = mySQLConnection.searchSQL("loss", new String[] {"doc_name","doc_used_in"},"tb_documents","");

        while (rs.next()){
            int id = rs.getInt("id");
            String Fname = rs.getString("Fname");
            String Lname = rs.getString("Lname");

            echo("ID: " + id + ", First name : " + Fname + ", Last name : " + Lname);
        }

        //int rs1 = mySQLConnection.updateSQL(new String[]{"Salvador"},new String[] {"Lname"},"tb_testing","2");
        //int rs1 = mySQLConnection.insertSQL(new String[]{"Sam","Salvador"},new String[] {"Fname","Lname"},"tb_testing");
        //int rs1 = mySQLConnection.deleteSQL("4","tb_testing");

        //if (rs1 > 0)
        //{echo("SUCCESS");}

        mySQLConnection.closeConnection(); //always closed the connection!

    }

    //Convert Print-Line to echo methods!
    static void echo(int data){System.out.println(data);}
    static void echo(String data){System.out.println(data);}
    static void echo(double data){System.out.println(data);}
}

