package ua.kiev.repairagency.dao;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;
import ua.kiev.repairagency.service.exception.DataBaseConnectionException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

public class PoolConnections {
    private static final Logger LOGGER = Logger.getLogger(PoolConnections.class);

    private static final BasicDataSource dataSource = new BasicDataSource();

    private static final String ABSOLUTE_CLASSPATH = Thread.currentThread().getContextClassLoader().getResource("").getPath();

    private static final Properties dbPoolProperties = new Properties() {{
        try (InputStream input = new FileInputStream(ABSOLUTE_CLASSPATH + "pool.properties")) {
            load(input);
        } catch (IOException e) {
            e.getMessage();
        }
    }};

    private static final String DRIVER = dbPoolProperties.getProperty("driverClassName");
    private static final String URL = dbPoolProperties.getProperty("url");
    private static final String USER = dbPoolProperties.getProperty("username");
    private static final String PASSWORD = dbPoolProperties.getProperty("password");
    private static final String MAXIMUM_POOL_SIZE = dbPoolProperties.getProperty("maximumPoolSize");
    private static final String MAX_WAIT = dbPoolProperties.getProperty("maxWait");

    public PoolConnections(String configurations) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(configurations);
        dataSource.setDriverClassName(DRIVER);
        dataSource.setUrl(URL);
        dataSource.setUsername(USER);
        dataSource.setPassword(PASSWORD);
        dataSource.setMaxIdle(Integer.parseInt(MAXIMUM_POOL_SIZE));
        dataSource.setMaxWaitMillis(Integer.parseInt(MAX_WAIT));
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
