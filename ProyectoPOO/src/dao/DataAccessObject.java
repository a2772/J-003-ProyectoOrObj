package dao;

import bd.DataBase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataAccessObject {
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost/primaria?serverTimezone=UTC";
	private static final String USER = DataBase.Conexion.getUser();
	private static final String PASSWORD = DataBase.Conexion.getPassword();
	private Connection connection = null;
	public DataAccessObject() throws ClassNotFoundException, SQLException{
		Class.forName(DRIVER);
		connection = DriverManager.getConnection(URL, USER, PASSWORD);
		// System.out.println("DataAccessObject.openConnection() - Database connection has been opened");
	}   
	public Statement createStatement() throws SQLException, DAOInitializationException{
		if(connection == null || connection.isClosed()){
			throw new DAOInitializationException("DAO was previously closed.");
		}else{
			return connection.createStatement();
		}
	}
	/* ********************************************************************* *
	 *                                                                       *
	 * ********************************************************************* */
	public PreparedStatement prepareStatement(String sql) throws SQLException, DAOInitializationException
	{
		if(connection == null || connection.isClosed())
		{
			throw new DAOInitializationException("DAO was previously closed.");
		}
		else
		{
			return connection.prepareStatement(sql);
		}
	}
	/* ********************************************************************* *
	 *                                                                       *
	 * ********************************************************************* */
	public void closeConnection()
	{
		try
		{
			if(connection == null || connection.isClosed())
			{
				throw new DAOInitializationException("DAO was previously closed.");
			}
			else
			{
				connection.close();
			}
			// System.out.println("DataAccessObject.closeConnection() - Database connection has been closed");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	/* ********************************************************************* *
	 *                                                                       *
	 * ********************************************************************* */
	public Connection getConnection() throws SQLException, ClassNotFoundException, DAOInitializationException
	{
		System.out.println("DataAccessObject.getConnection()");
		if(connection == null || connection.isClosed())
		{
			throw new DAOInitializationException("DAO was previously closed.");
		}
		return connection;
	}
	/* ********************************************************************* *
	 *                                                                       *
	 * ********************************************************************* */

	public void closeStatement(Statement stmt) throws SQLException, DAOInitializationException
	{
		if(connection == null || connection.isClosed())
		{
			throw new DAOInitializationException("DAO was previously closed.");
		}
		
		if(stmt != null && !stmt.isClosed())
		{
			stmt.close();
		}
		// System.out.println("DataAccessObject.closeStatement() - Database statement has been closed");
	}
	/* ********************************************************************* *
	 *                                                                       *
	 * ********************************************************************* */
	public void closeResultSet(ResultSet rs) throws SQLException, DAOInitializationException
	{
		if(connection == null || connection.isClosed())
		{
			throw new DAOInitializationException("DAO was previously closed.");
		}
		
		if(rs != null && !rs.isClosed())
		{
			rs.close();
		}
		// System.out.println("DataAccessObject.closeResultSet() - Database resultSet has been closed");
	}
	/* ********************************************************************* *
	 *                                                                       *
	 * ********************************************************************* */
}