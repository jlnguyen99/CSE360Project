import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableModel;

import java.io.*;
import java.io.File;
import java.io.FileWriter;
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
 * Yea.
*/
public class Save implements TableModelListener{
	
	// Preinitialized parameters
	private static final char DEFAULT_SEPARATOR = ',';
	public String[][] streamData;
	public Save() {}
	
	// Function to save the file.
	// The parameters are place-holders currently, fix later.
	public void saveFile(JTable table,File fileName) throws IOException{
		
	}
	
	// Auto-gen'd file to keep TableModel from being angry.
	@Override
	public void tableChanged(TableModelEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
}