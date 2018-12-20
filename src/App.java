import java.sql.SQLException;

import javax.swing.JOptionPane;

import control.*;
import model.DatabaseConnection;
import model.*;
import view.*;

public class App {

	public static void main(String[] args) {

		// Connect data base by singleton
        //
		DatabaseConnection instance;
		StudentDao studentDao;
		try {
			instance = DatabaseConnection.getInstance();
			studentDao = new StudentDaoImpl(instance.getConnection());
			Screen screen = new Screen();
			Controller c = new Controller(studentDao, screen);

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error in database connection",
					"Database connection",JOptionPane.WARNING_MESSAGE);

			e.printStackTrace();
		}

		// Model m = new Model();

	}

}
