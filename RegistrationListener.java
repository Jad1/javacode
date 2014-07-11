//NAME: Jad El-Houssami
//ID: w13651455

//REFERNECES:
//(1)Oracle. Class JFrame. [online]  Available from: <http://docs.oracle.com/javase/7/docs/api/javax/swing/JFrame.html> [Accessed 8th October 2013]
//Note: Date of publication could not be found.
//(2)Stack overflow,, (2011). How to convert a char array back to a string? (Java). [online] Available at: <http://stackoverflow.com/questions/7655127/how-to-convert-a-char-array-back-to-a-string-java> [Accessed 20th October 2013]
package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import models.Employee;
import views.Login;
import views.Registration;

/**
 *
 * @author Jad
 */
public class RegistrationListener implements ActionListener {
    /*
     * We write the user's details to a database iff they have filled in all details.
     */
    private String fullName, username, password;
    private char [] pwdInChars;
    private Employee employee;
    private Registration registration;
    private JFrame registrationFrame;
    
    /*Initialise variables for this class
     * @param myReg an instance of the Registration class.
     * @param myRegFrame an instance of the JFrame component.
     */
    public RegistrationListener(Registration myReg, JFrame myRegFrame){
        //Initialise objects.
        this.registration = myReg;
        this.registrationFrame = myRegFrame;
        this.employee = new Employee();
    }
    
    //What to do when an action occurs.
    @Override
    public void actionPerformed(ActionEvent e) {
        //Get the relevant account details for the customer.
        this.fullName = registration.fullNameTextField.getText();
        this.username = registration.usernameTextField.getText();
        this.pwdInChars = registration.passwordTextField.getPassword();
        
        switch(e.getActionCommand()){
            case "Register":
        //Password initially an array of characters so need to covert it to a string
        //to store in the database.
        //See references (2)
        this.password = new String (pwdInChars);
         //Display error message if a field has not been filled in.
       if ((this.fullName.equals("")) || (this.username.equals("")) || (this.password.equals(""))){
            JOptionPane.showMessageDialog(null, "All fields must be filled in.", "Missing fields", JOptionPane.ERROR_MESSAGE);
       }

       //If all fields are filled, save the emplpoyee's details.
       else{
           //If the username is already is not in use, display a success message.
           //Close the registration form and open the login form.
           //See references(1)
           if(!this.employee.addEmployee(this.fullName, this.username, this.password)){
           JOptionPane.showMessageDialog(null, "Succefully registered " + this.username, "Registration Complete", JOptionPane.INFORMATION_MESSAGE);            
           this.registrationFrame.dispose();
            Login login = new Login();
           }
           //Else display a message to say the username is already in use.
           else{
             JOptionPane.showMessageDialog(null, "This username is already in use.", "Duplicate Username" , JOptionPane.ERROR_MESSAGE);   
           }
       }
                break;
            case "Back":
                //Close the registration page and open the login page.
                //See references(1)
                registrationFrame.dispose();
                Login login = new Login();
                break;
            default:
                //Do nothing by default.
                break;
    }
    }
    
}
