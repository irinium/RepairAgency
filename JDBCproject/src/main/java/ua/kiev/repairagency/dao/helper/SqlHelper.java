package ua.kiev.repairagency.dao.helper;

import ua.kiev.repairagency.dao.config.DataBaseConfig;

import java.sql.*;

public class SqlHelper {

    public interface Query<T> {
        T execute(Statement statement) throws SQLException;
    }

    public interface PreparedQuery<T> {
        T execute(PreparedStatement preparedStatement) throws SQLException;
    }

    private abstract static class CustomStatement<T> {
        protected Statement statement = null;
        protected Connection connection = null;
        private String dbConnectionUrl;

        public CustomStatement(String dbConnectionUrl) {
            this.dbConnectionUrl = dbConnectionUrl;
        }

        abstract T getResult() throws SQLException;

        public T run() {
            T result = null;
            try {
                connection = DriverManager.getConnection(dbConnectionUrl);
                result = getResult();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            } finally {
                try {
                    if (statement != null) statement.close();
                    if (connection != null) connection.close();
                } catch (SQLException e) {
                    System.err.println(e);
                }
            }
            return result;
        }
    }

    public static <T> T prepareStatement(String sql, PreparedQuery query) {
        return new CustomStatement<T>(DataBaseConfig.DB_DATABASE_URL) {
            T getResult() throws SQLException {
                statement = connection.prepareStatement(sql);
                statement.setQueryTimeout(30);  // set timeout to 30 sec.
                return (T) query.execute((PreparedStatement) statement);
            }
        }.run();
    }


    public static <T> T createStatement(Query query) {
        return createStatement(query, DataBaseConfig.DB_DATABASE_URL);
    }

    public static <T> T createStatement(Query query, String dbConnectionUrl) {
        return new CustomStatement<T>(dbConnectionUrl) {
            T getResult() throws SQLException {
                statement = connection.createStatement();
                statement.setQueryTimeout(30);  // set timeout to 30 sec.
                return (T) query.execute(statement);
            }
        }.run();
    }
}
