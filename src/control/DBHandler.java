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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import model.CalendarItem;
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
    private ArrayList<Employee> employeeList;
    private boolean connected;

    public DBHandler(Connection conn, Statement stmt, String user, String pw, String host, String port, String dbName) {
        this.conn = conn;
        this.stmt = stmt;
        this.user = user;
        this.pw = pw;
        this.host = host;
        this.port = port;
        this.dbName = dbName;

    }

    public DBHandler(String user, String pw, String host, String port, String dbName) throws SQLException {
        this.user = user;
        this.pw = pw;
        this.host = host;
        this.port = port;
        this.dbName = dbName;
        connect();


    }

    private boolean connect() throws SQLException {

        connected = true;
        String connString = "jdbc:mysql://" + host + ":" + port + "/" + dbName;

        try {
            conn = (Connection) DriverManager.getConnection(connString, user, pw);
            stmt = (Statement) conn.createStatement();

        } catch (Exception ex) {
            connected = false;
        }
        System.out.println("Connected " + connected);
        return connected;

    }

    public boolean correctPassword(String username, String password) throws SQLException {
        boolean correctPassword = false;
        String query = "SELECT COUNT(*) as total FROM employee WHERE Username = '" + username + "' AND Password = '" + password + "'";
        System.out.println(query);
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            if (rs.getInt("total") == 1) {
                correctPassword = true;
            }
        }
        rs.close();
        return correctPassword;
    }

    public Employee retrieveUser(String username) throws SQLException {
        Employee user = null;
        String query = "SELECT * FROM employee WHERE Username = username;";

        ResultSet rs = stmt.executeQuery(query);
        if (rs.next()) {
            String name = rs.getString("Fullname");
            String pass = rs.getString("password");
            int accessLevel = rs.getInt("accessLevel");

            user = new Employee(username, name, pass, accessLevel);

        }

        return user;

    }

    public boolean insertEmployee(Employee employee) throws SQLException {
        boolean inserted = false;

        String query = "Insert into employee (Username,Fullname, Accesslevel,Password)"
                + "Values ('" + employee.getUsername() + "', '" + employee.getName() + "','" + employee.getAccessLevel() + "','" + employee.getPassword() + "')";

        System.out.println(query);


        int result = stmt.executeUpdate(query);
        if (result != 0) {
            inserted = true;
        }
        return inserted;

    }

    public boolean updateEmployee(Employee employee) throws SQLException {
        boolean updated = false;

        String query = "Update employee SET Password = '" + employee.getPassword() + "', Fullname = '" + employee.getName() + "', Accesslevel = '" + employee.getAccessLevel() + "' WHERE username = '" + employee.getUsername() + "'";

        int result = stmt.executeUpdate(query);
        if (result != 0) {
            updated = true;
        }
        return updated;

    }

    public ArrayList<Employee> retrieveAllUsers() throws SQLException {
        employeeList = new ArrayList<>();
        String query = "SELECT * FROM employee";
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            String name = rs.getString("Fullname");
            String username = rs.getString("Username");
            String password = rs.getString("Password");
            int al = rs.getInt("Accesslevel");

            Employee user = new Employee(username, name, password, al);
            System.out.println(name + username);

            employeeList.add(user);
        }
        return employeeList;

    }

    /**
     *
     * @param month måneden start på 0 (januar 0 osv.)
     * @param year som normalt (2000 er 2000 osv.)
     * @return returnere en liste med de calendarItems der er i den pågældende
     * måned.
     * @throws SQLException
     */
    public ArrayList<CalendarItem> retriveCalendarItems(int month, int year) throws SQLException {
        // vi starter op med at hente calendar instancen for at kunne arbejde med det.
        Calendar cal = Calendar.getInstance();
        // Vi starter med at sætte kalenderen til den måned og år vi ønsker at finde på.
        cal.set(year, month, cal.getActualMinimum(Calendar.DAY_OF_MONTH), 0, 0, 0);
        Date start = cal.getTime();
        // Vi finder den sidste dag på måneden ved at finde calendarens maximum af dage og sætter min osv til maks.
        cal.set(year, month, cal.getActualMaximum(Calendar.DAY_OF_MONTH), 23, 59, 59);
        Date slut = cal.getTime();

        String query = "SELECT * FROM worksheet WHERE "
                + "TimeOfJob between '" + dateFormatter("YYYY-MM-dd HH:mm:ss", start)
                + "' AND '" + dateFormatter("YYYY-MM-dd HH:mm:ss", slut) + "'";
      
        ArrayList<CalendarItem> calendarItemList = retriveCalendarItems(query);

        return calendarItemList;
    }

    public ArrayList<CalendarItem> retrieveAllCalendarItems() throws SQLException {

        String query = "SELECT * FROM worksheet";
        ArrayList<CalendarItem> calendarItemList = retriveCalendarItems(query);
        return calendarItemList;

    }

    /**
     * Lokal hjælpemetode til at hente CalendarItems ud med
     *
     * @param query Den query der skal sendes til databasen.
     * @return returnere et array af CalendarItems
     * @throws SQLException
     */
    private ArrayList<CalendarItem> retriveCalendarItems(String query) throws SQLException {
        ArrayList<CalendarItem> calendarItemList = new ArrayList<>();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            int orderId = rs.getInt("OrdreNr");
            String customerName = rs.getString("CustomerName");
            String customerAddress = rs.getString("CustomerAddress");
            String customerPhone = rs.getString("CustomerPhone");
            Date timeOfJob = rs.getDate("timeOfJob");
            String jobDescription = rs.getString("JobDescription");
            CalendarItem calendarItem = new CalendarItem(orderId,timeOfJob, customerName, customerAddress, customerPhone, jobDescription);
            
            calendarItemList.add(calendarItem);
        }
        return calendarItemList;
    }

    private String dateFormatter(String format, Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String rtnDate = sdf.format(date);

        return rtnDate;
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

    public boolean isConnected() {
        return connected;
    }
}
