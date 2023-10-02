package task6_dev.DataBase;

import java.sql.*;

public class DataBase {
    private static final DataBase INSTANCE = new DataBase();
    private Connection connection;

    private DataBase() {
        try {
            String connectionUrl = "jdbc:h2:./11111_db";
            connection = DriverManager.getConnection(connectionUrl);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DataBase getInstance() {
        return INSTANCE;
    }

    public int executeUpdate(String sql) {
        try (Statement statement = connection.createStatement();) {
            return statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }

    }

    public ResultSet executeQuery(String sql) {
        ResultSet resultSet = null;
        try {
            Statement st = connection.createStatement();
            resultSet = st.executeQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
           return resultSet; 

    }

    public Connection getConnection() {
        return connection;
    }

}
