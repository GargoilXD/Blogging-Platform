package org.blogging.platform.Services;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.blogging.platform.App;
import org.blogging.platform.Context;
import org.blogging.platform.Controllers.Controller;
import org.blogging.platform.Controllers.Intefaces.DynamicController;

import java.io.IOException;
import java.util.Stack;

public class Navigator {
    static class View {
        Parent root;
        Controller controller;
        View(Parent root, Controller controller) {
            this.root = root;
            this.controller = controller;
        }
    }
    public enum VIEWS {
        SPLASH("splash"),
        LOGIN("login"),
        REGISTER("register"),
        HOME("home"),
        POST_EDITOR("post_editor"),
        POST_VIEW("post_view");

        public final String name;
        VIEWS(String name) { this.name = name; }
    }
    private final Scene Screen = new Scene(new Pane(), 960, 560);
    private final Stack<View> Views = new Stack<>();

    public Navigator(Stage stage) {
        stage.setScene(Screen);
        stage.show();
    }
    @FXML
    public void push(VIEWS view, Context context, Object... arguments) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(String.format("/%s.fxml", view.name)));
        Parent root = fxmlLoader.load();
        Screen.setRoot(root);
        if (fxmlLoader.getController() instanceof Controller controller) {
            controller.initialize(root, context, arguments);
            Views.push(new View(root, controller));
        }
    }
    public void pop() {
        if (Views.size() > 1) {
            Views.pop();
            View view = Views.peek();
            Screen.setRoot(view.root);
            if (view.controller instanceof DynamicController controller) controller.reload();
        } else Screen.setRoot(Views.peek().root);
    }
}
