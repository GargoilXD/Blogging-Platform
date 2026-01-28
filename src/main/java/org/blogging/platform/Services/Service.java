package org.blogging.platform.Services;

import org.blogging.platform.DataSource.DataSourceFactory;

import javax.sql.DataSource;

public abstract class Service {
    static DataSource dataSource = DataSourceFactory.createDataSource();
    Service() {
        Initialize(dataSource);
    }
    public abstract void Initialize(DataSource dataSource);
}
