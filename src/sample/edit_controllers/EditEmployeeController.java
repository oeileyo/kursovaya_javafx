package sample.edit_controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.add_controllers.AddEmployeeController;
import sample.controllers.MainMenuController;
import sample.models.Employee;
import sample.utils.ApiSession;
import java.io.IOException;

/* Controller for editing employee page */
public class EditEmployeeController {
    /* FXML components */
    @FXML
    private TextField first_name_field;

    @FXML
    private TextField last_name_field;

    @FXML
    private Button cancel_button;

    @FXML
    private Button edit_button;

    /* Parameters */
    private Employee employee;
    private ApiSession apiSession = new ApiSession();

    /* Show page method */
    public static void show(Employee employee) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(AddEmployeeController.class.getResource("/edit_views/edit_employee.fxml"));

        try {
            loader.load();
        } catch (IOException e){
            e.printStackTrace();
        }

        EditEmployeeController controller = loader.getController();
        controller.buttonClick();
        controller.setEmployee(employee);

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.initOwner(MainMenuController.stage);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Edit Employee");
        stage.setScene(new Scene(root));
        stage.show();
    }

    /* Methods for buttons */
    private void buttonClick() {
        edit_button.setOnAction(event -> handleOk());
        cancel_button.setOnAction(event -> handleCancel());
    }

    /* Filling fields with data from DB */
    public void setEmployee(Employee employee) {
        this.employee = employee;
        first_name_field.setText(employee.getFirst_name());
        last_name_field.setText(employee.getLast_name());
    }

    /* Method for cancel button */
    @FXML
    private  void handleCancel() {
        first_name_field.getScene().getWindow().hide();
    }

    /* Method for ok button */
    @FXML
    private void handleOk() {
        employee.setFirst_name(first_name_field.getText());
        employee.setLast_name(last_name_field.getText());
        apiSession.updateEmployee(employee);
        first_name_field.getScene().getWindow().hide();
    }

    /* Constructor */
    public EditEmployeeController(){}
}
