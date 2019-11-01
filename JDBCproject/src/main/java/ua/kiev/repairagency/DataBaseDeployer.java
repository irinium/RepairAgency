package ua.kiev.repairagency;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import ua.kiev.repairagency.config.DataBaseConfig;
import ua.kiev.repairagency.dao.helper.SqlHelper;

import java.io.File;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.String.format;

public class DataBaseDeployer {
    private static final Logger LOGGER = Logger.getLogger(DataBaseDeployer.class);

    public static void main(String[] args) {
        createDb();
        deployDb();
    }

    public static void createDb() {
        SqlHelper.createStatement(statement -> {
            statement.executeUpdate("drop database if exists " + DataBaseConfig.DB_NAME);
            statement.executeUpdate("create database " + DataBaseConfig.DB_NAME);
            statement.executeUpdate("use " + DataBaseConfig.DB_NAME);
            return Void.TYPE;
        }, DataBaseConfig.DB_CONNECTION_URL);
    }

    public static void deployDb() {
        SqlHelper.createStatement(statement -> {
            File folder = new File(DataBaseConfig.DB_SCRIPTS_FOLDER);
            loadSqlFilesFromFolder(statement, folder);
            return Void.TYPE;
        });
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
        try (Scanner scanner = new Scanner(file).useDelimiter(DataBaseConfig.DB_SCRIPT_DELIMITER)) {
            while(scanner.hasNext()) {
                String sqlQuery = scanner.next().trim();
                if(sqlQuery.isEmpty()) continue;
                LOGGER.log(Level.INFO, sqlQuery);
                statement.execute(sqlQuery);
            }
        } catch (Exception e) {
            LOGGER.log(Level.WARN, format("*** Deploy DB Failed!\nFile: %s\nMessage: %s", file.getName(), e.getMessage()));
        }
    }
}
