package org.prosjekt.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import org.prosjekt.client.ClientExample;
import org.prosjekt.helperclasses.Coordinate;
import org.prosjekt.helperclasses.Farmer;
import org.prosjekt.helperclasses.Sheep;

public class SheepLogFrame extends JFrame implements ActionListener{
	private static CoordinateTable coordinateTable;
	private Farmer user;
	private List<Sheep> sheepList;
    private Color textColor = new Color(32, 87, 0);
    private Font font = new Font("kalinga", Font.PLAIN, 16);
    private JComboBox chooser;
    private List<Coordinate> currentCoordinates;
    private int counter = 0;
	
	public SheepLogFrame(Farmer user){
		super("Sheep Log");
		super.setContentPane(new BackgroundPanel(ClientExample.pathToBackGround()));
		setLayout(new BorderLayout());
		this.user = user;
		sheepList = this.user.getSheeps();
		
		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.setPreferredSize(new Dimension(20,80));
		topPanel.setOpaque(false);
		
		JLabel emptyTop = new JLabel("");
		emptyTop.setPreferredSize(new Dimension(20,20));
		
		
		topPanel.add(emptyTop, BorderLayout.PAGE_START);
		topPanel.add(createChooser(), BorderLayout.CENTER);
		
		add(topPanel, BorderLayout.PAGE_START);
		
		
		
		add(createTablePanel(), BorderLayout.CENTER);
		
		pack();
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 400);
		setVisible(true);
		
		
	}
	
	public void updateData(){
		
		//fireTableDataChanged();
		
	}
	
	private JPanel createChooser(){
		JPanel chooserPanel = new JPanel();
		chooserPanel.setPreferredSize(new Dimension(20, 50));
		chooserPanel.setOpaque(false);
		
		ArrayList<String> coordinateArrayList  = new ArrayList<String>();
		for (Sheep s: user.getSheeps()){
			coordinateArrayList.add(s.getId());
		}
		String [] coordinateArray = new String[user.getSheeps().size()];
		coordinateArrayList.toArray(coordinateArray);
		
		chooser = new JComboBox(coordinateArray);
		chooser.addActionListener(this);
		chooser.setSelectedIndex(0);
		
		JLabel chooseSheep = new JLabel("Choose sheep: ");
		chooseSheep.setPreferredSize(new Dimension(150,40));
		chooseSheep.setFont(font);
		chooseSheep.setForeground(textColor);
		
		
		chooserPanel.add(chooseSheep);
		chooserPanel.add(chooser);
		return chooserPanel;
		
	}
	
	private JPanel createTablePanel(){
		JPanel tablePanel = new JPanel(new BorderLayout());
		tablePanel.setOpaque(false);
		
		tablePanel.add(createScrollPane(), BorderLayout.CENTER);
		
		return tablePanel;
	}
	
	private JScrollPane createScrollPane(){
		JScrollPane scrollPane = new JScrollPane(createLogTable());
		
		scrollPane.setOpaque(false);
		
		return scrollPane;
	}
	private JTable createLogTable(){
		coordinateTable = new CoordinateTable(this.currentCoordinates);
		coordinateTable.setOpaque(false);
		
		return coordinateTable;
		
	}
	
	private void createTable(String sheepId){
		Sheep currentSheep = null;
		
		for (Sheep s: sheepList){
			if (s.getId().equals(sheepId)){
				currentSheep = s;
				break;
			}
		}
		
		currentCoordinates = currentSheep.getCordinates();
		//coordinateTable.updateData(currentCoordinates);
	}
	private void updateTable(String sheepId){
		Sheep currentSheep = null;
		
		for (Sheep s: sheepList){
			if (s.getId().equals(sheepId)){
				currentSheep = s;
				break;
			}
		}
		
		currentCoordinates = currentSheep.getCordinates();
		coordinateTable.updateData(currentCoordinates);
		//coordinateTable.updateUI();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {

		JComboBox cb = (JComboBox) e.getSource();
		String sheepId = (String) cb.getSelectedItem();
		if(counter == 0){
			createTable(sheepId);
			counter++;
		}
		else if (counter>0){
			updateTable(sheepId);
		}
		
	}
	
}
