package org.blogging.platform.Controllers;

import java.io.IOException;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import org.blogging.platform.Context;
import org.blogging.platform.DataAccessors.Exceptions.DataAccessException;
import org.blogging.platform.DataTransporters.CommentDataTransporter;
import org.blogging.platform.Models.Comment;
import org.blogging.platform.Models.Post;

public class PostViewController extends Controller {
    @FXML
    private Label TitleLabel;
    @FXML
    private Label AuthorLabel;
    @FXML
    private Label CreatedAtLabel;
    @FXML
    private Text BodyText;
    @FXML
    private TextArea CommentTextArea;
    @FXML
    private VBox CommentsVBox;
    @FXML
    private Label NoCommentsLabel;

    private Post post;
    private List<Comment> comments;

    @Override
    public void initialize(Parent parent, Context context, Object... arguments) throws IOException {
        super.initialize(parent, context, arguments);
        if (arguments[0] instanceof Post p) post = p;
        else throw new IllegalArgumentException("Argument 0 must be of type <Post>");
        TitleLabel.setText(post.Title);
        AuthorLabel.setText("by " + post.Username);
        //CreatedAtLabel.setText(post.CreatedAt);
        BodyText.setText(post.Body);
        LoadComments();
    }
    private void LoadComments() {
        try {
            comments = context.commentService.getForPost(post.ID);
        } catch (DataAccessException e) {
            ErrorAlert(e.getMessage());
        }
        if (comments.isEmpty()) {
            NoCommentsLabel.setVisible(true);
            NoCommentsLabel.setManaged(true);
        } else {
            NoCommentsLabel.setVisible(false);
            NoCommentsLabel.setManaged(false);
            CommentsVBox.getChildren().clear();
            try {
                Thread.sleep(100);
                Runnable LoadComments = this::LoadComments;
                for (Comment comment : comments) {
                    CommentsVBox.getChildren().add(Controller.load("comment_pane", context, comment, LoadComments).root);
                }
            } catch (InterruptedException | IOException e) {
                ErrorAlert(e.getMessage());
            }
        }
    }
    @FXML
    private void OnBackPressed() {
        context.navigator.pop();
    }
    @FXML
    private void OnCommentSubmitPressed() {
        try {
            context.commentService.create(
                    new CommentDataTransporter()
                            .setUserID(context.authenticationService.CurrentUser.ID)
                            .setUsername(context.authenticationService.CurrentUser.Username)
                            .setPostID(post.ID)
                            .setBody(CommentTextArea.getText())
            );
            LoadComments();
            CommentTextArea.clear();
        } catch (DataAccessException e) {
            ErrorAlert(e.getMessage());
        }
    }
}
