package model;
import java.sql.SQLException;
import java.util.List;

import model.Student;

public interface StudentDao {
	   public List<Student> getAll() throws SQLException;
	   public Student find(int rollNo) throws SQLException;
	   public boolean update(Student student) throws SQLException;
	   public boolean delete(int id) throws SQLException;
	   public boolean Insert(Student s) throws SQLException;
	   public Student find(String name) throws SQLException;

}
