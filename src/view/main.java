package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import model.Student;
import model.StudentDBManager;
import model.StudentModel;

public class main {

	public static void main(String[] args) {
	
		
		
	
		//m.add(new StudentGrades("22", "Stefan", "plavsic","521", "stef.plav@gmail.com"));
	StudentModel model=	new StudentModel();
		
	TableFrame t=new TableFrame(model);
	//StudentDBManager sm=null;
	
	/*
	try{
		
		sm=StudentDBManager.getInstance();
		
		
		sm.deleteAll();
		sm.insertStudent(new Student("22", "Stefan", "plavsic","521", "stef.plav@gmail.com"));
	sm.insertStudent(new Student("23","Hari","Pickl","521","harald.pickl@gmx.at"));
	sm.insertStudent(new Student("28","Alois","Huch","521","@"));
		
		
		Student[]st=sm.getStudents();
		for (Student students : st) {
			model.add(students);
		}
		
	}catch(SQLException e){
		
		e.printStackTrace();
	} finally {
		try {
		
			sm.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	*/	



	}

}
