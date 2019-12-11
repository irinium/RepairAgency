package ua.kiev.repairagency;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import ua.kiev.repairagency.dao.DataBaseConnector;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Scanner;

import static java.lang.String.format;

public class DataBaseDeployer {
    private static final Logger LOGGER = Logger.getLogger(DataBaseDeployer.class);

    private static final DataBaseConnector DATA_BASE_CONNECTOR;
    private static final Connection CONNECTION;
    private static final ResourceBundle RESOURCE;

    static {
        DATA_BASE_CONNECTOR = new DataBaseConnector("database");
        CONNECTION = DATA_BASE_CONNECTOR.getConnection();
        RESOURCE = ResourceBundle.getBundle("database");
    }

    private static final String ABSOLUTE_CLASSPATH = Thread.currentThread().getContextClassLoader().getResource("").getPath();

    private static final Properties dbProperties = new Properties() {{
        try (InputStream input = new FileInputStream(ABSOLUTE_CLASSPATH + "database.properties")) {
            load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }};

    public static void main(String[] args) {
        try {
            createDb();
            deployDb();
        } catch (SQLException e) {
            LOGGER.warn(format("Creating and deployment database are failed: %s", e.getMessage()));
        }

    }

    public static void createDb() throws SQLException {
        String dbName = RESOURCE.getString("db.name");
        Statement statement = CONNECTION.createStatement();
        statement.executeUpdate("drop database if exists " + dbName);
        statement.executeUpdate("create database " + dbName);
        statement.executeUpdate("use " + dbName);
    }

    public static void deployDb() throws SQLException {
        Statement statement = CONNECTION.createStatement();
        File folder = new File(ABSOLUTE_CLASSPATH + dbProperties.getProperty("db.scripts.folder"));
        loadSqlFilesFromFolder(statement, folder);
    }

    private static void loadSqlFilesFromFolder(Statement statement, final File folder) {
        File[] files = folder.listFiles();
        Arrays.sort(files);
        for (final File file : files) {
            if (file.isDirectory()) {
                loadSqlFilesFromFolder(statement, file);
            } else if (file.getName().endsWith(".sql")) {
                executeSqlFile(statement, file);
            }
        }
    }

    private static void executeSqlFile(Statement statement, File file) {
        try (Scanner scanner = new Scanner(file).useDelimiter(";")) {
            while (scanner.hasNext()) {
                String sqlQuery = scanner.next().trim();
                if (sqlQuery.isEmpty()) continue;
                LOGGER.log(Level.INFO, sqlQuery);
                statement.execute(sqlQuery);
            }
        } catch (Exception e) {
            LOGGER.warn(format("*** Deploy DB Failed! File: %s Message: %s", file.getName(), e.getMessage()));
        }
    }
}
