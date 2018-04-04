package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;
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
import javax.swing.SwingUtilities;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import model.Student;
import model.StudentModel;
import model.interfaces.I_ViewCallback;
import model.interfaces.I_ViewStudentModel;

public class TableFrame implements I_ViewCallback {

	 private JFrame frame;
	private static final int height = 600, width = 1000;

	
	private I_ViewStudentModel model;
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

	public TableFrame(I_ViewStudentModel model) {
		frame=new JFrame("PSW2-Results");
		this.model = model;
		this.model.setViewCallback(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		init();
		
		SwingUtilities.invokeLater(() -> {
			frame.pack();
			frame.setLocation(200, 200);
			frame.setVisible(true);
		});
	}

	private void init() {
		
		frame.setSize(width, height);
		northPnl = new JPanel();
		frame.getContentPane().add(northPnl, BorderLayout.NORTH);
		frame.setVisible(true);
		
	
		addBtn = new JButton("add");
		removeBtn = new JButton("delete");
		sortBtn = new JButton("sort");

		idField = new JTextField(8);
		nameField = new JTextField(12);
		FirstnameField = new JTextField(12);
		mail = new JTextField(20);
		
		jtable = new JTable(model);
		jtable.invalidate();
	    frame.add(jtable);
		//jtable.setSize(width, height);
		northPnl.add(idField);
		northPnl.add(nameField);
		initJBoxes();
		initRenderesEditors();
		northPnl.add(FirstnameField);
		northPnl.add(skzBox);
		northPnl.add(mail);
		northPnl.add(addBtn);
		northPnl.add(removeBtn);
		northPnl.add(sortBtn);
		model.addTableModelListener(new  TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent e) {
				e.getFirstRow();
				System.out.println("Table changed");
			//frame.repaint();
			}
		});
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
				
					model.add(new Student("22", "Stefan", "plavsic","521", "stef.plav@gmail.com"));
					model.add(new Student("28","Hari","Pickl","521","harald.pickl@gmx.at"));
				model.add(new Student("28","Alois","Huch","521","@"));
				
					if (id.isEmpty() || name.isEmpty() || firstName.isEmpty() || skz.isEmpty()) {return;}
					
					Student st = new Student(id, name, firstName, skz, mailAdress);
					model.add(st);
				
					
				}
				}
		});

		removeBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					int []rows=TableFrame.this.jtable.getSelectedRows();
				
					for (int i = 0; i < rows.length; i++)
					{
						model.delete(rows[i]);
					}
			}
		});
		sortBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				TableFrame.this.model.sort();
				
			}
		});
		
	}
	private void initRenderesEditors(){
		TableColumn col=jtable.getColumnModel().getColumn(6);
		col.setCellEditor(new DefaultCellEditor(pointsBox));
		frame.getContentPane().add(new JScrollPane(jtable), BorderLayout.CENTER);
		col=jtable.getColumn("GRADE");
		col.setCellRenderer(new ColorCellRenderer());
		
		PointsEditor p=new PointsEditor(pointsBox);
		
		DefaultCellEditor cellEditor=new DefaultCellEditor(pointsBox);
		
		for (int i = 0; i < 6; i++) 
		{
			jtable.getColumnModel().getColumn(i+5).setCellEditor(cellEditor);
		}
		cellEditor.addCellEditorListener(new CellEditorListener() {
			
			@Override
			public void editingStopped(ChangeEvent e) {
				// TODO Auto-generated method stub
				int row =jtable.getSelectedRow();
				TableFrame.this.model.calcPoints(row);
				}
			@Override
			public void editingCanceled(ChangeEvent e) {
				cellEditor.stopCellEditing();
			}
		});
		
		
		
	}
	private void initJBoxes(){
		
		Integer[] pointarr=new Integer[40];
		
		skzBox = new JComboBox<String>(new String[] { "521", "531", "567" });
		
		for (int i = 0; i < 40; i++) {pointarr[i]=new Integer(i+1);}
		
		pointsBox=new JComboBox<>(pointarr);


		
	}

	@Override
	public void updateView()  {
		System.out.println("in update view");
		jtable.repaint();
		frame.getContentPane().repaint();
		frame.getContentPane().repaint();
		frame.invalidate();
		frame.repaint();
	}

}
