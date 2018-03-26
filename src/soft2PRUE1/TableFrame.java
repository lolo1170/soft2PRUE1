package soft2PRUE1;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;

import javax.jws.Oneway;
import javax.swing.CellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class TableFrame extends JFrame {

	private static final int height = 600, width = 1000;

	private StudentModel model;
	private JTable jtable;

	private static JPanel northPnl;

	private static JButton addBtn;
	private static JButton removeBtn;
	private static JButton sortBtn;

	private static JTextField idField;
	private static JTextField nameField;
	private static JTextField FirstnameField;
	private static JTextField mail;

	private static JComboBox<String> skzBox;
	private static JComboBox<Integer>pointsBox;

	public TableFrame(StudentModel model) {
		this.model = model;
		init();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void init() {
		this.setTitle("PSW2-Results");
		this.setSize(width, height);
		northPnl = new JPanel();
		this.getContentPane().add(northPnl, BorderLayout.NORTH);
		
		initJBoxes();

		addBtn = new JButton("add");
		removeBtn = new JButton("delete");
		sortBtn = new JButton("sort");

		idField = new JTextField(8);
		nameField = new JTextField(12);
		FirstnameField = new JTextField(12);
		mail = new JTextField(20);
		
		northPnl.add(idField);
		northPnl.add(nameField);
		northPnl.add(FirstnameField);
		northPnl.add(skzBox);
		northPnl.add(mail);
		northPnl.add(addBtn);
		northPnl.add(removeBtn);
		northPnl.add(sortBtn);
		jtable = new JTable(model);
		jtable.setSize(width, height);
		this.setVisible(true);
		this.add(jtable);
		
		
		DefaultCellEditor cellEditor=new DefaultCellEditor(pointsBox);
		for (int i = 0; i < 6; i++) {
			jtable.getColumnModel().getColumn(i+5).setCellEditor(cellEditor);
		}
		cellEditor.addCellEditorListener(new CellEditorListener() {
			
			@Override
			public void editingStopped(ChangeEvent e) {
				// TODO Auto-generated method stub
				int row =jtable.getSelectedRow();
				model.calcPoints(row);
				model.fireTableDataChanged();
				jtable.repaint();
				
				}
			
			@Override
			public void editingCanceled(ChangeEvent e) {
				// TODO Auto-generated method stub
			}
		});
		
		TableColumn col=jtable.getColumnModel().getColumn(6);
		col.setCellEditor(new DefaultCellEditor(pointsBox));
		this.getContentPane().add(new JScrollPane(jtable), BorderLayout.CENTER);
		
		col=jtable.getColumn("GRADE");
		col.setCellRenderer(new ColorCellRenderer());
		

		addBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (e.getSource()==addBtn) 
				{
					String id = idField.getText();
					String name = nameField.getText();
					String firstName = FirstnameField.getText();
					String mailAdress = mail.getText();
					String skz = (String) skzBox.getSelectedItem();
					model.add(new StudentGrades("22", "Stefan", "plavsic","521", "stef.plav@gmail.com"));
					if (id.isEmpty() || name.isEmpty() || firstName.isEmpty() || skz.isEmpty()) {return;}
					
					StudentGrades st = new StudentGrades(id, name, firstName, skz, mailAdress);
					model.add(st);
					model.fireTableChanged(new TableModelEvent(model, model.getRowCount()-1));
					jtable.repaint();
					jtable.getRootPane().repaint();
				}
			}
		});

		removeBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource()==removeBtn) {
					int []rows=jtable.getSelectedRows();
					for (int i = 0; i < rows.length; i++) {
						System.out.println(rows[i]);
						model.delete(rows[i]);
					}
					
					model.fireTableRowsDeleted(rows[0], rows[rows.length-1]);
					jtable.repaint();
					repaint();
					
					
				}
			}
		});
		
	
		
	}
	private void initJBoxes(){
skzBox = new JComboBox<String>(new String[] { "521", "531", "567" });
		Integer[] pointarr=new Integer[60];
		for (int i = 0; i < 60; i++) {pointarr[i]=new Integer(i+1);}
		pointsBox=new JComboBox<>(pointarr);
		
		idField = new JTextField(8);
		nameField = new JTextField(12);
		FirstnameField = new JTextField(12);
		mail = new JTextField(20);
		
	}

}
