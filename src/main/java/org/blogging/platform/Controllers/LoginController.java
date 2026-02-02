package org.blogging.platform.Controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.blogging.platform.Context;
import org.blogging.platform.DataAccessors.Exceptions.DataAccessException;
import org.blogging.platform.Services.Navigator;

public class LoginController extends Controller {
    @FXML
    TextField UsernameField;
    @FXML
    PasswordField PasswordField;

    @Override
    public void initialize(Parent parent, Context context, Object... arguments) throws IOException {
        super.initialize(parent, context, arguments);
    }
    @FXML
    void OnLoginPressed() throws IOException {
        try {
            if (context.authenticationService.login(UsernameField.getText(), PasswordField.getText())) {
                context.navigator.push(Navigator.VIEWS.HOME, context);
            } else {
                ErrorAlert("Invalid username or password");
            }
        } catch (DataAccessException e) {
            ErrorAlert(e.getMessage());
        }
    }
    @FXML
    void OnRegisterPressed() throws IOException {
        context.navigator.push(Navigator.VIEWS.REGISTER,  context);
    }
}
