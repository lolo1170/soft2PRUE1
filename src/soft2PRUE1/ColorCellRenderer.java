package soft2PRUE1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.TextField;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import soft2PRUE1.StudentGrades.Grades;


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
			if (hasFocus) {
				
			
		Object grade=	(StudentGrades.Grades)	table.getModel().getValueAt(table.getSelectedRow(), table.getModel().getColumnCount()-1);
		
			if (grade==Grades.undefined) 
			{
				table.getModel().setValueAt(grade, row, table.getColumnModel().getColumnIndex("GRADE"));
					this.setBackground(Color.gray);
					this.add(new JTextField(grade.toString()));
				return this;
			}else if (grade==StudentGrades.Grades.Nicht_Genügend)
			{
				this.setBackground(Color.RED);
				text.setText(grade.toString());
				panel.add(text);
				table.setValueAt(StudentGrades.Grades.Nicht_Genügend, row, column);
			}else{
			this.add(new JTextField(grade.toString()));
				panel.add(text);
				this.setBackground(Color.GREEN);
			}
		
			}
		
		
		
		return this;
	}

}
