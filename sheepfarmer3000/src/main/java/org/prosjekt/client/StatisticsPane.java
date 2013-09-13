package org.prosjekt.GUIClient;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class StatisticsPane extends JPanel{
	protected JTabbedPane tabbedPane; //Burde ha en getter isteden
	
	public StatisticsPane(){
		super(new GridLayout(1 ,1));
		super.setPreferredSize(new Dimension(800, 600));
		
		tabbedPane = new JTabbedPane();
		
		JComponent sheepList = makeTextPanel("Sheep List");
		tabbedPane.addTab("List of sheep", sheepList);
		
		JComponent attackList = makeTextPanel("Attack List");
		tabbedPane.addTab("List of attacks", attackList);
		
		add(tabbedPane);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		
	}
	public void initialize(Farmer farmer){
		//henter all relevant info fra farmer objektet og lagrer det i feltene
	}
	
	
	protected JComponent makeTextPanel(String text){
		JPanel panel = new JPanel(false);
		JLabel filler = new JLabel(text);
		filler.setHorizontalAlignment(JLabel.CENTER);
		panel.setLayout(new GridLayout(1,1));
		panel.add(filler);
		return panel;
	}

}
