import java.sql.*;

public class Conection {
    private static final String url = "jdbc:mysql://localhost:3306/db_projecto";
    private static final String user = "root";
    private static final String password = "";

    // Voltar a null a cada operação para garantir que a conexão seja sempre
    // atualizada.
    private static Connection conn = null;

    public static Connection getConnection() throws SQLException {
        try {
            // Se a conexão for null ou estiver fechada, crie uma nova
            if (conn == null || conn.isClosed()) {
                // Carregar explicitamente o driver
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(url, user, password);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("Error: " + e.getMessage());
        }
        return conn;
    }

    public static void closeConnection() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }
}
