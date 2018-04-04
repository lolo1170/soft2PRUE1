package view;

import java.util.EventObject;

import javax.swing.CellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;

public class PointCellEditor implements CellEditor{

		JTable table;
	public PointCellEditor(JCheckBox checkBox ,JTable table) {
		//TODO check if 
		this.table=table;

			
	}
	@Override
	public void addCellEditorListener(CellEditorListener arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void cancelCellEditing() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Object getCellEditorValue() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isCellEditable(EventObject arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void removeCellEditorListener(CellEditorListener arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean shouldSelectCell(EventObject arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean stopCellEditing() {
		// TODO Auto-generated method stub
		return false;
	}

}
