import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableModel;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.ArrayList;

public class LoadRoster implements TableModelListener {
	public ArrayList<Student> studentList = new ArrayList<Student>();
	public String[][] data;

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
				String asurite = inputs[5];
				
				Student student = new Student(id, firstName, lastName, program, level, asurite);
				
				studentList.add(student);
			}
			
			bReader.close();
		} catch (IOException ioe) {
			System.out.println("Can't read file");
		}
		
	}
	
	public JTable loadRoster() {
		String[] columnNames = {"ID", "First Name", "Last Name", "Program", "Level", "ASURITE"};
		data = new String[studentList.size()][6];
		
		for (int i = 0; i < studentList.size(); i++) {
			data[i][0] = studentList.get(i).getId();
			data[i][1] = studentList.get(i).getFirstName();
			data[i][2] = studentList.get(i).getLastName();
			data[i][3] = studentList.get(i).getProgram();
			data[i][4] = studentList.get(i).getLevel();
			data[i][5] = studentList.get(i).getAsurite();
		}
		
		JTable table = new JTable(data, columnNames);
		
		table.getModel().addTableModelListener(this);
		
		table.repaint();
		
		return table;
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		// TODO Auto-generated method stub
		TableModel model = (TableModel)e.getSource();
	}
}
