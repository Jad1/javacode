//NAME: Jad El-Houssami
//ID: w13651455

//REFERNECES:
//(1) Java Home and Learn. Databases and Java Forms. [online] Available at: <http://www.homeandlearn.co.uk/java/databases_and_java_forms.html> [Accessed 15th October 2013]
//Note: Date of publication could not be found.


//SQL reference (not in code but still used when creating tables):
//(1)Binary Tides, (2009). Create AutoIncrement column/field in Apache Derby. [online] Available at: <http://www.binarytides.com/create-autoincrement-columnfield-in-apache-derby/> [Accessed 27th October 2013] 
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Jad
 */
public class Database {
    //Make static so the connection is available throughout the execution of the program.
    public static Connection connection;
    
    public Database(){
        try {
            //Connect to the ebank database.
            //See references (1)
           connection = DriverManager.getConnection("jdbc:derby://localhost:1527/ebank");
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage() + " (Database() in Database)");
        }
    }
}
