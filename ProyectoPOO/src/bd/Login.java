package bd;

import dao.DAOInitializationException;
import dao.DataAccessObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends DataAccessObject{
    public Login() throws ClassNotFoundException, SQLException {
        super();
    }
    public boolean inicioSesion (String email, String pass) throws ClassNotFoundException, SQLException, DAOInitializationException{
        ResultSet rs = null;
        PreparedStatement stmt = null;
        boolean isValid = false;
        String sql = "select ps.pass from personal p, pass ps where p.idPersonal=ps.idPersonal4 and p.correo=? and ps.pass=?";
        try {
            stmt = prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, pass);
            rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("Login correcto");
                isValid = true;
            }
        } catch (DAOInitializationException | SQLException ex) {
            System.out.println("Error 1");
        } finally {
            closeResultSet(rs);
            closeStatement(stmt);
        }
        return isValid;
    }
}
