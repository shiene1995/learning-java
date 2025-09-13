public class Main {
    public static void main(String[] args) throws Exception {

        //new Dashboard();
        new Login();

        //echo(33); //I shorten the System.out.print();
        //echo("Hello");
        //echo(2.0);

        //MySQL mySQLConnection = new MySQL("db_ciicc");
        //ResultSet rs = mySQLConnection.selectSQL("","","tb_account","");
        //ResultSet rs = mySQLConnection.searchSQL("loss", new String[] {"doc_name","doc_used_in"},"tb_documents","");

        //while (rs.next()){
        //    echo("ID: " + rs.getInt("id") + ", First name : " + rs.getString("Fname") + ", Last name : " + rs.getString("Lname"));
        //}

        //int rs1 = mySQLConnection.updateSQL(new String[]{"sam1"},new String[] {"Password"},"tb_account","1");
        //int rs1 = mySQLConnection.insertSQL(new String[]{"Zyrinne","Salvador","admin","admin"},new String[] {"Fname","Lname","Username","Password"},"tb_account");
        //int rs1 = mySQLConnection.deleteSQL("11","tb_account");

        //if (rs1 > 0) {echo("SUCCESS");}

        //rs.close();
        //mySQLConnection.close(); //always closed the connection!

    }

    //Convert Print-Line to echo methods!
    static void echo(int data){System.out.println(data);}
    static void echo(String data){System.out.println(data);}
    static void echo(double data){System.out.println(data);}
}

