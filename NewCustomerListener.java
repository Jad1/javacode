//NAME: Jad El-Houssami
//ID: w13651455

//REFERENCES: 
//(1)Stack overflow, (2009). java swing close window without exiting app. [online] Available from: <stackoverflow.com/questions/573317/java-swing-close-window-without-exiting-app> [Accessed 12th October 2013]


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import models.Account;
import models.Customer;
import views.CustomerList;
import views.Login;
import views.NewCustomer;

/**
 *
 * @author Jad
 */
public class NewCustomerListener implements ActionListener{
    private JFrame newCustomerFrame;
    private NewCustomer newCustomer;
    private Customer customer;
    private String fullName, accountType, balance, creditLimit;
    private Account account;
    private int customerId;
        
     /*Initialise variables for this class
     *@param myNewCustomerFrame an instance of the JFrame component.
     *@param newCust an instance of the NewCustomer class.
     */
    public NewCustomerListener(JFrame myNewCustomerFrame, NewCustomer newCust){
        this.newCustomerFrame = myNewCustomerFrame;
        this.account = new Account();
        this.newCustomer = newCust;
        this.customer = new Customer();
    }
    
    //What to do when an action occurs.
    @Override
    public void actionPerformed(ActionEvent e){
        //Get the full name from the text field.
        this.fullName = newCustomer.fullNameTextField.getText();
        this.accountType = newCustomer.accountTypeComboBox.getSelectedItem().toString();
        this.balance = newCustomer.balanceSpinner.getValue().toString();
        this.creditLimit = newCustomer.creditLimitSpinner.getValue().toString();
        switch(e.getActionCommand()){
            //Close the New Customer Page and open the Login page.
            //See references(1)
            case "Logout":
                this.newCustomerFrame.dispose();
                Login login = new Login();
                break;    
            case "Add Customer":
                //Check if any of the fields are empty. //Display error message if they are.
                if ((this.fullName.equals("")) || (this.accountType.equals("")) || (this.balance.equals("")) || (this.creditLimit.equals(""))){
                    JOptionPane.showMessageDialog(null, "A field is missing", "Missing field", JOptionPane.ERROR_MESSAGE);
                }
                
                else{
                    //Try to add a new customer (setFullName stops duplicate names being added)
                    if (this.customer.setFullName(this.fullName))
                    {
                        //Get the new customer's ID. (Or 0 if something goes wrong)
                        this.customerId = this.customer.getCustomerId(this.fullName);
                        if (this.customerId != 0){
                            //Add the account and display a message confirming the customer has been added.
                            //Close this page and go to the customer list page.
                            //See references (1)
                        this.account.setAccount(this.customerId, this.accountType, this.balance, this.creditLimit);
                        JOptionPane.showMessageDialog(null, "Added " + this.fullName + " to records", "New Customer Added", JOptionPane.INFORMATION_MESSAGE);
                        this.newCustomerFrame.dispose();
                        CustomerList customerList = new CustomerList();
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Name already exists", "Duplicate Name", JOptionPane.ERROR_MESSAGE);
                    }

                }
                break;  
                //Close the New Customer Page and open the Customer List page.
                //See references(1)
            case "Back":
                this.newCustomerFrame.dispose();
                CustomerList customerList  = new CustomerList();
                break;      
            default:
                //Do nothing by default.
                break;
        }
    }
}
