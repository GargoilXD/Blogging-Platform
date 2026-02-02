package org.blogging.platform.Services;

import org.blogging.platform.DataAccessors.Exceptions.DataAccessException;
import org.blogging.platform.DataAccessors.Interfaces.TagDataAccessor;

import java.util.HashMap;
import java.util.List;

public class TagService {
    TagDataAccessor dataAccessor;

    public TagService(TagDataAccessor dataAccessor) {
        this.dataAccessor = dataAccessor;
    }
    public List<String> getAllTags() throws DataAccessException {
        return dataAccessor.getAllTags();
    }
    public HashMap<Long, List<String>> getAllTagsByPosts() throws DataAccessException {
        return dataAccessor.getAllTagsByPosts();
    }
    public List<String> getForPost(long ID) throws DataAccessException {
        return dataAccessor.getTagsForPost(ID);
    }
    public List<String> getTagsForPost(long PostID) throws DataAccessException {
        return dataAccessor.getTagsForPost(PostID);
    }
    public void setPostTags(long PostID, List<String> tags) throws DataAccessException {
        dataAccessor.setPostTags(PostID, tags);
    }
}
