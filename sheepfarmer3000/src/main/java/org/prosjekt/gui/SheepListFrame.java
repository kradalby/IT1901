package org.prosjekt.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.prosjekt.client.ClientService;
import org.prosjekt.gui.AddSheep;
import org.prosjekt.gui.BackgroundPanel;
import org.prosjekt.gui.RemoveSheep;
import org.prosjekt.helperclasses.Farmer;
import org.prosjekt.helperclasses.Sheep;

public class SheepListFrame extends JFrame implements ActionListener{
	private static JTable sheepTable;
	private Farmer currentUser;
	private List<Sheep> sheepList;
    private Font headerFont = new Font("kalinga", Font.PLAIN, 30);
    private Color textColor = new Color(32, 87, 0);
    private Font font = new Font("kalinga", Font.PLAIN, 16);
    
    
    private final static String addS = "addSheep";
    private final static String removeS = "removeSheep";
    private final static String close = "close";
	
	public SheepListFrame(Farmer f){
		super("Sheep Overwiew");
		super.setContentPane(new BackgroundPanel(ClientService.pathToBackGround()));
		setLayout(new BorderLayout());
		this.currentUser = f;
		sheepList = currentUser.getSheeps();
		
		
		add(createHeader(), BorderLayout.PAGE_START);
		
		
		add(SheepTablePanel(), BorderLayout.CENTER);
		
		add(createButtons(), BorderLayout.PAGE_END);
		
		JLabel emptyLeft = new JLabel("");
		emptyLeft.setOpaque(false);
		emptyLeft.setPreferredSize(new Dimension(100,60));
		add(emptyLeft, BorderLayout.LINE_START);
		
		JLabel emptyRight = new JLabel("");
		emptyRight.setOpaque(false);
		emptyRight.setPreferredSize(new Dimension(100,60));
		add(emptyRight, BorderLayout.LINE_END);
		
		pack();
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 600);
		setVisible(true);
		
	}
	
	public static void updateData(){
//            if(openedFrom)
		sheepTable.updateUI();
	}
	
	private JPanel createHeader(){
		JPanel header = new JPanel(new BorderLayout());
		header.setPreferredSize(new Dimension(90,150));
		header.setOpaque(false);
		
		JLabel emptyLeft = new JLabel("");
		emptyLeft.setPreferredSize(new Dimension(300,100));
		header.add(emptyLeft, BorderLayout.LINE_START);
		
		JLabel emptyTop = new JLabel("");
		emptyTop.setPreferredSize(new Dimension(25,35));
		header.add(emptyTop, BorderLayout.PAGE_START);
		
		JLabel headerLabel = new JLabel("Sheep Overview");
		headerLabel.setOpaque(false);
		headerLabel.setForeground(textColor);
		headerLabel.setFont(headerFont);
		header.add(headerLabel, BorderLayout.CENTER);
		
		JLabel emptyBottom = new JLabel("");
		emptyBottom.setPreferredSize(new Dimension(50,15));
		header.add(emptyBottom, BorderLayout.PAGE_END);
		
		return header;
	}
	

	private JPanel createButtons(){
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BorderLayout());
		buttonPanel.setOpaque(false);
		Dimension buttonSize = new Dimension(120,50);
		buttonPanel.setPreferredSize(new Dimension(10,100));
		
		JButton addSheep = new JButton("add sheep");
		addSheep.setPreferredSize(buttonSize);
		addSheep.setOpaque(false);
		addSheep.setForeground(textColor);
		addSheep.setBackground(textColor);
		addSheep.setFont(font);
		addSheep.setActionCommand(addS);
		addSheep.addActionListener(this);
		
		JButton removeSheep = new JButton("remove sheep");
		removeSheep.setPreferredSize(buttonSize);
		removeSheep.setOpaque(false);
		removeSheep.setForeground(textColor);
		removeSheep.setBackground(textColor);
		removeSheep.setFont(font);
		removeSheep.setActionCommand(removeS);
		removeSheep.addActionListener(this);
		
		JButton closeButton = new JButton(close);
		closeButton.setPreferredSize(buttonSize);
		closeButton.setOpaque(false);
		closeButton.setForeground(textColor);
		closeButton.setBackground(textColor);
		closeButton.setFont(font);
		closeButton.setActionCommand(close);
		closeButton.addActionListener(this);
		
		JLabel emptyLeft = new JLabel("");
		emptyLeft.setOpaque(false);
		emptyLeft.setPreferredSize(new Dimension(190, 30));
		
		JLabel emptyRight = new JLabel("");
		emptyRight.setOpaque(false);
		emptyRight.setPreferredSize(new Dimension(190,30));
		
		JLabel emptyTop = new JLabel("");
		emptyTop.setOpaque(false);
		emptyTop.setPreferredSize(new Dimension(10,15));
		
		JLabel emptyBottom = new JLabel("");
		emptyBottom.setOpaque(false);
		emptyBottom.setPreferredSize(new Dimension(10,30));
		
		buttonPanel.add(emptyTop, BorderLayout.PAGE_START);
		buttonPanel.add(emptyLeft, BorderLayout.LINE_START);
		buttonPanel.add(emptyRight, BorderLayout.LINE_END);
		buttonPanel.add(emptyBottom, BorderLayout.PAGE_END);
		
		
		JPanel insideButtonPanel = new JPanel();
		insideButtonPanel.setOpaque(false);
		BorderLayout customLayout = new BorderLayout();
		customLayout.setHgap(15);
		insideButtonPanel.setLayout(customLayout);

		//insideButtonPanel.setPreferredSize(new Dimension(x,y));
		insideButtonPanel.add(addSheep, BorderLayout.LINE_START);
		insideButtonPanel.add(removeSheep, BorderLayout.CENTER);
		insideButtonPanel.add(closeButton, BorderLayout.LINE_END);
		
		buttonPanel.add(insideButtonPanel, BorderLayout.CENTER);
		
		return buttonPanel;
		
	}
	
	private JPanel SheepTablePanel(){
		JPanel sheepTablePanel = new JPanel(new BorderLayout());
		sheepTablePanel.setOpaque(false);
		sheepTablePanel.add(createScrollPane(), BorderLayout.CENTER);
		
		return sheepTablePanel;
	}
	
	private JScrollPane createScrollPane(){
		JScrollPane scrollPane = new JScrollPane(createSheepTable());
		scrollPane.setOpaque(false);
		
		return scrollPane;
	}
	private JTable createSheepTable(){
		sheepTable = new SheepTable(this.sheepList);
		sheepTable.setOpaque(false);
		
		return sheepTable;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		if (addS.equals(cmd)){
			new AddSheep(currentUser);
			
		}
		else if (removeS.equals(cmd)){
			new RemoveSheep(currentUser);
			//removeSheepFromList();
			//må legges en tester her
			
			//removes the currently selected sheep
			//open remove sheep frame
		}
		else if (close.equals(cmd)){
			this.dispose();
		}
		
	}
	//denne brukes ikke, trenger å finne en løsning på sletting av valgt sau i sheeplist
	private void removeSheepFromList(){
		int selectedRowIndex = this.sheepTable.getSelectedRow();
		int selectedColumnIndex = this.sheepTable.getSelectedColumn();
		
		Object selectedObject = (Object) this.sheepTable.getModel().getValueAt(selectedRowIndex, selectedColumnIndex);
		//Sheep s = (Sheep) selectedObject;
		System.out.println(selectedRowIndex);
		
		//need to get content of first column of selected row
	}
	
	
	
	
	

}
