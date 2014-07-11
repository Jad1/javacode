//NAME: Jad El-Houssami
//ID: w13651455

//REFERNECES:
//(1) Stack overflow., (2010). Difference between Statement and PreparedStatement. [online] Available at: <http://stackoverflow.com/questions/3271249/difference-between-statement-and-preparedstatement/3271420#3271420> [Accessed 20th October 2013]

package models;

import database.Database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author Jad
 */
public class Customer {
    private String sqlQuery;
    private PreparedStatement preppedstmt;
    private ResultSet resultSet;
    private ArrayList<String> names;
    private Database database;
    
    //Constructor to initialise the variables.
    public Customer(){
        database = new Database();
        names = new ArrayList<>();
    }
    
    /*Retrieve all the customer names from the customer table.
     * returns the full names of all customers or null if no customers are found.
     */
     public ArrayList getFullName(){
                try {

            //See references (1)
            //Query to be executed.
            sqlQuery = "SELECT full_name from APP.CUSTOMERS";
            
            //Tell the database that a table is about to be queried.
            preppedstmt = Database.connection.prepareStatement(sqlQuery, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            
            //Query the table.
           resultSet = preppedstmt.executeQuery();

           //Iterate through the ResultSet to add the names to the ArrayList.
           //Then return the ArrayList to the controller.
           while(resultSet.next()){
            names.add(resultSet.getString(1));
           }
           //Return the customer names in the customer table.
            return names;
        } 
        catch (SQLException sqle) {
             System.out.println(sqle.getMessage() + " (ArrayList getFullName() in Customer)");
        }

       //Return null because no names exist in the customer table.         
       return null;
    }
     
     /*Overloaded method just checks if the full name has already been used.
      * @param fullName the customer's full name
      * returns true if no match is found, or false if a match is found.
      */
     public boolean getFullName(String fullName){
         try {
             
            //See references (1)
            //Query to be executed.
            sqlQuery = "SELECT * from APP.CUSTOMERS WHERE full_name = ?";
            
            //Tell the database that a table is about to be queried.
            preppedstmt = Database.connection.prepareStatement(sqlQuery, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            
                     //The parameters to go into the SQL query.
            preppedstmt.setString(1, fullName);
            //Query the table.
           resultSet = preppedstmt.executeQuery();
           
           //If a name is found then return false as we don't want
           //to add this name again.
           if (resultSet.first()){
               return false;
           }
         } catch (Exception sqle) {
             System.out.println(sqle.getMessage() + " (boolean getName() in Customer)");
         }
         
         //Return true because the name doesn't exist in the customer table..
        return true;
     }
    
     /* Add the new customer's name to the customer table.
      * @param fullName the customer name to be added to the table.
      * returns true if name added successfully or false if it doesn't add successfully.
      */
    public boolean setFullName(String fullName){
        try {
            //Only add customer name if the name doesn't exist in the table.
            if (getFullName(fullName)){
            //See references (1)
            //Query to be executed.                
            sqlQuery = "INSERT INTO APP.CUSTOMERS(full_name) VALUES (?)";
            
            //Tell the database that a table is about to be queried.
            preppedstmt = Database.connection.prepareStatement(sqlQuery);
            
            //The parameters to go into the SQL query.
            preppedstmt.setString(1, fullName);
            
            //Query the table.
            preppedstmt.executeUpdate();
            
            //Return true because nothing went wrong.
            return true;      
            }
    }
        catch(SQLException sqle){
            //Output an error message to the console.
            System.out.println(sqle.getMessage() + " (setName() in Customer)");
        }
        //Return false because something went wrong.
        return false;
    }
    
    /* Select a customer ID based on the user's full name.
     * @param fullName the customer name that will be used to retrieve the ID.
     * return the customer ID if a match was found or zero if a match was not found.
     */
    public int getCustomerId(String fullName){
                     try {
            //See references (1)
            //Query to be executed.
            sqlQuery = "SELECT customer_id from APP.CUSTOMERS WHERE full_name = ?";
            
            //Tell the database that a table is about to be queried.
            preppedstmt = Database.connection.prepareStatement(sqlQuery, ResultSet.TYPE_SCROLL_INSENSITIVE,  ResultSet.CONCUR_READ_ONLY);
            
            //The parameter to go into the SQL query WHERE clause.
            preppedstmt.setString(1, fullName);
            
            //Query the table.
           resultSet = preppedstmt.executeQuery();

           //Move to the last row to check how many there are.
           //There should only be one row!
           resultSet.last();
           if (resultSet.getRow() == 1){
               //Return the customer ID if only one row exists.
           return resultSet.getInt(1);
           }

               return 0; //Zero can never be an ID so it symbolises a failed attempt to get an ID.
        } 
        catch (SQLException sqle) {
            //Output error message and return 0.
             System.out.println(sqle.getMessage() + " (getId() in Customer)");
             return 0;
        }
    }
    


}
