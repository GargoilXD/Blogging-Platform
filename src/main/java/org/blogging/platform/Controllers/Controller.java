package org.blogging.platform.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.blogging.platform.App;
import org.blogging.platform.Context;
import org.blogging.platform.Controllers.Exceptions.ControllerNotFoundException;

import java.io.IOException;

public abstract class Controller {
    Parent root;
    Context context;
    @FXML
    public void initialize(Parent root, Context context, Object... arguments) throws IOException {
        this.root = root;
        this.context = context;
    }
    @FXML
    public static Controller load(String screen, Context context, Object... arguments) throws IOException, ControllerNotFoundException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(String.format("/%s.fxml", screen)));
        Parent root = fxmlLoader.load();
        if (fxmlLoader.getController() instanceof Controller controller) {
            controller.initialize(root, context, arguments);
            return controller;
        }
        throw new ControllerNotFoundException("Controller Not Found");
    }
    @FXML
    protected void InformationAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML
    protected void ConfirmationDialog(String title, String message, Runnable onConfirm) {
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle(title);
        confirmation.setHeaderText(message);
        confirmation.showAndWait();
        if (confirmation.getResult() == ButtonType.OK) onConfirm.run();
    }
    @FXML
    protected void ErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
