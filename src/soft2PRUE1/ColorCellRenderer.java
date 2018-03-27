package soft2PRUE1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.TextField;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;

import soft2PRUE1.StudentGrades.Grades;


public class ColorCellRenderer extends JPanel implements TableCellRenderer{

	JTextField text;
	public ColorCellRenderer(){
		text=new JTextField("-");
		this.add(text);
		
	}

	private static final long serialVersionUID = 1L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,int row, int column) {
		//TODO if selected row is grade,here he selects all 	
		if (isSelected&&table.getSelectedColumn()==table.getModel().getColumnCount()-1) {
			Object grade=	(StudentGrades.Grades)	table.getModel().getValueAt(table.getSelectedRow(), table.getModel().getColumnCount()-1);
		
			if (grade==Grades.undefined) {
				this.setBackground(Color.gray);
				this.setVisible(true);
				return this;
			}
			
			if (grade==StudentGrades.Grades.Nicht_Genügend) {
				this.setBackground(Color.RED);
				text.setText(grade.toString());
				table.setValueAt(StudentGrades.Grades.Genügend, row, column);
			
			}else{
				text.setText(grade.toString());
				this.setBackground(Color.GREEN);
			}
		}
		
		
		
		return this;
	}

}
