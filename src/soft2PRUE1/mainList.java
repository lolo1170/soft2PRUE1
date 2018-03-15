package soft2PRUE1;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class mainList {

	public static void main(String[] args) {
		
		StundentModel model=new StundentModel();
		//StudentGradeList list=new StudentGradeList(model);
		JTable jt=new JTable(model);
		JFrame jf=new JFrame();
		jf.add(new JScrollPane(jt), BorderLayout.CENTER);
		model.add(new StudentGrades("22", "Stefan", "plavsic","521", "stef.plav@gmail.com"));
		jf.setVisible(true);

	}

}
