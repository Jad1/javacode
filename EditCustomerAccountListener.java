//NAME: Jad El-Houssami
//ID: w13651455

//REFERNECES:
//(1)Oracle. Class JFrame. [online]  Available from: <http://docs.oracle.com/javase/7/docs/api/javax/swing/JFrame.html> [Accessed 8th October 2013]
//Note: Date of publication could not be found.

package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import models.Account;
import models.Customer;
import views.CustomerList;
import views.EditCustomerAccount;
import views.Login;
import views.NewAccount;

/**
 *
 * @author Jad
 */

public class EditCustomerAccountListener implements  ActionListener{

    private JFrame editCustomerAccountFrame;
    private EditCustomerAccount editCustomerAccount;
    private String selectedCustomer, accountType, balance, creditLimit;;
    private Customer customer;
    private Account account;
    public ArrayList<String> accountIds, accountTypes;
    public ArrayList<Double> balances, creditLimits;
    private int customerId;
    private double oldBalance; //This stores the balance of the currently viewed account, to be used if the balance is modified.
    public static int currentRow = 0; //Static so that value atays the same when clicking next button or previous button.
    
    /*Initialise variables for this class
     *@param myCustomerAccount an instance of the JFrame component.
     *@param selectedCustomer the customer whose accounts can be seen.
     *@param editCustomer an instance of the EditCustomerAccount class.
     */
    public EditCustomerAccountListener(JFrame myEditCustomerAccountFrame, String selectedCustomer, EditCustomerAccount editCustomer){
        //Initialise variables and objects.
        this.customer = new Customer();
        this.account = new Account();
        this.selectedCustomer = selectedCustomer;
        this.customerId = customer.getCustomerId(selectedCustomer);
        this.editCustomerAccountFrame = myEditCustomerAccountFrame;
        this.editCustomerAccount = editCustomer;
        
        //Get the relevant account details for the customer.
        this.accountIds = account.getAccountIds(customerId);
        this.accountTypes = account.getTypes(customerId);
        this.balances = account.getBalances(customerId);
        this.creditLimits = account.getCreditLimits(customerId);
         
        oldBalance = balances.get(currentRow);
       //Initially populate text fields and combo box with first row values.
        this.editCustomerAccount.accountTypeComboBox.setSelectedItem(accountTypes.get(currentRow));
        this.editCustomerAccount.balanceSpinner.setValue((balances.get(currentRow)));
        this.editCustomerAccount.creditLimitSpinner.setValue(creditLimits.get(currentRow));           
    }
    
    //What to do when an action occurs.    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        //Get the values inside the text fields and combo box
        this.accountType = editCustomerAccount.accountTypeComboBox.getSelectedItem().toString();
        this.balance = editCustomerAccount.balanceSpinner.getValue().toString();
        this.creditLimit = editCustomerAccount.creditLimitSpinner.getValue().toString();
      switch(e.getActionCommand()){
          //Close the Edit Customer Account page and open the login page.
          //See references (1)
          case "Logout":
              this.editCustomerAccountFrame.dispose();
              Login login = new Login();
          break;
          case "Add Account":
          //Close the Edit Customer Account page and open the new account page.
          //See references (1)              
              this.editCustomerAccountFrame.dispose();
              NewAccount newAccount = new NewAccount(selectedCustomer);
              break;
          case "Delete Account":
              //Delete the account currently in view and show a message of completion.
              //Close and reopen the edit account page to see the updated account list. See references (1)    
              this.account.deleteAccount(Integer.valueOf(this.accountIds.get(currentRow)));
               JOptionPane.showMessageDialog(null, "Account deleted", "Edit Accounts", JOptionPane.INFORMATION_MESSAGE);  
               this.editCustomerAccountFrame.dispose();
               currentRow = 0;
               EditCustomerAccount reloadPageAfterDelete = new EditCustomerAccount(selectedCustomer);
              break;
          case "Update Account":
              //Check if any of the fields are empty.
              if ((this.accountType.equals("")) || (this.balance.equals("")) || (this.creditLimit.equals(""))){
                 JOptionPane.showMessageDialog(null, "All fields must be filled in", "Missing field", JOptionPane.ERROR_MESSAGE);                  
              }
              //Check if amount of money deducted from balance exceeds credit limit.
              if (((this.oldBalance) - Double.valueOf(this.balance)) > Double.valueOf(this.creditLimit)){
                  //Output error message and set spinner value to orginial balance.
                   JOptionPane.showMessageDialog(null, "Cannot withdraw more than £" + this.creditLimit, "Edit Accounts", JOptionPane.ERROR_MESSAGE);
                   editCustomerAccount.balanceSpinner.setValue(oldBalance);
              }
              else{
              //Update the account currently in view and show a message of completion.
              //Close and reopen the edit account page to see the updated account list. See references (1)                     
               this.account.updateAccount(accountType, balance, creditLimit, Integer.valueOf(accountIds.get(currentRow)));  
               JOptionPane.showMessageDialog(null, "Account updated", "Edit Accounts", JOptionPane.INFORMATION_MESSAGE);
               this.editCustomerAccountFrame.dispose();
               currentRow = 0;
               EditCustomerAccount reloadPageAfterUpdate = new EditCustomerAccount(selectedCustomer);
              }
              
              break;
          case "Back":
          //Close the Edit Customer Account page and open the customer list page.
          //See references (1)              
              this.editCustomerAccountFrame.dispose();
              CustomerList customerList = new CustomerList();
              break;
          case "<":
              //Go to previous record.
                    //Check if ArrayLists are at the first item. If they are, show an error message.
                    //(The ArrayLists should really all be in line (i.e. at the same index) but check all just in case)
                    if (currentRow == 0){
                        JOptionPane.showMessageDialog(null, "Can't go back as you are on the first account", "Edit Accounts", JOptionPane.PLAIN_MESSAGE);
                    }
                    else{
                        //Move the row counter back by 1.
                        currentRow--;
                        
                       //S
                        oldBalance = balances.get(currentRow);
                       //Populate text fields and combo box with new row values.
                        this.editCustomerAccount.accountTypeComboBox.setSelectedItem(this.accountTypes.get(currentRow));
                        this.editCustomerAccount.balanceSpinner.setValue(this.balances.get(currentRow));
                        this.editCustomerAccount.creditLimitSpinner.setValue(this.creditLimits.get(currentRow));
                    }    
              break;
          case ">":
              //Go to next record.
                    //Check if ArrayLists are at the last item. If they are, show an error message.
                    //(The ArrayLists should really all be in line (i.e. at the same index) but check all just in case)
                    //(subtract 1 from size as index starts from zero)
                    if ((currentRow == (this.accountTypes.size() - 1)) || (currentRow == (this.balances.size() - 1)) || (currentRow == (this.creditLimits.size() - 1))){
                        JOptionPane.showMessageDialog(null, "Can't go forward as you are on the last account", "Edit Accounts", JOptionPane.PLAIN_MESSAGE);
                    }
                    else{
                       //Move the row counter forward by 1.
                        currentRow++;

                        oldBalance = balances.get(currentRow);
                       //Populate text fields and combo box with new row values.
                        this.editCustomerAccount.accountTypeComboBox.setSelectedItem(this.accountTypes.get(currentRow));
                        this.editCustomerAccount.balanceSpinner.setValue(this.balances.get(currentRow));
                        this.editCustomerAccount.creditLimitSpinner.setValue(this.creditLimits.get(currentRow));
                    }
              break;
              
          default:
              //Do nothing by default.
              break;
      }
    }
    
}
