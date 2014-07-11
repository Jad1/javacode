//NAME: Jad El-Houssami
//ID: w13651455

//REFERNECES:
//(1)Oracle. Class JFrame. [online]  Available from: <http://docs.oracle.com/javase/7/docs/api/javax/swing/JFrame.html> [Accessed 8th October 2013]
//Note: Date of publication could not be found.

package listeners;

import interfaces.MyConstants;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import models.Account;
import models.Customer;
import views.EditCustomerAccount;
import views.Login;
import views.NewAccount;

/**
 *
 * @author Jad
 */

public class NewAccountListener implements ActionListener {
    private JFrame newAccountFrame;
    private NewAccount newAccount;
    private Customer customer;
    private String fullName, accountType, balance, creditLimit;
    private Account account;
    private int customerId;
    
     /*Initialise variables for this class
     *@param myNewAccountFrame an instance of the JFrame component.
     *@param selectedCustomer the customer for whom the account shall be added.
     *@param newAcc an instance of the NewAccount class.
     */
    public NewAccountListener(JFrame myNewAccountFrame, String selectedCustomer, NewAccount newAcc) {
        this.newAccountFrame = myNewAccountFrame;
        this.account = new Account();
        this.newAccount = newAcc;
        this.customer = new Customer();
        this.fullName = selectedCustomer;
        
        //Get the new customer's ID. (Or 0 if something goes wrong)
        this.customerId = customer.getCustomerId(this.fullName);
    }
    
    //What to do when an action occurs.
    @Override
    public void actionPerformed(ActionEvent e){
        //Get the values from the text fields and combo box.
        this.accountType = newAccount.accountTypeComboBox.getSelectedItem().toString();
        this.balance = newAccount.balanceSpinner.getValue().toString();
        this.creditLimit = newAccount.creditLimitSpinner.getValue().toString();
        switch(e.getActionCommand()){
            //Close the New Customer Page and open the Login page.
            //See references(1)
            case "Logout":
                this.newAccountFrame.dispose();
                Login login = new Login();
                break;
            case "Add Account":
                //Check if any of the fields are empty.
                if ((this.accountType.equals("")) || (balance.equals("")) || (creditLimit.equals(""))){
                    JOptionPane.showMessageDialog(null, "A field is missing", "Missing field", JOptionPane.ERROR_MESSAGE);
                }
                else{   
                      //Add the account and display a message confirming the customer has been added.
                      //Reset JComboBox and reset Spinners to zero.
                        this.account.setAccount(customerId, accountType, balance, creditLimit);
                        JOptionPane.showMessageDialog(null, "New account added for " + fullName, "New Account Added", JOptionPane.INFORMATION_MESSAGE);
                        newAccount.accountTypeComboBox.setSelectedItem(MyConstants.items[0]);
                        newAccount.balanceSpinner.setValue(0);
                        newAccount.creditLimitSpinner.setValue(0);
                }
                
                break;       
                //Close the New Account page and open the Edit Customer Account page.
                //See references(1)                
            case "Back":
                this.newAccountFrame.dispose();
                EditCustomerAccount editAccount = new EditCustomerAccount(fullName);
                break;                
           default:
                //Do nothing by default.
                break;
        }
    }
    
}
