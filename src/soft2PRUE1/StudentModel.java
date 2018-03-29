package soft2PRUE1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

public class StudentModel extends AbstractTableModel implements TableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static int COLCOUNT = 13;
	private static String[] colnames = { "ID", "NAME", "FIRSTNAME", "SKZ", "MAIL", "UE1", "UE2", "UE3", "UE4", "UE5","UE6", "SUM", "GRADE" };

	private List<StudentGrades> students;
	private HashSet<TableModelListener> listeners;

	public StudentModel() {
		students = new ArrayList<StudentGrades>();
		listeners = new HashSet<TableModelListener>();
	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		listeners.add(l);
	}
	public void delete(int row){
		if (row<0||row>=students.size()) {
			return;
		}
		students.remove(row);
		fireTableRowsDeleted(row, row);
		
	}
	/*
	 * 
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getColumnClass(int)
	 */
	@Override
	public Class<?> getColumnClass(int index) {
		
		if (checkIndex(index)) {

			if (index <= 4) {
				return new String().getClass();

			} else if (index > 4 && index <= 11) {

				return new Integer(0).getClass();
			
			} else if (index == 12) {
				return new StudentGrades(null, null, null, null, null).getGrade().getClass();// students.get(0).getGrade().getClass();;
			} 
		}
		return null;
	}

	@Override
	public int getColumnCount() {

		return COLCOUNT;
	}

	@Override
	public String getColumnName(int columnIndex) {
			return colnames[columnIndex];
	}

	@Override
	public int getRowCount() {
		return students.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

			
		StudentGrades st = students.get(rowIndex);

		if (checkIndex(columnIndex)) {
			switch (columnIndex) {
			case 0:
				return st.getId();
			case 1:
				return st.getName();
			case 2:
				return st.getFirstName();
			case 3:
				return st.getSkz();
			case 4:
				return st.getMail();
			case 5:
				return st.getPoints()[0];
			case 6:
				return st.getPoints()[1];
			case 7:
				return st.getPoints()[2];
			case 8:
				return st.getPoints()[3];
			case 9:
				return st.getPoints()[4];
			case 10:
				return st.getPoints()[5];
			case 11:
				return st.sumPoints;
			case 12:
				return st.getGrade();
			}
		}
		return null;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {

		if (columnIndex==COLCOUNT-1||columnIndex==COLCOUNT-2) {
			//12 is the Grade, it shoudl bbe edite by the program and not by user
			return false;
		}
		// TODO look all cells are editable, Edin thinks SKZ and points
		return true;
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		listeners.remove(l);
	}

	/**
	 * Java DOC
	 * 
	 */

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

		StudentGrades st = students.get(rowIndex);
		
		if (checkIndex(columnIndex)) {
			fireTableDataChanged();
			switch (columnIndex) {
			case 0:fireTableDataChanged();
				st.setId((String) aValue);fireTableCellUpdated(rowIndex, 0);
				return;
			case 1:fireTableDataChanged();fireTableCellUpdated(rowIndex, 1);
				st.setName((String) aValue);
				return;
			case 2:
				st.setFirstName((String) aValue);fireTableDataChanged();fireTableCellUpdated(rowIndex, 2);
				return;
			case 3:
				st.setSkz((String) aValue);fireTableDataChanged();fireTableCellUpdated(rowIndex, 3);
				return;
			case 4:
				st.setMail((String) aValue);fireTableDataChanged();fireTableCellUpdated(rowIndex, 4);
				return;
			case 5:
				st.getPoints()[0] = (int) aValue;fireTableDataChanged();fireTableCellUpdated(rowIndex, 5);
				return;
			case 6:
				st.getPoints()[1] = (int) aValue;fireTableDataChanged();fireTableCellUpdated(rowIndex, 6);
				return;
			case 7:
				st.getPoints()[2] = (int) aValue;fireTableDataChanged();fireTableCellUpdated(rowIndex, 7);
				return;
			case 8:
				st.getPoints()[3] = (int) aValue;fireTableDataChanged();fireTableCellUpdated(rowIndex, 8);
				return;
			case 9:
				st.getPoints()[4] = (int) aValue;fireTableDataChanged();fireTableCellUpdated(rowIndex, 9);
				return;
			case 10:
				st.getPoints()[5] = (int) aValue;fireTableDataChanged();fireTableCellUpdated(rowIndex, 10);
				return;
			case 11:st.calcPoints();st.setSumPoints((int)aValue);fireTableDataChanged();fireTableCellUpdated(rowIndex, 11);return;
			case 12:st.setGrade((StudentGrades.Grades)aValue);fireTableDataChanged();fireTableCellUpdated(rowIndex, 12);

			}
		}
	}

	public void add(StudentGrades st) {
		students.add(st);
		
		fireTableRowsInserted(students.size()-1, students.size());
		fireTableDataChanged();
		fireTableStructureChanged();
	}

	private boolean checkIndex(int index) {
		if (0 >= index || index <= COLCOUNT) {
			return true;
		}
		return false;
	}
	public void calcPoints(int row){
		if (row>students.size()) {
			return;
		}
		StudentGrades st=students.get(row);
		st.calcPoints();
		fireTableCellUpdated(row, COLCOUNT-2);
		fireTableDataChanged();
		
	}
	
	public void sort(){
	fireTableStructureChanged();
	fireTableRowsUpdated(0, getRowCount());
	fireTableDataChanged();
		students.sort(new Comparator<StudentGrades>() {

			@Override
			public int compare( StudentGrades o1, StudentGrades o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
	}
	
}