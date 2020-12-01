/* 
 * Author: Jasmine Nguyen, Gabriel Waegner, Jenny Zhang, Andrew Tran, Charis Han
 * Class ID: 70605
 * Final Project
 * Description: This file contains the LoadRoster class that implements TableModelListener.
 * 				It reads a csv file that the user selects and loads it into a JTable.
 * 
 */

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.ArrayList;

public class LoadRoster {
	public ArrayList<Student> studentList = new ArrayList<Student>();
	public String[][] data;
	public DefaultTableModel model = new DefaultTableModel();
	public String[] columnNames = {"ID", "First Name", "Last Name", "Program", "Level", "ASURITE"};

	/**
	 * Opens a JFileChooser window to allow the user to pick a csv file with the roster.
	 * Reads the data in the cvs file and puts it into the studentList. Ignores any rows
	 * with no asurite.
	 */
	public void getRoster() {
		JFileChooser chooser = new JFileChooser();
		
		FileFilter filter = new FileNameExtensionFilter("CSV Files", "csv");
		
		chooser.setFileFilter(filter);
		
		chooser.showOpenDialog(null);
		
		File csvFile = chooser.getSelectedFile();
		
		try {
			FileReader fReader = new FileReader(csvFile);
			BufferedReader bReader = new BufferedReader(fReader);
			String line = "";
			String[] inputs;
			
			while ((line = bReader.readLine()) != null) {
				inputs = line.split(",");

				String id = inputs[0];
				String firstName = inputs[1];
				String lastName = inputs[2];
				String program = inputs[3];
				String level = inputs[4];
				
				if (inputs.length == 6) {
					String asurite = inputs[5];
					Student student = new Student(id, firstName, lastName, program, level, asurite);
					studentList.add(student);
				}
			}
			
			bReader.close();
		} catch (IOException ioe) {
			System.out.println("Can't read file");
		}
		
	}
	
	/**
	 * Loads the data from the studentList into a table and returns the table
	 * @return table, the JTable holding the data from the roster in table form
	 */
	public JTable loadRoster() {
		data = new String[studentList.size()][6];
		
		for (int i = 0; i < studentList.size(); i++) {
			data[i][0] = studentList.get(i).getId();
			data[i][1] = studentList.get(i).getFirstName();
			data[i][2] = studentList.get(i).getLastName();
			data[i][3] = studentList.get(i).getProgram();
			data[i][4] = studentList.get(i).getLevel();
			data[i][5] = studentList.get(i).getAsurite();
		}
		
		JTable table = new JTable(model);
		
		model.addColumn("ID");
		model.addColumn("First Name");
		model.addColumn("Last Name");
		model.addColumn("Program");
		model.addColumn("Level");
		model.addColumn("ASURITE");
		
		for (int i = 0; i < studentList.size(); i++) {
			model.addRow(data[i]);
		}
		
		table.repaint();
		
		return table;
	}

	/**
	 * Returns the data array for the roster
	 * @return data, the 2D array holding the roster data
	 */
	public String[][] getData() {
		return data;
	}
	
	/**
	 * Returns the array holding the column Names for the roster
	 * @return columnNames, the array holding the column names for the roster
	 */
	public String[] getColumnNames() {
		return columnNames;
	}
}
