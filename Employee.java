//NAME: Jad El-Houssami
//ID: w13651455

//REFERNECES:
//(1) Stack overflow., (2010). Difference between Statement and PreparedStatement. [online] Available at: <http://stackoverflow.com/questions/3271249/difference-between-statement-and-preparedstatement/3271420#3271420> [Accessed 20th October 2013]
package models;

import database.Database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Jad
 */
public class Employee {
    private String sqlQuery;
    private PreparedStatement preppedstmt;
    private ResultSet resultSet;
    private Database database;
    
    public Employee(){        
        database = new Database(); 
    }
    
    /* Add new employee to the database.
     * @param fullName the employee's full name
     * @param username the employee's username
     * @param password the employee's password
     * return false if the username was not a duplicate, or true if it was a duplicate..
     */
    public boolean addEmployee(String fullName, String username, String password){
        try {
            //Query to be executed.
            sqlQuery = "SELECT * FROM APP.EMPLOYEES WHERE username = ?";
            
            //Tell database that table is about to be queried.
            preppedstmt = Database.connection.prepareStatement(sqlQuery, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            
            //The parameter to go into the SQL query.            
            preppedstmt.setString(1, username);
            
            //Query the table and store any results in the ResultSet.
            resultSet = preppedstmt.executeQuery();
             
            //If this condition returns false it means the query returned no rows 
            //(and by extension there are no employees with this username).
            if (!resultSet.first()){ 
            //See references (1)
            //Query to be executed.
            sqlQuery = "INSERT INTO APP.EMPLOYEES VALUES (?, ?, ?)";
            
            //Tell the database that a table is about to be queried.
            preppedstmt = Database.connection.prepareStatement(sqlQuery);
            
           //The parameters to go into the SQL query.
            preppedstmt.setString(1, fullName);
            preppedstmt.setString(2, username);
            preppedstmt.setString(3, password);
            
            //Query the table.
            preppedstmt.executeUpdate();
            
            //Return false because the username was not a duplicate.
            return false;
            }
        } 
        catch (SQLException sqle) {
             System.out.println(sqle.getMessage() + " (addEmployee() in Employee)");
        }
            return true;
    }
    

    /*Try to match the entered credentials to a record in the database.
     * @param username the entered username.
     * @param password the entered password.
     * returns true if a match was found, or false if a match wasn't found.
     */
    public boolean getCredentials(String username, String password){
        try {
            //Query to be executed.
            sqlQuery = "SELECT * FROM APP.EMPLOYEES WHERE username = ? AND password = ?";
            
            //Tell the database a table is about to be queried.
             preppedstmt = Database.connection.prepareStatement(sqlQuery, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); 
 
           //The parameters to go into the SQL query.
            preppedstmt.setString(1, username);
            preppedstmt.setString(2, password);
            
            //Query the table and store any results in the ResultSet.
           resultSet = preppedstmt.executeQuery();

           //If a row exists with the entered username and password (there should only be one!)
           //then return true.
            if (resultSet.first()){
                return true;
            }  
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage() + " (getCredentials() in Employee)");
        }
        //Return false because no match was found.
        return false;
    }
}
