package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Stefan
 *
 */
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
	
		private Connection conn; 
	 	private final PreparedStatement insertStudentStmt; 
		 private final PreparedStatement updateStudentStmt; 
		  private PreparedStatement selectStudentByIdStmt; 
		private final PreparedStatement selectStudentsStmt; 
		 private final PreparedStatement deleteAllStmt;
		 private final PreparedStatement deleteStudentByID;
		
		private  StudentDBManager() throws SQLException {	
			try {
			conn = DriverManager.getConnection(DBURL);
			createTables(conn);
			} catch (Exception e) {
				conn = DriverManager.getConnection(DBURL + ";create=true");
				createTables(conn); 
			}
			
			insertStudentStmt = conn.
					prepareStatement("insert into students values( ?, ?, ?, ?, ?,?,?,?,?, ?, ?)");
			updateStudentStmt = conn
				.prepareStatement("update students set ue1=?,ue2=?,ue3=?,ue4=?,ue5=?,ue6=? where id=? ");
			selectStudentsStmt = conn
					.prepareStatement("select * from students");
			deleteStudentByID=conn.
					prepareStatement("delete from students where id=?");
			deleteAllStmt = conn.
					prepareStatement("delete from students");
		}
		
		/**
		 * @param id the id should be unique
		 * @param name the name of the student to insert
		 * @param firstName the first name
		 * @param skz 
		 * @param mail
		 * @throws SQLException
		 * inserts a new Student without any points. So the point are displayed as "-"
		 */
		public void insertStudent(String id,String name,String firstName,String skz,String mail) throws SQLException {
			insertStudentStmt.setString(1,id);
			insertStudentStmt.setString(2, name);
			insertStudentStmt.setString(3, firstName);
			insertStudentStmt.setString(4,skz);
			insertStudentStmt.setString(5,mail);
			insertStudentStmt.execute();
			}
		
		/**
		 * @param student
		 * @throws SQLException
		 * Inserts a Student when it is alrready in the Database with all points
		 */
		public void insertStudent(Student student) throws SQLException{
			insertStudentStmt.setString(1,student.getId());
			insertStudentStmt.setString(2, student.getName());
			insertStudentStmt.setString(3, student.getFirstName());
			insertStudentStmt.setString(4,student.getSkz());
			insertStudentStmt.setString(5,student.getMail());
			insertStudentStmt.setInt(6, student.getPoints()[0]);
	        insertStudentStmt.setInt(7, student.getPoints()[1]);
	        insertStudentStmt.setInt(8, student.getPoints()[2]);
	        insertStudentStmt.setInt(9, student.getPoints()[3]);
	        insertStudentStmt.setInt(10, student.getPoints()[4]);
	        insertStudentStmt.setInt(11, student.getPoints()[5]);
			insertStudentStmt.execute();
		}
		
	
		/**
		 * @param id the id of the student, should be unique
		 * @param points the points the student achieved on the exercises
		 * @throws SQLException
		 */
		public void updateStudent(String id,int[]points) throws SQLException {
		
			synchronized (updateStudentStmt) {
				
				updateStudentStmt.setString(7, id);
				for (int i = 0; i < points.length; i++) 
				{
					updateStudentStmt.setInt(i+1, points[i]);
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
				for (int i = 6; i < 12; i++) {
					points[i-6]=r.getInt(i);
				}
				Student p = new Student(r.getString(1),r.getString(2), r.getString(3),r.getString(4),r.getString(5),points);
				p.calcPoints();
				students.add(p);
			}
			return students.toArray(new Student[students.size()]);
		}
		

		

		/**
		 * @param id of the hstudent
		 * @return
		 * @throws Exception 
		 */
		public Student getStudentById(String id) throws Exception {
			if (id==null) {
				 throw new Exception("Student is null");
			}
			ResultSet r;
			synchronized (selectStudentByIdStmt) {
				selectStudentByIdStmt.setString(1, id);
				r = selectStudentByIdStmt.executeQuery(); 
			}
			
			if (r.next()) {
				Student p = new Student(r.getString(1),r.getString(2), r.getString(3),r.getString(4),r.getString(5));
				//Also insert the points
				for (int i = 6; i < 12; i++)
				{
					p.getPoints()[i-6]=r.getInt(i);
				}
				p.calcPoints();
				return p;
			} else { 
				return null; 
			}
		}
		
		/**
		 * @param id
		 * delete one student from the database
		 * @throws SQLException
		 */
		synchronized void deleteStudentByID(String id) throws SQLException{
			deleteStudentByID.setString(1, id);
			deleteStudentByID.execute();
		}

		
		/**
		 * Deletes all records from the Students table. 
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
		 * Creates the Student  table. 
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
			} finally{
			}
		}
		
		
}
