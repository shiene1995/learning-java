import java.sql.*;
import java.sql.DriverManager;

public class MySQL {
    String url, databaseUsername, databasePassword = "";
    Connection conn;
    PreparedStatement stmt;
    String query, whereSQL, tableBind = "";

    public MySQL(String host, String dbName, String dbUsername, String dbPassword) throws SQLException {
        url = "jdbc:mysql://"+host+"/" + dbName + "?useSSL=false&serverTimezone=Asia/Manila";
        databaseUsername = dbUsername;
        databasePassword = dbPassword;

        try {conn = DriverManager.getConnection(url, databaseUsername, databasePassword);}
        catch (SQLException e) {throw new RuntimeException(e);}
    }

    public MySQL(String dbName){
        url = "jdbc:mysql://localhost:3306/" + dbName + "?useSSL=false&serverTimezone=Asia/Manila";
        databaseUsername = "root";
        databasePassword = "";
        try {conn = DriverManager.getConnection(url, databaseUsername, databasePassword);}
        catch (SQLException e) {throw new RuntimeException(e);}
    }

    public ResultSet selectSQL(String data) throws SQLException {
        try{
            stmt = conn.prepareStatement(data);
            return stmt.executeQuery();
        } catch (SQLException e) {throw new RuntimeException(e);}
    }

    /*
    SELECT MANUAL:

        - Do not leave the table empty!
        - data and column should be single search only!
        - addQuery is used for ASCENDING/DESCENDING purposes!
        - ResultSet rs = mySQLConnection.selectSQL("","","tb_documents","");
        - while (rs.next()){
            int id = rs.getInt("id");
            String doc_name = rs.getString("doc_name");
            String doc_used_in = rs.getString("doc_used_in");

            echo("ID: " + id + ", Document name : " + doc_name + ", Used in : " + doc_used_in);
            }
     */
    public ResultSet selectSQL(String data, String column, String table, String addQuery) throws SQLException {

        if (data.isEmpty() && column.isEmpty() && addQuery.isEmpty())
        {
            stmt = conn.prepareStatement("SELECT * FROM "+ table);
            return stmt.executeQuery();
        }

        if (!data.isEmpty() && !column.isEmpty()) {query = "SELECT * FROM "+ table + " WHERE " + column + " = ?";}

        if (!addQuery.isEmpty()) {query += " " + addQuery;}

        if (!data.isEmpty() && !column.isEmpty())
        {
            stmt = conn.prepareStatement(query);
            stmt.setString(1, data);
        }
        else {stmt = conn.prepareStatement("SELECT * FROM " + table + query);}

        return stmt.executeQuery();
    }

    /*
        SEARCH USAGE:

            - ResultSet rs = mySQLConnection.searchSQL("loss", new String[] {"doc_name","doc_used_in"},"tb_documents","");
            - while(rs.next()){}
     */
    public ResultSet searchSQL(String data, String[] column, String table, String addQuery) throws SQLException {

        String[] whereClause = new String[column.length];

        if (data.isEmpty() && column.length == 0 && addQuery.isEmpty())
        {
            stmt = conn.prepareStatement("SELECT * FROM "+ table);
            return stmt.executeQuery();
        }

        if (!data.isEmpty() && column.length != 0)
        {
            for (int x=0; x < column.length; x++) {whereClause[x] = column[x] + " LIKE ?";}
            whereSQL = String.join(" OR ", whereClause);
        }

        if (!addQuery.isEmpty()) {whereSQL += " " + addQuery;}

        if (!data.isEmpty() && column.length != 0)
        {
            stmt = conn.prepareStatement("SELECT * FROM "+table+" WHERE "+whereSQL);
            for (int x=1; x <= column.length; x++) {stmt.setString(x, "%" + data + "%");}
        }

        return stmt.executeQuery();
    }

    /*
        INSERT USAGE:

            - int rs = mySQLConnection.insertSQL(new String[]{"Kenneth"},new String[] {"first_name"},"tb_login");
            - if (rs > 0){echo("UPDATE SUCCESS");}
    */
    public int insertSQL(String[] data, String[] column, String table) throws SQLException {
        String[] binding = new String[column.length];

        if (data.length != column.length) {
            throw new IllegalArgumentException("Data and column arrays must be the same length.");
        }

        for (int x=0; x < column.length; x++) {
            binding[x] = "?";
        }
        whereSQL = String.join(", ", binding);
        tableBind = String.join(", ", column);
        stmt = conn.prepareStatement("INSERT INTO "+table+" ("+tableBind+") VALUES ("+whereSQL+")");

        for (int x=1; x <= column.length; x++) {
            stmt.setString(x, data[x-1]);
        }

        return stmt.executeUpdate();
    }

    /*
        UPDATE USAGE:

            - int rs = mySQLConnection.updateSQL(new String[]{"Salvador"},new String[] {"last_name"},"tb_login","3");
            - if (rs > 0){echo("UPDATE SUCCESS");}
     */
    public int updateSQL(String[] data, String[] column, String table, String id) throws SQLException {
        String[] whereClause = new String[column.length];

        if (data.length != column.length) {
            throw new IllegalArgumentException("Data and column arrays must be of the same length.");
        }

        for (int x=0; x < column.length; x++) {
            whereClause[x] = column[x] + " = ?";
        }
        whereSQL = String.join(", ", whereClause);

        stmt = conn.prepareStatement("UPDATE "+table+" SET "+whereSQL+" WHERE id = ?");
        for (int x=1; x <= column.length; x++) {
            stmt.setString(x, data[x-1]);
        }
        stmt.setString(column.length+1, id);

        return stmt.executeUpdate();
    }

    /*
        DELETE USAGE:

            - int rs = mySQLConnection.deleteSQL("4","tb_login");
            - if (rs > 0){echo("DELETE SUCCESS");}
    */
    public int deleteSQL(String id, String table) throws SQLException {
        stmt = conn.prepareStatement("DELETE FROM "+table+" WHERE id = ?");
        stmt.setString(1, id);
        return stmt.executeUpdate();
    }

    public void close() {
        try {if (conn != null && !conn.isClosed()) { stmt.close(); conn.close(); }}
        catch (SQLException e) {throw new RuntimeException(e);}
    }

}
