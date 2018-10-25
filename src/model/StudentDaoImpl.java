package model;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class StudentDaoImpl implements StudentDao {

	private Connection connection;

	private Statement statement;

	public StudentDaoImpl(Connection cnx) throws SQLException {
        this.connection = cnx;
        
        statement = connection.createStatement();
	}



	@Override
	public boolean delete(int id) throws SQLException {
		
		Boolean flag =false;
		String sql = "delete from student where id ='"+id+"'";
		int rs = statement.executeUpdate(sql);
		if (rs>=0) {
			flag = true;
		}
		
		System.out.println("Student: Roll No " + id + ", deleted from database");
		return flag;
	}

	@Override
	public boolean Insert(Student s) throws SQLException {
		
		Boolean flag =false;
		String sql ="INSERT INTO student (name, age, sex,color) " +"VALUES ( ?, ?, ?,?)";
		PreparedStatement preparedStmt = connection.prepareStatement(sql);
	   // preparedStmt.setInt(1, s.getId());
	    preparedStmt.setString (1, s.getName());
	    preparedStmt.setInt   (2, s.getAge());
	    preparedStmt.setBoolean(3, s.getSex());
	    preparedStmt.setInt(4, s.getColor().getRGB());
	    if(preparedStmt.execute()) flag=true;
		
		
		System.out.println("Student: Roll No " + s.getId() + ", insert into database");
		return flag;
	}
	@Override
	public List<Student> getAll() throws SQLException {
		List<Student> students= new ArrayList<>();
		String sql = "select * from student";
		ResultSet rs = statement.executeQuery(sql);
		while (rs.next()) {
			String name = rs.getString("name");
			String id = rs.getString("id");
			String age = rs.getString("age");
			String sex = rs.getString("sex");
			String c = rs.getString("color");
			System.out.println(id + "\t" + name);
            Student a = new Student(Integer.parseInt(id), name,Integer.parseInt(age),Boolean.parseBoolean(sex),Integer.parseInt(c));
			students.add(a);
		}
		rs.close();
		
		return students;
	}

	@Override
	public Student find(int rollNo) throws SQLException {
		List<Student> student = new ArrayList<>();
		String sql = "select * from student where id ='"+rollNo+"'";
		ResultSet rs = statement.executeQuery(sql);
		while (rs.next()) {
			String name = rs.getString("name");
			String id = rs.getString("id");
			String age = rs.getString("age");
			String sex = rs.getString("sex");
			String c = rs.getString("color");
			System.out.println(id + "\t" + name);

			student.add(new Student(Integer.parseInt(id), name,Integer.parseInt(age),Boolean.parseBoolean(sex),Integer.parseInt(c)));
		}
		rs.close();
		return student.get(0);
	}

	@Override
	public Student find(String name) throws SQLException {
		List<Student> student = new ArrayList<>();
		String sql = "select * from student where name ='"+name+"'";
		ResultSet rs = statement.executeQuery(sql);
		while (rs.next()) {
			String nam = rs.getString("name");
			String id = rs.getString("id");
			String age = rs.getString("age");
			String sex = rs.getString("sex");
			String c = rs.getString("color");
			System.out.println(id + "\t" + name);

			student.add(new Student(Integer.parseInt(id), nam,Integer.parseInt(age),Boolean.parseBoolean(sex),Integer.parseInt(c)));
		}
		rs.close();
		return student.get(0);
	}
	@Override
	public boolean update(Student student) throws SQLException {
		String name = student.getName();
		int id = student.getId();
		Boolean flag =false;
		String sql = "update student set name = '"+name+"' where id = '"+id+"'";
		int rs = statement.executeUpdate(sql);
		if (rs>=1) {
			flag = true;
		}
	
		System.out.println("Student: Roll No " + student.getId() + ", updated in the database");
		return flag;
	}
}
