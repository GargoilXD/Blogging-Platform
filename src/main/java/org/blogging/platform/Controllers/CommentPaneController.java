package org.blogging.platform.Controllers;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import org.blogging.platform.Context;
import org.blogging.platform.DataAccessors.Exceptions.DataAccessException;
import org.blogging.platform.DataTransporters.CommentDataTransporter;
import org.blogging.platform.Models.Comment;

import java.io.IOException;

public class CommentPaneController extends Controller {
    @FXML
    private Label AuthorLabel;
    @FXML
    private Label CreatedAtLabel;
    @FXML
    private TextArea Body;
    @FXML
    private Button EditButton;
    @FXML
    private Button DeleteButton;

    private Comment comment;
    private Runnable onDeleteCallback;

    @Override
    public void initialize(Parent parent, Context context, Object... arguments) throws IOException {
        super.initialize(parent, context, arguments);
        if (arguments[0] instanceof Comment c) comment = c;
        else throw new IllegalArgumentException("Argument 0 must be of type <Comment>");
        if (arguments[1] instanceof Runnable r) onDeleteCallback = r;
        else throw new IllegalArgumentException("Argument 1 must be of type <Runnable>");
        AuthorLabel.setText(comment.getUsername());
        CreatedAtLabel.setText(comment.getCreatedAt().toString());
        Body.setText(comment.getBody());
        EditButton.setVisible(comment.getUserID() == context.authenticationService.CurrentUser.ID);
        DeleteButton.setVisible(comment.getUserID() == context.authenticationService.CurrentUser.ID);
    }
    @FXML
    private void OnDeletePressed() {
        ConfirmationDialog("Delete Post", "Are you sure you want to delete this post?", () -> {
            try {
                context.commentService.delete(comment.getID());
                onDeleteCallback.run();
            } catch (DataAccessException e) {
                ErrorAlert(e.getMessage());
            }
        });
    }
    @FXML
    private void OnEditPressed() {
        EditButton.setText("Save");
        EditButton.setOnMouseClicked(e -> OnSavePressed());
        DeleteButton.setDisable(true);
        Body.setEditable(true);
    }
    @FXML
    private void OnSavePressed() {
        try {
            context.commentService.update(new CommentDataTransporter().setID(comment.getID()).setBody(Body.getText()));
            EditButton.setText("Edit");
            EditButton.setOnMouseClicked(e -> OnEditPressed());
            DeleteButton.setDisable(false);
            Body.setEditable(false);
        } catch (DataAccessException e) {
            ErrorAlert(e.getMessage());
        }
    }
}