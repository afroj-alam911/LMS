import java.sql.*;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/library?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "Admin@123";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // CJ driver registration
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC driver not found!", e);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
