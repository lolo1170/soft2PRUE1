package soft2PRUE1;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class StundentModel implements TableModel {

	public final static int COLCOUNT = 12;
	/*
	 * @JavaDoc
	 * 
	 */

	List<StudentGrades> list = new ArrayList<StudentGrades>();
	HashSet<TableModelListener> listeners = new HashSet<TableModelListener>();

	public StundentModel() {
		list = new ArrayList<StudentGrades>();
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
		// TODO Auto-generated method stub

		if (checkIndex(index)) {

			if (index <= 4) {
				return list.get(0).getFirstName().getClass();
			} else {

				return list.get(0).getPoints().getClass();
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
		if (checkIndex(columnIndex)) {

			switch (columnIndex) {
			case 0:
				return "id";
			case 1:
				return "name";
			case 2:
				return "firstname";
			case 3:
				return "skz";
			case 4:
				return "mail";
			case 6:
				return "UE1";
			case 7:
				return "UE2";
			case 8:
				return "UE3";
			case 9:
				return "UE4";
			case 10:
				return "UE5";
			case 11:
				return "UE6";
			}
		}
		return null;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		StudentGrades st = list.get(rowIndex);
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
			case 6:
				return st.getPoints()[0];
			case 7:
				return st.getPoints()[1];
			case 8:
				return st.getPoints()[2];
			case 9:
				return st.getPoints()[3];
			case 10:
				return st.getPoints()[4];
			case 11:
				return st.getPoints()[6];

			}
		}

		return null;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {

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

		StudentGrades st = list.get(rowIndex);
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
		
		list.add(st);
	
		
		
	}

	private boolean checkIndex(int index) {
		if (0 >= index && index <= 10) {
			return true;
		}
		return false;
	}
}