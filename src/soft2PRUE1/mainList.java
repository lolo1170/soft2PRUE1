package soft2PRUE1;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class mainList {

	public static void main(String[] args) {
		
		StundentModel model=new StundentModel();
		StudentGradeList list=new StudentGradeList(model);
		JFrame jf=new JFrame();
		list.add(new StudentGrades("22", "Stefan", "plavsic", "stef.plav@gmail.com"));
		jf.add(list);
		jf.setVisible(true);

	}

}
