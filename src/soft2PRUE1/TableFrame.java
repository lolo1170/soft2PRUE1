package soft2PRUE1;
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class TableFrame extends JFrame{
	
	private static String[]skz= {"521","520","543"};
	private static JPanel northPnl = new JPanel();
	private static JButton addBtn;
	private static JButton removeBtn;
	private static JButton updBtn;
	private static JButton sortBtn;
	private static JTextField idField;
	private static JTextField nameField;
	private static JTextField FirstnameField;
	private static JComboBox box;
	private static JTextField mail;
	
	public TableFrame(StudentModel model) {
	
	this.getContentPane().add(northPnl, BorderLayout.NORTH);
	addBtn=new JButton("add");
	removeBtn=new JButton("delete");
	updBtn=new JButton("update");
	sortBtn=new JButton("sort");
	box=new JComboBox<String>(skz);
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
	this.getContentPane().add(new JScrollPane(new JTable(model)), BorderLayout.CENTER);
	this.setVisible(true);
	}

}
