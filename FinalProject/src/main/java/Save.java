/* 
 * Author: Jasmine Nguyen, Gabriel Waegner, Jenny Zhang, Andrew Tran, Charis Han
 * Class ID: 70605
 * Final Project
 * Description: This file contains the Save class that saves to a CSV file.
 * 					A prompt is given to allow user input to save data
 * 					into a CSV file of the user's choosing.
 * 
 */
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.*;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

/* Save should be similar to load attendance, at least for exporting
 * Import FileReader is swapped with FileWriter
 * 
 * Look for the existing JTable and append the data as needed
 * Make sure to flush + close the stream when finished.
 * 
 * File will probably be named as given from the user
 * Find a way to allow for user to name the saved file
 * 
 * In the case of matching save files, go for an overwrite prompt
 * 
*/
public class Save {
	
	// Preinitialized parameters
	// Might not need these but I'll keep them here in case for future changes.
	public ArrayList<Student> studentList = new ArrayList<Student>();
	public String[][] streamData;
	public String[] columnName;
	public Save() {}
	
	// Function to save the file.
	// The parameters are place-holders currently, fix later.
	public void saveFile(JTable table) throws IOException{
		JFileChooser selection = new JFileChooser();
		selection.setDialogTitle("Specify file for saving data.");
		
		FileFilter filter = new FileNameExtensionFilter("CSV Files", "CSV");
		
		selection.setFileFilter(filter);
		selection.showSaveDialog(null);
		
		int userInput = selection.showSaveDialog(null);
		
		String fileName = selection.getSelectedFile().getName();
		
		if(userInput == JFileChooser.APPROVE_OPTION) {
			try {
				FileWriter writer = new FileWriter(fileName);
				BufferedWriter buffWrite = new BufferedWriter(writer);
				
				for(int i = 0; i < table.getColumnCount(); i++) {
					buffWrite.append(String.join(",",table.getColumnName(i)));
				}
				
				for(int i = 0; i < table.getRowCount(); i++) {
					buffWrite.newLine();
					for(int j = 0; j < table.getColumnCount(); j++) {
						buffWrite.append(String.join(",",(CharSequence[]) table.getValueAt(i,j)));
					}
				}
				
				buffWrite.flush();
				buffWrite.close();
			}catch(IOException e) {
				System.out.println("File inaccessible, cannot be read.");
			}
			
		}
	}
	
}