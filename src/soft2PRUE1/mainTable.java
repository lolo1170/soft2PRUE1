package soft2PRUE1;


public class mainTable {

	public static void main(String[] args) {
	
		
		
		StudentModel model=new StudentModel();
		//m.add(new StudentGrades("22", "Stefan", "plavsic","521", "stef.plav@gmail.com"));
	
		
		TableFrame t=new TableFrame(model);
		//SwingUtilities.invokeLater(t);
		
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
