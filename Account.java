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

public class Account {
    private ResultSet resultSet;
    private ArrayList<String> accountIds, accountTypes;
    private ArrayList<Double>balances, creditLimits;
    private Database database;
    private String sqlQuery;
    private PreparedStatement preppedstmt;
    
    //Constructor to initialise objects.
    public Account(){
        
        this.database = new Database();
        this.accountIds = new ArrayList<>();
        this.accountTypes = new ArrayList<>();
        this.balances = new ArrayList<>();
        this.creditLimits = new ArrayList<>();
    }
            /* Add a new account for a given customer.
             * @param customerId the customer's unique ID.
             * @param accountType the type of account.
             * @param balance the account's balance.
             * @param creditLimit the account's credit limit.
             * return true if addition was successful or false if a problem occured.
             */
            public boolean setAccount(int customerId, String accountType, String balance, String creditLimit){ 
                try{
                //See references (1)
            //Query to be executed.
            sqlQuery = "INSERT INTO APP.ACCOUNTS(customer_id, account_type, balance, credit_limit) VALUES (?,?,?,?)";
            
            //Tell the database that a table is about to be queried.
            preppedstmt = Database.connection.prepareStatement(sqlQuery);
            
           //The parameters to go into the SQL query.
            preppedstmt.setInt(1, customerId);
            preppedstmt.setString(2, accountType);
            preppedstmt.setDouble(3, Double.valueOf(balance));
            preppedstmt.setDouble(4, Double.valueOf(creditLimit));
            
            //Query the table.
            preppedstmt.executeUpdate();
            
            //Return true because nothing went wrong.
            return true;
            
    }
        catch(SQLException sqle){
            //Output an error message to the console.
            //Return false because something went wrong.
            System.out.println(sqle.getMessage() + " (setName() in Customer)");
           return false;
        } 
    }
            
            /*
             * Return the unique IDs of the accounts of a given customer
             * @param customerId the customer's ID.
             * return an ArrayList of account IDs or null if no accounts exist.
             */
            public ArrayList getAccountIds(int customerId){
           try {
            //Query to be executed.
            sqlQuery = "SELECT account_id FROM APP.ACCOUNTS WHERE customer_id = ?";
            
            //Tell database that table is about to be queried.
            preppedstmt = Database.connection.prepareStatement(sqlQuery, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            
            //The parameter to go into the SQL query.            
            preppedstmt.setInt(1, customerId);
            
            //Query the table and store any results in the ResultSet.
            resultSet = preppedstmt.executeQuery();
            
            //Add the data to the ArrayList
            while(resultSet.next()){
                accountIds.add(resultSet.getString(1));
            }
                //Return the account IDs if nothing goes wrong
                return accountIds;
                } catch (SQLException sqle) {
                   //Output an error message to the console.
                   //Return false because something went wrong.                    
                 System.out.println(sqle.getMessage() + " (getAccountIds() in Customer)");
                 return null;
                }
           
            }
            
            /*
             * Return the ccount types of the accounts of a given customer
             * @param customerId the customer's ID.
             * return an ArrayList of account types or null if no accounts exist.
             */
            public ArrayList getTypes(int customerId){
           try {
            //Query to be executed.
            sqlQuery = "SELECT account_type FROM APP.ACCOUNTS WHERE customer_id = ?";
            
            //Tell database that table is about to be queried.
            preppedstmt = Database.connection.prepareStatement(sqlQuery, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            
            //The parameter to go into the SQL query.            
            preppedstmt.setInt(1, customerId);
            
            //Query the table and store any results in the ResultSet.
            resultSet = preppedstmt.executeQuery();
            
            //Add the data to the ArrayList
            while(resultSet.next()){
                accountTypes.add(resultSet.getString(1));
            }
                //Return the account IDs if nothing goes wrong
                return accountTypes;
                } catch (SQLException sqle) {
                   //Output an error message to the console.
                   //Return false because something went wrong.                    
                 System.out.println(sqle.getMessage() + " (getTypes() in Customer)");
                 return null;
                }
            }
            
            /*
             * Return the balances of the accounts of a given customer
             * @param customerId the customer's ID.
             * return an ArrayList of balances or null if no accounts exist.
             */
            public ArrayList getBalances(int customerId){
           try {
            //Query to be executed.
            sqlQuery = "SELECT balance FROM APP.ACCOUNTS WHERE customer_id = ?";
            
            //Tell database that table is about to be queried.
            preppedstmt = Database.connection.prepareStatement(sqlQuery, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            
            //The parameter to go into the SQL query.            
            preppedstmt.setInt(1, customerId);
            
            //Query the table and store any results in the ResultSet.
            resultSet = preppedstmt.executeQuery();
            
            //Add the data to the ArrayList
            while(resultSet.next()){
                balances.add(resultSet.getDouble(1));
            }
                } catch (SQLException sqle) {
                 System.out.println(sqle.getMessage() + " (getBalances() in Customer)");
                }
                return balances;
            }
           
            /*
             * Return the credit limits of the accounts of a given customer
             * @param customerId the customer's ID.
             * return an ArrayList of credit limits or null if no accounts exist.
             */
            public ArrayList getCreditLimits(int customerId){
                try {
            //Query to be executed.
            sqlQuery = "SELECT credit_limit FROM APP.ACCOUNTS WHERE customer_id = ?";
            
            //Tell database that table is about to be queried.
            preppedstmt = Database.connection.prepareStatement(sqlQuery, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            
            //The parameter to go into the SQL query.            
            preppedstmt.setInt(1, customerId);
            
            //Query the table and store any results in the ResultSet.
            resultSet = preppedstmt.executeQuery();
         //Add the data to the ArrayList
            while(resultSet.next()){
                creditLimits.add(resultSet.getDouble(1));
            }
                } catch (SQLException sqle) {
                 System.out.println(sqle.getMessage() + " (getCreditLimits() in Account)");
                }
                return creditLimits;
            }
            
            /*
             * Update the user's account
             * @param customerId the customer's unique ID.
             * @param accountType the type of account.
             * @param balance the account's balance.
             * @param creditLimit the account's credit limit.
             */            
            public void updateAccount(String accountType, String balance, String creditLimit, int accountId){
                try {
            //Query to be executed.
            sqlQuery = "UPDATE APP.ACCOUNTS SET account_type = ?, balance = ?, credit_limit = ? WHERE account_id = ?";
            
            //Tell database that table is about to be queried.
            preppedstmt = Database.connection.prepareStatement(sqlQuery);
            
            //The parameter to go into the SQL query.            
            preppedstmt.setString(1, accountType);
            preppedstmt.setDouble(2, Double.valueOf(balance));
            preppedstmt.setDouble(3, Double.valueOf(creditLimit));
            preppedstmt.setInt(4, accountId);
            
            //Query the table.
             preppedstmt.executeUpdate();
            }
                catch(SQLException sqle){
                     System.out.println(sqle.getMessage() + " (updateAccount() in Account)");           
                }
            }
  
            /*Delete the selected account
             * @param accountId the unique ID of the account to be deleted.
             */
            public void deleteAccount(int accountId){
                try {
            //Query to be executed.
            sqlQuery = "DELETE FROM APP.ACCOUNTS WHERE account_id = ?";
            
            //Tell database that table is about to be queried.
            preppedstmt = Database.connection.prepareStatement(sqlQuery);
            
            //The parameter to go into the SQL query.            
            preppedstmt.setInt(1, accountId);
           
            //Query the table.
             preppedstmt.executeUpdate();                   
                } catch (SQLException sqle) {
                    System.out.println(sqle.getMessage() + " (deleteAccount() in Account)"); 
                }
            }
}
