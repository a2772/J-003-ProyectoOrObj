package bd;

import clss.catPerfil;
import dao.DAOInitializationException;
import dao.DataAccessObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Consultas extends DataAccessObject {

    public Consultas() throws ClassNotFoundException, SQLException {
        super();
    }

    public List<catPerfil> getLCatPerfil() throws SQLException, DAOInitializationException {
        ResultSet rs = null;
        List<catPerfil> lista = new ArrayList<>();
        catPerfil catP = new catPerfil();
        PreparedStatement stmt = null;
        boolean isValid = false;
        String sql = "select * from catPerfil";
        try {
            stmt = prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                isValid = true;
                //Tabla trabajador
                catP.setIdPerfil(rs.getInt("idPerfil"));
                catP.setPerfil(rs.getString("perfil"));
                lista .add(catP);
            }
        } catch (DAOInitializationException | SQLException ex) {
            lista = null;
        } finally {
            closeResultSet(rs);
            closeStatement(stmt);
        }
        return lista;
    }
    /*
	public UserValueObject authenticate(String email, String password) throws SQLException, DAOInitializationException
	{
		UserValueObject user = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		String sql = "SELECT * FROM users WHERE email = ?  AND password = ?";
		System.out.println("UserDAO.authenticate() - SQL - " + sql);
		
		
		try
		{
			stmt = prepareStatement(sql);

			stmt.setString(1, email);
			stmt.setString(2, password);
			
			rs = stmt.executeQuery();
			
			if(rs.next()) // Encontro un registro -- Credenciales validas
			{
				user = new UserValueObject();
				
				user.setEmail(rs.getString("email"));
				user.setFirstname(rs.getString("firstname"));
				user.setLastname(rs.getString("lastname"));
				user.setDaysOfPasswordValidity(rs.getInt("days_of_password_validity"));
				user.setDateOfLastPasswordUpdate(new Date(rs.getDate("date_of_last_password_update").getTime()));
				user.setTemporalPassword(rs.getBoolean("is_temporal_password"));
				user.setActivationKey(rs.getString("activation_key"));
				user.setStatus(rs.getString("status"));
				
				return user;
			}
			else
			{
				return null;
			}
		}
		finally
		{
			closeResultSet(rs);
			closeStatement(stmt);
		}
	}
	 * */
}
