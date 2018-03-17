package soft2PRUE1;

import javax.swing.JList;
import javax.swing.ListSelectionModel;

public class StudentGradeList extends JList<StudentGrades> {
	
	public StudentGradeList(StudentModel model) {
		//super(model);
		setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	
		
	}

}
