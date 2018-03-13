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
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentGrades other = (StudentGrades) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name)) {
			return false;}
		else if(!firstName.equals(other.firstName)) {
			return false;
			
		}else if(!mail.equals(other.mail)){
			
			return false;
			
		}else if(!id.equals(other.id)){
			
			return false;
			
		}
		
		
	
	return true;
	}
	
	
}
