package control;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import model.*;
public class Model {

	public List<Student> getData() {
		List<Student> l = new ArrayList<>();
	    for(int i=0; i<8;i++) {
	    	l.add(new Student( i, "leon"+i,i+20, true, Color.red));
	    }
	    return l;
	}
}
