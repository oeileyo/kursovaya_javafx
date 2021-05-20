package sample.add_controllers;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.controllers.MainMenuController;
import sample.models.Employee;
import sample.utils.ApiSession;
import java.io.IOException;

/* Controller for adding employee page */
public class AddEmployeeController {
    /* FXML components */
    @FXML
    private TextField id_field;

    @FXML
    private TextField first_name_field;

    @FXML
    private TextField last_name_field;

    @FXML
    private Button add_button;

    @FXML
    private  Button cancel_button;

    @FXML
    private Label message;

    /* Parameters */
    private ApiSession apiSession = new ApiSession();

    /* Show page method */
    public static void show() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(AddEmployeeController.class.getResource("/add_views/add_employee.fxml"));

        try {
            loader.load();
        } catch (IOException e){
            e.printStackTrace();
        }

        AddEmployeeController controller = loader.getController();
        controller.buttonClick();

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.initOwner(MainMenuController.stage);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Add Employee");
        stage.setScene(new Scene(root));
        stage.show();
    }

    /* Methods for buttons */
    private void buttonClick() {
        add_button.setOnAction(event -> handleOk());
        cancel_button.setOnAction(event -> handleCancel());
    }

    /* Input data validation */
    private boolean isInputValid() {
        String errorMessage = "";

        if (first_name_field.getText() == null || first_name_field.getText().length() == 0) {
            errorMessage += "Не введено имя сотрудника.\n";
        }
        if (last_name_field.getText() == null || last_name_field.getText().length() == 0) {
            errorMessage += "Не введена фамилия сотрудника.";
        }


        if (errorMessage.length() == 0) {
            return true;
        } else {
            message.setText(errorMessage);
            return false;
        }
    }

    /* Method for cancel button */
    @FXML
    private  void handleCancel() {
        first_name_field.getScene().getWindow().hide();
    }

    /* Method for ok button */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            Employee employee = new Employee(first_name_field.getText(), last_name_field.getText());
            apiSession.createEmployee(employee);
            first_name_field.getScene().getWindow().hide();
        }
    }

    /* Constructor */
    public AddEmployeeController(){}
}
