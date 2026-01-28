package org.blogging.platform.Services;

import org.blogging.platform.DataAccessors.CommentDataAccessor;

import javax.sql.DataSource;

public class CommentService extends Service {
    CommentDataAccessor dataAccessor;

    @Override
    public void Initialize(DataSource dataSource) {
        this.dataAccessor = new CommentDataAccessor(dataSource);
    }
}
