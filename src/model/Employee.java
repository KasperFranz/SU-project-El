package model;

public class Employee {
    private int userID;
    private String username;
    private String name;
    private String password;
    private int accessLevel;
/*
 Access Level er 0-2
 * 0 = ansat
 * 1 = Overmontør
 * 2 = Admin
 
 */
    public Employee(int userID,String username, String name, String password, int accessLevel) {
        this.userID = userID;
        this.username = username;
        this.name = name;
        this.password = password;
        this.accessLevel = accessLevel;
    }

    public Employee(String username, String name, String password, int accessLevel) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.accessLevel = accessLevel;
        userID = -1;
    }
             

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }

public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
    
    @Override
    public String toString() {
        return "("+userID +") "+ username + ", " + name + ", " + accessLevel;
    }

   
    
    
}
