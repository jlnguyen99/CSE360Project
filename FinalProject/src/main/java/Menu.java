import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


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
	

	public static void main(String args[]) {
		Menu world = new Menu();
		
	}
	
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
			System.out.println(data[0][0]);
			
			attendance.chooseDate();
			attendance.chooseFile(frame);
			scrollPane.setViewportView(attendance.addColumn(data, columnNames));
			data = attendance.getData();
			columnNames = attendance.getColumnNames();

			frame.setVisible(false);
			frame.setVisible(true);
		} else if (e.getSource() == save) {
			
		} else if (e.getSource() == plot) {
			
		}
	}



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
			d.setSize(260, 220);
			d.setVisible(true);
		}
	
		@Override
		public void menuDeselected(MenuEvent e) {
			// TODO Auto-generated method stub
		}
	
		@Override
		public void menuCanceled(MenuEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
}
