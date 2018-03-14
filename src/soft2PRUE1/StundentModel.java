package soft2PRUE1;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.table.AbstractTableModel;



public class StundentModel extends AbstractTableModel<StudentGrades>{
	
	List<StudentGrades> students= new ArrayList<StudentGrades>();


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
		fireIntervalAdded(this, students.size()-1, students.size()-1);
	}
	
	public void remove(StudentGrades stg) {
		
		if (students.indexOf(stg)!=-1) {
			fireIntervalRemoved(this, students.indexOf(stg), students.indexOf(stg));
			students.remove(students.indexOf(stg));
			
		}
		
		
	}
	

}
