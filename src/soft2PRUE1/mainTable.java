package soft2PRUE1;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class mainTable {

	public static void main(String[] args) {
		
	
		StudentModel model=new StudentModel();
	TableFrame tm=	new TableFrame(model);
	
	
		

		model.add(new StudentGrades("22", "Stefan", "plavsic","521", "stef.plav@gmail.com"));
		model.add(new StudentGrades("28", "Hari", "pickl","521", "hari.pickl@gmail.com"));

	}

}
