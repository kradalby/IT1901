package org.prosjekt.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.prosjekt.helperclasses.Farmer;
import org.prosjekt.helperclasses.Helper;
import org.prosjekt.helperclasses.Sheep;

public class HelperInformation extends JFrame implements ActionListener{
	private static String SAVE = "save";
        private static String CLOSE = "close";
        private static String REMOVE = "remove";
        private static String NEWHELPER  = "newHelper";
    
	private Farmer user;
	private String backgroundImage = "images\\bakgrunn 450x450.jpg";
	private Sheep currentSheep;
	private Helper currentHelper;
	
        private Font font = new Font("kalinga", Font.PLAIN, 17);
        private Font fontTextField = new Font("kalinga", Font.PLAIN, 12);
        private Font buttonFont = new Font("kalinga", Font.PLAIN, 15);
	private Color textColor = new Color(32, 87, 0);
	
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField phoneField;
	private JTextField emailField;
	
	private JComboBox chooser;
	
	
	
	public HelperInformation(Farmer user){
		super("Helper Information");
		super.setContentPane(new BackgroundPanel(backgroundImage));
		setLayout(new BorderLayout());
		this.user = user;
		
		createContentPanel();
		pack();
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 280);	
		setVisible(true);
	}


		private void createContentPanel(){
			JPanel contentPanel = new JPanel();
			contentPanel.setOpaque(false);
			contentPanel.setLayout(new BorderLayout());
			Dimension emptyBorders = new Dimension(40,30);
			
			
			JLabel emptyTop = new JLabel("");
			emptyTop.setOpaque(false);
			emptyTop.setPreferredSize(emptyBorders);
			
			JLabel emptyLeft = new JLabel("");
			emptyLeft.setOpaque(false);
			emptyLeft.setPreferredSize(emptyBorders);
			
			JLabel emptyRight = new JLabel("");
			emptyRight.setOpaque(false);
			emptyRight.setPreferredSize(emptyBorders);
			
			JLabel emptyBottom = new JLabel("");
			emptyBottom.setOpaque(false);
			emptyBottom.setPreferredSize(emptyBorders);
			
			contentPanel.add(emptyTop, BorderLayout.PAGE_START);
			contentPanel.add(emptyLeft, BorderLayout.LINE_START);
			contentPanel.add(emptyRight, BorderLayout.LINE_END);
			contentPanel.add(emptyBottom, BorderLayout.PAGE_END);
			
			contentPanel.add(createContent());
			
			add(contentPanel);
		}
		
		private JPanel createContent(){
			JPanel content = new JPanel();
			content.setLayout(new BorderLayout());
			content.setOpaque(false);
			
			content.add(createLeftSide(), BorderLayout.LINE_START);
			content.add(createRightSide(), BorderLayout.LINE_END);
			content.add(createButtons(), BorderLayout.PAGE_END);
			
			
			return content;
		}
		
		private JComboBox createChooser(){
			ArrayList<String> helperArrayList = new ArrayList<String>();
			
			for(Helper h: user.getHelpers()){
				helperArrayList.add(h.getFirstname()+" "+h.getLastname());
			}
			
			String [] helperArray = new String[user.getHelpers().size()];
			helperArrayList.toArray(helperArray);
			
			chooser = new JComboBox(helperArray);
			
			chooser.addActionListener(this);
			chooser.setAlignmentY(LEFT_ALIGNMENT);
			chooser.setSelectedIndex(0);
			return chooser;
		}
		
		private void updateView(String helperFirstnameLastName){
			
			Helper selectedHelper = null;
			
			String [] firstNameLastnamelist = helperFirstnameLastName.split(" ");
			
			String firstName = firstNameLastnamelist[0];
			String lastName = firstNameLastnamelist[1];
			
			
			for(Helper h: user.getHelpers()){
				if(h.getFirstname().equals(firstName)&&h.getLastname().equals(lastName)){
					selectedHelper = h;
						break;
				}
			}
			
			firstNameField.setText(selectedHelper.getFirstname());
			lastNameField.setText(selectedHelper.getLastname());
			phoneField.setText(selectedHelper.getPhone());
			emailField.setText(selectedHelper.getEmail());
			
			currentHelper = selectedHelper;	//trengs for at riktig helper skal bli slettet
		}
		
		private JPanel createLeftSide(){
			JPanel leftSide = new JPanel();
			leftSide.setLayout(new BoxLayout(leftSide, BoxLayout.Y_AXIS));
			leftSide.setOpaque(false);
			leftSide.setPreferredSize(new Dimension(180,100));
			
			JLabel chooser = new JLabel("Choose Helper: ");
			chooser.setFont(font);
			chooser.setForeground(textColor);
			
			JLabel firstNameLabel = new JLabel("First Name:");
			firstNameLabel.setFont(font);
			firstNameLabel.setForeground(textColor);
			
			JLabel lastNameLabel = new JLabel("Last Name:");
			lastNameLabel.setFont(font);
			lastNameLabel.setForeground(textColor);
			
			JLabel phoneLabel = new JLabel("Phone:");
			phoneLabel.setFont(font);
			phoneLabel.setForeground(textColor);
			
			JLabel emailLabel = new JLabel("Email:");
			emailLabel.setFont(font);
			emailLabel.setForeground(textColor);
			
			chooser.setAlignmentY(LEFT_ALIGNMENT);
			leftSide.add(chooser);
			
			firstNameLabel.setAlignmentY(LEFT_ALIGNMENT);
			leftSide.add(firstNameLabel);
			
			lastNameLabel.setAlignmentY(LEFT_ALIGNMENT);
			leftSide.add(lastNameLabel);
			
			phoneLabel.setAlignmentY(LEFT_ALIGNMENT);
			leftSide.add(phoneLabel);
			
			emailLabel.setAlignmentY(LEFT_ALIGNMENT);
			leftSide.add(emailLabel);
			
			
			
			return leftSide;
		}
		
		
		/*
		 * 
		 * textfeltene må instansieres med det formatet de skal ha
		 * 
		 */
		private JPanel createRightSide(){
			JPanel rightSide = new JPanel();
			rightSide.setLayout(new BoxLayout(rightSide, BoxLayout.Y_AXIS));
			rightSide.setOpaque(false);
			rightSide.setPreferredSize(new Dimension(180,100));
			
			
			
			firstNameField = new JTextField();
			firstNameField.setOpaque(false);
			firstNameField.setForeground(textColor);
			firstNameField.setFont(fontTextField);
			firstNameField.setActionCommand(SAVE);
			firstNameField.addActionListener(this);
			
			lastNameField = new JTextField();
			lastNameField.setOpaque(false);
			lastNameField.setForeground(textColor);
			lastNameField.setFont(fontTextField);
			lastNameField.setActionCommand(SAVE);
			lastNameField.addActionListener(this);
			
			phoneField = new JTextField();
			phoneField.setOpaque(false);
			phoneField.setForeground(textColor);
			phoneField.setFont(fontTextField);
			phoneField.setActionCommand(SAVE);
			phoneField.addActionListener(this);
			
			emailField = new JTextField();
			emailField.setOpaque(false);
			emailField.setForeground(textColor);
			emailField.setFont(fontTextField);
			emailField.setActionCommand(SAVE);
			emailField.addActionListener(this);
			
			rightSide.add(createChooser());
			
			firstNameField.setAlignmentY(LEFT_ALIGNMENT);
			rightSide.add(firstNameField);
			
			lastNameField.setAlignmentY(LEFT_ALIGNMENT);
			rightSide.add(lastNameField);
			
			phoneField.setAlignmentY(LEFT_ALIGNMENT);
			rightSide.add(phoneField);
			
			emailField.setAlignmentY(LEFT_ALIGNMENT);
			rightSide.add(emailField);
			
			
			
			return rightSide;
		}
		
		private JPanel createButtons(){
			JPanel buttons = new JPanel();
			BorderLayout customLayout = new BorderLayout();
			customLayout.setHgap(20);
			buttons.setLayout(customLayout);
			buttons.setOpaque(false);
			buttons.setPreferredSize(new Dimension(180, 50));
			
			JLabel emptyTop = new JLabel("");
			JLabel emptyBottom = new JLabel("");
			
			emptyTop.setPreferredSize(new Dimension(10,5));
			emptyBottom.setPreferredSize(new Dimension(10,5));
			
			
			JButton newHelper = new JButton("new");
			newHelper.setOpaque(false);
			newHelper.setForeground(textColor);
			newHelper.setBackground(textColor);
			newHelper.setFont(buttonFont);
			newHelper.setActionCommand(NEWHELPER);
			newHelper.addActionListener(this);
			//newHelper.setPreferredSize(new Dimension(100,40));
			
			JButton removeButton = new JButton("remove");
			removeButton.setOpaque(false);
			removeButton.setForeground(textColor);
			removeButton.setBackground(textColor);
			removeButton.setFont(buttonFont);
			removeButton.setActionCommand(REMOVE);
			removeButton.addActionListener(this);
			
			JButton saveButton = new JButton(SAVE);
			saveButton.setOpaque(false);
			saveButton.setForeground(textColor);
			saveButton.setBackground(textColor);
			saveButton.setFont(buttonFont);
			saveButton.setActionCommand(SAVE);
			saveButton.addActionListener(this);
			
			JButton closelButton = new JButton(CLOSE);
			closelButton.setOpaque(false);
			closelButton.setForeground(textColor);
			closelButton.setBackground(textColor);
			closelButton.setFont(buttonFont);
			closelButton.setActionCommand(CLOSE);
			closelButton.addActionListener(this);
			
			buttons.add(emptyTop, BorderLayout.PAGE_START);
			buttons.add(emptyBottom, BorderLayout.PAGE_END);
			
			JPanel helperButtons = new JPanel();
			BorderLayout helperButtonsLayout = new BorderLayout();
			helperButtonsLayout.setHgap(2);
			helperButtons.setLayout(helperButtonsLayout);
			helperButtons.setOpaque(false);
			
			JPanel saveButtons = new JPanel();
			BorderLayout saveButtonsLayout = new BorderLayout();
			saveButtonsLayout.setHgap(2);
			saveButtons.setLayout(saveButtonsLayout);
			saveButtons.setOpaque(false);
			
			helperButtons.add(newHelper, BorderLayout.LINE_START);
			helperButtons.add(removeButton, BorderLayout.LINE_END);
			
			saveButtons.add(saveButton, BorderLayout.LINE_START);
			saveButtons.add(closelButton, BorderLayout.LINE_END);
			
			buttons.add(helperButtons, BorderLayout.LINE_START);		
			buttons.add(saveButtons, BorderLayout.LINE_END);
			
			
			return buttons;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			
			
			
			if(SAVE.equals(cmd)){
				saveHelperInfo();
				
				if (!saveChanges()){	//husk at dataTime antagelig må legges til her her
					JOptionPane.showMessageDialog(this, "Changes were not saved! Please try again.",
							"", JOptionPane.ERROR_MESSAGE);
					
				}
				else{
					JOptionPane.showMessageDialog(this, "Changes were successfully saved!.",
							"", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
			else if (CLOSE.equals(cmd)){
				this.dispose();
			}
			else if (NEWHELPER.equals(cmd)){
				newHelper();
				chooser.setSelectedItem(user.getHelpers().size());
			}
			else if (REMOVE.equals(cmd)){
				removeHelper();
			}
			else{
				JComboBox cb = (JComboBox) e.getSource();
				String helperFirstnameLastName = (String)cb.getSelectedItem();
				updateView(helperFirstnameLastName);	//Denne må legges til igjen
			}
			
		}
		
		//--Finished--//
		private void removeHelper(){
			if(!(user.getHelpers().size()==1)){
				user.getHelpers().remove(currentHelper);
				ArrayList<String> helperArrayList = new ArrayList<String>();
				
				for(Helper h: user.getHelpers()){
					helperArrayList.add(h.getFirstname()+" "+h.getLastname());
				}
				
				String [] helperArray = new String[user.getHelpers().size()];
				helperArrayList.toArray(helperArray);
				this.chooser.setModel(new JComboBox(helperArray).getModel());
				String helperFirstnameLastName = (String)chooser.getSelectedItem();
				updateView(helperFirstnameLastName);
				
			}
			else{
				JOptionPane.showMessageDialog(this, "You must have at least one helper registered",
						"Error", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
		private void newHelper(){
			Helper newHelper = new Helper(user.getId(),"new","helper","","");
			List<Helper> helperList =  new ArrayList<Helper>();
			helperList = user.getHelpers();
			helperList.add(newHelper);
			user.setHelpers(helperList);
			
			ArrayList<String> helperArrayList = new ArrayList<String>();
			for(Helper h: user.getHelpers()){
				helperArrayList.add(h.getFirstname()+" "+h.getLastname());
			}
			
			
			String [] helperArray = new String[user.getHelpers().size()];
			helperArrayList.toArray(helperArray);
			
			this.chooser.setModel(new JComboBox(helperArray).getModel());
			String helperFirstnameLastName = (String)chooser.getSelectedItem();
			updateView(helperFirstnameLastName);
			
			
		}
		
		private void saveHelperInfo(){
			//user.getHelpers().remove(currentHelper);
			//Helper newHelper = new Helper(user.getId(),"new","helper","","");
			String firstName = firstNameField.getText();
			String lastName = lastNameField.getText();
			String phone = phoneField.getText();
			String email = emailField.getText();
			
			currentHelper.setFirstname(firstName);
			currentHelper.setLastname(lastName);
			currentHelper.setPhone(phone);
			currentHelper.setEmail(email);
			//user.getHelpers().add(newHelper);
			updateView(firstName+" "+lastName);
		}
		
		
		private boolean saveChanges(){	//date må byttes ut med datetime
			
			Farmer tempUser = user;

			if(Main.saveChangesToFarmer(tempUser)){
				user = tempUser;
				return true;
			}

			
			return false;
		}

}