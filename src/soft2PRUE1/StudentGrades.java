package soft2PRUE1;

public class StudentGrades {

	String name;
	String firstName;
	String id;
	String mail;
	int[]points=new int[6];
	
	int sumPoints=0;
	
	public StudentGrades(String id,String name,String firstName,String mail) {
		this.name=name;
		this.id=id;
		this.firstName=firstName;
		this.mail=mail;
		
	}
	
	public void addpointsAtIndex(int index,int achievedPoints) {
		//ToDo check if right index
		points[index]=achievedPoints;
		
		
	}
	
}
