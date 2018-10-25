package model;
import java.awt.Color;

public class Student {
    private String name;
    private int id, age;
    private boolean sex;
    private Color  color;
    
    public Student() {		
	}
    
	public Student(int id, String name, int age, boolean sex, Color c) {
		this.id=id;
		this.name=name;
		this.age=age;
		this.sex=sex;
		this.setColor(c);
	}
	
	public Student(int id, String name, int age, boolean sex, int c){
		  this.id = id;
	      this.name = name;
	      this.age=age;
		  this.sex=sex;
	      this.color=new Color(c);

	   }
	 
	public Student(String name, int age, boolean sex, int c){
	      this.name = name;
	      this.age=age;
		  this.sex=sex;
	      this.color=new Color(c);

	   }
	 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public boolean getSex() {
		return sex;
	}
	public void setSex(boolean sex) {
		this.sex = sex;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
}
