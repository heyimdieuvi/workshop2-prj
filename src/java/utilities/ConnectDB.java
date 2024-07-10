/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.ServletContext;

/**
 *
 * @author ADMIN
 */
public class ConnectDB {

    private String hostName;
    private String instance;
    private String port;
    private String dbName;
    private String userName;
    private String password;

    public ConnectDB() {
        this.hostName = "localhost";
        this.instance = "DIEUVI";
        this.port = "1433";
        this.dbName = "ProductIntro";
        this.userName = "sa";
        this.password = "sa123456";
    }

    public ConnectDB(ServletContext sc) {
        this.hostName = sc.getInitParameter("hostAddress");
        this.instance = sc.getInitParameter("instance");
        this.dbName = sc.getInitParameter("dbName");
        this.port = sc.getInitParameter("dbPort");
        this.userName = sc.getInitParameter("userName");
        this.password = sc.getInitParameter("userPass");
    }

    private String urlString() {
        return String.format("jdbc:sqlserver://%s%s:%s;DatabaseName=%s;UserName=%s;Password=%s;",
                this.hostName, (this.instance.length() > 0 ? "\\" + this.instance : ""), this.port, this.dbName, this.userName, this.password);
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection connect = DriverManager.getConnection(urlString());
        return connect;
    }
}
