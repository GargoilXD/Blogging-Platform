package org.blogging.platform.DataSourceFactory;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.github.cdimascio.dotenv.Dotenv;

import javax.sql.DataSource;

public class HikariDataSourceFactory {
    public static DataSource createDataSource() {
        Dotenv dotenv = Dotenv.load();
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/blog");
        config.setUsername(dotenv.get("POSTGRESQL_USERNAME"));
        config.setPassword(dotenv.get("POSTGRESQL_PASSWORD"));
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
