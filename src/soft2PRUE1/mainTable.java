package soft2PRUE1;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

public class mainTable {

	public static void main(String[] args) {
	
		
		
		StudentModel model=new StudentModel();
		//m.add(new StudentGrades("22", "Stefan", "plavsic","521", "stef.plav@gmail.com"));
	
		
		TableFrame t=new TableFrame(model);
		
		/*
		StudentDBManager sm=null;
	
		
		try{
			
			sm=StudentDBManager.getInstance();
			
			
			//sm.deleteAll();
			int[] points=new int[6];
			sm.insertStudent("22", "Stefan", "plavsic","521", "stef.plav@gmail.com",points);
			sm.insertStudent("28","Hari","Pickl","521","harald.pickl@gmx.at",points);
			
			StudentGrades[]st=sm.getStudents();
			for (StudentGrades studentGrades : st) {
				System.out.println(studentGrades.getFirstName());
			}
			
		}catch(SQLException e){
			
			e.printStackTrace();

		} finally {
			try {
				sm.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}


		//SwingUtilities.invokeLater(t);
		
			JTable tb=new JTable(model);
			JButton delete=new JButton("Delete");
			JFrame jf=new JFrame();
			JPanel jp=new JPanel();
			JButton add=new JButton("add");
			add.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					model.add(new StudentGrades("28","Hari","Pickl","521","harald.pickl@gmx.at"));
					
				}
			});
			jp.add(add);
					jp.add(delete);
			jf.getContentPane().add(jp,BorderLayout.NORTH);
			jf.getContentPane().add(new JScrollPane(tb), BorderLayout.CENTER);
			jf.setVisible(true);
			jf.setSize(500, 600);
			model.add(new StudentGrades("22", "Stefan", "plavsic","521", "stef.plav@gmail.com"));
			model.add(new StudentGrades("28", "Hari", "pickl","521", "hari.pickl@gmail.com"));
			
			delete.addActionListener(new ActionListener() {
				
			

				@Override
				public void actionPerformed(ActionEvent arg0) {
					model.delete(tb.getSelectedRow());
				}
			});

	*/

	}

}
