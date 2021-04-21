package sample.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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
    private AnchorPane mainAP;

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


    private void buttonClick(){
        mainButton.setOnAction(event -> loadView(mainButton.getText()));
        employeesButton.setOnAction(event -> loadView(employeesButton.getText()));
        categoriesButton.setOnAction(event -> loadView(categoriesButton.getText()));
        appointmentsButton.setOnAction(event -> loadView(appointmentsButton.getText()));
        aboutButton.setOnAction(event -> loadView(aboutButton.getText()));
    }

    private void loadView(String buttonName) {
        try {
            mainAP.getChildren().clear(); // clear main anchor pane which shows the content

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/"+buttonName.toLowerCase()+".fxml"));
            Parent view = loader.load();

            mainAP.getChildren().add(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void show() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainMenuController.class.getResource("/views/mainMenu.fxml"));

        try {
            loader.load();
        } catch (IOException e){
            e.printStackTrace();
        }

        MainMenuController controller = loader.getController();
        controller.buttonClick();

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
}
