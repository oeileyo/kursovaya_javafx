package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button mainButton;

    @FXML
    private Button employeesButton;

    @FXML
    private Button categoriesButton;

    @FXML
    private Button appointmentsButton;

    @FXML
    private Button aboutButton;

    @FXML
    private AnchorPane mainAP;

    public static void show() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainMenuController.class.getResource("/views/mainMenu.fxml"));

        try {
            loader.load();
        } catch (IOException e){
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
}
