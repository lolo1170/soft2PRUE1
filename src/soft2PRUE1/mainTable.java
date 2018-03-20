package soft2PRUE1;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JWindow;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class mainTable {

	public static void main(String[] args) {
		
		StudentModel m=new StudentModel();
		m.add(new StudentGrades("22", "Stefan", "plavsic","521", "stef.plav@gmail.com"));
		
	JTable tb=new JTable(m);
	JFrame jf=new JFrame();
	JPanel jp=new JPanel();
	jp.add(new JButton("add"));
	jf.getContentPane().add(jp,BorderLayout.NORTH);
	
	jf.add(tb);
	jf.setVisible(true);
	jf.setSize(500, 600);
	
	
	
	
//	TableFrame tm=	new TableFrame(new StudentModel());
	
	
		

		//model.add(new StudentGrades("22", "Stefan", "plavsic","521", "stef.plav@gmail.com"));
		//model.add(new StudentGrades("28", "Hari", "pickl","521", "hari.pickl@gmail.com"));

	}

}
