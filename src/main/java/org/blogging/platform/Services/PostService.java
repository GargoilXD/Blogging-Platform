package org.blogging.platform.Services;

import org.blogging.platform.DataAccessors.PostDataAccessor;

import javax.sql.DataSource;

public class PostService extends Service {
    PostDataAccessor dataAccessor;

    @Override
    public void Initialize(DataSource dataSource) {
        this.dataAccessor = new PostDataAccessor(dataSource);
    }
}
