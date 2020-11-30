import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableModel;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Properties;
import java.awt.Frame;
import java.io.BufferedReader;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.text.DateFormatter;
import javax.swing.JFormattedTextField.AbstractFormatter;
import org.jdatepicker.impl.*;
import org.jdatepicker.util.*;
import org.jdatepicker.*;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class AddAttendance implements TableModelListener {
	public ArrayList<Attendance> attendanceList = new ArrayList<Attendance>();
	public ArrayList<Student> studentList = new ArrayList<Student>();
	String stringDate;
	
	public String[][] data;
	
	static class Attendance{
		public String asurite;
		public int minutes;
		
		
		public Attendance(String asurite, int minutes) {
			this.asurite = asurite;
			this.minutes = minutes;
		}
		
		public String getAsurite() {
			return asurite;
		}
		
		public String getMinutes() {
			String stringMinutes = Integer.toString(minutes);
			return stringMinutes;
		}
	}
	
	private static Attendance newAttendance(String[]data) {
		String asurite = data[0];
		int minutes = Integer.parseInt(data[1]);
		
		//if(asurite.equalsIgnoreCase(asurite)) {
		//	minutes = minutes + minutes;
		//}
		
		return new Attendance(asurite, minutes);
	}

	
	public void copyRoster(Student student) {
		studentList.add(student);
	}
	
	public boolean returnAsurite(String asurite) {
		for (Student checkStudent : studentList ) {
			if (checkStudent.getAsurite().compareTo(asurite) == 0) {
				return true;
			}
		}
		return false;
	}
	
	public void updateRoster(Attendance att) {
		for (Student student : studentList) {
			if (student.getAsurite().compareTo(att.getAsurite()) == 0) {
				//student.getMinutes());
			}
		}
	}
	
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
			
			if(!returnAsurite(attendanceInfo.getAsurite())) {
				asuriteNotFound.add(attendanceInfo);
			}
				else {
					totalAsuriteEntered++;
				
				}
			}
		if (asuriteNotFound.size() != 0) {
	    	JDialog d = new JDialog(frame, "Not Found");
	   	 String dialogue = "Data loaded for" + totalAsuriteEntered;
		 dialogue += " users in the roster.";
	     dialogue += "\n" + asuriteNotFound.size() + " additional attendee was found: \n";
	    	for (Attendance att : asuriteNotFound) {
	    		dialogue += "\n" + att.getAsurite() + " connected for " + att.getMinutes() + " minutes.";
	    	}
	    JLabel l = new JLabel(dialogue)	;
	    d.add(l);
		}
				
				
		
		bReader.close();
	} catch (IOException ioe) {
		System.out.println("Can't read file");
	}
	
		}
	    
	    
	   

	}
	public void chooseDate() {
		String[] monthList = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
		Integer[] dayList = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31 };
		Integer[] yearList = {2005, 2006, 2007, 2008, 2009, 2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018, 2019, 2020 };
		String stringDate = "";
		
		JComboBox monthSelection = new JComboBox(monthList);
		monthSelection.setSelectedIndex(0);	
		JComboBox daySelection = new JComboBox(dayList);
		daySelection.setSelectedIndex(0);
		JComboBox yearSelection = new JComboBox(yearList);
		yearSelection.setSelectedIndex(0);
		
		JPanel popUp = new JPanel();
		popUp.add(monthSelection);
		popUp.add(daySelection);
		popUp.add(yearSelection);

		int result = JOptionPane.showConfirmDialog(null, popUp, 
	               "Attendance date?", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
	         stringDate = monthList[monthSelection.getSelectedIndex()] + " " + dayList[daySelection.getSelectedIndex()] + " " + yearList[yearSelection.getSelectedIndex()];
		}
	}
		
		public JTable addColumn() {
			
			LoadRoster loadRoster = new LoadRoster();
			String [][] copyData = loadRoster.getData();
		
			String[] columnNames = {"ID", "First Name", "Last Name", "Program", "Level", stringDate, };
			
		
		for (int i = 0; i < attendanceList.size(); i++) {
			copyData[i][6] = attendanceList.get(i).getMinutes();
			
		}
		JTable table = new JTable(copyData, columnNames);
		
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

