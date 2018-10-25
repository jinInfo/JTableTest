package view;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;
import model.*;
public class JTableModel extends DefaultTableModel{
	private List<Student> data;
	private String[] columnNames = {"id","name","age", "sex","color"};
	
	
    public JTableModel(List<Student> data) {
		this.data=data;
	}

	@Override
	public int getColumnCount() {
		
		return 5;
	}

	@Override
	public int getRowCount() {
		int result = 0;
		synchronized (this) {
			if (this.data != null) {
				result = data.size();
			} // if
		} // synchronized
		return result;
	}

	@Override
	public Object getValueAt(int line, int col) {
		Object ob=null;
		Student s = data.get(line);
		switch(col) {
		case 0: {
			ob = s.getId();
			break;
		}
		case 1: {
			ob = s.getName();
			break;
		}
		case 2: {
			ob = s.getAge();
			break;
		}
		case 3: {
			ob = s.getSex();
			break;
		}
		case 4: {
			ob = s.getColor();
			break;
		}
		}
		return ob;
	}

	

	@Override
	public void fireTableRowsDeleted(int arg0, int arg1) {
		// TODO Auto-generated method stub
		super.fireTableRowsDeleted(arg0, arg1);
	}

	@Override
	public boolean isCellEditable(int line, int col) {
		Boolean ifEdit =true;
		if(col==0) {
			ifEdit=false;
		}
		return ifEdit;
	}

	@Override
	public void setValueAt(Object ob, int line, int col) {
		Student s = data.get(line);
		switch(col) {
		case 1: {
			s.setName((String) ob);	break;		
		}
		case 2: {
			s.setAge((int) ob);break;
		}	
		case 3: {
			s.setSex((boolean) ob);break;
		}	
		case 4: {
			s.setColor((Color) ob);break;
		}	
		}
	}

	//@Override
	public Class<?> getColumnClass(int col) {
		if(col==1) return String.class;
		if(col==3) return Boolean.class;
		if(col==4) return Color.class;
		return Integer.class;
	}

	@Override
	public String getColumnName(int col) {
		
		return columnNames[col];
	}

	@Override
	public void removeRow(int line) {
		data.remove(line);
		fireTableRowsDeleted(line, line);
	}

	
	
}
