package bd;

public class DataBase {
    public static class Conexion {

        private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
        private static final String URL = "jdbc:mysql://localhost/primaria?serverTimezone=UTC";
        private static final String USER = "root";
        private static final String PASSWORD = "paris72";

        public static String getDriver() {
            return DRIVER;
        }

        public static String getUrl() {
            return URL;
        }

        public static String getUser() {
            return USER;
        }

        public static String getPassword() {
            return PASSWORD;
        }

    }
}
