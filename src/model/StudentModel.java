package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.management.remote.SubjectDelegationPermission;
import javax.swing.table.AbstractTableModel;
/*
 * Class which holds all students
 * The Model for the JTable
 * 
 */

/**
 * @author Stefan
 *
 */

/**
 * @author Stefan
 *
 */
/**
 * @author Stefan
 *
 */
public class StudentModel extends AbstractTableModel{


	private static final long serialVersionUID = 1L;
	private final static int COLCOUNT = 13;
	private static String[] colnames = { "ID", "NAME", "FIRSTNAME", "SKZ", "MAIL", 
																"UE1", "UE2", "UE3", "UE4", "UE5","UE6", "SUM", "GRADE" };

	
	private List<Student> students;
	
	public StudentModel() {
		students = new ArrayList<Student>();
	
			Student[] inDB;
			try {
				inDB = StudentDBManager.getInstance().getStudents();
				for (int i = 0; i < inDB.length; i++) {
					students.add(inDB[i]);
					}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
	
		
	}

	/**
	 * @param row
	 * @throws SQLException
	 * Deletes a Student from the table and from the database
	 */
	public void delete(int row) throws SQLException{
		
		if (row<0||row>=students.size()) {return;}
		
		Student delete=students.get(row);
		students.remove(delete);
		fireTableRowsDeleted(row, row);
		StudentDBManager.getInstance().deleteStudentByID(delete.getId());
		
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

	/**
	 * @param rowIndex
	 * @param columnIndex
	 * @return the value in the asked column
	 * Here i did not us a "D array i used a list where the row is in student in the list and the col the field of the stuent
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		Student st = students.get(rowIndex);

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
		return null;
	}


	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {

		if (columnIndex>=5&&columnIndex<=10) {
			return true;
		}
		return false;
	}


	@SuppressWarnings("finally")
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	
		if (aValue==null) {
			return;
		}

		Student st = students.get(rowIndex);
		
		
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
		
		try {
			StudentDBManager.getInstance().updateStudent(st.getId(), st.getPoints());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param student
	 * @throws SQLException
	 * Adds a Student into the model and afterwards in the Table and in the Database
	 */
	public void add(Student student) throws SQLException {
		
	StudentDBManager.getInstance().insertStudent(student);
	students.add(student);
	fireTableRowsInserted(students.size()-1,students.size()-1); //notifies listeners to changes at the end of the list
	
	}

	/**
	 * @param row
	 * a student has a point array and this methods sums up all points and 
	 * calculates the grade
	 */
	public void calcGrade(int row)
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
	
	/**
	 * method for sorting the students in the list. We compare 2 Students by their name
	 * 
	 */
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