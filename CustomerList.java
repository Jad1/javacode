//NAME: Jad El-Houssami
//ID: w13651455

//REFERNECES:
//(1)Oracle. Class JFrame. [online]  Available from: <http://docs.oracle.com/javase/7/docs/api/javax/swing/JFrame.html> [Accessed 8th October 2013]
//Note: Date of publication could not be found.
//(2)CodeRanch, (2005). multiple selection in a JList. [online] Available from: <http://www.coderanch.com/t/341526/GUI/java/multiple-selection-JList> [Accessed 30th October 2013]
//(3)University of Westminster. Program SwingBox2. [Accessed 26th October 2013]
//Note: Date of publication could not be found.
package views;

import interfaces.MyConstants;
import java.awt.*;
import javax.swing.*;
import listeners.CustomerListListener;

/**
 *
 * @author Jad
 */
public class CustomerList implements MyConstants{
        private JLabel customerListHeadingLabel, info1Label;
        private JButton logoutButton, addNewCustomerButton, editCustomerAccountButton;
        private JFrame customerListFrame;
        private JPanel northPanel, centrePanel, southPanel;
        private CustomerListListener custListListener;
        public JList jList;
        private JScrollPane scroller;
        private Box centreBox, southBox;
        private GridBagLayout gbl = new GridBagLayout();
        private GridBagConstraints gbc = new GridBagConstraints();  
        private Font font;
        GroupLayout northPanelLayout;
        
         //Display this frame.
    public CustomerList(){
        //Initialise  the JFrame, the north panel and the heading label.
        customerListFrame = new JFrame("Customer List");
        northPanel =  new JPanel();
        customerListHeadingLabel = new JLabel("Customer List");
        
        //Create and add Font settings for customerListHeadingLabel.
        font = new Font("Arial", Font.BOLD, headingFontSize); 
        customerListHeadingLabel.setFont(font);  
        
        //Add registrationHeadingLabel to northPanel and position it to the north of registrationFrame.
        northPanel.add(customerListHeadingLabel);
        customerListFrame.add(northPanel, "North");     
        
        centrePanel = new JPanel();
       info1Label = new JLabel("Click on name in list to edit details.");
        custListListener = new CustomerListListener(customerListFrame, this);
        centreBox = Box.createVerticalBox();
        //Populate the JList with the list of customer names.
        //Set the JList such that only a single name can be selected.
        //See references(2)
        jList = new JList(custListListener.names.toArray());
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scroller = new JScrollPane(jList);

        centreBox.add(info1Label);
        centreBox.add(Box.createVerticalStrut(boxSpacing));
        centreBox.add(scroller);
        centrePanel.add(centreBox);
        customerListFrame.add(centrePanel, "Center");   
        
        southPanel = new JPanel();
        logoutButton = new JButton("Logout");
        addNewCustomerButton = new JButton("Add New Customer");
        editCustomerAccountButton = new JButton("Edit Customer Account");
        //Set a Box for the southPanel. The components inside will be laid out 
        //next to each other.
        //Add the components then add the southPanel to the customerListFrame.
        //Between each component, add some spacing.
        //See references (3)
        southBox = Box.createHorizontalBox();
        southBox.add(logoutButton);
        southBox.add(Box.createHorizontalStrut(boxSpacing));
        southBox.add(addNewCustomerButton);
        southBox.add(Box.createHorizontalStrut(boxSpacing));
        southBox.add(editCustomerAccountButton);
        southPanel.add(southBox);
        customerListFrame.add(southPanel, "South");
        
        //Tell the program to terminate if the customerListFrame is closed.
        //See references (1)
          customerListFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Set the dimentions of the customerListFrame and set its visibility to true.
        customerListFrame.setSize(frameSideLength + 50, frameSideLength);
        customerListFrame.setVisible(true);
        
        //Add listeners to the buttons.
        logoutButton.addActionListener(custListListener);
        addNewCustomerButton.addActionListener(custListListener);
        editCustomerAccountButton.addActionListener(custListListener);
    }
}
