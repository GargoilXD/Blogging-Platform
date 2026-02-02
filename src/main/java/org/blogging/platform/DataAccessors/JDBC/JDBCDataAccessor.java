package org.blogging.platform.DataAccessors.JDBC;

import org.blogging.platform.DataSourceFactory.HikariDataSourceFactory;
import org.blogging.platform.DatabaseInitializer.JDBCDatabaseInitializer;

import javax.sql.DataSource;
import java.sql.SQLException;

public abstract class JDBCDataAccessor {
    static DataSource dataSource = null;
    JDBCDataAccessor() {
        try {
            if (dataSource == null) {
                JDBCDatabaseInitializer.initializeDatabase();
                dataSource = HikariDataSourceFactory.createDataSource();
            }
            initialize();
        } catch (SQLException e) {
            System.err.printf("Error while initializing database: %s%n", e.getMessage());
            System.exit(1);
        }
    }
    abstract void initialize() throws SQLException;
}
