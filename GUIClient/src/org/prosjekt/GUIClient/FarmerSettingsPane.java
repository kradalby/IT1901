package org.prosjekt.GUIClient;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;




import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.Border;

public class FarmerSettingsPane extends JPanel implements ActionListener{
	private Farmer currentUser;	//kan hende denne kan fjernes
	private JLabel personHeader;
	private JLabel firstName;
	private JLabel lastName;
	private JLabel addressHeader;
	private JLabel street;
	private JLabel zip;
	private JLabel city;
	private JLabel contactHeader;
	private JLabel phone;
	private JLabel email;
	private JLabel helperHeader;
	private JLabel helperFirstName;
	private JLabel helperLastName;
	private JLabel helperEmail;
	private JLabel helperPhone;
	private JTextField textFirstName;
	private JTextField textLastName;
	private JTextField textStreet;
	private JTextField textZip;
	private JTextField textCity;
	private JTextField textPhone;
	private JTextField textEmail;
	private JTextField textHelperFirstName;
	private JTextField textHelperLastName;
	private JTextField textHelperEmail;
	private JTextField textHelperPhone;
	private Border standardBorder = BorderFactory.createLineBorder(Color.black);
	private JFrame frame;
	final static int GAP = 10;
	
	protected JTabbedPane tabbedPane; //Burde ha en getter isteden, EDIT!vet ikke hvorfor dette er skrevet
	
	
	public FarmerSettingsPane(Farmer user, JFrame frame){
		//super(new GridLayout(1 ,1));  Kan hende denne trengs for at fanene skal fungere optimalt
		super.setPreferredSize(new Dimension(550, 550));
		this.currentUser = user;
		this.frame = frame;
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		
		
		
		
		tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Personal Details", createPersonalDataTab());
		tabbedPane.addTab("Password", createPasswordTab());
		//sett inn flere faner her dersom det er n�dvendig
		
		add(tabbedPane);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	}
	
	//Lager hele personal information fanen
	private JPanel createPersonalDataTab(){
		
		//Lager et JPanel som skal holde alle elementene i dette vinduet
		JPanel personalData = new JPanel(new SpringLayout());
		
		
		//Lager overskriften for Personal Information
		JLabel emptyLabel1 = new JLabel("", JLabel.TRAILING);
		personalData.add(emptyLabel1);
		personHeader = new JLabel("Personal Information");
		personalData.add(personHeader);
		
		//Lager elementene for fornavn og etternavn
		firstName = new JLabel("First Name: ", JLabel.TRAILING);
		personalData.add(firstName);
		textFirstName = new JTextField(currentUser.getFirstName());
		firstName.setLabelFor(textFirstName);
		personalData.add(textFirstName);
		
		lastName = new JLabel("Last Name: ", JLabel.TRAILING);
		personalData.add(lastName);
		textLastName = new JTextField(currentUser.getLastName());
		lastName.setLabelFor(textLastName);
		personalData.add(textLastName);
		
		//Lager overskriften for address information
		JLabel emptyLabel2 = new JLabel("", JLabel.TRAILING);
		personalData.add(emptyLabel2);
		addressHeader = new JLabel("Address Information");
		personalData.add(addressHeader);
		
		//Lager elementene for adresseopplysninger
		street = new JLabel("Street: ", JLabel.TRAILING);
		personalData.add(street);
		textStreet = new JTextField("Her m� adresse fylles inn, legges inn i farmer-objektet");	//!!!
		street.setLabelFor(textStreet);
		personalData.add(textStreet);
		
		zip = new JLabel("Zip: ", JLabel.TRAILING);
		personalData.add(zip);
		textZip = new JTextField("Her m� zip legges inn i farmer-objektet");	//!!!
		zip.setLabelFor(textZip);	
		personalData.add(textZip);
		
		city = new JLabel("City:", JLabel.TRAILING);
		personalData.add(city);
		textCity = new JTextField("Her m� city fylles ut i farmer-objektet");	//!!!
		city.setLabelFor(textCity);
		personalData.add(textCity);
		
		//Lager overskriften for Contact Information
		JLabel emptyLabel3 = new JLabel("", JLabel.TRAILING);
		personalData.add(emptyLabel3);
		contactHeader = new JLabel("Contact Information");
		personalData.add(contactHeader);
		
		//Lager elementene for contact information
		phone = new JLabel("Phone: ", JLabel.TRAILING);
		personalData.add(phone);
		textPhone = new JTextField(currentUser.getPhone());
		phone.setLabelFor(textPhone);
		personalData.add(textPhone);
		
		email = new JLabel("Email: ", JLabel.TRAILING);
		personalData.add(email);
		textEmail = new JTextField(currentUser.getEmail());
		email.setLabelFor(textEmail);
		personalData.add(textEmail);
		
		//Lager overskrift for helper
		JLabel emptyLabel4 = new JLabel("", JLabel.TRAILING);
		personalData.add(emptyLabel4);
		helperHeader = new JLabel("Helper Information");
		personalData.add(helperHeader);
		
		//Lager elementene for helper information
		helperFirstName = new JLabel("First Name: ", JLabel.TRAILING);
		personalData.add(helperFirstName);
		textHelperFirstName = new JTextField(currentUser.getHelperFirstName());
		helperFirstName.setLabelFor(textHelperFirstName);
		personalData.add(textHelperFirstName);
		
		helperLastName = new JLabel("Last Name: ", JLabel.TRAILING);
		personalData.add(helperLastName);
		textHelperLastName = new JTextField(currentUser.getHelperLastName());
		helperLastName.setLabelFor(textHelperLastName);
		personalData.add(textHelperLastName);
		
		helperEmail = new JLabel("Email: ", JLabel.TRAILING);
		personalData.add(helperEmail);
		textHelperEmail = new JTextField(currentUser.getHelperEmail());
		helperEmail.setLabelFor(textHelperEmail);
		personalData.add(textHelperEmail);
		
		helperPhone = new JLabel("Phone; ", JLabel.TRAILING);
		personalData.add(helperPhone);
		textHelperPhone = new JTextField(currentUser.getHelperPhone());
		helperPhone.setLabelFor(textHelperPhone);
		personalData.add(textHelperPhone);
		
		
		//Legger til knappene nederst i vinduet
		JLabel empytLabel = new JLabel("", JLabel.TRAILING);
		personalData.add(empytLabel);
		personalData.add(createButtons());
		
		
		//Denne organiserer alle elementene i et grid, er n�dvendig for at springlayout skal gi best mulig resultat 
		//P� denne skal kun den f�rste int endres til � passe antall elementer
		SpringUtilities.makeCompactGrid(personalData,16,2,6,6,6,6);
		
		
		
		//kan hende vi m� kalle personalData.setOpaque(true);

		return personalData;
	}
	
	private JPanel createPasswordTab(){
		//JPanel passwordTab = new JPanel(new BorderLayout());
		//passwordTab.setPreferredSize(new Dimension(400,200));
		JPanel passwordField = new JPanel(new GridLayout(5,3));
		
		JLabel fill1 = new JLabel("");
		passwordField.add(fill1);
		JLabel fill2 = new JLabel("");
		passwordField.add(fill2);
		JLabel fill3 = new JLabel("");
		passwordField.add(fill3);
		

		//Lager boksen for old password
		JLabel oldPasswordText = new JLabel("Old Password: ", JLabel.TRAILING);
		passwordField.add(oldPasswordText);
		JPasswordField oldPassword = new JPasswordField(10);
		oldPasswordText.setLabelFor(oldPassword);
		passwordField.add(oldPassword);
		
		JLabel emptyLabel1 = new JLabel("", JLabel.TRAILING);
		passwordField.add(emptyLabel1);
		
		//Lager boksen for new password
		JLabel newPasswordText = new JLabel("New Password: ", JLabel.TRAILING);
		passwordField.add(newPasswordText);
		JPasswordField newPassword = new JPasswordField(10);
		newPasswordText.setLabelFor(newPassword);
		passwordField.add(newPassword);
		
		JLabel emptyLabel2 = new JLabel("", JLabel.TRAILING);
		passwordField.add(emptyLabel2);
		
		//Lager boksen for retype new password
		JLabel retypePasswordText = new JLabel("Retype new password:", JLabel.TRAILING);
		passwordField.add(retypePasswordText);
		JPasswordField retypeNewPassword = new JPasswordField(10);
		retypePasswordText.setLabelFor(retypeNewPassword);
		passwordField.add(retypeNewPassword);
		
		JLabel emptyLabel3 = new JLabel("", JLabel.TRAILING);
		passwordField.add(emptyLabel3);
		
		
		//lager ok/cancel/apply bokser
		JLabel emptyLabel4 = new JLabel("");
		passwordField.add(emptyLabel4);
		passwordField.add(createButtonsForChangePassword());
		
		return passwordField;
	}
	
	
	private JPanel createButtonsForChangePassword(){
		JPanel buttons = new JPanel();
		JButton ok = new JButton("OK");
		ok.addActionListener(this);
		ok.setActionCommand("okPassword");
		buttons.add(ok);
		
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(this);
		cancel.setActionCommand("cancelPassword");
		buttons.add(cancel);
		
		JButton apply = new JButton("Apply");
		apply.addActionListener(this);
		apply.setActionCommand("applyPassword");
		buttons.add(apply);
		
		return buttons;	
	}
	
	
	//Creates OK, APPLY, cancel
	private JPanel createButtons(){
		JPanel buttons = new JPanel();
		JButton ok = new JButton("OK");
		ok.addActionListener(this);
		ok.setActionCommand("ok");
		buttons.add(ok);
		
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(this);
		cancel.setActionCommand("cancel");
		buttons.add(cancel);
		
		JButton apply = new JButton("Apply");
		apply.addActionListener(this);
		apply.setActionCommand("apply");
		buttons.add(apply);
		
		return buttons;	
	}
	
	//registrerer knappe-trykk
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if(command == "cancel"){
			frame.dispose();
		}
		else if(command == "ok"){
			saveData();
			frame.dispose();
		}
		else if(command == "apply"){
			saveData();
		}	
	}

	//Lagrer data n�r ok/apply er trykket
	private void saveData(){
		Gui.currentUser.setFirstName(textFirstName.getText());
		Gui.currentUser.setLastName(textLastName.getText());
		Gui.currentUser.setEmail(textEmail.getText());
		Gui.currentUser.setPhone(textPhone.getText());
		Gui.currentUser.setHelperFirstName(textHelperFirstName.getText());
		Gui.currentUser.setHelperLastName(textHelperLastName.getText());
		Gui.currentUser.setHelperEmail(textHelperEmail.getText());
		Gui.currentUser.setHelperPhone(textHelperPhone.getText());	
		
	}
	
	
	//Tester om inntastet gammeltpassord er korrekt
	private boolean checkOldPasswor(char[] input){
		boolean tester = false;
		char[] oldPassword = Gui.currentUser.getPassword();
		if (input.length != oldPassword.length) {
			tester = false;
		}
		else {
			tester = Arrays.equals(input, oldPassword);
		}
		
		return tester;
	}

	
	
	

}
