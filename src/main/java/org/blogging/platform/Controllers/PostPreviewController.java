package org.blogging.platform.Controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import org.blogging.platform.Context;
import org.blogging.platform.DataAccessors.Exceptions.DataAccessException;
import org.blogging.platform.Models.Post;
import org.blogging.platform.Services.Navigator;

public class PostPreviewController extends Controller {
    @FXML
    private Label TitleLabel;
    @FXML
    private FlowPane TagFlowPane;
    @FXML
    private Label AuthorLabel;
    //@FXML
    //private Label CreatedAtLabel;
    @FXML
    private Label BodyPreviewLabel;
    @FXML
    private Button EditButton;
    @FXML
    private Button DeleteButton;
    @FXML
    private Label DraftLabel;

    Post post;
    String[] tags;
    private Runnable onDeleteCallback;

    @Override
    public void initialize(Parent parent, Context context, Object... arguments) throws IOException {
        super.initialize(parent, context, arguments);
        if (arguments[0] instanceof Post p) post = p;
        else throw new IllegalArgumentException("Argument 0 must be of type <Post>");
        if (arguments[1] instanceof String[] t) tags = t;
        else throw new IllegalArgumentException("Argument 1 must be of type <String[]>");
        if (arguments[2] instanceof Runnable r) onDeleteCallback = r;
        else throw new IllegalArgumentException("Argument 2 must be of type <Runnable>");
        TitleLabel.setText(post.Title);
        for (String tag : tags) {
            Button tagButton = new Button();
            tagButton.setText("#" + tag);
            TagFlowPane.getChildren().add(tagButton);
        }
        AuthorLabel.setText("by " + post.Username);
        BodyPreviewLabel.setText(post.Body);
        DraftLabel.setVisible(post.IsDraft);
        EditButton.setVisible(post.UserID == context.authenticationService.CurrentUser.ID);
        DeleteButton.setVisible(post.UserID == context.authenticationService.CurrentUser.ID);
    }
    @FXML
    private void OnPressed() throws IOException {
        context.navigator.push(Navigator.VIEWS.POST_VIEW, context, post);
    }
    @FXML
    private void OnEditPressed() throws IOException {
        context.navigator.push(Navigator.VIEWS.POST_EDITOR, context, post);
    }
    @FXML
    private void OnDeletePressed() {
        ConfirmationDialog(
            "Delete Post",
            "Are you sure you want to delete this post?",
            () -> {
                try {
                    context.postService.delete(post.ID);
                    onDeleteCallback.run();
                } catch (DataAccessException e) {
                    ErrorAlert(e.getMessage());
                }
            }
        );
    }
}
