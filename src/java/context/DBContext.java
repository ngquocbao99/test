/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dell
 */
public class DBContext {

    public static Connection connection = null;

    public static Connection connect() {
        // Doi ten database cac thu o day
        String server = "localhost";
        String port = "1433";
        String database = "Tuesday";
        String user = "sa";
        String password = "sa";

        try {
            // Khai bao driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection("jdbc:sqlserver://"
                    + server + ":" + port + ";databaseName=" + database + ";"
                    + "user=" + user + ";password=" + password
                    + ";encrypt=true;" + "trustServerCertificate=true;");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }
}
