package org.blogging.platform.Services;

import java.util.ArrayList;
import java.util.List;

import org.blogging.platform.DataAccessors.Exceptions.DataAccessException;
import org.blogging.platform.DataAccessors.Interfaces.CommentDataAccessor;
import org.blogging.platform.DataTransporters.CommentDataTransporter;
import org.blogging.platform.Models.Comment;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CommentServiceTest {

    static class FakeCommentDataAccessor implements CommentDataAccessor {
        List<Comment> comments = new ArrayList<>();
        boolean createCalled = false;
        boolean updateCalled = false;
        boolean deleteCalled = false;
        boolean deleteForPostCalled = false;

        FakeCommentDataAccessor(List<Comment> comments) { this.comments = comments; }

        @Override
        public List<Comment> getForPost(long PostID) throws DataAccessException { return comments; }

        @Override
        public void create(CommentDataTransporter transporter) throws DataAccessException { createCalled = true; }

        @Override
        public void update(CommentDataTransporter transporter) throws DataAccessException { updateCalled = true; }

        @Override
        public void delete(String ID) throws DataAccessException { deleteCalled = true; }

        @Override
        public void deleteForPost(long PostID) throws DataAccessException { deleteForPostCalled = true; }
    }

    @Test
    @DisplayName("getForPost returns comments")
    void testGetForPostReturnsComments() throws DataAccessException {
        long start = System.nanoTime();
        List<Comment> sample = List.of(new Comment("1", 1L, 1L, "alice", "hello", java.time.Instant.now()));
        FakeCommentDataAccessor fake = new FakeCommentDataAccessor(sample);
        CommentService svc = new CommentService(fake);

        List<Comment> result = svc.getForPost(1);
        assertEquals(1, result.size());

        long end = System.nanoTime();
        System.out.printf("CommentServiceTest#testGetForPostReturnsComments took %d ms%n", (end-start)/1_000_000);
    }

    @Test
    @DisplayName("create/update/delete delegate to accessor")
    void testCreateUpdateDeleteDelegate() throws DataAccessException {
        long start = System.nanoTime();
        FakeCommentDataAccessor fake = new FakeCommentDataAccessor(new ArrayList<>());
        CommentService svc = new CommentService(fake);

        svc.create(new CommentDataTransporter());
        svc.update(new CommentDataTransporter());
        svc.delete("1");

        assertTrue(fake.createCalled);
        assertTrue(fake.updateCalled);
        assertTrue(fake.deleteCalled);

        long end = System.nanoTime();
        System.out.printf("CommentServiceTest#testCreateUpdateDeleteDelegate took %d ms%n", (end-start)/1_000_000);
    }
}
