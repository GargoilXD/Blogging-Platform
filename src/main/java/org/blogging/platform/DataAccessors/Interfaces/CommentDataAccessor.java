package org.blogging.platform.DataAccessors.Interfaces;

import org.blogging.platform.DataTransporters.CommentDataTransporter;
import org.blogging.platform.DataAccessors.Exceptions.DataAccessException;
import org.blogging.platform.Models.Comment;

import java.util.List;

public interface CommentDataAccessor {
    List<Comment> getForPost(long PostID) throws DataAccessException;
    void create(CommentDataTransporter transporter) throws DataAccessException;
    void update(CommentDataTransporter transporter) throws DataAccessException;
    void delete(String ID) throws DataAccessException;
    void deleteForPost(long PostID) throws DataAccessException;
}
