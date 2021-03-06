package model;

/**
 * @author Stefan
 *Class which holds one students and his points + grade
 */

public class Student {

	private String id;
	private String name;
	private String firstName;
	private String skz;
	private String mail;
	private int[]points = new int[6];
	
	int sumPoints=0;
	Grades grade=Grades.undefined;
	
	public enum Grades{Sehr_Gut,Gut,Befriedingend,Gen�gend,Nicht_Gen�gend,undefined;}
	
	

	public Student(String id,String name,String firstName,String skz,String mail) {
		this.name=name;
		this.id=id;
		this.firstName=firstName;
		this.mail=mail;
		this.skz=skz;
		
		for (int i = 0; i < points.length; i++) 
		{
			points[i]=-1;
		}
		calcPoints();
	}
	
	public Student(String id,String name,String firstName,String skz,String mail,int ue1,int ue2,int ue3,int ue4,int ue5,int ue6,Grades grade){
		
		this.name=name;
		this.id=id;
		this.firstName=firstName;
		this.mail=mail;
		this.skz=skz;
		points[0]=ue1;
		points[1]=ue2;
		points[2]=ue3;
		points[3]=ue4;
		points[4]=ue5;
		points[5]=ue6;
		for (int i = 0; i < points.length; i++) {
			sumPoints+=points[i];
		}
		this.grade=grade;
	}
	
public Student(String id,String name,String firstName,String skz,String mail,int[]points){
		
		this.name=name;
		this.id=id;
		this.firstName=firstName;
		this.mail=mail;
		this.skz=skz;
		
		
		for (int i = 0; i < points.length; i++) {
			sumPoints+=points[i];
			this.points[i]=points[i];
		}
	}
	
	public Grades getGrade() {
		return grade;
	}

	public void setGrade(Grades grade) {
		this.grade = grade;
	}

	public void addpointsAtIndex(int index,int achievedPoints) {
		//ToDo check if right index
		if (index<0||index>5) {
			return;
		}
		points[index]=achievedPoints;
		
		
	}
	
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name)) {
			return false;}
		else if(!firstName.equals(other.firstName)) {
			return false;
		}else if(!id.equals(other.id)){
			
			return false;
			
		}
	return true;
	}

	/**
	 * sums up all points and calculates the grade
	 */
	public void calcPoints(){
			int sum =0;
			boolean notAllSubmited=false;
			boolean oneUnder16=false;
		for (int i = 0; i < points.length; i++) 
		{
			if (points[i]==-1) {
				notAllSubmited=true;
			}
			if (points[i]!=-1)
			{
				if (points[i]<16) 
				{
					oneUnder16=true;
				}
				sum+=points[i];
			}
		}
		sumPoints=sum;
		if (notAllSubmited) {
			setGrade(Grades.undefined);
			return;
		}else if(oneUnder16){
			setGrade(Grades.Nicht_Gen�gend);
			return;
		}
		if (sum<144) {
			setGrade(Grades.Nicht_Gen�gend);
		}else if(sum>=144&&sumPoints<168){
			setGrade(Grades.Gen�gend);
		}else if(sum>=168&&sumPoints<192){
			setGrade(Grades.Befriedingend);
		}else if(sum>=192&&sumPoints<216){
			setGrade(Grades.Gut);
		}else{
			setGrade(Grades.Sehr_Gut);
		}
		
	}
	//geters and Setters for one Student
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSkz() {
		return skz;
	}

	public void setSkz(String skz) {
		this.skz = skz;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public int[] getPoints() {
		return points;
	}

	public void setPoints(int[] points) {
		this.points = points;
	}

	public int getSumPoints() {
		return sumPoints;
	}

	public void setSumPoints(int sumPoints) {
		this.sumPoints = sumPoints;
	}
	
}
