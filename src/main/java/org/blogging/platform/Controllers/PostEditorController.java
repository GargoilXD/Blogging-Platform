package org.blogging.platform.Controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.blogging.platform.Context;
import org.blogging.platform.DataAccessors.Exceptions.DataAccessException;
import org.blogging.platform.DataTransporters.PostDataTransporter;
import org.blogging.platform.Models.Post;

public class PostEditorController extends Controller {
    @FXML
    private Label PostLabel;
    @FXML
    private TextField TitleField;
    @FXML
    private TextArea Body;
    @FXML
    private FlowPane TagFlowPane;
    @FXML
    private ComboBox<String> TagComboBox;
    @FXML
    private Button AddTagButton;
    @FXML
    private RadioButton DraftRadioButton;
    @FXML
    private Button FinishButton;

    private Post toBeEditedPost;
    private final List<String> selectedTags = new ArrayList<>();

    @Override
    public void initialize(Parent parent, Context context, Object... arguments) throws IOException {
        super.initialize(parent, context, arguments);
        try {
            if (arguments.length > 0) {
                if (arguments[0] instanceof Post post) toBeEditedPost = post;
                else throw new IllegalArgumentException("Argument 0 must be of type <Post>");
                PostLabel.setText("Edit Post");
                TitleField.setText(toBeEditedPost.Title);
                Body.setText(toBeEditedPost.Body);
                FinishButton.setOnMouseClicked(e -> OnUpdatePostPressed());
                DraftRadioButton.setSelected(toBeEditedPost.IsDraft);
                context.tagService.getTagsForPost(toBeEditedPost.ID).forEach(this::AddTag);
            }
            TagComboBox.getItems().addAll(context.tagService.getAllTags());
        } catch (DataAccessException e) {
            ErrorAlert(e.getMessage());
        }
    }
    @FXML
    private void OnBackPressed() {
        context.navigator.pop();
    }
    @FXML
    private void OnAddTagPressed() {
        if (TagComboBox.getValue() == null) return;
        if (TagComboBox.getValue().isBlank()) return;
        AddTag(TagComboBox.getValue());
    }
    @FXML
    private void OnPostPressed() {
        if (TitleField.getText().isBlank()) return;
        if (Body.getText().isBlank()) return;
        try {
            long newID = context.postService.create(new PostDataTransporter()
                    .setUserID(context.authenticationService.CurrentUser.ID)
                    .setTitle(TitleField.getText())
                    .setBody(Body.getText())
                    .setIsDraft(DraftRadioButton.isSelected())
            );
            context.tagService.setPostTags(newID, selectedTags);
            InformationAlert("Post Created Successfully");
            context.navigator.pop();
        } catch (DataAccessException e) {
            ErrorAlert(e.getMessage());
        }
    }
    private void OnUpdatePostPressed() {
        if (TitleField.getText().isBlank()) return;
        if (Body.getText().isBlank()) return;
        try {
            context.postService.update(new PostDataTransporter()
                    .setID(toBeEditedPost.ID)
                    .setTitle(TitleField.getText())
                    .setBody(Body.getText())
                    .setIsDraft(DraftRadioButton.isSelected()));
            context.tagService.setPostTags(toBeEditedPost.ID, selectedTags);
            InformationAlert("Post Updated Successfully");
            context.navigator.pop();
        } catch (DataAccessException e) {
            ErrorAlert(e.getMessage());
        }
    }
    private void AddTag(String tag) {
        if (selectedTags.contains(tag)) return;
        Button tagButton = new Button();
        tagButton.setText("#" + tag);
        ImageView icon = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/assets/cancel.png"))));
        icon.setFitHeight(12);
        icon.setFitWidth(12);
        tagButton.setGraphic(icon);
        tagButton.contentDisplayProperty().setValue(javafx.scene.control.ContentDisplay.RIGHT);
        tagButton.setOnMouseClicked(e -> {
            TagFlowPane.getChildren().remove(tagButton);
            selectedTags.remove(tag);
        });
        TagFlowPane.getChildren().add(tagButton);
        selectedTags.add(tag);
    }
}
