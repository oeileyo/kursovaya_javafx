package sample.controllers;

import com.google.gson.Gson;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Main;
import sample.add_controllers.AddAppointmentController;
import sample.models.ApiModel;
import sample.models.Appointment;
import sample.models.Employee;
import sample.utils.ApiSession;
import sample.utils.DateUtil;

import java.util.HashMap;
import java.util.Map;

public class AppointmentsController implements ApiModel {

    @FXML
    private ComboBox<Employee> employee_selection;

    @FXML
    private DatePicker date_selection;

    @FXML
    private RadioButton status_selection;

    @FXML
    private Button find_button;

    @FXML
    private Button add_button;

    @FXML
    private Button delete_button;

    @FXML
    private Button refresh_button;

    @FXML
    private Label message;

    @FXML
    private TableView<Appointment> appointments_table;

    @FXML
    private TableColumn<Appointment, String> id_column;

    @FXML
    private TableColumn<Appointment, String> employee_column;

    @FXML
    private TableColumn<Appointment, String> date_column;

    @FXML
    private TableColumn<Appointment, String> time_column;

    @FXML
    private TableColumn<Appointment, String> status_column;

    @FXML
    private TableColumn<Appointment, String> client_column;

    @FXML
    private TableColumn<Appointment, String> category_column;

    @FXML
    private TableColumn<Appointment, String> price_column;


    private Main main;
    private ApiSession apiSession = new ApiSession();
    private ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();

    @FXML
    private void initialize(){
        buttonClick();

        updateAppointmentTable();

        id_column.setCellValueFactory(new PropertyValueFactory<Appointment, String>("id"));
        employee_column.setCellValueFactory(new PropertyValueFactory<Appointment, String>("employee_id"));
        date_column.setCellValueFactory(new PropertyValueFactory<Appointment, String>("id"));
        time_column.setCellValueFactory(new PropertyValueFactory<Appointment, String>("id"));
        status_column.setCellValueFactory(new PropertyValueFactory<Appointment, String>("str_status"));
        client_column.setCellValueFactory(new PropertyValueFactory<Appointment, String>("id"));
        category_column.setCellValueFactory(new PropertyValueFactory<Appointment, String>("id"));
        price_column.setCellValueFactory(new PropertyValueFactory<Appointment, String>("price"));
    }

    private void buttonClick() {
//        add_button.setOnAction(event -> AddAppointmentController.show());
        refresh_button.setOnAction(event -> updateAppointmentTable());
//        edit_button.setOnAction(event -> {
//            String errorMessage = "";
//            message.setText(errorMessage);
//            int selectedIndex = appointments_table.getSelectionModel().getSelectedIndex();
//
//            if (selectedIndex >= 0) {
//                Appointment appointment = appointments_table.getItems().get(selectedIndex);
//                EditAppointmentController.show(appointment);
//            } else {
//                errorMessage += "Выберите строку, которую хотите изменить.";
//                message.setText(errorMessage);
//            }
//        });
        delete_button.setOnAction(event -> {
            String errorMessage = "";
            message.setText(errorMessage);
            int selectedIndex = appointments_table.getSelectionModel().getSelectedIndex();

            if (selectedIndex >= 0) {
                Appointment appointment = appointments_table.getItems().get(selectedIndex);
                apiSession.deleteAppointment(appointment);
                appointments_table.getItems().remove(selectedIndex);
            } else {
                errorMessage += "Выберите строку, которую хотите удалить.";
                message.setText(errorMessage);
            }
        });
    }

    @FXML
    private void updateAppointmentTable(){
        appointmentList.clear();
        appointmentList.addAll(apiSession.getAllAppointments());
        appointments_table.setItems(appointmentList);
    }


    SimpleStringProperty id;
    SimpleStringProperty name;
    SimpleStringProperty price;


    public AppointmentsController(String id, String name, String price) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleStringProperty(price);
    }

    public AppointmentsController() {
    }

    public String getId() { return id.get(); }

    public SimpleStringProperty idProperty() { return id; }

    public void setId(String id) { this.id.set(id); }


    public String getName() { return name.get(); }

    public SimpleStringProperty nameProperty() { return name; }

    public void setName(String name) { this.name.set(name); }


    public String getPrice() { return price.get(); }

    public SimpleStringProperty priceProperty() { return price; }

    public void setPrice(String price) { this.price.set(price); }


    @Override
    public String toString() {
        return "EmployeesController{" +
                "id=" + id +
                ", name=" + name +
                ", price=" + price +
                '}';
    }

    @Override
    public String toJson() {
        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<>();
        map.put("date_time", DateUtil.toDateFormat(date.get(), time.get()));
        map.put("status", status.get()=="Свободно" ? true : false);
        map.put("employee_id", employee_id.get());
        map.put("client_id", client_id.get());
        map.put("category_id", category_id.get());
        return gson.toJson(map);
    }

    @Override
    public String toJsonWithId() {
        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<>();
        map.put("id", id.get());
        map.put("date_time", DateUtil.toDateFormat(date.get(), time.get()));
        map.put("status", status.get()=="Свободно" ? true : false);
        map.put("employee_id", employee_id.get());
        map.put("client_id", client_id.get());
        map.put("category_id", category_id.get());
        return gson.toJson(map);
    }
}
