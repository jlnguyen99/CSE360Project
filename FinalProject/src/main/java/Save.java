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

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

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
	
	/**
	 * Default class constructor
	 */
	public Save() {}
	
	// Function to save the file.
	/**
	 * SaveFile function saves the data provided from parameters into a .CSV file.
	 * The function first writes the headers, then the data for students.
	 * @param studentList, information for students based on pre-existing headers
	 * @param columnNames, the headers for information on the JTable
	 * @throws IOException, catches incorrect or illegally-accessed files.
	 */
	public void saveFile(String[][] studentList, String[] columnNames) throws IOException{
		
		// Select a file to begin saving process.
		JFileChooser selection = new JFileChooser();
		selection.setDialogTitle("Specify file for saving data.");
		
		FileFilter filter = new FileNameExtensionFilter("CSV Files", "CSV");
		
		selection.setFileFilter(filter);
		selection.showSaveDialog(null);
		
		int userInput = selection.showSaveDialog(null);
		
		// If file is chosen, attempt to write data to file.
		if(userInput == JFileChooser.APPROVE_OPTION) {
			try {
				File fileCreate = selection.getSelectedFile();
				
				// Creates a new file if one does not exist.
				if(!fileCreate.exists()) {
					fileCreate.createNewFile();
				}
				
				FileWriter writer = new FileWriter(fileCreate.getAbsoluteFile());
				BufferedWriter buffWrite = new BufferedWriter(writer);
				
				// Begin writing process to file
				for(int i = 0; i < columnNames.length; i++) {
					buffWrite.append(String.join(",", columnNames[i]));
				}
				
				
				for(int i = 0; i < studentList.length; i++) {
					buffWrite.append("\n");
					for(int j = 0; j < studentList.length; j++) {
						buffWrite.append(String.join(",", studentList[i][j]));
					}
				}
				
				buffWrite.flush();
				buffWrite.close();
			}catch(IOException e) { // Exception for inaccessible files.
				System.out.println("File inaccessible, cannot be read.");
			}
			
		}
	}
	
}