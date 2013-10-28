package org.prosjekt.client;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import org.prosjekt.helperclasses.Farmer;

public class PasswordBox extends JPanel implements ActionListener {
	
	private Gui g;
	private Main main;
	
	private boolean test = false;
	private boolean isCorrect = false;
	//Disse maa hentes fra server
	//private String tempUsername = "admin";
	//private char[] correctPassword = {'a','d','m','i','n'};
	private Farmer currentUser;
	//Husk aa legge et eget felt for brukernavn
	private static String OK = "ok";
    private static String HELP = "help";
    
    private JFrame controllingFrame;
    private JPasswordField passwordField;
    private JTextField usernameField;
    
    public PasswordBox(Gui gui){
    	controllingFrame = gui.frame;
    	g = gui;
    	
    	usernameField = new JTextField(10);
    	usernameField.setActionCommand(OK);
    	usernameField.addActionListener(this);
    	
    	passwordField = new JPasswordField(10);
    	passwordField.setActionCommand(OK);
    	passwordField.addActionListener(this);
    	
    	JLabel usernameLabel = new JLabel("Enter username");
    	JLabel passwordLabel = new JLabel("Enter password: ");
    	
    	usernameLabel.setLabelFor(usernameField);
    	passwordLabel.setLabelFor(passwordField);
    	JComponent buttonPane = createButtonPanel();
    	
    	JPanel passwordTextPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));
    	JPanel usernameTextPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));
    	usernameTextPane.add(usernameLabel);
    	usernameTextPane.add(usernameField);
    	passwordTextPane.add(passwordLabel);
    	passwordTextPane.add(passwordField);
    	
    	add(usernameTextPane);
    	add(passwordTextPane);
    	add(buttonPane);
    }
    protected JComponent createButtonPanel(){
    	JPanel p = new JPanel(new GridLayout(0,1));
    	JButton okButton = new JButton("OK");
    	JButton helpButton = new JButton("HELP");
    	
    	okButton.setActionCommand(OK);
    	helpButton.setActionCommand(HELP);
    	okButton.addActionListener(this);
    	helpButton.addActionListener(this);
    	p.add(okButton);
    	p.add(helpButton);
    	
    	return p;
    }
    
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		if (OK.equals(cmd)){
			char[] inputPassword = passwordField.getPassword();
			String inputUsername = usernameField.getText();
			boolean listContainsCurrentUserName = false;
//			for(Farmer f : main.testServer.getFarmerList()){
//				if(f.getId().equals(inputUsername)){
//					listContainsCurrentUserName = true;
//					currentUser=f;
//					break;
//				}
//			}
			if(listContainsCurrentUserName){
				if (isPasswordCorrect(inputPassword)){
					//JOptionPane.showMessageDialog(controllingFrame, "Succes!");
					g.passwordConfirmed(currentUser);
					//String testNavn = currentUser.getFirstName();
					//System.out.println(testNavn.toString());
					//g.setCurrentUser(currentUser);
				}
				else {
					JOptionPane.showMessageDialog(controllingFrame, "invalid password",
							"error messager0", JOptionPane.ERROR_MESSAGE);
				}
			}
			else{
				JOptionPane.showMessageDialog(controllingFrame, "invalid username",
						"error messager0", JOptionPane.ERROR_MESSAGE);
			}

		}
		
	}
	
        
        /**
         * Edit christbu: password boer vaere hasha i databasen.  
         *  Inntil videre send inn plain text som String. 
         *  
         * @param input String passhash/password 
         * @return true if password is correct.   
         */
	private boolean isPasswordCorrect(char[] input){
		//boolean isCorrect = false;
//		main.testServer.getFarme
		char[] correctPassword = currentUser.getPasshash().toCharArray();
		if (input.length != correctPassword.length) {
			isCorrect = false;
		}
		else {
			isCorrect = Arrays.equals(input, correctPassword);
		}
		//Arrays.fill(correctPassword, '0');
		
		return isCorrect;
	}
    protected void resetFocus(){
    	usernameField.requestFocusInWindow();
    }
    public boolean getCorrect(){
    	return isCorrect;
    }
    
    public void setPassword(char[] password){
    	
    }
    public Farmer getCurrentUser(){
    	return currentUser;
    	
    }



}
