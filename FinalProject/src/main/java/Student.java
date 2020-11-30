/* 
 * Author: Jasmine Nguyen, Gabriel Waegner, Jenny Zhang, Andrew Tran, Charis Han
 * Class ID: 70605
 * Final Project
 * Description: This file contains the Student class, which has 6 attributes: id,
 * 				firstName, lastName, program, level, and asurite. 
 * 
 */

public class Student {
	private String id;
	private String firstName;
	private String lastName;
	private String program;
	private String level;
	private String asurite;
	
	/**
	 * Constructor for the Student class which sets the attributes of the class
	 * @param id, the student's id
	 * @param fName, the student's first name
	 * @param lName, the student's last name
	 * @param program, the program the student's in
	 * @param level, the grade level the student's in
	 * @param asurite, the asurite for the student
	 */
	public Student (String id, String fName, String lName, String program, 
			String level, String asurite) {
		this.id = id;
		firstName = fName;
		lastName = lName;
		this.program = program;
		this.level = level;
		this.asurite = asurite;
	}
	
	/**
	 * Returns the id of the student
	 * @return id, the student's id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Returns the student's first name
	 * @return firstName, the student's first name
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Returns the student's last name
	 * @return lastName, the student's last name
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * Returns the program the student is in
	 * @return program, the program the student is in
	 */
	public String getProgram() {
		return program;
	}
	
	/**
	 * Returns the grade level the student is in
	 * @return level, the grade level the student is in
	 */
	public String getLevel() {
		return level;
	}
	
	/**
	 * Returns the student's asurite
	 * @return asurite, the student's asurite
	 */
	public String getAsurite() {
		return asurite;
	}
}