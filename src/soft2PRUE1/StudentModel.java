package soft2PRUE1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

public class StudentModel implements TableModel {

	private final static int COLCOUNT = 13;
	private static String[] colnames = { "ID", "NAME", "FIRSTNAME", "SKZ", "MAIL", "UE1", "UE2", "UE3", "UE4", "UE5","UE5", "SUM", "GRADE" };

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

		if (columnIndex==COLCOUNT-1) {
			//12 is the Frade, it shoudl bbe edite by the program and not by user
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

			switch (columnIndex) {
			case 0:
				st.setId((String) aValue);
				return;
			case 1:
				st.setName((String) aValue);
				return;
			case 2:
				st.setFirstName((String) aValue);
				return;
			case 3:
				st.setSkz((String) aValue);
				return;
			case 4:
				st.setMail((String) aValue);
				return;
			case 6:
				st.getPoints()[0] = (int) aValue;
				return;
			case 7:
				st.getPoints()[1] = (int) aValue;
				return;
			case 8:
				st.getPoints()[2] = (int) aValue;
				return;
			case 9:
				st.getPoints()[3] = (int) aValue;
				return;
			case 10:
				st.getPoints()[4] = (int) aValue;
				return;
			case 11:
				st.getPoints()[6] = (int) aValue;
				return;

			}
		}
	}

	public void add(StudentGrades st) {
		students.add(st);

	}

	private boolean checkIndex(int index) {
		if (0 >= index || index <= COLCOUNT) {
			return true;
		}
		return false;
	}
}