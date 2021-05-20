package sample.add_controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.controllers.MainMenuController;
import sample.models.Appointment;
import sample.models.Category;
import sample.models.Client;
import sample.models.Employee;
import sample.utils.ApiSession;
import sample.utils.DateUtil;
import java.io.IOException;

/* Controller for adding appointment page */
public class AddAppointmentController {
    /* FXML components */
    @FXML
    private TextField client_name_field;

    @FXML
    private Button add_button;

    @FXML
    private Label message;

    @FXML
    private Button cancel_button;

    @FXML
    private DatePicker date_select;

    @FXML
    private ComboBox<String> employee_select;

    @FXML
    private ComboBox<String> time_select;

    @FXML
    private TextField client_phone_field;

    @FXML
    private ComboBox<String> category_select;

    /* Parameters */
    private ApiSession apiSession = new ApiSession();
    ObservableList<Employee> employeeList = FXCollections.observableArrayList();
    ObservableList<String> employeeNameList = FXCollections.observableArrayList();
    ObservableList<Category> categoryList = FXCollections.observableArrayList();
    ObservableList<String> categoryNameList = FXCollections.observableArrayList();
    ObservableList<String> timeList = FXCollections.observableArrayList();

    /* Show page method */
    public static void show() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(AddAppointmentController.class.getResource("/add_views/add_appointment.fxml"));

        try {
            loader.load();
        } catch (IOException e){
            e.printStackTrace();
        }

        AddAppointmentController controller = loader.getController();
        controller.buttonClick();
        controller.employeeComboBox();
        controller.categoryComboBox();
        controller.timeComboBox();

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.initOwner(MainMenuController.stage);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Add Appointment");
        stage.setScene(new Scene(root));
        stage.show();
    }

    /* Methods for buttons */
    private void buttonClick() {
        add_button.setOnAction(event -> handleOk());
        cancel_button.setOnAction(event -> handleCancel());
    }

    /* Setting data for employee combo box */
    private void employeeComboBox(){
        employeeList.clear();
        employeeList.addAll(apiSession.getAllEmployees());

        employeeNameList.clear();
        for (Employee employee: employeeList){
            employeeNameList.add(employee.getFirst_name() + " " + employee.getLast_name());
        }
        employee_select.setItems(employeeNameList);
    }

    /* Setting data for time combo box */
    private void timeComboBox(){
        timeList.clear();
        timeList = FXCollections.observableArrayList("10:00", "12:00",
                "14:00", "16:00", "18:00", "20:00", "22:00");
        time_select.setItems(timeList);
    }

    /* Setting data for category combo box */
    private void categoryComboBox(){
        categoryList.clear();
        categoryList.addAll(apiSession.getAllCategories());

        categoryNameList.clear();
        for (Category category: categoryList){
            categoryNameList.add(category.getName());
        }
        category_select.setItems(categoryNameList);
    }

    /* Input data validation */
    private boolean isInputValid() {
        String errorMessage = "";
        if (client_name_field.getText() == null || client_name_field.getText().length() == 0) {
            errorMessage += "Не введено имя клиента.\n";
        }
        if (client_phone_field.getText() == null || client_phone_field.getText().length() == 0) {
            errorMessage += "Не введен телефон клиента.\n";
        }

        try {
            Integer tmp = Integer.valueOf(client_phone_field.getText());
        }
        catch (Exception e){
            errorMessage += "Неверный тип данных для строки \"Телефон\".";
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
        client_name_field.getScene().getWindow().hide();
    }

    /* Method for ok button */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            Client client = new Client(apiSession.getLastClient().getId(), client_name_field.getText(),
                    Integer.valueOf(client_phone_field.getText()));
            apiSession.createClient(client);

            Appointment appointment = new Appointment(apiSession.GetEmployeeByName(employee_select.getValue()),
                    DateUtil.dateToString(date_select.getValue()),
                    time_select.getValue(),
                    apiSession.getLastClient().getId(),
                    apiSession.GetCategoryByName(category_select.getValue()));

            apiSession.createAppointment(appointment);

            client_name_field.getScene().getWindow().hide();
        }
    }

    /* Constructor */
    public AddAppointmentController(){}
}
