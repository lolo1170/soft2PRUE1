package model.interfaces;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import model.Student;

/**
 * Methods which are accessible by the view 
 * @author Stefan
 *
 */
public interface I_ViewStudentModel extends TableModel {

	/*
	 * add adds a student into the model
	 * 
	 */
	void add(Student student);

	void delete(int index);

	void calcPoints(int row);

	void sort();

	void setViewCallback(I_ViewCallback tableFrame);

}
