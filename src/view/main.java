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
	
		
	TableFrame t=new TableFrame(new StudentModel());
		
		



	}

}