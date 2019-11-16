package ua.kiev.repairagency.dao;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;
import ua.kiev.repairagency.service.exception.DataBaseConnectionException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DataBaseConnector {
    private static final Logger LOGGER = Logger.getLogger(DataBaseConnector.class);

    private final BasicDataSource dataSource;

    public DataBaseConnector(String fileConfigName) {
        dataSource = new BasicDataSource();
        ResourceBundle resourceBundle = ResourceBundle.getBundle(fileConfigName);

        dataSource.setDriverClassName(resourceBundle.getString("db.driver"));
        dataSource.setUrl(resourceBundle.getString("db.url"));
        dataSource.setUsername(resourceBundle.getString("db.user"));
        dataSource.setPassword(resourceBundle.getString("db.password"));
        dataSource.setMaxIdle(Integer.parseInt(resourceBundle.getString("db.maximumPoolSize")));
        dataSource.setMaxWaitMillis(Integer.parseInt(resourceBundle.getString("db.maxWait")));
    }

    public Connection getConnection(){
        try {
            return dataSource.getConnection();
        }catch (SQLException e){
            LOGGER.error("Database connection failed");
            throw new DataBaseConnectionException( e.getMessage());
        }
    }
}
