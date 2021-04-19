package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.controllers.SignInController;

import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        stage.setResizable(false);
        SignInController.loadView(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }}
