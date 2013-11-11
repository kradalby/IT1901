package org.prosjekt.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import org.prosjekt.client.ClientService;
import org.prosjekt.helperclasses.Farmer;
import org.prosjekt.helperclasses.Sheep;

public class AttackLogFrame extends JFrame {
	private static AttackTable attackTable;
	private Farmer user;
	private List<Sheep> sheepList;
	private Font headerFont = new Font("kalinga", Font.PLAIN, 24);
    private Color textColor = new Color(32, 87, 0);
    private Font font = new Font("kalinga", Font.PLAIN, 16);
 
    
    public AttackLogFrame(Farmer user){
    	super("Attack Log");
		super.setContentPane(new BackgroundPanel(ClientService.pathToBackGround()));
		setLayout(new BorderLayout());
		this.user = user;
		sheepList = this.user.getSheeps();
		
		
		
		add(createContent(), BorderLayout.CENTER);
		
		pack();
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 400);
		setVisible(true);
    }
    
    private JPanel createContent(){
    	JPanel contentPanel = new JPanel(new BorderLayout());
    	contentPanel.setOpaque(false);
    	
    	JPanel headerPanel = new JPanel();
    	headerPanel.setOpaque(false);
    	headerPanel.setPreferredSize(new Dimension(50, 90));
    	
    	
    	JLabel header = new JLabel("Attack Log");
		header.setFont(headerFont);
		header.setForeground(textColor);
		
		JLabel emptyLeft = new JLabel("");
		emptyLeft.setPreferredSize(new Dimension(150,0));
		
		JLabel emptyRight = new JLabel("");
		emptyRight.setPreferredSize(new Dimension(150,0));
		
		JLabel emptyTop = new JLabel("");
		emptyTop.setPreferredSize(new Dimension(0,20));
		
		headerPanel.add(emptyTop, BorderLayout.PAGE_START);
		headerPanel.add(emptyLeft, BorderLayout.LINE_START);
		headerPanel.add(emptyRight, BorderLayout.LINE_END);
		headerPanel.add(header, BorderLayout.CENTER);
		
		contentPanel.add(headerPanel, BorderLayout.PAGE_START);
		
		contentPanel.add(createTablePanel(), BorderLayout.CENTER);
		
		return contentPanel;
    }
	
	private JPanel createTablePanel(){
		JPanel tablePanel = new JPanel(new BorderLayout());
		tablePanel.setOpaque(false);
		
		tablePanel.add(createScrollPane(), BorderLayout.CENTER);
		
		return tablePanel;
	}
	
	private JScrollPane createScrollPane(){
		JScrollPane scrollPane = new JScrollPane(createAttackTable());
		
		scrollPane.setOpaque(false);
		
		return scrollPane;
	}
	private JTable createAttackTable(){
		attackTable = new AttackTable(this.sheepList);
		attackTable.setOpaque(false);
		
		return attackTable;
		
	}
	
	
	
}
