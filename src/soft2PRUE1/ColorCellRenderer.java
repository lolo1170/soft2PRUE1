package soft2PRUE1;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;


public class ColorCellRenderer extends JPanel implements TableCellRenderer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
			if (isSelected) {
			Object grade=	(StudentGrades.Grades)	table.getModel().getValueAt(table.getSelectedRow(), table.getSelectedColumn());

			if (grade==StudentGrades.Grades.Nicht_Genügend) {
				this.setBackground(Color.RED);
			}
			
			}else{
				setBorder(null);
			}
		return this;
	}

}
