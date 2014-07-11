//Name: Jad El-Houssami
//ID: w13651455

//REFERENCES: 
//(1)Stack overflow, (2009). java swing close window without exiting app. [online] Available from: <stackoverflow.com/questions/573317/java-swing-close-window-without-exiting-app> [Accessed 12th October 2013]
//(2)Stack overflow, (2012). Java - How do I get a all the selected values from a JList? [online] Available from: <http://stackoverflow.com/questions/8886405/java-how-do-i-get-a-all-the-selected-values-from-a-jlist> [Accessed 30th October 2013]
package listeners;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import models.Customer;
import views.CustomerList;
import views.EditCustomerAccount;
import views.Login;
import views.NewCustomer;

/**
 *
 * @author Jad
 */

public class CustomerListListener implements ActionListener{

    private JFrame customerListFrame;
    private CustomerList customerList;
    public ArrayList<String>names;
    private Customer customer;
    private String selectedCustomer;
    private String chosenListItem;
    
    /*Initialise variables for this class
     *@param myCustomerListFrame an instance of the JFrame component.
     *@param custList an instance of the CustomerList class.
     */
    public CustomerListListener(JFrame myCustomerListFrame,CustomerList custList){
        this.customer = new Customer();
        this.customerListFrame = myCustomerListFrame;
        this.customerList = custList;
        this.names = customer.getFullName();
        this.selectedCustomer = new String();
    }
    
    //What to do when an action occurs.
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "Logout":
                //Close the Customer List page and open the Login page.
                //See references (1)
                this.customerListFrame.dispose();
                Login login = new Login();
                break;
            case "Add New Customer":
                //Close the Customer List page and open the Add New Customer page.
                //See references (1)                
                this.customerListFrame.dispose();
                NewCustomer newCustomer = new NewCustomer();
                break;
            case "Edit Customer Account":                
                //Get the selected value from the JList and remove the square brackets around it.
                //See reference(2)
                this.chosenListItem = customerList.jList.getSelectedValuesList().toString();
                this.selectedCustomer = chosenListItem.substring(1, chosenListItem.length() - 1);
                //Check if a customer name was selected. 
                if ((this.selectedCustomer.equals("")) || (this.selectedCustomer == null)){
                    //Output error message if no customer name was selected.
                    JOptionPane.showMessageDialog(null, "Please select a customer", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                else{
                //Close the Customer List page and open the Edit Customer Details page
                //with the relevant name for the selected customer.
                this.customerListFrame.dispose();
                EditCustomerAccountListener.currentRow = 0;
                EditCustomerAccount editCustomerAccount = new EditCustomerAccount(selectedCustomer);
                }
                break;
            default:
                //Do nothing by default.
                break;
        }
    }
}
