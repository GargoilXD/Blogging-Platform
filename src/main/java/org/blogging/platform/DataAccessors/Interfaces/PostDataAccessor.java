package org.blogging.platform.DataAccessors.Interfaces;

import org.blogging.platform.DataAccessors.Exceptions.DataAccessException;
import org.blogging.platform.DataTransporters.PostDataTransporter;
import org.blogging.platform.Models.Post;

import java.util.List;
import java.util.Optional;

public interface PostDataAccessor {
    Optional<Post> getByID(Long id) throws DataAccessException;
    Optional<Post> getByTitle(String title) throws DataAccessException;
    List<Post> getAll() throws DataAccessException;
    long create(PostDataTransporter transporter) throws DataAccessException;
    void update(PostDataTransporter transporter) throws DataAccessException;
    void delete(Long ID) throws DataAccessException;
}
