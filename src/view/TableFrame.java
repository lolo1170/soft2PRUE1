package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import model.Student;
import model.StudentModel;


/**
 * @author Stefan
 *class for the Frame, the view.
 *It holds all buttons and thexfields and is also the conrtoller(what happens when i cklick on add)
 */
public class TableFrame {

	 private JFrame frame;
	private static final int height = 600, width = 1000;

	
	private final StudentModel model;
	private final JTable jtable;

	private final JPanel northPnl;

	private final JButton addBtn;
	private final JButton removeBtn;
	private  final JButton sortBtn;

	private  final JTextField idField;
	private  final JTextField nameField;
	private  final JTextField FirstnameField;
	private  final JTextField mail;

	private final  JComboBox<String> skzBox;
	private  final JComboBox<Integer>pointsBox;
	

	public TableFrame(StudentModel model) {
		
		northPnl=new JPanel();
		this.model = model;
		jtable = new JTable(model);
		
		frame=new JFrame("PSW2-Results");
		frame.getContentPane().add(new JScrollPane(jtable), BorderLayout.CENTER);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		addBtn = new JButton("add");
		removeBtn = new JButton("delete");
		sortBtn = new JButton("sort");
		
		idField = new JTextField(8);
		nameField = new JTextField(12);
		FirstnameField = new JTextField(12);
		mail = new JTextField(20);
		
		
		skzBox = new JComboBox<String>(new String[] { "521", "531", "567" });
		
		
		Integer[] pointarr=new Integer[40];
		
	
		
		for (int i = 0; i < 40; i++) {pointarr[i]=new Integer(i+1);}
		
		pointsBox=new JComboBox<>(pointarr);
	
	init();
		SwingUtilities.invokeLater(() -> {
			frame.pack();
			frame.setLocation(200, 200);
			frame.setVisible(true);
		});
	}

	/**
	 * inits the JFrame and adds listeners to the buttons
	 */
	private final void init() {
		initNorthPanel();
	initRenderesEditors();
	

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
				
					if (id.isEmpty() || name.isEmpty() || firstName.isEmpty() || skz.isEmpty()) {return;}
					
					Student st = new Student(id, name, firstName, skz, mailAdress);
				
						try {
							model.add(st);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					
					
				
					
				}
			}
		});

		removeBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					int []rows=TableFrame.this.jtable.getSelectedRows();
				
					for (int i = 0; i < rows.length; i++)
					{
							try {
								model.delete(rows[i]);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
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
	
	/**
	 * adds the buttons into the panel
	 */
	private void initNorthPanel(){

		
		northPnl.add(idField);
		northPnl.add(nameField);
		northPnl.add(FirstnameField);
		northPnl.add(skzBox);
		northPnl.add(mail);
		northPnl.add(addBtn);
		northPnl.add(removeBtn);
		northPnl.add(sortBtn);
		frame.getContentPane().add(northPnl, BorderLayout.NORTH);
		
		
		
	}
	/**
	 * inits the Renderer and the editor and installs it
	 */
	private void initRenderesEditors(){

		jtable.getColumn("GRADE").setCellRenderer(new ColorCellRenderer());
		
		
		for (int i = 0; i < 6; i++) 
		{
			jtable.getColumnModel().getColumn(i+5).setCellEditor(new DefaultCellEditor(pointsBox));
			jtable.getColumnModel().getColumn(i+5).setCellRenderer(new ColorCellRenderer());
		}
		
	}
	}
	
