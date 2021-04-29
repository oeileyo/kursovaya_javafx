package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import com.google.gson.Gson;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Main;
import sample.add_controllers.AddEmployeeController;
import sample.edit_controllers.EditEmployeeController;
import sample.models.ApiModel;
import sample.models.Employee;
import sample.utils.ApiSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EmployeesController implements ApiModel {
    @FXML
    private TableView<Employee> employees_table;

    @FXML
    private TableColumn<Employee, String> id_column;

    @FXML
    private TableColumn<Employee, String> first_name_column;

    @FXML
    private TableColumn<Employee, String> last_name_column;

    @FXML
    private Button add_button;

    @FXML
    private Button refresh_button;

    @FXML
    private Label message;

    @FXML
    private Button delete_button;

    @FXML
    private Button edit_button;


    private Main main;
    private ApiSession apiSession = new ApiSession();
    private ObservableList<Employee> employeeList = FXCollections.observableArrayList();
//    private ObservableList<String> companyNameList = FXCollections.observableArrayList();
//    private List<Company> companyList = new ArrayList<>();


    @FXML
    private void initialize(){
        buttonClick();

        updateEmployeesTable();

        id_column.setCellValueFactory(new PropertyValueFactory<Employee, String>("id"));
        first_name_column.setCellValueFactory(new PropertyValueFactory<Employee, String>("first_name"));
        last_name_column.setCellValueFactory(new PropertyValueFactory<Employee, String>("last_name"));
    }

    private void buttonClick() {
        add_button.setOnAction(event -> AddEmployeeController.show());
        refresh_button.setOnAction(event -> updateEmployeesTable());
        edit_button.setOnAction(event -> {
            String errorMessage = "";
            message.setText(errorMessage);
            int selectedIndex = employees_table.getSelectionModel().getSelectedIndex();

            if (selectedIndex >= 0) {
                Employee employee = employees_table.getItems().get(selectedIndex);
                EditEmployeeController.show(employee);
            } else {
                errorMessage += "Выберите строку, которую хотите изменить.";
                message.setText(errorMessage);
            }
        });
        delete_button.setOnAction(event -> {
            String errorMessage = "";
            message.setText(errorMessage);
            int selectedIndex = employees_table.getSelectionModel().getSelectedIndex();

            if (selectedIndex >= 0) {
                Employee employee = employees_table.getItems().get(selectedIndex);
                apiSession.deleteEmployee(employee);
                employees_table.getItems().remove(selectedIndex);
            } else {
                errorMessage += "Выберите строку, которую хотите удалить.";
                message.setText(errorMessage);
            }
        });
    }

    @FXML
    private void updateEmployeesTable(){
        employeeList.clear();
        employeeList.addAll(apiSession.getAllEmployees());
        employees_table.setItems(employeeList);
    }


    SimpleStringProperty id;
    SimpleStringProperty first_name;
    SimpleStringProperty last_name;

    public EmployeesController(String id, String first_name, String last_name) {
            this.id = new SimpleStringProperty(id);
            this.first_name = new SimpleStringProperty(first_name);
            this.last_name = new SimpleStringProperty(last_name);
    }

    public EmployeesController() {
    }

    public String getId() { return id.get(); }

    public void setId(String id) { this.id = new SimpleStringProperty(id); }

    public String getFirstName() { return first_name.get(); }

    public void setFirstName(String first_name) { this.first_name = new SimpleStringProperty(first_name); }

    public String getLastName() { return last_name.get(); }

    public void setLastName(String last_name) { this.last_name = new SimpleStringProperty(last_name); }


    @Override
    public String toString() {
        return "EmployeesController{" +
            "id=" + id +
            ", first_name=" + first_name +
            ", last_name=" + last_name +
            '}';
    }

    @Override
    public String toJson() {
        Map<String, String> map = new HashMap<>();
        map.put("id", id.get());
        map.put("first_name", first_name.get());
        map.put("last_name", first_name.get());

        Gson gson = new Gson();
        return gson.toJson(map);
    }

    @Override
    public String toJsonWithId() {
        Map<String, String> map = new HashMap<>();
        map.put("id", id.get());
        map.put("first_name", first_name.get());
        map.put("last_name", first_name.get());

        Gson gson = new Gson();
        return gson.toJson(map);
    }
}
