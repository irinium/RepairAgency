package ua.kiev.repairagency.dao;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;
import ua.kiev.repairagency.service.exception.DataBaseConnectionException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PoolConnections {
    private static final Logger LOGGER = Logger.getLogger(PoolConnections.class);

    private static final BasicDataSource dataSource = new BasicDataSource();

    public PoolConnections(String fileConfigName) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(fileConfigName);
        dataSource.setDriverClassName(resourceBundle.getString("driverClassName"));
        dataSource.setUrl(resourceBundle.getString("url"));
        dataSource.setUsername(resourceBundle.getString("username"));
        dataSource.setPassword(resourceBundle.getString("password"));
        dataSource.setMaxIdle(Integer.parseInt(resourceBundle.getString("maximumPoolSize")));
        dataSource.setMaxWaitMillis(Integer.parseInt(resourceBundle.getString("maxWait")));
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
