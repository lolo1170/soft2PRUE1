package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.TextField;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import model.Student;
import model.Student.Grades;


public class ColorCellRenderer extends JPanel implements TableCellRenderer{

	JTextField text;
	JPanel panel;
	
	public ColorCellRenderer(){
		text=new JTextField("-");
		panel=new JPanel();
		this.add(text);
		this.setOpaque(true);
	}

	private static final long serialVersionUID = 1L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,int row, int column) {
		//TODO if selected row is grade,here he selects all 	
		//super.getTableCellRendererComponent(table, value,  isSelected,  hasFocus,row, column);
			if (isSelected) {
				
			
		//Object grade=	(Student.Grades)table.getModel().getValueAt(table.getSelectedRow(), table.getModel().getColumnCount()-1);
		
			if (value==Grades.undefined) 
			{
				//table.getModel().setValueAt(grade, row, table.getColumnModel().getColumnIndex("GRADE"));
				//	this.setBackground(Color.gray);
					//this.add(new JTextField(grade.toString()));
				//panel.setBackground(Color.gray);
				text.setBackground(Color.GRAY);
				
				panel.add(text);
				return text;
			}
			
			else if (value==Student.Grades.Nicht_Genügend)
			{
				text.setBackground(Color.RED);
				text.setText(value.toString());
				panel.add(text);
				table.setValueAt(Student.Grades.Nicht_Genügend, row, column);
				return text;
			}
			else
			{
			//this.add(new JTextField(grade.toString()));
				this.text.setText(value.toString());
				text.setBackground(Color.GREEN);
				//panel.add(text);
				//this.setBackground(Color.GREEN);
			}
		
			}
		
		
		
		return text;
	}

}
