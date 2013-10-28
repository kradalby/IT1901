package org.prosjekt.client;

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
import org.prosjekt.GUIClient.SpringUtilities;
import org.prosjekt.helperclasses.Farmer;
import org.prosjekt.helperclasses.Helper;

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
		super.setPreferredSize(new Dimension(800, 600));
		this.currentUser = user;
		this.frame = frame;
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		
		
		
		
		tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Personal Details", createPersonalDataTab());
		tabbedPane.addTab("Password", createPasswordTab());
		//sett inn flere faner her dersom det er noedvendig
		
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
		textStreet = new JTextField("Her maa adresse fylles inn, legges inn i farmer-objektet");	//!!!
		street.setLabelFor(textStreet);
		personalData.add(textStreet);
		
		zip = new JLabel("Zip: ", JLabel.TRAILING);
		personalData.add(zip);
		textZip = new JTextField("Her maa zip legges inn i farmer-objektet");	//!!!
		zip.setLabelFor(textZip);	
		personalData.add(textZip);
		
		city = new JLabel("City:", JLabel.TRAILING);
		personalData.add(city);
		textCity = new JTextField("Her maa city fylles ut i farmer-objektet");	//!!!
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
		textHelperFirstName = new JTextField(currentUser.getHelpers().get(0).getFirstname());
		helperFirstName.setLabelFor(textHelperFirstName);
		personalData.add(textHelperFirstName);
		
		helperLastName = new JLabel("Last Name: ", JLabel.TRAILING);
		personalData.add(helperLastName);
		textHelperLastName = new JTextField(currentUser.getHelpers().get(0).getLastname());
		helperLastName.setLabelFor(textHelperLastName);
		personalData.add(textHelperLastName);
		
		helperEmail = new JLabel("Email: ", JLabel.TRAILING);
		personalData.add(helperEmail);
		textHelperEmail = new JTextField(currentUser.getHelpers().get(0).getEmail());
		helperEmail.setLabelFor(textHelperEmail);
		personalData.add(textHelperEmail);
		
		helperPhone = new JLabel("Phone; ", JLabel.TRAILING);
		personalData.add(helperPhone);
		textHelperPhone = new JTextField(currentUser.getHelpers().get(0).getPhone());
		helperPhone.setLabelFor(textHelperPhone);
		personalData.add(textHelperPhone);
		
		
		//Legger til knappene nederst i vinduet
		JLabel empytLabel = new JLabel("", JLabel.TRAILING);
		personalData.add(empytLabel);
		personalData.add(createButtons());
		
		
		//Denne organiserer alle elementene i et grid, er noedvendig for at springlayout skal gi best mulig resultat 
		//Paa denne skal kun den foerste int endres til aa passe antall elementer
		SpringUtilities.makeCompactGrid(personalData,16,2,6,6,6,6);
		
		
		
		//kan hende vi maa kalle personalData.setOpaque(true);

		return personalData;
	}
	
	//Lager "change password"-fanen
	private JPanel createPasswordTab(){
		//JPanel passwordTab = new JPanel(new BorderLayout());
		//passwordTab.setPreferredSize(new Dimension(400,200));
		JPanel passwordField = new JPanel(new GridLayout(17,3));
		
		//Fill-labels laget for aa faa denne siden til aa se ut som den gjoer
		JLabel fill1 = new JLabel("");
		passwordField.add(fill1);
		JLabel fill2 = new JLabel("");
		passwordField.add(fill2);
		JLabel fill3 = new JLabel("");
		passwordField.add(fill3);
		
		JLabel fill7 = new JLabel("");
		passwordField.add(fill7);
		JLabel fill8 = new JLabel("");
		passwordField.add(fill8);
		JLabel fill9 = new JLabel("");
		passwordField.add(fill9);
		
		JLabel fill22 = new JLabel("");
		passwordField.add(fill22);
		JLabel fill23 = new JLabel("");
		passwordField.add(fill23);
		JLabel fill24 = new JLabel("");
		passwordField.add(fill24);
		
		JLabel fill31 = new JLabel("");
		passwordField.add(fill31);
		JLabel fill32 = new JLabel("");
		passwordField.add(fill32);
		JLabel fill33 = new JLabel("");
		passwordField.add(fill33);
		
		JLabel fill34 = new JLabel("");
		passwordField.add(fill34);
		JLabel fill35 = new JLabel("");
		passwordField.add(fill35);
		JLabel fill36 = new JLabel("");
		passwordField.add(fill36);
		

		//Lager boksen for old password
		JLabel oldPasswordText = new JLabel("Old Password: ", JLabel.TRAILING);
		passwordField.add(oldPasswordText);
		JPasswordField oldPassword = new JPasswordField(10);
		oldPasswordText.setLabelFor(oldPassword);
		passwordField.add(oldPassword);
		JLabel emptyLabel1 = new JLabel("", JLabel.TRAILING);
		passwordField.add(emptyLabel1);
		
		JLabel fill13 = new JLabel("");
		passwordField.add(fill13);
		JLabel fill14 = new JLabel("");
		passwordField.add(fill14);
		JLabel fill15 = new JLabel("");
		passwordField.add(fill15);
		
		//Lager boksen for new password
		JLabel newPasswordText = new JLabel("New Password: ", JLabel.TRAILING);
		passwordField.add(newPasswordText);
		JPasswordField newPassword = new JPasswordField(10);
		newPasswordText.setLabelFor(newPassword);
		passwordField.add(newPassword);
		JLabel emptyLabel2 = new JLabel("", JLabel.TRAILING);
		passwordField.add(emptyLabel2);
		
		JLabel fill16 = new JLabel("");
		passwordField.add(fill16);
		JLabel fill17 = new JLabel("");
		passwordField.add(fill17);
		JLabel fill18 = new JLabel("");
		passwordField.add(fill18);
		
		//Lager boksen for retype new password
		JLabel retypePasswordText = new JLabel("Retype new password:", JLabel.TRAILING);
		passwordField.add(retypePasswordText);
		JPasswordField retypeNewPassword = new JPasswordField(10);
		retypePasswordText.setLabelFor(retypeNewPassword);
		passwordField.add(retypeNewPassword);
		JLabel emptyLabel3 = new JLabel("", JLabel.TRAILING);
		passwordField.add(emptyLabel3);
		
		JLabel fill19 = new JLabel("");
		passwordField.add(fill19);
		JLabel fill20 = new JLabel("");
		passwordField.add(fill20);
		JLabel fill21 = new JLabel("");
		passwordField.add(fill21);
		
		//lager ok/cancel/apply bokser
		JLabel emptyLabel4 = new JLabel("");
		passwordField.add(emptyLabel4);
		passwordField.add(createButtonsForChangePassword());
		JLabel emptyLabel5 = new JLabel("");
		passwordField.add(emptyLabel5);
		
		
		
		JLabel fill4 = new JLabel("");
		passwordField.add(fill4);
		JLabel fill5 = new JLabel("");
		passwordField.add(fill5);
		JLabel fill6 = new JLabel("");
		passwordField.add(fill6);
		
		JLabel fill10 = new JLabel("");
		passwordField.add(fill10);
		JLabel fill11 = new JLabel("");
		passwordField.add(fill11);
		JLabel fill12 = new JLabel("");
		passwordField.add(fill12);
		
		JLabel fill25 = new JLabel("");
		passwordField.add(fill25);
		JLabel fill26 = new JLabel("");
		passwordField.add(fill26);
		JLabel fill27 = new JLabel("");
		passwordField.add(fill27);
		
		JLabel fill28 = new JLabel("");
		passwordField.add(fill28);
		JLabel fill29 = new JLabel("");
		passwordField.add(fill29);
		JLabel fill30 = new JLabel("");
		passwordField.add(fill30);
		
		JLabel fill37 = new JLabel("");
		passwordField.add(fill37);
		JLabel fill38 = new JLabel("");
		passwordField.add(fill38);
		JLabel fill39 = new JLabel("");
		passwordField.add(fill39);
		
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
		else if(command == "okPassword"){
			savePassword();
			frame.dispose();
		}
		else if(command == "cancelPassword"){
			frame.dispose();
		}
		else if(command == "applyPassword"){
			savePassword();
		}
	}

	//Lagrer data naar ok/apply er trykket
	private void saveData(){
		Gui.currentUser.setFirstName(textFirstName.getText());
		Gui.currentUser.setLastName(textLastName.getText());
		Gui.currentUser.setEmail(textEmail.getText());
		Gui.currentUser.setPhone(textPhone.getText());
                Helper helper = new Helper(currentUser.getId(),textHelperFirstName.getText(), textHelperLastName.getText(), textHelperPhone.getText(), textHelperEmail.getText() );
                Gui.currentUser.getHelpers().add(helper);
	}
	
	//Denne lagrer passordet til farmer-objektet dersom riktig gammelt passord er skrevet inn
	private void savePassword(){
		
	}
	
	
	//Tester om inntastet gammeltpassord er korrekt
	private boolean checkOldPasswor(char[] input){
		boolean tester = false;
		char[] oldPassword = Gui.currentUser.getPasshash().toCharArray();
		if (input.length != oldPassword.length) {
			tester = false;
		}
		else {
			tester = Arrays.equals(input, oldPassword);
		}
		
		return tester;
	}

	
	
	

}
