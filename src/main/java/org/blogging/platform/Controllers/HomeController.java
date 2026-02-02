package org.blogging.platform.Controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;

import org.blogging.platform.Context;
import org.blogging.platform.Controllers.Intefaces.DynamicController;
import org.blogging.platform.DataAccessors.Exceptions.DataAccessException;
import org.blogging.platform.Models.Post;
import org.blogging.platform.Services.Navigator;
import org.blogging.platform.Utilities.Sorter;

public class HomeController extends Controller implements DynamicController {
    @FXML
    Text ProfileText;
    @FXML
    TextField SearchField;
    @FXML
    ChoiceBox<String> SortingChoiceBox;
    @FXML
    FlowPane PostPreviewFlowPane;
    @FXML
    Label NoPostsLabel;
    @FXML
    Label DraftLabel;

    enum State { MY_POSTS, ALL_POSTS, MY_DRAFTS }
    State state = State.ALL_POSTS;

    List<Post> posts = new ArrayList<>();
    HashMap<Long, List<String>> tagsByPosts = new HashMap<>();

    @Override
    public void initialize(Parent parent, Context context, Object... arguments) throws IOException {
        super.initialize(parent, context, arguments);
        SortingChoiceBox.getItems().addAll("A-Z", "Latest", "By Author");
        SortingChoiceBox.setValue("A-Z");
        SortingChoiceBox.setOnAction(event -> displayPosts());
        ProfileText.setText(context.authenticationService.CurrentUser.FullName);
        LoadPosts();
    }
    @Override
    public void reload() {
        LoadPosts();
    }
    void LoadPosts() {
        try {
            posts = context.postService.getAll();
            tagsByPosts = context.tagService.getAllTagsByPosts();
        } catch (DataAccessException e) {
            ErrorAlert(e.getMessage());
        }
        if (posts.isEmpty()) {
            NoPostsLabel.setVisible(true);
            NoPostsLabel.setManaged(true);
        } else {
            NoPostsLabel.setVisible(false);
            NoPostsLabel.setManaged(false);
            displayPosts();
        }
    }
    @FXML
    void displayPosts() {
        PostPreviewFlowPane.getChildren().clear();
        List<Post> filteredPosts = new ArrayList<>();
        for (Post post : posts) {
            switch (state) {
                case MY_POSTS:
                    if (post.UserID == context.authenticationService.CurrentUser.ID) filteredPosts.add(post);
                    break;
                case MY_DRAFTS:
                    if (post.IsDraft && post.UserID == context.authenticationService.CurrentUser.ID) filteredPosts.add(post);
                    break;
                case ALL_POSTS:
                    if (!post.IsDraft) filteredPosts.add(post);
            }
        }
        Runnable LoadPosts = this::LoadPosts;
        for (Post post : Sorter.sort(filteredPosts, SortingChoiceBox.getValue())) {
            try {
                String[] tags = new String[0];
                if (tagsByPosts.get(post.ID) != null) tags = tagsByPosts.get(post.ID).toArray(tags);
                Controller postPreview = Controller.load("post_preview", context, post, tags, LoadPosts);
                postPreview.root.setUserData(postPreview);
                PostPreviewFlowPane.getChildren().add(postPreview.root);
            } catch (IOException e) {
                ErrorAlert(e.getMessage());
            }
        }
    }
    @FXML
    void OnLogoutPressed() {
        context.navigator.pop();
    }
    @FXML
    void OnSearchPressed() {
        String query = SearchField.getText().toLowerCase();
        if (query.isEmpty()) {
            for (var child : PostPreviewFlowPane.getChildren()) {
                child.setVisible(true);
                child.setManaged(true);
            }
        } else{
            for (var child : PostPreviewFlowPane.getChildren()) {
                PostPreviewController controller = (PostPreviewController) child.getUserData();
                if (controller != null) {
                    boolean matches = controller.post.Title.toLowerCase().contains(query) || controller.post.Body.toLowerCase().contains(query) || controller.post.Username.toLowerCase().contains(query) || Arrays.stream(controller.tags).anyMatch(tag -> tag.toLowerCase().contains(query.toLowerCase()));
                    child.setVisible(matches);
                    child.setManaged(matches);
                }
            }
        }
    }
    @FXML
    void OnCreatePostPressed() throws IOException {
        context.navigator.push(Navigator.VIEWS.POST_EDITOR, context);
    }
    @FXML
    void OnAllPostsPressed() {
        state = State.ALL_POSTS;
        displayPosts();
    }
    @FXML
    void OnMyPostsPressed() {
        state = State.MY_POSTS;
        displayPosts();
    }
    @FXML
    void OnMyDraftsPressed() {
        state = State.MY_DRAFTS;
        displayPosts();
    }
}
