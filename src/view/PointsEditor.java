package view;

import java.awt.Color;
import java.awt.Component;import javax.security.auth.login.CredentialExpiredException;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class PointsEditor extends DefaultCellEditor implements TableCellRenderer  {
	
	JPanel panel;
	JTextField textfield;
	public PointsEditor(JComboBox comboBox) {
		super(comboBox);
		
		panel=new JPanel();
		super.getComponent().setBackground(Color.DARK_GRAY);
	//	super.getComponent().getGraphics().drawString("-",5,5);
		//super.editorComponent.setBackground(Color.WHITE);
		//textfield=new JTextField();
		//panel.setBackground(Color.DARK_GRAY);
	}


	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		if (value.equals(-1))
		{
			System.out.println("val is 1");
			panel.setBackground(Color.darkGray);
			textfield.setText("-");
			return panel;
		}
			
		
			
		
		
		return 	super.getTableCellEditorComponent(table, value, isSelected, row, column);
	}

}
