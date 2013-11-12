package org.prosjekt.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
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
    private JComboBox<Helper> chooser;
    private DefaultComboBoxModel<Helper> comboBoxModel;
    private int currentlySelectedIndex;
    private boolean newHelperListener;
	
	
    public HelperInformation(Farmer user){
		super("Helper Information");
		super.setContentPane(new BackgroundPanel(backgroundImage));
		setLayout(new BorderLayout());
		this.user = user;
		currentlySelectedIndex = 0;
		newHelperListener = false;
		
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
		
		private JComboBox<Helper> createChooser(){
			
			chooser = new JComboBox<Helper>();
			comboBoxModel = new DefaultComboBoxModel<Helper>();
			fillChooser();
			
			chooser.addActionListener(this);
			chooser.setAlignmentY(LEFT_ALIGNMENT);
			chooser.setSelectedIndex(0);
			return chooser;
		}
		private void fillChooser(){
			//comboBoxModel.removeAllElements();
			for(Helper h: user.getHelpers()){
				comboBoxModel.addElement(h);
			}
			chooser.setModel(comboBoxModel);
			chooser.setRenderer(new CustomComboBoxRenderer());
		}
		
		private void updateView(){
			
			firstNameField.setText(currentHelper.getFirstname());
			lastNameField.setText(currentHelper.getLastname());
			phoneField.setText(currentHelper.getPhone());
			emailField.setText(currentHelper.getEmail());

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
				//saveHelperInfo();
				
				if(newHelperListener){
					saveNewHelper();
					newHelperListener = false;
				}
				
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
				//chooser.setSelectedItem(0);
			}
			else if (NEWHELPER.equals(cmd)){
				newHelper();
				//chooser.setSelectedItem(user.getHelpers().size());
			}
			else if (REMOVE.equals(cmd)){
				removeHelper();
			}
			else{
				//currentlySelectedIndex = chooser.getSelectedIndex();
				JComboBox<Helper> cb = (JComboBox<Helper>) e.getSource();
				Helper focusedHelper = (Helper)cb.getSelectedItem();
				currentHelper = focusedHelper;
				updateView();	//Denne må legges til igjen
				
			}
			
		}
		
		private void removeHelper(){
			if(!(user.getHelpers().size()==1)){
				Helper tempHelper = (Helper) comboBoxModel.getSelectedItem();
				comboBoxModel.removeElement(tempHelper);

				user.getHelpers().remove(tempHelper);
			}
			else{
				JOptionPane.showMessageDialog(this, "You must have at least one helper registered",
						"Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		private void newHelper(){
			chooser.setEnabled(false);
			
			newHelperListener = true;
			
			firstNameField.setText("");
			lastNameField.setText("");
			phoneField.setText("");
			emailField.setText("");
			
			
			
			
			//her skal må feltene fylles og deretter lagres til helper objektet
		}
		
		private void saveNewHelper(){
			
			String fn = firstNameField.getText();
			String ln = lastNameField.getText();
			String phone = phoneField.getText();
			String email = emailField.getText();
			
			
			
			Helper tempNewHelper = new Helper(user.getId(),fn, ln, phone, email);
			
			user.getHelpers().add(tempNewHelper);
			
			comboBoxModel.addElement(tempNewHelper);
			chooser.setModel(comboBoxModel);
			chooser.setRenderer(new CustomComboBoxRenderer());

		}
		
		
		private boolean saveChanges(){	//date må byttes ut med datetime
			chooser.setEnabled(true);
			Farmer tempUser = user;

			if(Main.saveChangesToFarmer(tempUser)){
				user = tempUser;
				return true;
			}

			
			return false;
		}
		
	
}