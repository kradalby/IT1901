package org.prosjekt.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import org.prosjekt.client.ClientService;
import org.prosjekt.helperclasses.Farmer;
import org.prosjekt.helperclasses.Passhash;

public class UserInformation extends JFrame implements ActionListener {
	private static String OK = "ok";
    private static String CANCEL = "cancel";
    private static String APPLY = "apply";
    
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField phoneTextField;
    private JTextField emailTextField;
    private JPasswordField oldPasswordField;
    private JPasswordField newPasswordField;
    private JPasswordField retypePasswordField;
    
    
    private Font headerFont = new Font("kalinga", Font.PLAIN, 24);
    private Font smallHeaderFont = new Font("kalinga", Font.PLAIN, 22);
    private Font font = new Font("kalinga", Font.PLAIN, 16);
    private Font fontTextField = new Font("kalinga", Font.PLAIN, 12);
	private Color textColor = new Color(32, 87, 0);
	private Farmer tempUser;
	

	
	
	public UserInformation(Farmer f){	
		super.setContentPane(new BackgroundPanel(ClientService.pathToBackGround()));
		setLayout(new BorderLayout());
		tempUser = f;
		createContent();
		
		
		pack();
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(455, 475);
		setVisible(true);
	}
	
	
	private void createContent(){
		
		
		add(createUserInformationHeader(), BorderLayout.PAGE_START);
		
		JLabel emptyLeftSide = new JLabel("");
		emptyLeftSide.setPreferredSize(new Dimension(60,50));	
		add(emptyLeftSide, BorderLayout.LINE_START);
		
		add(createContentPanel(), BorderLayout.CENTER);
		
		JLabel emptyRightSide = new JLabel("");
		emptyRightSide.setPreferredSize(new Dimension(40,50));
		add(emptyRightSide, BorderLayout.LINE_END); 
		
		add(createButtons(), BorderLayout.PAGE_END);
	}
	
	private JPanel createUserInformationHeader(){
		JPanel header = new JPanel(new BorderLayout());
		header.setPreferredSize(new Dimension(90,100));
		header.setOpaque(false);
		
		JLabel emptyLeft = new JLabel("");
		emptyLeft.setPreferredSize(new Dimension(130,100));
		header.add(emptyLeft, BorderLayout.LINE_START);
		
		JLabel emptyTop = new JLabel("");
		emptyTop.setPreferredSize(new Dimension(25,35));
		header.add(emptyTop, BorderLayout.PAGE_START);
		
		JLabel headerLabel = new JLabel("User Information");
		headerLabel.setOpaque(false);
		headerLabel.setForeground(textColor);
		headerLabel.setFont(headerFont);
		header.add(headerLabel, BorderLayout.CENTER);
		
		JLabel emptyBottom = new JLabel("");
		emptyBottom.setPreferredSize(new Dimension(50,15));
		header.add(emptyBottom, BorderLayout.PAGE_END);
		
		return header;
	}
	
	private JPanel createContentPanel(){
		JPanel contentPanel = new JPanel(new BorderLayout());
		contentPanel.setOpaque(false);
		
		JPanel nameAndContact = new JPanel(new BorderLayout());
		nameAndContact.setOpaque(false);
		nameAndContact.add(createLeftSideNameContact(), BorderLayout.LINE_START);
		nameAndContact.add(createRightSideNameContact(), BorderLayout.LINE_END);
		contentPanel.add(nameAndContact, BorderLayout.PAGE_START);
		
		JLabel line = new JLabel("__________________________________________________________");
		line.setForeground(new Color(32, 130, 0));
		line.setFont(new Font("kalinga", Font.PLAIN, 12));
		contentPanel.add(line, BorderLayout.CENTER);
		//contentPanel.add(createPasswordHeader(), BorderLayout.CENTER);
		
		JPanel password = new JPanel(new BorderLayout());
		password.setOpaque(false);
		password.add(createLeftSidePassword(), BorderLayout.LINE_START);
		password.add(createRightSidePassword(), BorderLayout.LINE_END);
		contentPanel.add(password, BorderLayout.PAGE_END);
		
		
		return contentPanel;
		
	}
	
	//Ikke i bruk
	private JPanel createPasswordHeader(){
		JPanel header = new JPanel(new BorderLayout());
		header.setPreferredSize(new Dimension(90,50));
		header.setOpaque(false);
		
		JLabel emptyLeft = new JLabel("");
		emptyLeft.setPreferredSize(new Dimension(75,20));
		header.add(emptyLeft, BorderLayout.LINE_START);
		
		JLabel emptyTop = new JLabel("");
		emptyTop.setPreferredSize(new Dimension(1,1));
		//header.add(emptyTop, BorderLayout.PAGE_START);
		
		JLabel headerLabel = new JLabel("Change Password");	//endre til change password?
		headerLabel.setOpaque(false);
		headerLabel.setForeground(textColor);
		headerLabel.setFont(smallHeaderFont);
		header.add(headerLabel, BorderLayout.CENTER);
		
		JLabel emptyBottom = new JLabel("");
		emptyBottom.setPreferredSize(new Dimension(50,15));
		//header.add(emptyBottom, BorderLayout.PAGE_END);
		
		return header;
	}

	private JPanel createLeftSideNameContact(){
		JPanel leftSide = new JPanel();
		leftSide.setLayout(new BoxLayout(leftSide, BoxLayout.Y_AXIS));
		leftSide.setOpaque(false);
		
		JLabel firstNameLabel = new JLabel("First Name: ");
		firstNameLabel.setFont(font);
		firstNameLabel.setForeground(textColor);
		
		JLabel lastNameLabel = new JLabel("Last Name: ");
		lastNameLabel.setFont(font);
		lastNameLabel.setForeground(textColor);
		
		JLabel phoneLabel = new JLabel("Phone Number: ");
		phoneLabel.setFont(font);
		phoneLabel.setForeground(textColor);
		
		JLabel emailLabel = new JLabel("Email: ");
		emailLabel.setFont(font);
		emailLabel.setForeground(textColor);
		
		
		firstNameLabel.setAlignmentY(LEFT_ALIGNMENT);
		leftSide.add(firstNameLabel);
		
		lastNameLabel.setAlignmentY(LEFT_ALIGNMENT);
		leftSide.add(lastNameLabel);
		
		phoneLabel.setAlignmentY(LEFT_ALIGNMENT);
		leftSide.add(phoneLabel);
		
		emailLabel.setAlignmentY(LEFT_ALIGNMENT);
		leftSide.add(emailLabel);
		
		//Legg inn en separator her
		
		
		return leftSide;
	}
	
	private JPanel createRightSideNameContact(){
		JPanel rightSide = new JPanel();
		rightSide.setLayout(new BoxLayout(rightSide, BoxLayout.Y_AXIS));
		rightSide.setOpaque(false);
		
		firstNameField = new JTextField(tempUser.getFirstName(),15);
		firstNameField.setOpaque(false);
		firstNameField.setForeground(textColor);
		firstNameField.setFont(fontTextField);
		firstNameField.setActionCommand(OK);
		firstNameField.addActionListener(this);
		
		lastNameField = new JTextField(tempUser.getLastName(), 15);
		lastNameField.setOpaque(false);
		lastNameField.setForeground(textColor);
		lastNameField.setFont(fontTextField);
		lastNameField.setActionCommand(OK);
		lastNameField.addActionListener(this);
		
		phoneTextField = new JTextField(tempUser.getPhone(), 15);
		phoneTextField.setOpaque(false);
		phoneTextField.setForeground(textColor);
		phoneTextField.setFont(fontTextField);
		phoneTextField.setActionCommand(OK);
		phoneTextField.addActionListener(this);
		
		emailTextField = new JTextField(tempUser.getEmail(), 15);
		emailTextField.setOpaque(false);
		emailTextField.setForeground(textColor);
		emailTextField.setFont(fontTextField);
		emailTextField.setActionCommand(OK);
		emailTextField.addActionListener(this);
		

		
		
		firstNameField.setAlignmentY(LEFT_ALIGNMENT);
		rightSide.add(firstNameField);
		
		lastNameField.setAlignmentY(LEFT_ALIGNMENT);
		rightSide.add(lastNameField);
		
		phoneTextField.setAlignmentY(LEFT_ALIGNMENT);
		rightSide.add(phoneTextField);
		
		emailTextField.setAlignmentY(LEFT_ALIGNMENT);
		rightSide.add(emailTextField);
		
		//rightSide.add(new JSeparator());
		
		return rightSide;
		
	}
	
	private JPanel createLeftSidePassword(){
		JPanel leftSide = new JPanel();
		leftSide.setLayout(new BoxLayout(leftSide, BoxLayout.Y_AXIS));
		leftSide.setOpaque(false);
		
		JLabel oldPasswordLabel = new JLabel("Old Password: ");
		oldPasswordLabel.setFont(font);
		oldPasswordLabel.setForeground(textColor);
		
		JLabel newPasswordLabel = new JLabel("New Password: ");
		newPasswordLabel.setFont(font);
		newPasswordLabel.setForeground(textColor);
		
		JLabel retypePasswordLabel = new JLabel("Retype new password: ");
		retypePasswordLabel.setFont(font);
		retypePasswordLabel.setForeground(textColor);
		
		oldPasswordLabel.setAlignmentY(LEFT_ALIGNMENT);
		leftSide.add(oldPasswordLabel);
		
		newPasswordLabel.setAlignmentY(LEFT_ALIGNMENT);
		leftSide.add(newPasswordLabel);
		
		retypePasswordLabel.setAlignmentY(LEFT_ALIGNMENT);
		leftSide.add(retypePasswordLabel);
		
		
		
		return leftSide;
	}
	
	private JPanel createRightSidePassword(){
		JPanel rightSide = new JPanel();
		rightSide.setLayout(new BoxLayout(rightSide, BoxLayout.Y_AXIS));
		rightSide.setOpaque(false);
		
		oldPasswordField = new JPasswordField(15);
		oldPasswordField.setOpaque(false);
		oldPasswordField.setForeground(textColor);
		oldPasswordField.setFont(fontTextField);
		oldPasswordField.setActionCommand(OK);
		oldPasswordField.addActionListener(this);
		
		newPasswordField = new JPasswordField(15);
		newPasswordField.setOpaque(false);
		newPasswordField.setForeground(textColor);
		newPasswordField.setFont(fontTextField);
		newPasswordField.setActionCommand(OK);
		newPasswordField.addActionListener(this);
		
		retypePasswordField = new JPasswordField(15);
		retypePasswordField.setOpaque(false);
		retypePasswordField.setForeground(textColor);
		retypePasswordField.setFont(fontTextField);
		retypePasswordField.setActionCommand(OK);
		retypePasswordField.addActionListener(this);
		
		oldPasswordField.setAlignmentY(LEFT_ALIGNMENT);
		rightSide.add(oldPasswordField);
		
		newPasswordField.setAlignmentY(LEFT_ALIGNMENT);
		rightSide.add(newPasswordField);
		
		retypePasswordField.setAlignmentY(LEFT_ALIGNMENT);
		rightSide.add(retypePasswordField);
		
		
		return rightSide;
	}

	private JPanel createButtons(){
		JPanel buttons = new JPanel(new BorderLayout());
		buttons.setPreferredSize(new Dimension(100,140));
    	buttons.setOpaque(false);
    	
    	JLabel buttonsEmptyTop = new JLabel("");
    	buttonsEmptyTop.setPreferredSize(new Dimension(100,50));
    	buttons.add(buttonsEmptyTop, BorderLayout.PAGE_START);
    	
    	JLabel buttonsEmptyLeft = new JLabel("");
    	buttonsEmptyLeft.setPreferredSize(new Dimension(60,50));
    	buttons.add(buttonsEmptyLeft, BorderLayout.LINE_START);
    	
    	JLabel buttonsEmptyRigt = new JLabel("");
    	buttonsEmptyRigt.setPreferredSize(new Dimension(40,50));
    	buttons.add(buttonsEmptyRigt, BorderLayout.LINE_END);
    	
    	JLabel buttonsEmptyBottom = new JLabel("");
    	buttonsEmptyBottom.setPreferredSize(new Dimension(20,50));
    	buttons.add(buttonsEmptyBottom, BorderLayout.PAGE_END);
    	
    	JPanel buttonsPanel = new JPanel(new BorderLayout());
    	buttonsPanel.setOpaque(false);
    	JButton okButton = new JButton(OK);
    	okButton.setPreferredSize(new Dimension(100,10));
    	okButton.setOpaque(false);
    	okButton.setForeground(textColor);
    	okButton.setBackground(textColor);
    	okButton.setFont(font);
    	okButton.setActionCommand(OK);
    	okButton.addActionListener(this);
    	
    	JButton applyButton = new JButton(APPLY);
    	applyButton.setPreferredSize(new Dimension(100,10));
    	applyButton.setOpaque(false);
    	applyButton.setForeground(textColor);
    	applyButton.setBackground(textColor);
    	applyButton.setFont(font);
    	applyButton.setActionCommand(APPLY);
    	applyButton.addActionListener(this);
    	
    	JButton cancelButton = new JButton(CANCEL);
    	cancelButton.setPreferredSize(new Dimension(100,10));
    	cancelButton.setOpaque(false);
    	cancelButton.setForeground(textColor);
    	cancelButton.setBackground(textColor);
    	cancelButton.setFont(font);
    	cancelButton.setActionCommand(CANCEL);
    	cancelButton.addActionListener(this);
    	
    	JPanel applyPanel = new JPanel(new BorderLayout());
    	applyPanel.setOpaque(false);
    	JLabel applyEmptyLeft = new JLabel("");
    	applyEmptyLeft.setPreferredSize(new Dimension(20,10));
    	JLabel applyEmptyRight = new JLabel("");
    	applyEmptyRight.setPreferredSize(new Dimension(20,10));
    	applyPanel.add(applyEmptyLeft, BorderLayout.LINE_START);
    	applyPanel.add(applyButton, BorderLayout.CENTER);
    	applyPanel.add(applyEmptyRight, BorderLayout.LINE_END);
    	
    	buttonsPanel.add(okButton, BorderLayout.LINE_START);
    	buttonsPanel.add(applyPanel, BorderLayout.CENTER);
    	buttonsPanel.add(cancelButton, BorderLayout.LINE_END);
    	
    	buttons.add(buttonsPanel, BorderLayout.CENTER);
    	
    	return buttons;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		if (OK.equals(cmd)){
			if (!saveChanges()){
				JOptionPane.showMessageDialog(this, "Changes were not saved! Please try again.",
						"", JOptionPane.ERROR_MESSAGE);
			}
			else{
				this.dispose();
			}
			
		}
		else if (APPLY.equals(cmd)){
			if (!saveChanges()){
				JOptionPane.showMessageDialog(this, "Changes were not saved! Please try again.",
						"", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		else if (CANCEL.equals(cmd)){
			this.dispose();
		}
		

	}
	
	private boolean saveChanges(){
		char [] empty = {};
		boolean changeSaved = false;
		tempUser.setFirstName(firstNameField.getText());
		tempUser.setLastName(lastNameField.getText());
		tempUser.setEmail(emailTextField.getText());
		tempUser.setPhone(phoneTextField.getText());
		if(!Arrays.equals(oldPasswordField.getPassword(), empty)
				||!Arrays.equals(newPasswordField.getPassword(), empty)
				||!Arrays.equals(retypePasswordField.getPassword(), empty)){
			if(passwordTest()){
				changeSaved = Main.saveChangesToFarmer(tempUser);
			}			
		}
		else{
			changeSaved = Main.saveChangesToFarmer(tempUser);
		}
		
		
		return changeSaved;
	}
	
	private boolean passwordTest(){
		boolean passwordChangeOK = false;
		char [] inputOldPassword = oldPasswordField.getPassword();
		char [] inputNewPassword = newPasswordField.getPassword();
		char [] inputRetypePassword = retypePasswordField.getPassword();
		Passhash userPasshash = tempUser.getPasshash();
		char [] realOldPassword = userPasshash.toCharArray();
		if (Arrays.equals(realOldPassword, inputOldPassword)){
			if(Arrays.equals(inputNewPassword, inputRetypePassword)){ 
				String newPassword = new String(inputNewPassword);
				userPasshash.setPasshash(newPassword);
				tempUser.setPasshash(userPasshash);
				passwordChangeOK = true;
				JOptionPane.showMessageDialog(this, "Password changed!",
						"", JOptionPane.INFORMATION_MESSAGE);
				
				
			}
		}
		oldPasswordField.setText("");
		newPasswordField.setText("");
		retypePasswordField.setText("");
		
		return passwordChangeOK;
	}
}
