/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Employee;

/**
 *
 * @author Marc
 */
public class DBHandler {

    private Connection conn;
    private Statement stmt;
    private String user;
    private String pw;
    private String host;
    private String port;
    private String dbName;

    public DBHandler(Connection conn, Statement stmt, String user, String pw, String host, String port, String dbName) {
        this.conn = conn;
        this.stmt = stmt;
        this.user = user;
        this.pw = pw;
        this.host = host;
        this.port = port;
        this.dbName = dbName;

    }
    
    public DBHandler(){
        
    }

    private boolean connect() throws SQLException {

        boolean connected = true;
        String connString = "jdbc:mysql://" + host + ":" + port + "/" + dbName;

        try {
            conn = (Connection) DriverManager.getConnection(connString, user, pw);
            stmt = (Statement) conn.createStatement();
        } catch (Exception ex) {
            connected = false;
        }

        return connected;
    }

    public Employee retrieveUser(String username) throws SQLException {

        String query = "SELECT * FROM employee WHERE Username = username;";
        ResultSet rs = stmt.executeQuery(query);
        String name = rs.getString("Fullname");
        String pass = rs.getString("password");
        int accessLevel = rs.getInt("accessLevel");

        Employee user = new Employee(username, name, pass, accessLevel);

        return user;

    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public Statement getStmt() {
        return stmt;
    }

    public void setStmt(Statement stmt) {
        this.stmt = stmt;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
}
