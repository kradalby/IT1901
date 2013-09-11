package org.prosjekt.GUIClient;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;




import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.Border;

public class FarmerSettingsPane extends JPanel implements ActionListener{
	private Farmer currentUser;	//kan hende denne kan byttes ut ved å ha currentuser protected i gui
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
	private JLabel helperFirstname;
	private JLabel helperLastname;
	private JLabel helperEmail;
	private JLabel helperPhone;
	private JTextField textFirstName;
	private JTextField textLastName;
	private JTextField textStreet;
	private JTextField textZip;
	private JTextField textCity;
	private JTextField textPhone;
	private JTextField textEmail;
	private JTextField texthelperFirstName;
	private JTextField textHelperLastName;
	private JTextField textHelperEmail;
	private JTextField textHelperPhone;
	private Border standardBorder = BorderFactory.createLineBorder(Color.black);
	private JFrame frame;
	final static int GAP = 10;
	
	protected JTabbedPane tabbedPane; //Burde ha en getter isteden
	
	
	public FarmerSettingsPane(Farmer user, JFrame frame){
		//super(new GridLayout(1 ,1));
		super.setPreferredSize(new Dimension(800, 600));
		this.currentUser = user;
		this.frame = frame;
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		
		
		
		
		tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Personal Details", createPersonalDataTab());
		//insert more tabs here
		
		//Trenger en tab som styrer passord, dette kan være et passordbox, hvis ikke dette blir for avansert
		
		add(tabbedPane);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	}
	
	private JPanel createPersonalDataTab(){
			
		JPanel personalData = new JPanel(new SpringLayout());
		
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
		
		street = new JLabel("Street: ", JLabel.TRAILING);
		personalData.add(street);
		textStreet = new JTextField("Her må adresse fylles inn, legges inn i farmer-objektet");	//!!!
		street.setLabelFor(textStreet);
		personalData.add(textStreet);
		
		zip = new JLabel("Zip: ", JLabel.TRAILING);
		personalData.add(zip);
		textZip = new JTextField("Her må zip legges inn i farmer-objektet");	//!!!
		zip.setLabelFor(textZip);	
		personalData.add(textZip);
		
		city = new JLabel("City:", JLabel.TRAILING);
		personalData.add(city);
		textCity = new JTextField("Her må city fylles ut i farmer-objektet");	//!!!
		city.setLabelFor(textCity);
		personalData.add(textCity);
		
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
		
		JLabel empytLabel = new JLabel("", JLabel.TRAILING);
		personalData.add(empytLabel);
		personalData.add(createButtons());
		
		
		//Her legges data inn for helper
		
		SpringUtilities.makeCompactGrid(personalData,8,2,6,6,6,6);
		//På denne skal kun den første int endres til å passe antall elementer
		
		
		//kan hende vi må kalle personalData.setOpaque(true);

		return personalData;
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

	
	protected JComponent makeTextPanel(String text){	//Brukes denne?!!
		JPanel panel = new JPanel(false);
		JLabel filler = new JLabel(text);
		filler.setHorizontalAlignment(JLabel.CENTER);
		panel.setLayout(new GridLayout(1,1));
		panel.add(filler);
		return panel;
	}
	
	private void saveData(){
		//Saves the current data when the buttons apply or ok is pressed.
		Gui.currentUser.setFirstName(textFirstName.getText());
		Gui.currentUser.setLastName(textLastName.getText());
		Gui.currentUser.setEmail(textEmail.getText());
		Gui.currentUser.setPhone(textPhone.getText());
		
		
		
	}
	

	
	
	

}
