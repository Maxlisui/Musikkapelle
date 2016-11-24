package DBConnection;
import java.sql.*;

public class DBManager 
{
	private Connection conn;
	private static DBManager instance;

	private DBManager() throws SQLException, ClassNotFoundException
	{
		Class.forName("com.mysql.jdvc.Driver");

		if(conn == null) {

			conn = DriverManager.getConnection("jdbc:mysql://localhost/employees", "root", "root");
		}
	}

	public static synchronized DBManager getInstance() throws SQLException, ClassNotFoundException{
		if(instance == null){
			instance = new DBManager();
		}
		return instance;
	}
	
	public void close() throws SQLException
	{
		conn.close();
	}
}
