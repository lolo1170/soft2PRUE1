package soft2PRUE1;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;



public class StundentModel extends AbstractListModel<StudentGrades>{
	
	List<StudentGrades> students;

	 public StundentModel() {
		students= new ArrayList<StudentGrades>();
	 
	 }
	
	@Override
	public StudentGrades getElementAt(int index) {
     return students.get(index);
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return students.size();
	}
	
	public void add (StudentGrades student) {
		if (students.contains(student)) {
			return;
		}
		this.add(student);
		fireIntervalAdded(null, students.size()-1, students.size()-1);
	}
	
	public void remove(StudentGrades stg) {
		
		if (students.indexOf(stg)!=-1) {
			fireIntervalRemoved(stg, students.indexOf(stg), students.indexOf(stg));
			students.remove(students.indexOf(stg));
			
		}
		
		
	}
	

}
