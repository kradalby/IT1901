package org.prosjekt.gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import org.prosjekt.client.ClientService;

public class LoginBox extends JFrame implements ActionListener{
	
    private static String OK = "ok";
    private static String HELP = "help";
    private static String CANCEL = "cancel";
    private Font font = new Font("kalinga", Font.PLAIN, 20);
    private Font fontTextField = new Font("kalinga", Font.PLAIN, 12);
    private Color textColor = new Color(32, 87, 0);
    private String backgroundImage = "innlogging1.jpg";
    private JPasswordField passwordField;
    private JTextField usernameField;
    
    
	public LoginBox(){
       
		setContentPane(new BackgroundPanel(ClientService.getPathToResources(backgroundImage)));
		setLayout(new BorderLayout());
		
		//lager tomme labels for toppen og venstre av siden
		JLabel emptyTop = new JLabel("");
		emptyTop.setPreferredSize(new Dimension(90,165));
		add(emptyTop, BorderLayout.PAGE_START);
		
		JLabel emptyLeftSide = new JLabel("");
		emptyLeftSide.setPreferredSize(new Dimension(80,50));
		add(emptyLeftSide, BorderLayout.LINE_START);
		
		
		/*
		 * Lager "logg inn"-feltene
		 */
		
		//Lager hovedboksen for logg inn felter
		JPanel loginUI = new JPanel();
		loginUI.setLayout(new BorderLayout());
		loginUI.setOpaque(false);
		
		//lager textfeltet for username
		usernameField = new JTextField(15);
		usernameField.setOpaque(false);
		usernameField.setForeground(textColor);
                usernameField.setFont(fontTextField);
                usernameField.setActionCommand(OK);
                usernameField.addActionListener(this);
                
                //lager passordfeltet
                passwordField = new JPasswordField(15);
                passwordField.setOpaque(false);
                passwordField.setForeground(textColor);
                passwordField.setFont(fontTextField);
                passwordField.setActionCommand(OK);
                passwordField.addActionListener(this);
		
    	//lager teksten User Name: 
		JLabel usernameLabel = new JLabel("Username: ");
		usernameLabel.setFont(font);
		usernameLabel.setForeground(textColor);
    	
		//Lager teksten Enter Password
		JLabel passwordLabel = new JLabel("Password:  ");
		passwordLabel.setFont(font);
		passwordLabel.setForeground(textColor);
		
		//Hekter tekstfelt p� skrivefelt
		usernameLabel.setLabelFor(usernameField);
    	passwordLabel.setLabelFor(passwordField);
		
    	//lager panels som holder tekst og skrivefelter
    	JPanel passwordTextPane = new JPanel(new FlowLayout(FlowLayout.LEADING));
    	passwordTextPane.setOpaque(false);
    	passwordTextPane.setPreferredSize(new Dimension(200,50));
    	
    	JPanel usernameTextPane = new JPanel(new FlowLayout(FlowLayout.LEADING));
    	usernameTextPane.setOpaque(false);
    	usernameTextPane.setPreferredSize(new Dimension(200,50));
    	
    	usernameTextPane.add(usernameLabel);
    	usernameTextPane.add(usernameField);
    	passwordTextPane.add(passwordLabel);
    	passwordTextPane.add(passwordField);
    	
		
    	//Lager knapper
    	JPanel buttons = new JPanel(new BorderLayout());
    	buttons.setOpaque(false);
    	
    	JButton okButton = new JButton(OK);
    	okButton.setPreferredSize(new Dimension(120,10));
    	okButton.setOpaque(false);
    	okButton.setForeground(textColor);
    	okButton.setBackground(textColor);
    	okButton.setFont(font);
    	okButton.setActionCommand(OK);
    	okButton.addActionListener(this);
    	
    	JButton cancelButton = new JButton(CANCEL);
    	cancelButton.setPreferredSize(new Dimension(120,10));
    	cancelButton.setOpaque(false);
    	cancelButton.setForeground(textColor);
    	cancelButton.setBackground(textColor);
    	cancelButton.setFont(font);
    	cancelButton.setActionCommand(CANCEL);
    	cancelButton.addActionListener(this);
    	
    	
    	buttons.add(okButton, BorderLayout.LINE_START);
    	buttons.add(cancelButton, BorderLayout.LINE_END);
    	
    	
    	
    	JPanel buttonPanel = new JPanel(new BorderLayout());
    	buttonPanel.setOpaque(false);
    	
    	JLabel emptyLeftButtons = new JLabel("");
    	emptyLeftButtons.setPreferredSize(new Dimension(8,40));	//endre y her for � endre h�yde p� knapper
    	JLabel emptyRightButtons = new JLabel("");
    	emptyRightButtons.setPreferredSize(new Dimension(9,10));
    	
    	//Tror ikke denne trengs
    	JLabel emptyTopButtons = new JLabel("");
    	emptyTopButtons.setPreferredSize(new Dimension(100, 30));
    	
    	JLabel emptyBottomButtons = new JLabel("");
    	emptyBottomButtons.setPreferredSize(new Dimension(100, 10));
    	
    	
    	//buttonPanel.add(emptyTopButtons, BorderLayout.PAGE_START);
    	buttonPanel.add(emptyLeftButtons, BorderLayout.LINE_START);
    	buttonPanel.add(emptyRightButtons, BorderLayout.LINE_END);
    	buttonPanel.add(emptyBottomButtons, BorderLayout.PAGE_END);
    	
    	
    	buttonPanel.add(buttons, BorderLayout.CENTER);
    	
		
		
		
    	//legger til panels p� loginUI
		loginUI.add(usernameTextPane, BorderLayout.PAGE_START);
		loginUI.add(passwordTextPane, BorderLayout.CENTER);
		loginUI.add(buttonPanel, BorderLayout.PAGE_END);
		
		//legger UI til frame
		add(loginUI, BorderLayout.CENTER);
		
		//Styrer bunn og h�yre ramme
		JLabel emptyRightSide = new JLabel("");
		emptyRightSide.setPreferredSize(new Dimension(75,50));
		add(emptyRightSide, BorderLayout.LINE_END);
		
		JLabel emptyBottom = new JLabel("");
		emptyBottom.setPreferredSize(new Dimension(100,95));
		add(emptyBottom, BorderLayout.PAGE_END);
		
		pack();
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(455, 475);
		setVisible(true);
		

		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		int userId = 0;
		if (OK.equals(cmd)){
			char[] inputPassword = passwordField.getPassword();
			String inputUsername = usernameField.getText();
			try{
				userId = Integer.parseInt(inputUsername);
			}
			catch (Exception exc){
				//denne exceptionen blir h�ndtert under ved at userId da blir 0, noe som ikke eksiterer
			}
			
			if(TopLevelGui.checkPassword(userId, inputPassword)){
				this.dispose();
			}
			else{
				JOptionPane.showMessageDialog(this, "invalid username or password",
						"error messager0", JOptionPane.ERROR_MESSAGE);
				usernameField.setText("");
				passwordField.setText("");
			
			}

		}
		else if (CANCEL.equals(cmd)){
			this.dispose();
		}
		
	}
	protected void closeWindow(){
		this.dispose();
	}

}
