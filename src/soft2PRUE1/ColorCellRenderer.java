package soft2PRUE1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;

import soft2PRUE1.StudentGrades.Grades;


public class ColorCellRenderer extends JPanel implements TableCellRenderer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,int row, int column) {
		//TODO if selected row is grade,here he selects all 	
		if (isSelected) {
			Object grade=	(StudentGrades.Grades)	table.getModel().getValueAt(table.getSelectedRow(), table.getModel().getColumnCount()-1);
			this.add(new JTextField(Grades.Gut.toString()),BorderLayout.CENTER);
			if (grade==Grades.undefined) {
				this.setBackground(Color.gray);
				this.setVisible(true);
				return this;
			}
			
			if (grade==StudentGrades.Grades.Nicht_Genügend) {
				this.setBackground(Color.RED);
				table.setValueAt(StudentGrades.Grades.Genügend, row, column);
			
			}else{
				this.setBackground(Color.GREEN);
			}
		}
		return this;
	}

}
