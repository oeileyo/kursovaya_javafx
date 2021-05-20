package sample.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {


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
    private Button statisticsButton;

    @FXML
    private Button aboutButton;

    public static Stage stage = new Stage();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/main.fxml"));
            Parent view = loader.load();
            mainAP.getChildren().add(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void buttonClick(){
        mainButton.setOnAction(event -> loadView("/views/main.fxml"));
        employeesButton.setOnAction(event -> loadView("/views/employees.fxml"));
        categoriesButton.setOnAction(event -> loadView("/views/categories.fxml"));
        appointmentsButton.setOnAction(event -> loadView("/views/appointments.fxml"));
        statisticsButton.setOnAction(event -> loadView("/views/statistics.fxml"));
        aboutButton.setOnAction(event -> loadView("/views/about.fxml"));
    }

    private void loadView(String page_name) {
        try {
            mainAP.getChildren().clear(); // clear main anchor pane which shows the content

            FXMLLoader loader = new FXMLLoader(getClass().getResource(page_name));
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

        stage.setTitle("Massage Salon System");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
