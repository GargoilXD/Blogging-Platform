package org.blogging.platform;

import io.github.cdimascio.dotenv.Dotenv;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.blogging.platform.DataAccessors.Mongo.MongoDBDataAccessor;
import org.blogging.platform.Services.Navigator;
import org.blogging.platform.Utilities.DummyDataInsertor;

import java.io.IOException;

public class App extends Application {
    Context context;

    @Override
    public void start(Stage stage) throws Exception {
        context = new Context(stage);
        context.navigator.push(Navigator.VIEWS.SPLASH, context);
        new Thread(() -> {
            try {
                Dotenv dotenv = Dotenv.load();
                if (dotenv.get("DUMMY_DATA").equals("YES")) {
                    DummyDataInsertor Insertor = new DummyDataInsertor();
                    Insertor.Insert(context);
                }
                Thread.sleep(2000);
                Platform.runLater(() -> {
                    try {
                        context.navigator.push(Navigator.VIEWS.LOGIN, context);
                    } catch (IOException e) {
                        System.err.println(e.getMessage());
                        Platform.exit();
                    }
                });
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
                Platform.exit();
            }
        }).start();
    }
    public static void main(String[] args) {
        launch();
        MongoDBDataAccessor.close();
    }
}