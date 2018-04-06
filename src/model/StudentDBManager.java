package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




public class StudentDBManager {

	
	static {
		
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			getInstance(); 
		} catch (SQLException | ClassNotFoundException e) {
			 e.printStackTrace();
		}
	}
		/** Constant string for the database url */ 
		private static final String DBURL = "jdbc:derby:./studentsdb"; 

		/** the variable holding the singleton instance */
		private static StudentDBManager INSTANCE; 
	

	 public synchronized static StudentDBManager getInstance() throws SQLException {
			if (INSTANCE == null) 
			{
				INSTANCE = new StudentDBManager(); 
			}
			 	return INSTANCE;
			}
	
		public Connection conn; 
		private PreparedStatement insertStudentStmt; 
		private PreparedStatement updateStudentStmt; 
		private PreparedStatement selectStudentByIdStmt; 
		private PreparedStatement selectStudentByNameStmt; 
		private PreparedStatement selectStudentsStmt; 
		private PreparedStatement deleteAllStmt;
		private PreparedStatement deleteStudentByID;
		
		private StudentDBManager() throws SQLException {	
			try {
				
			
			conn = DriverManager.getConnection(DBURL);
			createTables(conn);
			} catch (Exception e) {
				conn = DriverManager.getConnection(DBURL + ";create=true");
				createTables(conn); 
			}
			
			insertStudentStmt = conn.
					prepareStatement("insert into students values( ?, ?, ?, ?, ?,?,?,?,?, ?, ?)");
					//.prepareStatement("insert into students values( ?, ?, ?, ?, ?)"); vorher
			updateStudentStmt = conn
				.prepareStatement("update students set ue1=?,ue2=?,ue3=?,ue4=?,ue5=?,ue6=? where id=? ");
			selectStudentsStmt = conn
					.prepareStatement("select * from students");
			
			deleteStudentByID=conn.prepareStatement("delete from students where id=?");
			deleteAllStmt = conn.prepareStatement("delete from students");
		}
		
		public void insertStudent(String id,String name,String firstName,String skz,String mail) throws SQLException {
			insertStudentStmt.setString(1,id);
			insertStudentStmt.setString(2, name);
			insertStudentStmt.setString(3, firstName);
			insertStudentStmt.setString(4,skz);
			insertStudentStmt.setString(5,mail);
			insertStudentStmt.execute();
			}
		
		public void insertStudent(Student s) throws SQLException{
			insertStudentStmt.setString(1,s.getId());
			insertStudentStmt.setString(2, s.getName());
			insertStudentStmt.setString(3, s.getFirstName());
			insertStudentStmt.setString(4,s.getSkz());
			insertStudentStmt.setString(5,s.getMail());
			insertStudentStmt.setInt(6, s.getPoints()[0]);
	        insertStudentStmt.setInt(7, s.getPoints()[1]);
	        insertStudentStmt.setInt(8, s.getPoints()[2]);
	        insertStudentStmt.setInt(9, s.getPoints()[3]);
	        insertStudentStmt.setInt(10, s.getPoints()[4]);
	        insertStudentStmt.setInt(11, s.getPoints()[5]);
			insertStudentStmt.execute();
		}
		
		/**
		 * Updates the data of a person with the given id. 
		 * @param id the id of the person (used as key in the Persons table)
		 * @param name the new name of the person 
		 * @param sex the possibly change sex of the person 
		 * @return the person object with the changed values 
		 * @throws SQLException
		 */
		public void updateStudent(String id,int[]points) throws SQLException {
			/*
			 * Only the points can be updated The grade and the final sum is calclated from the view
			 * 
			 */
			synchronized (updateStudentStmt) {
				
				updateStudentStmt.setString(7, id);
				for (int i = 0; i < points.length; i++) 
				{
					updateStudentStmt.setInt(i+1, points[i]);
					System.out.println(points[i]+"das sind die Puntke vom studenten");
				}
				updateStudentStmt.executeUpdate();
			}
			updateStudentStmt.executeUpdate();
		}
		
		public Student[] getStudents() throws SQLException {
			List<Student> students = new ArrayList<Student>(); 
		
			ResultSet r = selectStudentsStmt.executeQuery(); 
		
			while (r.next()) {
				
				int[]points=new int[6];
				System.out.println(r.getString(2));
				for (int i = 6; i < 12; i++) {
					points[i-6]=r.getInt(i);
					System.out.println(r.getInt(i)+"das sind points in get Students");
					//System.out.println(points[i]+"das sind im array die points die dem Stunden übergeben werden ");
				}
				Student p = new Student(r.getString(1),r.getString(2), r.getString(3),r.getString(4),r.getString(5),points);
				p.calcPoints();
				/*
				for (int i = 6; i < 12; i++) {
					p.getPoints()[i-6]=r.getInt(i);
				}
				
				p.calcPoints();
				*/
				students.add(p);
			}
			return students.toArray(new Student[students.size()]);
		}
		
		/**
		 * Gets the person object from the given name of a person. 
		 * Reads the person with given name from the Persons table.  
		 * @param name the name of the person to read 
		 * @return the person object with that name
		 * @throws SQLException
		 */
		public Student getStudentByName(String name) throws SQLException {
			ResultSet r;
			synchronized (selectStudentByNameStmt) {

				selectStudentByNameStmt.setString(1, name);
				r = selectStudentByNameStmt.executeQuery(); 
			}
			if (r.next()) 
			{
				Student p = new Student(r.getString(1),r.getString(2), r.getString(3),r.getString(4),r.getString(5));
				for (int i = 6; i < 12; i++) {
					p.getPoints()[i-6]=r.getInt(i);
			}
				p.calcPoints();
				return p;
				
			} else { 
				return null; 
			}
		}
		
		/**
		 * Gets the person object from the given id of a person. 
		 * Reads the person with given id from the Persons table.  
		 * @param id the unique id of the person 
		 * @return the person object with that id
		 * @throws SQLException
		 */
		public Student getStudentById(String id) throws SQLException {
			ResultSet r;
			synchronized (selectStudentByIdStmt) {
				selectStudentByIdStmt.setString(1, id);
				r = selectStudentByIdStmt.executeQuery(); 
			}
			
			if (r.next()) {
				Student p = new Student(r.getString(1),r.getString(2), r.getString(3),r.getString(4),r.getString(5));
				for (int i = 6; i < 12; i++) {
					p.getPoints()[i-6]=r.getInt(i);
				}
				p.calcPoints();
				return p;
			
			} else { 
				return null; 
			}
		}
		
		synchronized void deleteStudentByID(String id) throws SQLException{
			deleteStudentByID.setString(1, id);
			deleteStudentByID.execute();
		}

		
		/**
		 * Deletes all records from the Persons table. 
		 * @throws SQLException
		 */
		
		public void deleteAll() throws SQLException {
			deleteAllStmt.execute(); 
		}
		/**
		 * Closes the database connection. 
		 * @throws SQLException
		 */
		public void close() throws SQLException {
			conn.close();
		}
		/**
		 * Creates the Persons table. 
		 * @param conn
		 * @throws SQLException 
		 */
		public static void createTables(Connection conn) throws SQLException {
			
		String createStudentsTableStr = "create table students (id varchar(30) primary key,"+" name varchar(30), firstname varchar(30),skz varchar(30),mail varchar(30), ue1 int,ue2 int, ue3 int, ue4 int, ue5 int, ue6 int)";  
		//String	createStudentsTableStr = "drop table Students";
		
			try {
				PreparedStatement createStudentsTableStmt = conn.prepareStatement(createStudentsTableStr);
				createStudentsTableStmt.executeUpdate();
			} catch (SQLException e) {
				
			//	e.printStackTrace();
			} finally{
			}
		}
		
		
}
