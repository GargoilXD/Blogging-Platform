package org.blogging.platform.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class DataSourceFactory {
    public static DataSource createDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/blogdb");
        config.setUsername(System.getenv("POSTGRESQL_USERNAME"));
        config.setPassword(System.getenv("POSTGRESQL_PASSWORD"));
        config.setMaximumPoolSize(12);
        config.setMinimumIdle(2);
        config.setConnectionTimeout(30_000);
        config.setIdleTimeout(600_000);
        config.setMaxLifetime(1_800_000);
        config.setPoolName("BlogPool");
        config.setConnectionTestQuery("SELECT 1");
        config.setValidationTimeout(5_000);
        config.setLeakDetectionThreshold(60_000); // dev
        return new HikariDataSource(config);
    }
}
