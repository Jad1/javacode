//NAME: Jad El-Houssami
//ID: w13651455

//REFERNECES:
//(1)Oracle. Class JFrame. [online] Oracle. Available from: <http://docs.oracle.com/javase/7/docs/api/javax/swing/JFrame.html> [Accessed 8th October 2013]
//Note: Date of publication could not be found.
//(2)University of Westminster. Program GridBagLayout01. [Accessed 25th October 2013]
//Note: Date of publication could not be found.
//(3)University of Westminster. Program SwingBox2. [Accessed 26th October 2013]
//Note: Date of publication could not be found.
package views;

import interfaces.MyConstants;
import java.awt.*;
import javax.swing.*;
import listeners.RegistrationListener;

/**
 *
 * @author Jad
 */
public class Registration implements MyConstants{

        private JFrame registationFrame = new JFrame("Registration");;
        private JLabel registationHeadingLabel, fullNameLabel, usernameLabel, passwordLabel;
        public JTextField fullNameTextField, usernameTextField;
        public JPasswordField passwordTextField;
        private JButton registerButton, backButton;
        private JPanel northPanel, centrePanel, southPanel;
        private GridBagLayout gbl = new GridBagLayout();
        private GridBagConstraints gbc = new GridBagConstraints();
        private Box box;
        
         //Display this frame.
    public Registration(){
        
        northPanel = new JPanel();
        registationHeadingLabel = new JLabel("New Employee");
        
        //Create and add Font settings for registrationHeadingLabel.
        Font font = new Font("Arial", Font.BOLD, headingFontSize ); 
        registationHeadingLabel.setFont(font);
        
        //Add registrationHeadingLabel to northPanel and position it to the north of registrationFrame.
        northPanel.add(registationHeadingLabel);
        registationFrame.add(northPanel, "North");
        
        centrePanel = new JPanel();
        fullNameLabel = new JLabel("Full Name: ");
        usernameLabel = new JLabel("Username: ");
        passwordLabel = new JLabel("Password: ");
        fullNameTextField = new JTextField(textFieldSize);
        usernameTextField = new JTextField(textFieldSize);
        passwordTextField = new JPasswordField(textFieldSize);
        registerButton = new JButton("Register");
        backButton = new JButton("Back");
        
        //Set constraints for the components to be added to the centrePanel 
        //(which will have a GridBagLayout)
        //See references (2)
        //fullNameLabel
        gbc.gridx = gbc.gridy = distanceFromOrigin;
        gbc.gridheight = gbc.gridwidth = componentLength;
        gbl.setConstraints(fullNameLabel, gbc);
        
        //fullNameTextField
        gbc.gridx = distanceFromOrigin + 3; 
        gbc.gridy = distanceFromOrigin;
        gbc.gridheight = gbc.gridwidth = componentLength;
        gbl.setConstraints(fullNameTextField, gbc);   
        
        //usernameLabel
        gbc.gridx = distanceFromOrigin;
        gbc.gridy = distanceFromOrigin + 1;
        gbc.gridheight = gbc.gridwidth = componentLength;
        gbl.setConstraints(usernameLabel, gbc);   
        
        //usernameTextField
        gbc.gridx = distanceFromOrigin + 3;
        gbc.gridy = distanceFromOrigin + 1;
        gbc.gridheight = gbc.gridwidth = componentLength;
        gbl.setConstraints(usernameTextField, gbc);        
        
        //passwordLabel
        gbc.gridx = distanceFromOrigin;
        gbc.gridy = distanceFromOrigin + 2;
        gbc.gridheight = gbc.gridwidth = componentLength;
        gbl.setConstraints(passwordLabel, gbc);
      
        //passwordTextField
        gbc.gridx = distanceFromOrigin + 3;
        gbc.gridy = distanceFromOrigin + 2;
        gbc.gridheight = gbc.gridwidth = componentLength;
        gbl.setConstraints(passwordTextField, gbc); 
        
        //registrationButton
        gbc.gridx = distanceFromOrigin + 1;
        gbc.gridy = distanceFromOrigin + 3;
        gbc.weighty = extraSpacing;
        gbc.gridheight = gbc.gridwidth = componentLength;
        gbl.setConstraints(registerButton, gbc);   
        
        //backButton
        gbc.gridx = distanceFromOrigin + 2;
        gbc.gridy = distanceFromOrigin + 3;
        gbc.weighty = extraSpacing;
        gbc.gridheight = gbc.gridwidth = componentLength;
        gbl.setConstraints(backButton, gbc);   
        
        //Add all the components given constraints to centrePanel.
        //Set the layout of the panel then add the panel to the centre of the registrationFrame.
        centrePanel.add(fullNameLabel);
        centrePanel.add(fullNameTextField);
        centrePanel.add(usernameLabel);
        centrePanel.add(usernameTextField);
        centrePanel.add(passwordLabel);
        centrePanel.add(passwordTextField);
        
        //Set the layout of the centrePanel and add it to the centre of the loginFrame.
        centrePanel.setLayout(gbl);
        registationFrame.add(centrePanel, "Center");       
        
        southPanel = new JPanel();
        
        //Set a Box for the southPanel. The components inside will be laid out 
        //next to each other.
        //Add the components then add the southPanel to the customerListFrame.
        //Between each component, add some spacing.
        //See references (3)
        box = Box.createHorizontalBox();
        box.add(registerButton);
        box.add(Box.createHorizontalStrut(boxSpacing));
        box.add(backButton);
        southPanel.add(box);
        registationFrame.add(southPanel, "South");
        //Tell the program to terminate if the loginFrame is closed.
        //See references (1)
          registationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Set the dimentions of the loginFrame and set its visibility to true.
        registationFrame.setSize(frameSideLength - 100, frameSideLength - 200);
        registationFrame.setVisible(true);

        //Add a listener to the buttons.
        registerButton.addActionListener(new RegistrationListener(this, registationFrame));
        backButton.addActionListener(new RegistrationListener(this, registationFrame));
    }
}
