//Name: Jad El-Houssami
//ID: w13651455

//REFERENCES: 
//(1)Stack overflow., (2009). java swing close window without exiting app. [online] Available from: <stackoverflow.com/questions/573317/java-swing-close-window-without-exiting-app> [Accessed 12th October 2013]
//(2)Stack overflow., (2011). How to convert a char array back to a string? (Java). [online] Available at: <http://stackoverflow.com/questions/7655127/how-to-convert-a-char-array-back-to-a-string-java> [Accessed 20th October 2013]
package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import models.Employee;
import views.CustomerList;
import views.Login;
import views.Registration;


/**
 *
 * @author Jad
 */

public class LoginListener implements ActionListener{
    
    private String username, password;
    private char [] pwordInChars;
    private JFrame loginFrame;
    private Employee employee;
    private Login login;

    /*Initialise variables for this class
     *@param myLoginFrame an instance of the JFrame component.
     *@param login an instance of the Login class.
     */
    public LoginListener(JFrame myLoginFrame, Login login){
        this.employee = new Employee();
        this.login = login;
        this.loginFrame = myLoginFrame;
        this.username = login.usernameTextField.getText();
        this.pwordInChars = login.passwordTextField.getPassword();
        //See references (2)
        this.password = new String(pwordInChars);
    }
    
    //What to do when an action occurs.
    @Override
    public void actionPerformed(ActionEvent e) {
        
        //Get the username and password from the text fields.
        //Need to convert the password back to a String because it 
        //is retrieved as an array of characters.
        this.username = login.usernameTextField.getText();
        this.pwordInChars = login.passwordTextField.getPassword();
        //See references (2)
        this.password = new String(pwordInChars);
        switch(e.getActionCommand()){
            //If the Register button is clicked, close the login screen and
            //open the Registration page.
            //See references (1)
            case "Register":
                this.loginFrame.dispose();
                Registration registrationFrame = new Registration();
                break;
              
                //If the Login button is clicked, check that the given username and password
                //exist.
            case "Login":
                //If a match is found in the database (there should only be one!) then show
                //a message, close the login page and move to the customer list page.
                //See references (1)
                if ((this.employee.getCredentials(username, password))){
                    JOptionPane.showMessageDialog(null, "Welcome " + username, "Successful Login", JOptionPane.INFORMATION_MESSAGE);
                    this.loginFrame.dispose();
                    CustomerList customerList = new CustomerList();
                }
                else{
                    //Show a message to say the login was not successful. Make contents of text fields blank.
                    JOptionPane.showMessageDialog(null, "Incorrect username and/or password", "Incorrect Login", JOptionPane.ERROR_MESSAGE);
                    login.usernameTextField.setText("");
                    login.passwordTextField.setText("");
                }          
               break;
            default:
                //Do nothing by default.
                break;
        }
    }
    
}
