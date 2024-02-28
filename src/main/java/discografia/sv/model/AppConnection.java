package discografia.sv.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AppConnection {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/discografia2";
    // Database credentials
    static final String USER = "root";
    static final String PASS = "";

    protected Connection conn = null;
    protected Statement stmt = null;
    protected PreparedStatement pstmt = null;
    protected ResultSet resultSet = null;

    public AppConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AppConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void connect() throws SQLException {
        if (conn == null || conn.isClosed()) {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        }
    }

    public void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
                conn = null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AppConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Métodos para obtener y manejar el ResultSet
    protected ResultSet executeQuery(String query) throws SQLException {
        connect();
        stmt = conn.createStatement();
        resultSet = stmt.executeQuery(query);
        return resultSet;
    }

    protected void closeResultSet() {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException ex) {
                Logger.getLogger(AppConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
