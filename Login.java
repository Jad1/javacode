//NAME: Jad El-Houssami
//ID: w13651455

//REFERNECES:
//(1)Oracle. Class JFrame. [online] Oracle. Available from: <http://docs.oracle.com/javase/7/docs/api/javax/swing/JFrame.html> [Accessed 8th October 2013]
//Note: Date of publication could not be found.
//(2)University of Westminster. Program GridBagLayout01. [Accessed 25th October 2013]
//Note: Date of publication could not be found.

package views;

import interfaces.MyConstants;
import java.awt.*;
import javax.swing.*;
import listeners.LoginListener;
/**
 *
 * @author Jad
 */

public class Login implements MyConstants{

        private JFrame loginFrame;
        private JLabel loginHeadingLabel, usernameLabel, passwordLabel;
        public JTextField usernameTextField;
        public JPasswordField passwordTextField;
        private JButton loginButton, registerButton;
        private JPanel northPanel, centrePanel;
        private GridBagLayout gbl; 
        private GridBagConstraints gbc;
        private Font font;
        
        //Display this frame.
    public Login(){
        //Initialise ths JFrame and heading label.
        loginFrame = new JFrame("Login");
        loginHeadingLabel = new JLabel("Login");
        
        //Create and add Font settings for loginHeadingLabel.
        font = new Font("Arial", Font.BOLD, MyConstants.headingFontSize); 
        loginHeadingLabel.setFont(font); 
        northPanel  = new JPanel();
        
        //Add loginHeadingLabel to northPanel and position it to the north of loginFrame.
        northPanel.add(loginHeadingLabel);
        loginFrame.add(northPanel, "North");
        
        //Instantiate atomic components to be added to the centre panel.
        usernameLabel = new JLabel("Username");
        passwordLabel = new JLabel ("Password");
        usernameTextField = new JTextField(MyConstants.textFieldSize);
        passwordTextField = new JPasswordField(MyConstants.textFieldSize);
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");
        
        //Create and add Font settings for usernameLabel and passwordLsbel
        font = new Font("Arial", Font.BOLD, MyConstants.nonHeadingFontSize);
        usernameLabel.setFont(font);
        passwordLabel.setFont(font);
        
        //Initialise centrePanel and objects relating to the GridBagLayout to be used within the centrePanel.
        centrePanel = new JPanel();
        gbl = new GridBagLayout(); 
        gbc  = new GridBagConstraints();
        
        //Set constraints for the components to be added to the centrePanel 
        //(which will have a GridBagLayout)
        //See references (2)
        //usernameLabel
        gbc.gridx = gbc.gridy = MyConstants.distanceFromOrigin;
        gbc.gridheight = gbc.gridwidth = MyConstants.componentLength;
        gbl.setConstraints(usernameLabel, gbc);

        //usernameTextField
        gbc.gridx = MyConstants.distanceFromOrigin + 3;
        gbc.gridy = MyConstants.distanceFromOrigin;
        gbc.gridheight = gbc.gridwidth = MyConstants.componentLength;
        gbl.setConstraints(usernameTextField, gbc);

        //passwordLabel
        gbc.gridx = MyConstants.distanceFromOrigin;
        gbc.gridy = MyConstants.distanceFromOrigin + 1;
        gbc.gridheight = gbc.gridwidth = MyConstants.componentLength;
        gbl.setConstraints(passwordLabel, gbc);
 
         //passwordTextField
        gbc.gridx = MyConstants.distanceFromOrigin + 3;
        gbc.gridy = MyConstants.distanceFromOrigin + 1;
        gbc.gridheight = gbc.gridwidth = MyConstants.componentLength;
        gbl.setConstraints(passwordTextField, gbc);
        
        //loginButton
        gbc.gridx = MyConstants.distanceFromOrigin + 1;
        gbc.gridy = MyConstants.distanceFromOrigin + 2;
        gbc.weighty = MyConstants.extraSpacing;
        gbc.gridheight = gbc.gridwidth = MyConstants.componentLength;
        gbl.setConstraints(loginButton, gbc);
        
        //registerButton
        gbc.gridx = MyConstants.distanceFromOrigin + 1;
        gbc.gridy = MyConstants.distanceFromOrigin + 3;
        gbc.ipadx = MyConstants.extraSpacing;
        gbc.gridheight = gbc.gridwidth = MyConstants.componentLength;
        gbl.setConstraints(registerButton, gbc); 
        
        
        //Add all the components given constraints to centrePanel.
        //Set the layout of the panel then add the panel to the centre of the loginFrame.
        centrePanel.add(usernameLabel);
        centrePanel.add(usernameTextField);
        centrePanel.add(passwordLabel);
        centrePanel.add(passwordTextField);
        centrePanel.add(loginButton);
        centrePanel.add(registerButton);
        
        //Set the layout of the centrePanel and add it to the centre of the loginFrame.
        centrePanel.setLayout(gbl);
        loginFrame.add(centrePanel, "Center");       
        
        
        //Tell the program to terminate if the loginFrame is closed.
        //See references (1)
          loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Set the dimentions of the loginFrame and set its visibility to true.
        loginFrame.setSize(MyConstants.frameSideLength - 100, MyConstants.frameSideLength - 200);        
        loginFrame.setVisible(true);
        
        //Add a listener to the register and logout buttons.
        registerButton.addActionListener(new LoginListener(loginFrame,this ));
        loginButton.addActionListener(new LoginListener(loginFrame,this ));
    }
    public static void main(String[] args) {
        Login login = new Login();
    }
}