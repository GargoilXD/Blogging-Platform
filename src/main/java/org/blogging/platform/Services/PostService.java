package org.blogging.platform.Services;

import org.blogging.platform.DataAccessors.Exceptions.DataAccessException;
import org.blogging.platform.DataAccessors.Interfaces.CommentDataAccessor;
import org.blogging.platform.DataAccessors.Interfaces.PostDataAccessor;
import org.blogging.platform.DataAccessors.Interfaces.TagDataAccessor;
import org.blogging.platform.DataTransporters.PostDataTransporter;
import org.blogging.platform.Models.Post;

import java.util.List;

public class PostService {
    PostDataAccessor dataAccessor;
    TagDataAccessor tagAccessor;
    CommentDataAccessor commentAccessor;

    public PostService(PostDataAccessor dataAccessor, TagDataAccessor tagAccessor, CommentDataAccessor commentAccessor) {
        this.dataAccessor = dataAccessor;
        this.tagAccessor = tagAccessor;
        this.commentAccessor = commentAccessor;

    }
    public List<Post> getAll() throws DataAccessException {
        return dataAccessor.getAll();
    }
    public long create(PostDataTransporter transporter) throws DataAccessException {
        return dataAccessor.create(transporter);
    }
    public void update(PostDataTransporter transporter) throws DataAccessException {
        dataAccessor.update(transporter);
    }
    public void delete(long ID) throws DataAccessException {
        dataAccessor.delete(ID);
        tagAccessor.setPostTags(ID, List.of());
        commentAccessor.deleteForPost(ID);
    }
}
