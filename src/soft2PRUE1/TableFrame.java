package soft2PRUE1;
import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;

public class TableFrame extends JFrame{
	
	private int height=600,width=1200;
	private static String[]skz= {"521","520","543"};
	private static JPanel northPnl = new JPanel();
	private static JButton addBtn;
	private static JButton removeBtn;
	private static JButton updBtn;
	private static JButton sortBtn;
	private static JTextField idField;
	private static JTextField nameField;
	private static JTextField FirstnameField;
	private static JComboBox <String>box;
	private static JTextField mail;
	
	public TableFrame(StudentModel model) {
	
		this.setTitle("PSW2-Results");
	this.setSize(width, height);
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
	JTable jtable=new JTable(model);
	//jtable.setDefaultRenderer(model.getColumnClass(0), );
	this.getContentPane().add(new JScrollPane(jtable), BorderLayout.CENTER);
	this.setVisible(true);
	}



}
