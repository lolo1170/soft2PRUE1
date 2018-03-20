package soft2PRUE1;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableCellRenderer;

public class TableFrame extends JFrame{
	
	private static final int height=600,width=1200;
	
	private StudentModel model;
	
	private 	JTable jtable;

	private static JPanel northPnl ;
	
	private static JButton addBtn;
	private static JButton removeBtn;
	private static JButton updBtn;
	private static JButton sortBtn;
	
	
	private static JTextField idField;
	private static JTextField nameField;
	private static JTextField FirstnameField;
	private static JTextField mail;

	private static JComboBox <String>box;
	
	public TableFrame(StudentModel model) {
		
		this.model=model;
	init();
	
	addBtn.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("added");
			
			String id=idField.getText();
			String name=nameField.getText();
			String firstName=FirstnameField.getText();
			String mailAdress=mail.getText();
			String skz=(String)box.getSelectedItem();
			
			StudentGrades st=new StudentGrades(id, name, firstName, skz, mailAdress);
			model.add(st);
			jtable.repaint();
			
	
		}
	});


	}
	
	private void init(){
		this.getContentPane().add(new JScrollPane(jtable), BorderLayout.CENTER);
		this.setTitle("PSW2-Results");
	this.setSize(width, height);
	northPnl= new JPanel();
	this.getContentPane().add(northPnl, BorderLayout.NORTH);

	addBtn=new JButton("add");
	removeBtn=new JButton("delete");
	updBtn=new JButton("update");
	sortBtn=new JButton("sort");
	box=new JComboBox<String>(new String []{"521","531","567"})  ;
	idField=new JTextField(8);
	nameField=new JTextField(12);
	FirstnameField=new JTextField(12);
	mail=new JTextField(20);
	northPnl.add(idField);
	northPnl.add(nameField);
	northPnl.add(FirstnameField);
	northPnl.add(box);
	northPnl.add(mail);
	northPnl.add(addBtn);
	northPnl.add(removeBtn);
	northPnl.add(sortBtn);
	jtable=new JTable(model);
	this.setVisible(true);
	this.add(jtable);
	}
			
			
	
}
