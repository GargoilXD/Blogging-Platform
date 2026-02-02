package org.blogging.platform;

import javafx.stage.Stage;
import org.blogging.platform.DataAccessors.Interfaces.*;
import org.blogging.platform.DataAccessors.JDBC.JDBCPostDataAccessor;
import org.blogging.platform.DataAccessors.JDBC.JDBCUserDataAccessor;
import org.blogging.platform.DataAccessors.Mongo.MongoDBCommentDataAccessor;
import org.blogging.platform.DataAccessors.Mongo.MongoDBTagDataAccessor;
import org.blogging.platform.Services.*;

public class Context {
    public Navigator navigator;
    public AuthenticationService authenticationService;
    public CommentService commentService;
    public PostService postService;
    public TagService tagService;

    public Context(Stage stage) {
        UserDataAccessor userDataAccessor = new JDBCUserDataAccessor();
        PostDataAccessor postDataAccessor = new JDBCPostDataAccessor();
        TagDataAccessor tagDataAccessor = new MongoDBTagDataAccessor();
        CommentDataAccessor commentDataAccessor = new MongoDBCommentDataAccessor();
        this.navigator = new Navigator(stage);
        this.authenticationService = new AuthenticationService(userDataAccessor);
        this.postService = new PostService(postDataAccessor, tagDataAccessor, commentDataAccessor);
        this.tagService = new TagService(tagDataAccessor);
        this.commentService = new CommentService(commentDataAccessor);
    }
}
