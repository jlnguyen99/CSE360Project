/* 
 * Author: Jasmine Nguyen, Gabriel Waegner, Jenny Zhang, Andrew Tran, Charis Han
 * Class ID: 70605
 * Final Project
 * Description: This file is the Menu, creating the menu interface for the program.
 * 					The menu consists of a blank screen with About and File options.
 * 					Under File is multiple buttons for various functions.
 * 					Under About is an about the team description placed in a dialogue box.
 * 
 */
import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

/**
 * Menu class creates the framework required to view the program.
 * Framework includes the buttons, the interface, and the table for viewing recorded data.
 * @author Gabriel Waegner
 *
 */
public class Menu implements ActionListener {
	JFrame frame = new JFrame("CSE 360 Final Project");
	
	JMenuBar menubar = new JMenuBar();
	JMenu file = new JMenu("File");
	JMenu about = new JMenu("About");
	
	JMenuItem load = new JMenuItem("Load a Roster");
	JMenuItem att = new JMenuItem("Add Attendance");
	JMenuItem save = new JMenuItem("Save");
	JMenuItem plot = new JMenuItem("Plot Data");
	
	LoadRoster roster;
	JScrollPane scrollPane = new JScrollPane();
	String[][] data;
	String[] columnNames;
	
	AddAttendance attendance = new AddAttendance();
	
	Save saveFunction;
	
	/**
	 * Menu constructor creates the base menu for the program.
	 * This menu allows users to view the about or interact with various buttons.
	 * The function of the buttons are described under actionPerformed.
	 * The about button's function is described under the AboutEvent class.
	 * @author Gabriel Waegner
	 */
	public Menu() {
		frame.setSize(600,500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	
		
		file.add(load);
		file.add(att);
		file.add(save);
		file.add(plot);
		
		menubar.add(file);
		menubar.add(about);
		
		about.addMenuListener(new AboutEvent());
		load.addActionListener(this);
		att.addActionListener(this);
		save.addActionListener(this);
		plot.addActionListener(this);
		
		frame.setJMenuBar(menubar);
		frame.getContentPane().add(scrollPane);
		
		frame.setVisible(false);
		frame.setVisible(true);
	}
	

	/**
	 * Default main, creates a new menu to initialize the program.
	 * @param args
	 * @author Gabriel Waegner
	 */
	public static void main(String args[]) {
		Menu world = new Menu();
		
	}
	
	/**
	 * ActionPerformed directs actions based on which button is clicked.
	 * Load is directed by Jasmine Nguyen, performs loading a roster from a .CSV file onto a table.
	 * Attendance is directed by Jenny Zhang, adding attendance records from a .CSV file onto a table.
	 * Save is directed by Andrew Tran, saving the table records into a .CSV file of the user's choice.
	 * Plot Data is directed by Gabriel Waegner, creating a scatter plot of the data received from the table.
	 * @author Gabriel Waegner, Jasmine Nguyen, Andrew Tran, Jenny Zhang, Charis Han
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == load) {
			roster = new LoadRoster();
			
			if (roster.getRoster() == true) {
				scrollPane.setViewportView(roster.loadRoster());
				data = roster.getData();
				columnNames = roster.getColumnNames();

				frame.setVisible(false);
				frame.setVisible(true);
			}
		} else if (e.getSource() == att) {			
			attendance.chooseDate();
			attendance.chooseFile(frame);
			scrollPane.setViewportView(attendance.addColumn(data, columnNames));
			data = attendance.getData();
			columnNames = attendance.getColumnNames();

			frame.setVisible(false);
			frame.setVisible(true);
		} else if (e.getSource() == save) {
			saveFunction = new Save();
			saveFunction.saveFile(data, columnNames);
		} else if (e.getSource() == plot) {
			String[][] temp = attendance.getData();
			JFrame frame = new JFrame("CSE 360 Final Project");
			XYSeriesCollection dataset = new XYSeriesCollection(); 
			for (int i = 6; i < columnNames.length; i++) {
				LinkedList<Double> plotL = new LinkedList<Double>();
				System.out.println(columnNames[i]);
				XYSeries x = new XYSeries(columnNames[i]);
				for (int j = 0; j < temp.length; j++) {
					plotL.add(Double.parseDouble(temp[j][i])/75);
				}	
				for (int k = 0; k < plotL.size(); k++) {
					double val = plotL.get(k);
					if (val > 1) {val = 1;}
					int counter = 1;
					for (int m = 0; m < plotL.size(); m++) {
						if (m == k) {
							
						} else {
							if (val >= plotL.get(m)) {
								counter++;
							}
						}
					}
					System.out.println(val);
					x.add(val, counter);
				}
				dataset.addSeries(x);
			}
			
			JFreeChart chart = ChartFactory.createScatterPlot(" ", "% of Lecture Attended", "Number of Students", dataset);
			XYPlot plot = (XYPlot)chart.getPlot();  
		    plot.setBackgroundPaint(new Color(255,228,196));
		    
		    ChartPanel jfreeChartPanel = new ChartPanel(chart);
		    frame.setSize(800,600);
		    frame.add(jfreeChartPanel);
		    frame.setVisible(true);

			
		}
	}


/**
 * AboutEvent class, when selected in function, shows the contributors to the project.
 * A dialogue box is generated displaying the information of all team members.
 * @author Gabriel Waegner
 *
 */
	class AboutEvent implements MenuListener {
		@Override
		public void menuSelected(MenuEvent e) {
			// TODO Auto-generated method stub
			
			JDialog d = new JDialog(frame, "About");
			JLabel l = new JLabel("<html><p style = padding:3'>Team Members:<br><p style='padding:10; margin-top:-5'>"
					+ "Gabriel Waegner -- gwaegner@asu.edu<br><p style='padding:10; margin-top:-5'>"
					+ "Jasmine Nguyen -- jlnguye6@asu@edu<br><p style='padding:10; margin-top:-5'>"
					+ "Andrew Tran -- atran32@asu.edu<br><p style='padding:10; margin-top:-5'>"
					+ "Jenny Zhang -- jzhan444@asu.edu<br><p style='padding:10; margin-top:-5'>"
					+ "Charis Han -- chan25@asu.edu<br><p style='padding:10; margin-top:-5'>");
			
			
			d.add(l);
			d.setSize(260, 280);
			d.setVisible(true);
		}
	
		/**
		 * Automatically generated function for when the box is dismissed.
		 */
		@Override
		public void menuDeselected(MenuEvent e) {
			// TODO Auto-generated method stub
		}
	
		/**
		 * Automatically generated function for when the box is cancelled.
		 */
		@Override
		public void menuCanceled(MenuEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
}
