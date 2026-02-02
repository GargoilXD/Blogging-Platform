package org.blogging.platform.Controllers;

import java.io.IOException;
import java.util.HashMap;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.blogging.platform.Context;
import org.blogging.platform.DataAccessors.Exceptions.DataAccessException;
import org.blogging.platform.DataTransporters.UserDataTransporter;

public class RegisterController extends Controller {
    @FXML
    private TextField UsernameField;
    @FXML
    private TextField FullNameField;
    @FXML
    private PasswordField PasswordField1;
    @FXML
    private PasswordField PasswordField2;
    @FXML
    private TextField EmailField;
    @FXML
    private ChoiceBox<String> GenderChoiceBox;

    @Override
    public void initialize(Parent parent, Context context, Object... arguments) throws IOException {
        super.initialize(parent, context, arguments);
        GenderChoiceBox.getItems().add("Male");
        GenderChoiceBox.getItems().add("Female");
    }
    @FXML
    private void OnBackPressed() throws IOException {
        context.navigator.pop();
    }
    @FXML
    private void Register() throws IOException {
        if (UsernameField.getText().isBlank()) {
            ErrorAlert("Enter a username");
            return;
        }
        if (FullNameField.getText().isBlank()) {
            ErrorAlert("Enter your full name");
            return;
        }
        if (PasswordField1.getText().isBlank()) {
            ErrorAlert("Enter your password");
            return;
        }
        if (!PasswordField1.getText().equals(PasswordField2.getText())) {
            ErrorAlert("Password Mismatch");
            return;
        }
        if (!EmailField.getText().toLowerCase().matches("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$")) {
            ErrorAlert("Enter a valid email");
            return;
        }
        if (GenderChoiceBox.getValue() == null) {
            ErrorAlert("Choose your gender");
            return;
        }
        try {
            HashMap<String, String> data = new HashMap<>();
            data.put("Male", "M");
            data.put("Female", "F");
            UserDataTransporter transporter = new UserDataTransporter();
            transporter
                    .setUsername(UsernameField.getText())
                    .setPassword(PasswordField1.getText())
                    .setFullName(FullNameField.getText())
                    .setEmail(EmailField.getText())
                    .setGender(data.get(GenderChoiceBox.getValue()));
            context.authenticationService.register(transporter);
            InformationAlert("Successfully Registered");
            context.navigator.pop();
        } catch (DataAccessException e){
            ErrorAlert(e.getMessage());
        }
    }
}
