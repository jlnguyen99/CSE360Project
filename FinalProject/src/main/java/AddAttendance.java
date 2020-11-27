import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableModel;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Properties;
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

import javax.swing.JDialog;

public class AddAttendance implements TableModelListener{
	public ArrayList<Attendance> attendanceList = new ArrayList<Attendance>();
	String stringDate;
	public String[][] data;
	
	static class Attendance{
		public String asurite;
		public int minutes;
		
		
		public Attendance(String asurite, int minutes) {
			this.asurite = asurite;
			this.minutes = minutes;
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
	
	public void chooseFile() {
	JFileChooser chooser = new JFileChooser();
	File csvFile = null;
	
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
			
			Attendance attendanceInfo = newAttendance(attendance);
			
			attendanceList.add(attendanceInfo);
			
			line = bReader.readLine();

			}
		
		bReader.close();
	} catch (IOException ioe) {
		System.out.println("Can't read file");
	}
	
		}

	}
	public void datePicker(){
		//date picker
				UtilDateModel dateModel = new UtilDateModel();
				Properties prop = new Properties();
				prop.put("text.month", "Month");
				prop.put("text.today", "Today");
				prop.put("text.year", "Year");
				JDatePanelImpl datePanel = new JDatePanelImpl(dateModel, prop);
				
				JDatePickerImpl datePicker;
				datePicker = new JDatePickerImpl(datePanel,new DateFormatter());
				
				dateModel.setDate(2020, 4, 20);
				dateModel.setSelected(true);
				
				
	}
	public void dateToString(Object datePickerValue,String stringDate) throws ParseException {
		if(datePickerValue != null) {
		Calendar newCalendar = (Calendar) datePickerValue;
		SimpleDateFormat newFormat = new SimpleDateFormat("MM-dd-yyyy");
		stringDate = newFormat.format(newCalendar.getTime());
		}
	}
	
	public class addColumn extends LoadRoster {
		public void addAttendance(JTable table) {
			String[] columnNames = {stringDate};
			data = new String[attendanceList.size()][1];
		
		for (int i = 0; i < attendanceList.size(); i++) {
			data[i][6] = attendanceList.get(i).getMinutes();
			
		}
		
		table.getModel().addTableModelListener(this);
		
		table.repaint();
		}
		
	}
	
	@Override
	public void tableChanged(TableModelEvent e) {
		// TODO Auto-generated method stub
		TableModel model = (TableModel)e.getSource();
	}

}

