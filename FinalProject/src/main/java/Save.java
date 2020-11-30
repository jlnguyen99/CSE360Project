import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableModel;

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
public class Save implements TableModelListener{
	
	// Preinitialized parameters
	private static final char DEFAULT_SEPARATOR = ',';
	public ArrayList<Student> studentList = new ArrayList<Student>();
	public String[][] streamData;
	public Save() {}
	
	// Function to save the file.
	// The parameters are place-holders currently, fix later.
	public void saveFile(JTable table) throws IOException{
		
		JFileChooser selection = new JFileChooser();
		
		FileFilter filter = new FileNameExtensionFilter("CSV Files", "CSV");
		
		selection.setFileFilter(filter);
		selection.showSaveDialog(null);
		
		String path = selection.getSelectedFile().getAbsolutePath();
		String fileName = selection.getSelectedFile().getName();
		FileWriter csvFile = new FileWriter(fileName);
		
		try {
			FileWriter writer = new FileWriter(fileName);
			BufferedWriter buffWrite = new BufferedWriter(writer);
			
		}catch(IOException e) {
			System.out.println("File inaccessible, cannot be read.");
		}
		
	}
	
	// Auto-gen'd file to keep TableModel from being angry.
	@Override
	public void tableChanged(TableModelEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
}