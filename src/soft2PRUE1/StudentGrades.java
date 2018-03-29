package soft2PRUE1;

public class StudentGrades {

	private String id;
	private String name;
	private String firstName;
	private String skz;
	private String mail;
	private int[]points = new int[6];
	
	int sumPoints=0;
	Grades grade=Grades.undefined;
	
	enum Grades{Sehr_Gut,Gut,Befriedingend,Genügend,Nicht_Genügend,undefined;}
	
	

	public StudentGrades(String id,String name,String firstName,String skz,String mail) {
		this.name=name;
		this.id=id;
		this.firstName=firstName;
		this.mail=mail;
		this.skz=skz;
		for (int i = 0; i < points.length; i++) {
			points[i]=-1;
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
			setGrade(Grades.Nicht_Genügend);
			return;
		}
		if (sum<144) {
			setGrade(Grades.Nicht_Genügend);
		}else if(sum>=144&&sumPoints<168){
			setGrade(Grades.Genügend);
		}else if(sum>=168&&sumPoints<192){
			setGrade(Grades.Befriedingend);
		}else if(sum>=192&&sumPoints<216){
			setGrade(Grades.Gut);
		}else{
			setGrade(Grades.Sehr_Gut);
		}
		
	}
	
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
