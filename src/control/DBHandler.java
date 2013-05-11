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
import model.Worksheet;
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

    public boolean isUserCorrectPassword(String username, String password) throws SQLException {
        boolean correctPassword = false;
        String query = "SELECT COUNT(*) as total FROM employee WHERE Username = \"" + username + "\" AND Password = \"" + password + "\"";
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

    public Employee retrieveEmployee(String username) throws SQLException {
        Employee user = null;
        String query = "SELECT * FROM employee WHERE Username = \"" + username + "\"";

        ResultSet rs = stmt.executeQuery(query);
        if (rs.next()) {
            int userID = rs.getInt("UserID");
            String name = rs.getString("Fullname");
            String pass = rs.getString("password");
            int accessLevel = rs.getInt("accessLevel");

            user = new Employee(userID, username, name, pass, accessLevel);

        }

        return user;

    }

    public Employee retrieveEmployee(int userID) throws SQLException {
        Employee user = null;
        String query = "SELECT * FROM employee WHERE UserID = \"" + userID + "\"";

        ResultSet rs = stmt.executeQuery(query);
        if (rs.next()) {
            String name = rs.getString("Fullname");
            String pass = rs.getString("password");
            String username = rs.getString("Username");
            int accessLevel = rs.getInt("accessLevel");

            user = new Employee(userID, username, name, pass, accessLevel);

        }

        return user;

    }

    public boolean insertEmployee(Employee employee) throws SQLException {
        boolean inserted = false;

        String query = "Insert into employee (Username,Fullname, Accesslevel,Password)"
                + "Values (\"" + employee.getUsername() + "\", \"" + employee.getName() + "\",\"" + employee.getAccessLevel() + "\",\"" + employee.getPassword() + "\")";

        System.out.println(query);


        int result = stmt.executeUpdate(query);
        if (result != 0) {
            inserted = true;
        }
        return inserted;

    }

    /**
     * Opdater en employee udfra hans userID.
     *
     * @param employee den employee der skal opdateres.
     * @return retrunere true/false an på om det er opdateret eller ej, dette
     * burde kun give false hvis den ikke kan fidne brugeren.
     * @throws SQLException
     */
    public boolean updateEmployee(Employee employee) throws SQLException {
        boolean updated = false;

        String query = "Update employee SET Password = \"" + employee.getPassword() + "\", Username = \"" + employee.getUsername() + "\", Fullname = \"" + employee.getName() + "\", Accesslevel = \"" + employee.getAccessLevel() + "\" WHERE UserID = \"" + employee.getUserID() + "\"";

        int result = stmt.executeUpdate(query);
        if (result != 0) {
            updated = true;
        }
        return updated;

    }

    /**
     *
     * @param month måneden start på 0 (januar 0 osv.)
     * @param year som normalt (2000 er 2000 osv.)
     * @return returnere en liste med de calendarItems der er i den pågældende
     * måned.
     * @throws SQLException
     */
    public ArrayList<Worksheet> retriveWorksheets(int month, int year) throws SQLException {
        // vi starter op med at hente calendar instancen for at kunne arbejde med det.
        Calendar cal = Calendar.getInstance();
        // Vi starter med at sætte kalenderen til den måned og år vi ønsker at finde på.
        cal.set(year, month, cal.getActualMinimum(Calendar.DAY_OF_MONTH), 0, 0, 0);
        Date start = cal.getTime();
        // Vi finder den sidste dag på måneden ved at finde calendarens maximum af dage og sætter min osv til maks.
        cal.set(year, month, cal.getActualMaximum(Calendar.DAY_OF_MONTH), 23, 59, 59);
        Date slut = cal.getTime();

        String query = "SELECT * FROM worksheet WHERE "
                + "TimeOfJob between \"" + dateFormatter("YYYY-MM-dd HH:mm:ss", start)
                + "\" AND \"" + dateFormatter("YYYY-MM-dd HH:mm:ss", slut) + "\"";

        ArrayList<Worksheet> calendarItemList = retriveWorksheets(query,true);

        return calendarItemList;
    }

    public ArrayList<Worksheet> retrieveAllWorksheets() throws SQLException {

        String query = "SELECT * FROM worksheet";
        ArrayList<Worksheet> calendarItemList = retriveWorksheets(query, true);
        return calendarItemList;

    }

    /**
     * Lokal hjælpemetode til at hente CalendarItems/Worksheets ud med
     *
     * @param query Den query der skal sendes til databasen.
     * @return returnere et array af CalendarItems
     * @throws SQLException
     */
    private ArrayList<Worksheet> retriveWorksheets(String query, boolean getUserInfo) throws SQLException {
        ArrayList<Worksheet> calendarItemList = new ArrayList<>();
        ArrayList<Integer> tempEmployee = new ArrayList<>();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            int orderId = rs.getInt("OrdreNr");
            String customerName = rs.getString("CustomerName");
            String customerAddress = rs.getString("CustomerAddress");
            String customerPhone = rs.getString("CustomerPhone");
            Date timeOfJob = rs.getDate("timeOfJob");
            String jobDescription = rs.getString("JobDescription");
            String comment = rs.getString("Comments");
            tempEmployee.add(rs.getInt("Employee"));
            Worksheet calendarItem = new Worksheet(orderId, timeOfJob, customerName, customerAddress, customerPhone, jobDescription, comment);

            calendarItemList.add(calendarItem);
        }
        rs.close();
        if (getUserInfo) {
            for (int i = 0; i < tempEmployee.size(); i++) {
                if (tempEmployee.get(i) != null) {
                    calendarItemList.get(i).setEmployee(retrieveEmployee(tempEmployee.get(i)));
                    System.out.println("EmPLOYEE:"+tempEmployee.get(i));
                }
            }
        }
        return calendarItemList;
    }

    /**
     * Indsæt et worksheet udfra et Worksheet
     *
     * @param item det calendarItem objekt vi ønsker skal indsættes i databasen.
     * @return returnerer true/false an på om det er indsat i databasen.
     * @throws SQLException
     */
    public boolean insertWorksheet(Worksheet item) throws SQLException {
        boolean inserted = false;

        String query = "Insert into Worksheet "
                + "(CustomerName,CustomerAddress, CustomerPhone,timeOfJob,JobDescription)"
                + "Values "
                + "(\"" + item.getCustomerName() + "\", \"" + item.getCustomerAdress() + "\",\"" + item.getCustomerPhone() + "\",\"" + dateFormatter("YYYY-MM-dd HH:mm:ss", item.getTimeOfJob()) + "\",\"" + item.getJobDescription() + "\")";
        int result = stmt.executeUpdate(query);
        if (result != 0) {
            inserted = true;
        }
        return inserted;

    }

    /**
     * *
     * Opdater et worksheet med data (opdatere udfra orderID).
     *
     * @param item det Worksheet der skal opdateres (skal indeholde et ID der er
     * større end 0);
     * @return returnere om det er opdateret eller ej.
     * @throws SQLException
     */
    public boolean updateWorksheet(Worksheet item) throws SQLException {
        boolean updated = false;
        if (item.getOrderId() > 0) {
            String query = "Update worksheet SET CustomerName = \"" + item.getCustomerName() + "\", CustomerAddress = \"" + item.getCustomerAdress() + "\", CustomerPhone = \"" + item.getCustomerPhone() + "\", TimeOfJob = \"" + dateFormatter("YYYY-MM-dd HH:mm:ss", item.getTimeOfJob()) + "\",Jobdescription = \"" + item.getJobDescription() + "\", Comments = \"" + item.getComment() + "\", Employee = \"" + item.getEmployee().getUserID() + "\" WHERE OrdreNr = \"" + item.getOrderId() + "\"";
            System.out.println(query);
            int result = stmt.executeUpdate(query);
            if (result != 0) {
                updated = true;
            }
        }

        return updated;

    }

    public ArrayList<Employee> retrieveAllEmployees() throws SQLException {

        String query = "SELECT * FROM employee";
        ArrayList<Employee> employeeList = retriveEmployees(query);
        return employeeList;
    }

    private ArrayList<Employee> retriveEmployees(String query) throws SQLException {
        ArrayList<Employee> employeeList = new ArrayList<>();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            int userID = rs.getInt("UserID");
            String username = rs.getString("Username");
            String employeeName = rs.getString("Fullname");
            int employeeAccesslevel = rs.getInt("Accesslevel");
            String emplyeePassword = rs.getString("Password");
            Employee employee = new Employee(userID, username, employeeName, emplyeePassword, employeeAccesslevel);

            employeeList.add(employee);
        }
        return employeeList;
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

    public void setUser(String user) {
        this.user = user;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public boolean isConnected() {
        return connected;
    }

    @Override
    public String toString() {
        return "DBHANDLER connected: " + connected;
    }

    public ArrayList<Worksheet> retrieveWorksheets(Employee employee) throws SQLException {

        String query = "SELECT * FROM worksheet WHERE Employee = " + employee.getUserID();
        ArrayList<Worksheet> calendarItemList = retriveWorksheets(query,false);
        for (int i = 0; i < calendarItemList.size(); i++) {
            calendarItemList.get(i).setEmployee(employee);
        }
        System.out.println("I: "+calendarItemList.size() +" "+ query);
        return calendarItemList;

    }
}
