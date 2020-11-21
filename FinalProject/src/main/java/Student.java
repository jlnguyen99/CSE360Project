public class Student {
	private String id;
	private String firstName;
	private String lastName;
	private String program;
	private String level;
	private String asurite;
	
	public Student (String id, String fName, String lName, String program, 
			String level, String asurite) {
		this.id = id;
		firstName = fName;
		lastName = lName;
		this.program = program;
		this.level = level;
		this.asurite = asurite;
	}
	
	public String getId() {
		return id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getProgram() {
		return program;
	}
	
	public String getLevel() {
		return level;
	}
	
	public String getAsurite() {
		return asurite;
	}
}