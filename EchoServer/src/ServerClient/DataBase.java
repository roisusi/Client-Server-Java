package ServerClient;

import java.sql.*;

public class DataBase {

    private Connection con;



    //                          //
    //  Connection To DataBase  //
    //                          //
    public void connect() throws Exception {
        con = DatabaseConnection.getInstance().getConnection();
    }
    public void disconnect() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException throwable) {

                System.out.println("Connection Closed");
            }
        }
    }
    public Connection getCon() {
        return con;
    }


}
