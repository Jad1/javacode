//NAME: Jad El-Houssami
//ID: w13651455

//REFERNECES:
//(1)Oracle. Class JFrame. [online] Oracle. Available from: <http://docs.oracle.com/javase/7/docs/api/javax/swing/JFrame.html> [Accessed 8th October 2013]
//Note: Date of publication could not be found.
//(2)Oracle. How to use JSpinners. [online]. Available from: <http://docs.oracle.com/javase/tutorial/uiswing/components/spinner.html> [Accessed 9th November 2013]
//Note: Date of publication could not be found.
//(3)Stack overflow, (2011). Make JSpinner completely numeric. [online] Available from: <http://stackoverflow.com/questions/6449350/make-jspinner-completely-numeric> [Accessed 9th November 2013]
//(4)Code Ranch, (2004). JSpinner Size. [online] Available from: <www.coderanch.com/t/339225/GUI/java/JSpinner-Size> [Accessed 9th November 2013]
//(5)University of Westminster. Program GridBagLayout01. [Accessed 25th October 2013]
//Note: Date of publication could not be found.
package views;

import interfaces.MyConstants;
import java.awt.*;
import javax.swing.*;
import javax.swing.text.NumberFormatter;
import listeners.EditCustomerAccountListener;
/**
 *
 * @author Jad
 */

public class EditCustomerAccount implements MyConstants{
        private JFrame editCustomerAccountFrame = new JFrame("Edit Customer Account");
        private JLabel editDetailsHeadingLabel;
        private JButton addAccountButton, deleteAccountButton, updateAccountButton, backButton, logoutButton, nextAccountButton, previousAccountButton;
        private JPanel northPanel, centrePanel, southPanel;
        private JLabel accountTypeLabel, balanceLabel, creditLimitLabel;
        public JSpinner creditLimitSpinner, balanceSpinner;
        private SpinnerModel balanceSpinnerModel, creditLimitSpinnerModel;
       public JComboBox accountTypeComboBox;
       private JFormattedTextField balanceFormatter, creditLimitFormatter;
       private Dimension spinnerSize;
        private GridBagLayout centrePanelgbl, southPanelgbl;
        private GridBagConstraints centrePanelgbc, southPanelgbc;
    public EditCustomerAccount(String selectedCustomer){
   
        northPanel = new JPanel();
        editDetailsHeadingLabel = new JLabel("Edit Details");
        
        //Create and add Font settings for editDetailsHeadingLabel.
        Font font = new Font("Arial", Font.BOLD, headingFontSize); 
        editDetailsHeadingLabel.setFont(font);
        
        //Add editDetailsHeadingLabel to northPanel and position it to the north of editCustomerAccountFrame.
        northPanel.add(editDetailsHeadingLabel);
        editCustomerAccountFrame.add(northPanel, "North");
       
        
        //Set up the SpinnerModels for the JSpinners.
        //See references (2)
        balanceSpinnerModel = new SpinnerNumberModel(spinnerInitValue, //initial value (zero but gets changed when the listener passes the view a value from the database)
                               spinnerMinValue, //min
                               spinnerMaxValue, //max
                               spinnerIncrement); //increment

        creditLimitSpinnerModel = new SpinnerNumberModel(spinnerInitValue, //initial value (zero but gets changed when the listener passes the view a value from the database)
                               spinnerMinValue, //min
                               spinnerMaxValue, //max
                               spinnerIncrement); //increment
        
        centrePanel = new JPanel();
        accountTypeLabel = new JLabel("Account Type");
        balanceLabel = new JLabel("Balance");
        creditLimitLabel = new JLabel("Credit Limit");
        balanceSpinner = new JSpinner(balanceSpinnerModel);
        creditLimitSpinner = new JSpinner(creditLimitSpinnerModel);
        
        //Stop non-numeric values being added to the JSpinner.
        //See references (3)
        balanceFormatter = ((JSpinner.NumberEditor)balanceSpinner.getEditor()).getTextField();
        ((NumberFormatter)balanceFormatter.getFormatter()).setAllowsInvalid(false);
        creditLimitFormatter = ((JSpinner.NumberEditor)creditLimitSpinner.getEditor()).getTextField();
        ((NumberFormatter)creditLimitFormatter.getFormatter()).setAllowsInvalid(false);
        
        //Set the width of the JSpinners.
        //See references(4)
        spinnerSize = creditLimitSpinner.getPreferredSize();
        spinnerSize.width = spinnerWidth;  
        creditLimitSpinner.setPreferredSize(spinnerSize);  
        balanceSpinner.setPreferredSize(spinnerSize);
        
        accountTypeComboBox = new JComboBox(items);
        previousAccountButton = new JButton("<");
        nextAccountButton = new JButton(">");
        
        centrePanelgbl = new GridBagLayout();
        centrePanelgbc = new GridBagConstraints();
        
        //Set constraints for the components to be added to the centrePanel 
        //(which will have a GridBagLayout)
        //See references (5)

        //accountTypeLabel
        centrePanelgbc.gridx =centrePanelgbc.gridy = distanceFromOrigin;
        centrePanelgbc.gridheight = centrePanelgbc.gridwidth = componentLength;
        centrePanelgbc.ipady = padding;        
        centrePanelgbl.setConstraints(accountTypeLabel, centrePanelgbc);
 
        //accountTypeComboBox
        centrePanelgbc.gridx = distanceFromOrigin + 3;
        centrePanelgbc.gridy = distanceFromOrigin;
        centrePanelgbc.gridheight = centrePanelgbc.gridwidth = componentLength;
        centrePanelgbc.ipady = padding;
        centrePanelgbl.setConstraints(accountTypeComboBox, centrePanelgbc);
       
        //balanceLabel
        centrePanelgbc.gridx = distanceFromOrigin;
        centrePanelgbc.gridy = distanceFromOrigin + 1;
        centrePanelgbc.gridheight = centrePanelgbc.gridwidth = componentLength;
        centrePanelgbl.setConstraints(balanceLabel, centrePanelgbc);
        
        //balanceSpinner
        centrePanelgbc.gridx = distanceFromOrigin + 3;
        centrePanelgbc.gridy = distanceFromOrigin + 1;
        centrePanelgbc.gridheight = centrePanelgbc.gridwidth = componentLength;
        centrePanelgbl.setConstraints(balanceSpinner, centrePanelgbc);
        
        //creditLimitLabel
        centrePanelgbc.gridx = distanceFromOrigin;
        centrePanelgbc.gridy = distanceFromOrigin + 2;
        centrePanelgbc.gridheight = centrePanelgbc.gridwidth = componentLength;
        centrePanelgbl.setConstraints(creditLimitLabel, centrePanelgbc);           
        
        //creditLimitSpinner
        centrePanelgbc.gridx = distanceFromOrigin + 3;
        centrePanelgbc.gridy = distanceFromOrigin + 2;
        centrePanelgbc.gridheight = centrePanelgbc.gridwidth = componentLength;
        centrePanelgbl.setConstraints(creditLimitSpinner, centrePanelgbc);
        
        //previousAccountButton
        centrePanelgbc.gridx = distanceFromOrigin;
        centrePanelgbc.gridy = distanceFromOrigin + 4;
        centrePanelgbc.gridheight = centrePanelgbc.gridwidth = componentLength;
        centrePanelgbl.setConstraints(previousAccountButton, centrePanelgbc);

        //nextAccountButton
        centrePanelgbc.gridx = distanceFromOrigin + 3;
        centrePanelgbc.gridy = distanceFromOrigin + 4;
        centrePanelgbc.gridheight = centrePanelgbc.gridwidth = componentLength;
        centrePanelgbl.setConstraints(nextAccountButton, centrePanelgbc);        
        
        
        //Add components to centrePanel and position it to the centre of editCustomerAccountFrame.
        centrePanel.add(accountTypeLabel);
        centrePanel.add(accountTypeComboBox);
        centrePanel.add(balanceLabel);
        centrePanel.add(balanceSpinner);
        centrePanel.add(creditLimitLabel);
        centrePanel.add(creditLimitSpinner);
        centrePanel.add(previousAccountButton);
        centrePanel.add(nextAccountButton);
        centrePanel.setLayout(centrePanelgbl);
        editCustomerAccountFrame.add(centrePanel, "Center");
        
        southPanel = new JPanel();
        addAccountButton = new JButton("Add Account");
        deleteAccountButton = new JButton ("Delete Account");
        updateAccountButton = new JButton ("Update Account");
        backButton = new JButton ("Back");
        logoutButton = new JButton ("Logout");
        
        
        southPanelgbl = new GridBagLayout();
        southPanelgbc = new GridBagConstraints();
        
        //Set constraints for the components to be added to the southPanel 
        //(which will have a GridBagLayout)
        //See references (5)
        
        //addAccountButton
        southPanelgbc.gridx = distanceFromOrigin;
        southPanelgbc.gridy = distanceFromOrigin;
        southPanelgbc.gridheight = southPanelgbc.gridwidth = componentLength;  
        southPanelgbc.weighty = padding;
        southPanelgbl.setConstraints(addAccountButton, southPanelgbc);
 
        //deleteAccountButton
        southPanelgbc.gridx = distanceFromOrigin +1;
        southPanelgbc.gridy = distanceFromOrigin;
        southPanelgbc.gridheight = southPanelgbc.gridwidth = componentLength;
        southPanelgbc.weighty = padding;
        southPanelgbl.setConstraints(deleteAccountButton, southPanelgbc);
       
        //updateAccountButton
        southPanelgbc.gridx = distanceFromOrigin + 2;
        southPanelgbc.gridy = distanceFromOrigin;
        southPanelgbc.gridheight = southPanelgbc.gridwidth = componentLength;
        southPanelgbc.weighty = padding;
        centrePanelgbl.setConstraints(updateAccountButton, southPanelgbc);
         
        //backButton
        southPanelgbc.gridx = distanceFromOrigin;
        southPanelgbc.gridy = distanceFromOrigin + 2;
        southPanelgbc.gridheight = southPanelgbc.gridwidth = componentLength;
        southPanelgbl.setConstraints(backButton, southPanelgbc);           
        
        //logoutButton
        southPanelgbc.gridx = distanceFromOrigin + 1;
        southPanelgbc.gridy = distanceFromOrigin + 2;
        southPanelgbc.gridheight = southPanelgbc.gridwidth = componentLength;
        southPanelgbl.setConstraints(logoutButton, southPanelgbc);
        
        southPanel.add(logoutButton);
        southPanel.add(addAccountButton);
        southPanel.add(deleteAccountButton);
        southPanel.add(updateAccountButton);
        southPanel.add(backButton);
        southPanel.setLayout(southPanelgbl);
        editCustomerAccountFrame.add(southPanel, "South");
   
        //Tell the program to terminate if the loginFrame is closed.
        //See references (1)
          editCustomerAccountFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Set the dimentions of the loginFrame and set its visibility to true.
        editCustomerAccountFrame.setSize(frameSideLength, frameSideLength);
        editCustomerAccountFrame.setVisible(true);
        
        //Add listeners to the buttons.
        logoutButton.addActionListener(new EditCustomerAccountListener(editCustomerAccountFrame, selectedCustomer, this));
        backButton.addActionListener(new EditCustomerAccountListener(editCustomerAccountFrame, selectedCustomer, this));
        previousAccountButton.addActionListener(new EditCustomerAccountListener(editCustomerAccountFrame, selectedCustomer, this));
        nextAccountButton.addActionListener(new EditCustomerAccountListener(editCustomerAccountFrame, selectedCustomer, this));
        addAccountButton.addActionListener(new EditCustomerAccountListener(editCustomerAccountFrame, selectedCustomer, this));
        deleteAccountButton.addActionListener(new EditCustomerAccountListener(editCustomerAccountFrame, selectedCustomer, this));
        updateAccountButton.addActionListener(new EditCustomerAccountListener(editCustomerAccountFrame, selectedCustomer, this));
        
    }
}
