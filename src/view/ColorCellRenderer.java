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


public class ColorCellRenderer extends DefaultTableCellRenderer{
	
	public ColorCellRenderer(){
		
		this.setOpaque(true);
		
	}

	private static final long serialVersionUID = 1L;
	/*
	 * (non-Javadoc)
	 * @see javax.swing.table.DefaultTableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
	 Here we color the cell in the table. if the grade is undefined(when on ue is not graded or submited)
	 *
	 */

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,int row, int column) {
			
		
		if (value.equals(-1)) {
			setText("-");
			setBackground(Color.lightGray);
			
			
		}else{
			setBackground(Color.WHITE);
			setText(value.toString());
		}
		
		
			if (value==Grades.undefined) 
			{
				setText("-");
				setBackground(Color.lightGray);
			}
			
			else if (value==Student.Grades.Nicht_Genügend)
			{
				setBackground(Color.RED);
				setText(value.toString());
				return this;
		
			}else if(value.equals(Grades.Genügend)){
				setBackground(Color.GREEN);
				setText(value.toString());
				return this;
				
			}else if(value.equals(Grades.Befriedingend)){
				setText(value.toString());
				setBackground(Color.green.brighter());
				return this;
				
			}else if(value.equals(Grades.Gut)){
				setText(value.toString());
				setBackground(Color.green.brighter().brighter());
				return this;
				
			}else if(value.equals(Grades.Sehr_Gut)){
				setText(value.toString());
				setBackground(Color.green.brighter().brighter().brighter());
				return this;
				
			}
		
		return this;
	}

}
