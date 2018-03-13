package soft2PRUE1;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;



public class StundentModel extends AbstractListModel<StudentGrades>{
	
	List<StudentGrades> students;

	 public StundentModel() {
		students= new ArrayList();
	 
	 }
	
	@Override
	public StudentGrades getElementAt(int index) {
		// TODO Auto-generated method stub
     return students.get(index);
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return students.size();
	}
	
	
	

}
