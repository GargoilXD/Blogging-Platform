package org.blogging.platform.Interfaces;

import org.blogging.platform.Exceptions.DataAccessException;

import java.util.List;
import java.util.Optional;

public interface DataAccessor<V, T extends DataTransporter> {
    default Optional<V> getByID(long id) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported");
    }
    default List<V> getAll() throws DataAccessException {
        throw new UnsupportedOperationException("Not supported");
    }
    default void save(T value) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported");
    }
    default void update(T value) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported");
    }
    default void delete(long ID) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported");
    }
}
