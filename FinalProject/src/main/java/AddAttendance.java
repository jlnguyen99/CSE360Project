/* 
 * Author: Jasmine Nguyen, Gabriel Waegner, Jenny Zhang, Andrew Tran, Charis Han
 * Class ID: 70605
 * Final Project
 * Description: This file contains the AddAttendance class, where it adds another column for attendance
 * 				after reading in a second csv file that the user selects and loads it into a JTable.
 * 
 */

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.awt.Frame;
import java.io.BufferedReader;

import java.util.ArrayList;

public class AddAttendance {
	public ArrayList<Attendance> attendanceList = new ArrayList<Attendance>();
	public ArrayList<Student> studentList = new ArrayList<Student>();
	String stringDate;
	
	public String[] columnNames;
	public String[][] data;
	
	/**
	*Attendance class to store the information that is being read from another
	*csv file
	*/
	
	static class Attendance{
		public String asurite;
		public int minutes;
		
		/**
		*Attendance constructor to initialize values
		*@param asurite to hold the asu id of the student
		*@param minutes to hold the minutes the student has attended the class
		*/
		
		public Attendance(String asurite, int minutes) {
			this.asurite = asurite;
			this.minutes = minutes;
		}
		
		 /**
		  * function to get and return asurite id
		  * @return the asurite from the Attendance class
		  */
		
		public String getAsurite() {
			return asurite;
		}
		
		/**
		 * function to get and return the minutes each student had spent attending
		 * @return the minutes as a string from the Attendance class
		 */
		
		public String getMinutes() {
			String stringMinutes = Integer.toString(minutes);
			return stringMinutes;
		}
	}
	
	/**
	 * Hold in the asurite and minutes that was read from each column from the 
	 * csv file
	 * @param data is an array to hold the read in information
	 * @return the new values to the constructor
	 */
	
	private static Attendance newAttendance(String[]data) {
		String asurite = data[0];
		int minutes = Integer.parseInt(data[1]);		
		return new Attendance(asurite, minutes);
	}
	
	/**
	 * copy the initial roster and add each student's information in the arrayList
	 * @param student is used to represent the student's data
	 */
	
	public void copyRoster(Student student) {
		studentList.add(student);
	}
	
	/**
	 * boolean function to return whether or not a student is found in the roster compared to the
	 * added csv attendance file
	 * @param asurite to compare if the asurite is in the initial roster
	 * @return true if the asurite is found
	 * @return false if the asurite is not found
	 */
	
	public boolean returnAsurite(String asurite) {
		for (Student checkStudent : studentList ) {
			if (checkStudent.getAsurite().compareTo(asurite) == 0) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * This function is intended to choose a csv file and take in the data
	 * and store that data into an array. It will also output a dialogue box when a student 
	 * that is not already in the roster is added for attendance
	 * @param frame to call the frame
	 */
	
	public void chooseFile(Frame frame) {
	JFileChooser chooser = new JFileChooser();
	File csvFile = null;
	
	ArrayList<Attendance> asuriteNotFound = new ArrayList<Attendance>();
	int totalAsuriteEntered = 0;
	
	FileFilter filter = new FileNameExtensionFilter("CSV Files", "csv");
	chooser.setFileFilter(filter);
	int correctFile = chooser.showOpenDialog(null);
	
	if(correctFile == JFileChooser.APPROVE_OPTION) {
		csvFile = chooser.getSelectedFile();
		
	try {
		FileReader fReader = new FileReader(csvFile);
		BufferedReader bReader = new BufferedReader(fReader);
		
		String line = bReader.readLine();
		
		while (line != null) {
			String[] attendance = line.split(",");
			
			Attendance attendanceInfo = new Attendance(attendance[0], Integer.parseInt(attendance[1]));
			
			attendanceList.add(attendanceInfo);
			
			line = bReader.readLine();
			
			if(returnAsurite(attendanceInfo.getAsurite()) == true) {
				asuriteNotFound.add(attendanceInfo);
			}
				else {
					totalAsuriteEntered++;
				
				}
			}
		if (asuriteNotFound.size() != 0) {
			String dialogue = "Data loaded for " + totalAsuriteEntered;
			 dialogue += " users in the roster.";
			 dialogue += "\n" + asuriteNotFound.size() + " additional attendee was found: \n";
	    	for (Attendance att : asuriteNotFound) {
	    		dialogue += "\n" + att.getAsurite() + " connected for " + att.getMinutes() + " minutes.";
	    	}
	    	JOptionPane.showMessageDialog(frame,dialogue, "Attendance Report",JOptionPane.INFORMATION_MESSAGE);
		}
				
				
		
		bReader.close();
	} catch (IOException ioe) {
		System.out.println("Can't read file");
		}
	}
}
	
	/**
	 * function to create a option pane with dropdown boxes to pick a date and store in a variable
	 */
	
	public void chooseDate() {
		String[] monthList = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
		Integer[] dayList = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31 };
		Integer[] yearList = {2005, 2006, 2007, 2008, 2009, 2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018, 2019, 2020 };
		
		JComboBox monthSelection = new JComboBox(monthList);
		monthSelection.setSelectedIndex(0);	
		JComboBox daySelection = new JComboBox(dayList);
		daySelection.setSelectedIndex(0);
		JComboBox yearSelection = new JComboBox(yearList);
		yearSelection.setSelectedIndex(0);
		
		JPanel datePanel = new JPanel();
		datePanel.add(monthSelection);
		datePanel.add(daySelection);
		datePanel.add(yearSelection);

		int result = JOptionPane.showConfirmDialog(null, datePanel, 
	               "Attendance date?", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
	         stringDate = monthList[monthSelection.getSelectedIndex()] + " " + dayList[daySelection.getSelectedIndex()] + " " + yearList[yearSelection.getSelectedIndex()];
		}
		    
	}
	
	/**
	 * This function services to copy the data from the file and use it to add
	 * an attendance column
	 * @param data to hold the data from the initial roster
	 * @param columnNames string of names for the column headers
	 * @return the new Jtable and repaint
	 */
		
	public JTable addColumn(String[][] data, String[] columnNames) {
		String[] newColumn = new String[attendanceList.size()];
		
		
		for (int i = 0; i < attendanceList.size(); i++) {
			newColumn[i] = attendanceList.get(i).getMinutes();
		}
		
		DefaultTableModel model = new DefaultTableModel();
		JTable table = new JTable(model);
		
		for (int i = 0; i < columnNames.length; i++) {
			model.addColumn(columnNames[i]);
		}
		
		for (int i = 0; i < data.length; i++) {
			model.addRow(data[i]);
		}
		
		model.addColumn(stringDate, newColumn);
		
		table.repaint();
		
		this.data = new String[model.getRowCount()][model.getColumnCount()];
		
		for (int i = 0; i < model.getRowCount(); i++) {
			for (int j = 0; j < model.getColumnCount() - 1; j++) {
				this.data[i][j] = data[i][j];
			}
			
			this.data[i][model.getColumnCount() - 1] = newColumn[i];
		}
		
		this.columnNames = new String[model.getColumnCount()];
		
		for (int i = 0; i < model.getColumnCount() - 1; i++) {
			this.columnNames[i] = columnNames[i];
		}
		
		this.columnNames[model.getColumnCount() - 1] = stringDate;
		
		return table;
		
	}
	
	/**
	 * returns the data array for the table
	 * @return data, the 2d array holding the data for the roster and attendance
	 */
	
	public String[][] getData() {
		return data;
	}	
	
	/**
	 * Returns the column name array for the table
	 * @return columnNames, the 2d array holding the data for the column names 
	 * of the Jtable
	 */
	
	public String[] getColumnNames() {
		return columnNames;
	}



}