package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;


import javax.swing.table.AbstractTableModel;


public class StudentModel extends AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static int COLCOUNT = 13;
	private static String[] colnames = { "ID", "NAME", "FIRSTNAME", "SKZ", "MAIL", 
																"UE1", "UE2", "UE3", "UE4", "UE5","UE6", "SUM", "GRADE" };

	private List<Student> students;
	private StudentDBManager db=null;
	
	public StudentModel() {
		students = new ArrayList<Student>();
		try {
			db=StudentDBManager.getInstance();
			Student[]inDB=db.getStudents();
			for (int i = 0; i < inDB.length; i++) {
				students.add(inDB[i]);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void delete(int row) throws SQLException{
		
		if (row<0||row>=students.size()) {return;}
		
		Student delete=students.get(row);
		students.remove(delete);
		fireTableRowsDeleted(row, row);
		db.deleteStudentByID(delete.getId());
		
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
				return new Student(null, null, null, null, null).getGrade().getClass();
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
/*
			if (students.size()<=rowIndex||rowIndex<0) {
				return null;}
	*/	
		Student st = students.get(rowIndex);

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

		if (columnIndex>=5||columnIndex<=10) {
			//only the points can be  edited afterwards
			return true;
		}
		return false;
	}

	/**
	 * Java DOC
	 * 
	 */
	@SuppressWarnings("finally")
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (rowIndex>=students.size()) 
		{
			return ;
		}

		Student st = students.get(rowIndex);
		
		if (checkIndex(columnIndex)) {
			fireTableDataChanged();
			switch (columnIndex) {
			case 0:fireTableDataChanged();
				st.setId((String) aValue);fireTableCellUpdated(rowIndex, 0);
				break;
			case 1:fireTableDataChanged();fireTableCellUpdated(rowIndex, 1);
				st.setName((String) aValue);
				break;
			case 2:
				st.setFirstName((String) aValue);fireTableDataChanged();fireTableCellUpdated(rowIndex, 2);
				break;
			case 3:
				st.setSkz((String) aValue);fireTableDataChanged();fireTableCellUpdated(rowIndex, 3);
				break;
			case 4:
				st.setMail((String) aValue);fireTableDataChanged();fireTableCellUpdated(rowIndex, 4);
				break;
			case 5:
				st.getPoints()[0] = (int) aValue;st.calcPoints();fireTableDataChanged();fireTableCellUpdated(rowIndex, 5);fireTableCellUpdated(rowIndex, 11);fireTableCellUpdated(rowIndex, 12);
				break;
			case 6:
				st.getPoints()[1] = (int) aValue;st.calcPoints();fireTableDataChanged();fireTableCellUpdated(rowIndex, 6);fireTableCellUpdated(rowIndex, 11);fireTableCellUpdated(rowIndex, 12);
				
				break;
			case 7:
				st.getPoints()[2] = (int) aValue;st.calcPoints();fireTableDataChanged();fireTableCellUpdated(rowIndex, 7);fireTableCellUpdated(rowIndex, 11);fireTableCellUpdated(rowIndex, 12);
				break;
			case 8:
				st.getPoints()[3] = (int) aValue;st.calcPoints();fireTableDataChanged();fireTableCellUpdated(rowIndex, 8);fireTableCellUpdated(rowIndex, 11);fireTableCellUpdated(rowIndex, 12);
				break;
			case 9:
				st.getPoints()[4] = (int) aValue;st.calcPoints();fireTableDataChanged();fireTableCellUpdated(rowIndex, 9);fireTableCellUpdated(rowIndex, 11);fireTableCellUpdated(rowIndex, 12);
				break;
			case 10:
				st.getPoints()[5] = (int) aValue;st.calcPoints();fireTableDataChanged();fireTableCellUpdated(rowIndex, 10);fireTableCellUpdated(rowIndex, 11);fireTableCellUpdated(rowIndex, 12);
				break;
			case 11:st.calcPoints();fireTableCellUpdated(rowIndex, 11);fireTableCellUpdated(rowIndex, 12);
			break;
			case 12:st.setGrade((Student.Grades)aValue);fireTableDataChanged();fireTableCellUpdated(rowIndex, 12);
			break;
			
			}
		}
		try {
			System.out.println("im update");
			db.updateStudent(st.getId(), st.getPoints());
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			
			return;
		}
	}

	public void add(Student st) throws SQLException {
		
		db.insertStudent(st);
	students.add(st);
	fireTableRowsInserted(students.size()-1,students.size()-1); //notifies listeners to changes at the end of the list
	
	}

	private boolean checkIndex(int index) {
		if (0 >= index || index <= COLCOUNT) 
		{
			return true;
		}
		return false;
	}
	public void calcPoints(int row)
	{
		if (row>=students.size()||row<0) 
		{
			return;
		}
		Student st=students.get(row);
		st.calcPoints();
		fireTableCellUpdated(row, COLCOUNT-2);
		fireTableDataChanged();
		
	}
	
	public void sort(){
	fireTableRowsUpdated(0, getRowCount());
		students.sort(new Comparator<Student>() {

			@Override
			public int compare( Student o1, Student o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
	}
	
}