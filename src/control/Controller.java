package control;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import view.*;
import model.*;
/**
 * Where to manager the errors , and can be split into several sub controller
 * @author cjin
 *
 */
public class Controller {

	private Model model;
	JTableModel tableM;
	private Screen screen;
	private JTable table;
	private List<Student> data;
	private StudentDao studentDao;

	/**
	 * public Controller(Model m, Screen s) { this.model=m; this.screen=s;
	 * 
	 * data = model.getData(); tableM = new JTableModel(data); table
	 * =screen.getTable(); table.setModel(tableM);
	 * 
	 * setColumnColorComboBox(); screen.setVisible(true);
	 * 
	 * addDeleteRow(); }
	 */

	public Controller(StudentDao studentDao, Screen s) throws SQLException {
		this.studentDao = studentDao;
		this.screen = s;
		System.out.println("constructor");
		// Create JTable
		data = studentDao.getAll();
		tableM = new JTableModel(data);
		table = screen.getTable();
		table.setModel(tableM);

		setColumnColorComboBox();
		screen.setVisible(true);

		addDeleteRow();
	}

	private void setColumnColorComboBox() {
		Color[] colors = { Color.red, Color.black, Color.blue };
		JComboBox comboBox = new JComboBox(colors);
		comboBox.setRenderer(new MyCellRenderer());// set comboBox renderer color
		TableColumn modelCol = table.getColumn("color");
		modelCol.setCellEditor(new DefaultCellEditor((JComboBox) comboBox));
		modelCol.setCellRenderer(new MyCellRenderer());// set cell in table renderer color

		
		
	}


	/**
	 * Ajout ou supprime un etudiant
	 * 
	 * @return void
	 */
	private void addDeleteRow() {

		//Add button
		screen.getBtnAdd().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Student s = new Student("ll", 6, true, 0);//Create new student	without id	
				List<Student> sAll = null;
				try {
					studentDao.Insert(s);// Insert into database
					sAll = studentDao.getAll();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null,"Error in insert method in database connection",
							"Database connection",JOptionPane.WARNING_MESSAGE);
					e.printStackTrace();
				}
				//Find the id of the inserted student in database
				
				int id = sAll.get(sAll.size()-1).getId();
				s.setId(id);
				int insertedLine = data.size();
				data.add(s);
				tableM.fireTableRowsInserted(insertedLine, insertedLine);

			}
		});

		// Delete button
		screen.getBtnDelete().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
//				
				// method 1
				int selectedRow = table.getSelectedRow(); //Get the selected row number
				Student s = data.get(selectedRow); //Get the student from the jtable
				try {
					studentDao.delete(s.getId());
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null,"Error in delte method in database connection",
							"Database connection",JOptionPane.WARNING_MESSAGE);
					e.printStackTrace();
				}//Set the student with id

				data.remove(selectedRow); //Delete the line in the temporary data  need to do in the model where store the data
				tableM.fireTableRowsDeleted(selectedRow, selectedRow);
				//tableM.removeRow(selectedRow);
				
			}
		});
	}

}
