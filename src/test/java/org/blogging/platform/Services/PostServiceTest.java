package org.blogging.platform.Services;

import java.util.List;

import org.blogging.platform.DataAccessors.Exceptions.DataAccessException;
import org.blogging.platform.DataAccessors.Interfaces.CommentDataAccessor;
import org.blogging.platform.DataAccessors.Interfaces.PostDataAccessor;
import org.blogging.platform.DataAccessors.Interfaces.TagDataAccessor;
import org.blogging.platform.DataTransporters.PostDataTransporter;
import org.blogging.platform.Models.Post;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PostServiceTest {

    static class FakePostDataAccessor implements PostDataAccessor {
        List<Post> posts;
        boolean updateCalled = false;
        boolean deleteCalled = false;

        FakePostDataAccessor(List<Post> posts) { this.posts = posts; }

        @Override
        public java.util.Optional<Post> getByID(Long id) throws DataAccessException { return java.util.Optional.empty(); }

        @Override
        public java.util.Optional<Post> getByTitle(String title) throws DataAccessException { return java.util.Optional.empty(); }

        @Override
        public List<Post> getAll() throws DataAccessException { return posts; }

        @Override
        public long create(PostDataTransporter transporter) throws DataAccessException { return 42L; }

        @Override
        public void update(PostDataTransporter transporter) throws DataAccessException { updateCalled = true; }

        @Override
        public void delete(Long ID) throws DataAccessException { deleteCalled = true; }
    }

    static class FakeTagDataAccessor implements TagDataAccessor {
        boolean setPostTagsCalled = false;

        @Override
        public java.util.List<String> getAllTags() throws DataAccessException { return List.of(); }

        @Override
        public java.util.HashMap<Long, List<String>> getAllTagsByPosts() throws DataAccessException { return new java.util.HashMap<>(); }

        @Override
        public java.util.List<String> getTagsForPost(long PostID) throws DataAccessException { return List.of(); }

        @Override
        public void setPostTags(long postID, List<String> tags) throws DataAccessException { setPostTagsCalled = true; }
    }

    static class FakeCommentDataAccessor implements CommentDataAccessor {
        boolean deleteForPostCalled = false;

        @Override
        public java.util.List<org.blogging.platform.Models.Comment> getForPost(long PostID) throws DataAccessException { return List.of(); }

        @Override
        public void create(org.blogging.platform.DataTransporters.CommentDataTransporter transporter) throws DataAccessException { }

        @Override
        public void update(org.blogging.platform.DataTransporters.CommentDataTransporter transporter) throws DataAccessException { }

        @Override
        public void delete(String ID) throws DataAccessException { }

        @Override
        public void deleteForPost(long PostID) throws DataAccessException { deleteForPostCalled = true; }
    }

    @Test
    @DisplayName("getAll returns posts and create/update delegate")
    void testGetAllAndCreateUpdate() throws DataAccessException {
        long start = System.nanoTime();
        FakePostDataAccessor postAccessor = new FakePostDataAccessor(List.of(new Post(1,1,"u","t","b",false,"now")));
        FakeTagDataAccessor tagAccessor = new FakeTagDataAccessor();
        FakeCommentDataAccessor commentAccessor = new FakeCommentDataAccessor();
        PostService svc = new PostService(postAccessor, tagAccessor, commentAccessor);

        assertEquals(1, svc.getAll().size());
        assertEquals(42L, svc.create(new PostDataTransporter()));
        svc.update(new PostDataTransporter());
        assertTrue(postAccessor.updateCalled);

        long end = System.nanoTime();
        System.out.printf("PostServiceTest#testGetAllAndCreateUpdate took %d ms%n", (end-start)/1_000_000);
    }

    @Test
    @DisplayName("delete removes tags and comments")
    void testDeleteRemovesTagsAndComments() throws DataAccessException {
        long start = System.nanoTime();
        FakePostDataAccessor postAccessor = new FakePostDataAccessor(List.of());
        FakeTagDataAccessor tagAccessor = new FakeTagDataAccessor();
        FakeCommentDataAccessor commentAccessor = new FakeCommentDataAccessor();
        PostService svc = new PostService(postAccessor, tagAccessor, commentAccessor);

        svc.delete(5L);
        assertTrue(postAccessor.deleteCalled);
        assertTrue(tagAccessor.setPostTagsCalled);
        assertTrue(commentAccessor.deleteForPostCalled);

        long end = System.nanoTime();
        System.out.printf("PostServiceTest#testDeleteRemovesTagsAndComments took %d ms%n", (end-start)/1_000_000);
    }
}
