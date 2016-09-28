package application;

import java.sql.Connection;
import java.sql.SQLException;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;

public class DBConnectionFactory {
    public DBConnectionFactory() {
        super();
    }
    
    protected static Connection conn = null;

    public static Connection getConnection() throws Exception {
        if (conn == null) {
            try {
                // create a database connection
                String Dir = AdfmfJavaUtilities.getDirectoryPathRoot(AdfmfJavaUtilities.ApplicationDirectory);
                String connStr = "jdbc:sqlite:" + Dir + "/metaprocure.db";
                conn = new SQLite.JDBCDataSource(connStr).getConnection();
            } catch (SQLException e) {
                // if the error message is "out of memory",
                // it probably means no database file is found
                System.err.println(e.getMessage());
            }
        }
        return conn;
    }
    
}