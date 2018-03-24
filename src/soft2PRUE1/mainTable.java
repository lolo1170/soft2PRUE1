package soft2PRUE1;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	
		
		
		StudentModel model=new StudentModel();
		//m.add(new StudentGrades("22", "Stefan", "plavsic","521", "stef.plav@gmail.com"));
		TableFrame t=new TableFrame(model);
		
		
	/*			
			JTable tb=new JTable(model);
			JButton delete=new JButton("Delete");
			JFrame jf=new JFrame();
			JPanel jp=new JPanel();
			jp.add(new JButton("add"));
			jp.add(delete);
			jf.getContentPane().add(jp,BorderLayout.NORTH);
			jf.getContentPane().add(new JScrollPane(tb), BorderLayout.CENTER);
			jf.setVisible(true);
			jf.setSize(500, 600);
			model.add(new StudentGrades("22", "Stefan", "plavsic","521", "stef.plav@gmail.com"));
			model.add(new StudentGrades("28", "Hari", "pickl","521", "hari.pickl@gmail.com"));
			
			delete.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					model.delete(tb.getSelectedRow());
					//System.out.println("removed");
				}
			});

	*/

	}

}
