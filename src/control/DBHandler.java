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
import util.PlanningDatePanel;

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
    public ArrayList<Worksheet> retrieveWorksheets(int week, int year) throws SQLException {
        // vi starter op med at hente calendar instancen for at kunne arbejde med det.
        Calendar cal = Calendar.getInstance();
        // Vi starter med at sætte kalenderen til den måned og år vi ønsker at finde på.
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.HOUR, 00);
        cal.set(Calendar.MINUTE, 00);
        cal.set(Calendar.SECOND, 00);
        cal.set(Calendar.WEEK_OF_YEAR, week);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        Date start = cal.getTime();
        // Vi finder den sidste dag på måneden ved at finde calendarens maximum af dage og sætter min osv til maks.
        cal.add(Calendar.DAY_OF_MONTH, 7);
        // Vi finder den sidste dag på måneden ved at finde calendarens maximum af dage og sætter min osv til maks.
        Date slut = cal.getTime();

        String query = "SELECT * FROM worksheet WHERE "
                + "TimeOfJob between \"" + dateFormatter("YYYY-MM-dd HH:mm:ss", start)
                + "\" AND \"" + dateFormatter("YYYY-MM-dd HH:mm:ss", slut) + "\"";
        System.out.println(query);
        ArrayList<Worksheet> calendarItemList = retriveWorksheets(query, true);

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
        ArrayList<Worksheet> Worksheets = new ArrayList<>();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            int orderId = rs.getInt("OrdreNr");
            String customerName = rs.getString("CustomerName");
            String customerAddress = rs.getString("CustomerAddress");
            String customerPhone = rs.getString("CustomerPhone");
            Date timeOfJob = rs.getDate("timeOfJob");
            String jobDescription = rs.getString("JobDescription");
            String comment = rs.getString("Comments");
            Worksheet calendarItem = new Worksheet(orderId, timeOfJob, customerName, customerAddress, customerPhone, jobDescription, comment);

            Worksheets.add(calendarItem);
        }
        rs.close();
        if (getUserInfo) {
            for (int i = 0; i < Worksheets.size(); i++) {
                assignEmployeeOnWorksheet(Worksheets.get(i));
            }
        }
        return Worksheets;
    }

    /**
     * Indsæt et worksheet udfra et Worksheet
     *
     * @param worksheet det calendarItem objekt vi ønsker skal indsættes i
     * databasen.
     * @return returnerer true/false an på om det er indsat i databasen.
     * @throws SQLException
     */
    public boolean insertWorksheet(Worksheet worksheet) throws SQLException {
        boolean inserted = false;

        String query = "Insert into worksheet "
                + "(CustomerName,CustomerAddress, CustomerPhone,timeOfJob,JobDescription)"
                + "Values "
                + "(\"" + worksheet.getCustomerName() + "\", \"" + worksheet.getCustomerAdress() + "\",\"" + worksheet.getCustomerPhone() + "\",\"" + dateFormatter("YYYY-MM-dd HH:mm:ss", worksheet.getTimeOfJob()) + "\",\"" + worksheet.getJobDescription() + "\")";
        stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = stmt.getGeneratedKeys();
        int result = 0;
        if (rs.next()) {
            result = rs.getInt(1);
        }

        if (result != 0) {
            inserted = true;
            System.out.println("RESULT::::" + result);
            worksheet.setOrderId(result);
            assignEmployeeOnWorksheet(worksheet);
        }
        return inserted;

    }

    /**
     * *
     * Opdater et worksheet med data (opdatere udfra orderID).
     *
     * @param worksheet det Worksheet der skal opdateres (skal indeholde et ID
     * der er større end 0);
     * @return returnere om det er opdateret eller ej.
     * @throws SQLException
     */
    public boolean updateWorksheet(Worksheet worksheet) throws SQLException {
        boolean updated = false;
        if (worksheet.getOrderId() > 0) {
            String query = "Update worksheet SET CustomerName = \"" + worksheet.getCustomerName() + "\", CustomerAddress = \"" + worksheet.getCustomerAdress() + "\", CustomerPhone = \"" + worksheet.getCustomerPhone() + "\", TimeOfJob = \"" + dateFormatter("YYYY-MM-dd HH:mm:ss", worksheet.getTimeOfJob()) + "\",Jobdescription = \"" + worksheet.getJobDescription() + "\", Comments = \"" + worksheet.getComment() + "\" WHERE OrdreNr = \"" + worksheet.getOrderId() + "\"";
            System.out.println(query);
            updateAssignedEmployeesOnWorksheet(worksheet);
            int result = stmt.executeUpdate(query);
            if (result != 0) {
                updated = true;
            }
        }

        return updated;

    }

    private void updateAssignedEmployeesOnWorksheet(Worksheet worksheet) throws SQLException {
        if (worksheet.getOrderId() != 0) {
            String query = "DELETE FROM onWorksheet WHERE OrdreNr = " + worksheet.getOrderId();
            System.out.println(query);
            stmt.executeUpdate(query);


            for (int i = 0; i < worksheet.getEmployees().size(); i++) {
                query = "Insert into onWorksheet "
                        + "(OrdreNr,Employee)"
                        + "Values "
                        + "(\"" + worksheet.getOrderId() + "\", \"" + worksheet.getEmployees().get(i).getUserID() + "\")";
                stmt.execute(query);

            }
        }


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
            System.out.println("EMPLOYEE ADDED:"+username);
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

        String query = "SELECT * FROM (worksheet join onWorksheet on worksheet.OrdreNR = onWorksheet.Employee) WHERE Employee = " + employee.getUserID();
        ArrayList<Worksheet> calendarItemList = retriveWorksheets(query, false);
        for (int i = 0; i < calendarItemList.size(); i++) {
            assignEmployeeOnWorksheet(calendarItemList.get(i));
        }
        System.out.println("I: " + calendarItemList.size() + " " + query);
        return calendarItemList;

    }

    private void assignEmployeeOnWorksheet(Worksheet worksheet) throws SQLException {

        String query = "SELECT * FROM (employee join onWorksheet on employee.UserID = onWorksheet.Employee) WHERE onWorksheet.OrdreNr = " + worksheet.getOrderId();
        worksheet.setEmployee(retriveEmployees(query));

    }

    public void deleteWorksheet(Worksheet worksheet) throws SQLException{
        String query = "DELETE FROM onWorksheet WHERE OrdreNr = " + worksheet.getOrderId();
            System.out.println(query);
            stmt.executeUpdate(query);
            query = "DELETE FROM worksheet WHERE OrdreNr = "+worksheet.getOrderId();
            stmt.executeUpdate(query);
    }
}
