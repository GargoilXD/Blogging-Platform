package org.blogging.platform.Services;

import java.util.HashMap;
import java.util.List;

import org.blogging.platform.DataAccessors.Exceptions.DataAccessException;
import org.blogging.platform.DataAccessors.Interfaces.TagDataAccessor;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TagServiceTest {

    static class FakeTagDataAccessor implements TagDataAccessor {
        boolean setCalled = false;

        @Override
        public List<String> getAllTags() throws DataAccessException { return List.of("a", "b"); }

        @Override
        public HashMap<Long, List<String>> getAllTagsByPosts() throws DataAccessException {
            HashMap<Long, List<String>> m = new HashMap<>();
            m.put(1L, List.of("a"));
            return m;
        }

        @Override
        public List<String> getTagsForPost(long PostID) throws DataAccessException { return List.of("a"); }

        @Override
        public void setPostTags(long postID, List<String> tags) throws DataAccessException { setCalled = true; }
    }

    @Test
    @DisplayName("getAll/getForPost and others delegate to data accessor")
    void testGettersDelegate() throws DataAccessException {
        long start = System.nanoTime();
        FakeTagDataAccessor fake = new FakeTagDataAccessor();
        TagService svc = new TagService(fake);

        assertEquals(2, svc.getAllTags().size());
        assertEquals(1, svc.getAllTagsByPosts().size());
        assertEquals(1, svc.getForPost(1).size());
        assertEquals(1, svc.getTagsForPost(1).size());

        long end = System.nanoTime();
        System.out.printf("TagServiceTest#testGettersDelegate took %d ms%n", (end-start)/1_000_000);
    }

    @Test
    @DisplayName("setPostTags delegates to data accessor")
    void testSetPostTagsDelegates() throws DataAccessException {
        long start = System.nanoTime();
        FakeTagDataAccessor fake = new FakeTagDataAccessor();
        TagService svc = new TagService(fake);

        svc.setPostTags(2L, List.of("x"));
        assertTrue(fake.setCalled);

        long end = System.nanoTime();
        System.out.printf("TagServiceTest#testSetPostTagsDelegates took %d ms%n", (end-start)/1_000_000);
    }
}
