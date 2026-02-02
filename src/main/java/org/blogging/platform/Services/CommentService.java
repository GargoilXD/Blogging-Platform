package org.blogging.platform.Services;

import org.blogging.platform.DataAccessors.Exceptions.DataAccessException;
import org.blogging.platform.DataAccessors.Interfaces.CommentDataAccessor;
import org.blogging.platform.DataTransporters.CommentDataTransporter;
import org.blogging.platform.Models.Comment;

import java.util.List;

public class CommentService {
    CommentDataAccessor dataAccessor;

    public CommentService(CommentDataAccessor dataAccessor) {
        this.dataAccessor = dataAccessor;
    }
    public List<Comment> getForPost(long ID) throws DataAccessException {
        return dataAccessor.getForPost(ID);
    }
    public void create(CommentDataTransporter transporter) throws DataAccessException {
        dataAccessor.create(transporter);
    }
    public void update(CommentDataTransporter transporter) throws DataAccessException {
        dataAccessor.update(transporter);
    }
    public void delete(String ID) throws DataAccessException {
        dataAccessor.delete(ID);
    }
}
