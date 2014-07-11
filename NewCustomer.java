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
//(6)University of Westminster. Program SwingBox2. [Accessed 26th October 2013]
//Note: Date of publication could not be found.

package views;
import interfaces.MyConstants;
import java.awt.*;
import javax.swing.*;
import javax.swing.text.NumberFormatter;
import listeners.NewCustomerListener;

/**
 *
 * @author Jad
 */

public class NewCustomer implements MyConstants{
        private JFrame newCustomerFrame;
        private JLabel newCustomerHeadingLabel, fullNameLabel, accountTypeLabel, balanceLabel, creditLimitLabel;
        private JButton logoutButton, addCustomerButton, backButton;
        public JTextField fullNameTextField;
        public JComboBox accountTypeComboBox;
        private JPanel northPanel, centrePanel, southPanel;
        private Font font;
        private Box box;
        private GridBagLayout gbl;
        private GridBagConstraints gbc;
        public JSpinner creditLimitSpinner, balanceSpinner;
        private SpinnerModel balanceSpinnerModel, creditLimitSpinnerModel;
        private JFormattedTextField balanceFormatter, creditLimitFormatter;
        private Dimension spinnerSize;

    public NewCustomer(){
        newCustomerFrame = new JFrame("New Customer");
        
        
        northPanel = new JPanel();
        newCustomerHeadingLabel = new JLabel("New Customer");
        
         //Create and add Font settings for newCustomerHeadingLabel.
        font = new Font("Arial", Font.BOLD, headingFontSize); 
        newCustomerHeadingLabel.setFont(font);
        
        //Add newCustomerHeadingLabel to northPanel and position it to the north of newCustomerFrame.
        northPanel.add(newCustomerHeadingLabel);
        newCustomerFrame.add(northPanel, "North");
        
                //Set up the SpinnerModels for the JSpinners.
        //See references (2)
        balanceSpinnerModel = new SpinnerNumberModel(spinnerInitValue, //initial value
                               spinnerMinValue, //min
                               spinnerMaxValue, //max
                               spinnerIncrement); //increment

        creditLimitSpinnerModel = new SpinnerNumberModel(spinnerInitValue, //initial value 
                               spinnerMinValue, //min
                               spinnerMaxValue, //max
                               spinnerIncrement); //increment


        centrePanel = new JPanel();
        fullNameLabel = new JLabel("Full Name");
        accountTypeLabel = new JLabel("Account Type");
        balanceLabel = new JLabel("Balance");
        creditLimitLabel = new JLabel("Credit Limit");
        fullNameTextField = new JTextField(textFieldSize);
        
        balanceSpinner = new JSpinner(balanceSpinnerModel);
        creditLimitSpinner = new JSpinner(creditLimitSpinnerModel);
        //Stop non-numeric values being added to the JSpinner.
        //See references (3)
        balanceFormatter = ((JSpinner.NumberEditor)balanceSpinner.getEditor()).getTextField();
        ((NumberFormatter)balanceFormatter.getFormatter()).setAllowsInvalid(false);
        creditLimitFormatter = ((JSpinner.NumberEditor)creditLimitSpinner.getEditor()).getTextField();
        ((NumberFormatter)creditLimitFormatter.getFormatter()).setAllowsInvalid(false);
        
        //Set the width of the JSpinners to 100.
        //See references(4)
        spinnerSize = creditLimitSpinner.getPreferredSize();
        spinnerSize.width = spinnerWidth;  
        creditLimitSpinner.setPreferredSize(spinnerSize);  
        balanceSpinner.setPreferredSize(spinnerSize);
        
        accountTypeComboBox = new JComboBox(items);
        
        gbl = new GridBagLayout();
        gbc = new GridBagConstraints();
        
        //Set constraints for the components to be added to the centrePanel 
        //(which will have a GridBagLayout)
        //See references (5)
        
        //fullNameLabel
        gbc.gridx = gbc.gridy = distanceFromOrigin;
        gbc.gridheight = gbc.gridwidth = componentLength;
        gbc.ipady = padding;
        gbl.setConstraints(fullNameLabel, gbc);

        //fullNameTextField
        gbc.gridx = distanceFromOrigin + 1;
        gbc.gridy = distanceFromOrigin;
        gbc.gridheight = gbc.gridwidth = componentLength;
        gbc.ipady = padding;
        gbl.setConstraints(fullNameTextField, gbc);

        //accountTypeLabel
        gbc.gridx = distanceFromOrigin;
        gbc.gridy = distanceFromOrigin + 1;
        gbc.gridheight = gbc.gridwidth = componentLength;
        gbc.ipady = padding;        
        gbl.setConstraints(accountTypeLabel, gbc);
 
        //accountTypeComboBox
        gbc.gridx = distanceFromOrigin + 1;
        gbc.gridy = distanceFromOrigin + 1;
        gbc.gridheight = gbc.gridwidth = componentLength;
        gbc.ipady = padding;
        gbl.setConstraints(accountTypeComboBox, gbc);
       
        //balanceLabel
        gbc.gridx = distanceFromOrigin;
        gbc.gridy = distanceFromOrigin + 2;
        gbc.gridheight = gbc.gridwidth = componentLength;
        gbl.setConstraints(balanceLabel, gbc);
        
        //balanceSpinner
        gbc.gridx = distanceFromOrigin + 1;
        gbc.gridy = distanceFromOrigin + 2;
        gbc.gridheight = gbc.gridwidth = componentLength;
        gbl.setConstraints(balanceSpinner, gbc);
        
        //creditLimitLabel
        gbc.gridx = distanceFromOrigin;
        gbc.gridy = distanceFromOrigin + 3;
        gbc.gridheight = gbc.gridwidth = componentLength;
        gbl.setConstraints(creditLimitLabel, gbc);           
        
        //creditLimitSpinner
        gbc.gridx = distanceFromOrigin + 1;
        gbc.gridy = distanceFromOrigin + 3;
        gbc.gridheight = gbc.gridwidth = componentLength;
        gbl.setConstraints(creditLimitSpinner, gbc);        
        
        
        //Add components to centrePanel and position it to the centre of newCustomerFrame.
        centrePanel.add(fullNameLabel);
        centrePanel.add(fullNameTextField);
        centrePanel.add(accountTypeLabel);
        centrePanel.add(accountTypeComboBox);
        centrePanel.add(balanceLabel);
        centrePanel.add(balanceSpinner);
        centrePanel.add(creditLimitLabel);
        centrePanel.add(creditLimitSpinner);
        centrePanel.setLayout(gbl);
        newCustomerFrame.add(centrePanel, "Center");
      
        southPanel = new JPanel();
        logoutButton = new JButton("Logout");
        addCustomerButton = new JButton("Add Customer");
        backButton = new JButton("Back");
        
        //Set a Box for the southPanel. The components inside will be laid out 
        //next to each other.
        //Add the components to the Box. Between each component, add some spacing.
        //See references (6)
        box = Box.createHorizontalBox();
        box.add(logoutButton);
        box.add(Box.createHorizontalStrut(boxSpacing));
        box.add(addCustomerButton);
        box.add(Box.createHorizontalStrut(boxSpacing));
        box.add(backButton);
        southPanel.add(box);
        newCustomerFrame.add(southPanel, "South");
        
         //Tell the program to terminate if the loginFrame is closed.
        //See references (1)
          newCustomerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Set the dimentions of the loginFrame and set its visibility to true.
        newCustomerFrame.setSize(frameSideLength + 50, frameSideLength - 150);
        newCustomerFrame.setVisible(true);
        
        //Add listeners to the buttons.
        logoutButton.addActionListener(new NewCustomerListener(newCustomerFrame, this));
        backButton.addActionListener(new NewCustomerListener(newCustomerFrame, this));
        addCustomerButton.addActionListener(new NewCustomerListener(newCustomerFrame, this));
    }
    
}
