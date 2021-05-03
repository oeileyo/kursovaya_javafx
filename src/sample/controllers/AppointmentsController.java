package sample.controllers;

import com.google.gson.Gson;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
        employee_column.setCellValueFactory(new PropertyValueFactory<Appointment, String>("employee_name"));
        date_column.setCellValueFactory(new PropertyValueFactory<Appointment, String>("date"));
        time_column.setCellValueFactory(new PropertyValueFactory<Appointment, String>("time"));
        status_column.setCellValueFactory(new PropertyValueFactory<Appointment, String>("status"));
        client_column.setCellValueFactory(new PropertyValueFactory<Appointment, String>("client_name"));
        category_column.setCellValueFactory(new PropertyValueFactory<Appointment, String>("category_name"));
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
    SimpleStringProperty employee_name;
    SimpleStringProperty employee_id;
    SimpleStringProperty date;
    SimpleStringProperty time;
    SimpleStringProperty status;
    SimpleStringProperty client_name;
    SimpleStringProperty client_id;
    SimpleStringProperty category_name;
    SimpleStringProperty category_id;
    SimpleStringProperty price;


    public AppointmentsController(String id, String employee_id, String employee_name, String date, String time,
                                  String status, String client_id, String client_name, String category_id,
                                  String category_name, String price) {
        this.id = new SimpleStringProperty(id);
        this.employee_name = new SimpleStringProperty(employee_name);
        this.employee_id = new SimpleStringProperty(employee_id);
        this.date = new SimpleStringProperty(date);
        this.time = new SimpleStringProperty(time);
        this.status = new SimpleStringProperty(status);
        this.client_name = new SimpleStringProperty(client_name);
        this.client_id = new SimpleStringProperty(client_id);
        this.category_name = new SimpleStringProperty(category_name);
        this.category_id = new SimpleStringProperty(category_id);
        this.price = new SimpleStringProperty(price);
    }

    public AppointmentsController() {
    }


    public String getId() { return id.get(); }

    public void setId(String id) { this.id.set(id); }

    public String getEmployee_name() { return employee_name.get(); }

    public void setEmployee_name(String employee_name) { this.employee_name.set(employee_name); }

    public String getDate() { return date.get(); }

    public void setDate(String date) { this.date.set(date); }

    public String getTime() { return time.get(); }

    public void setTime(String time) { this.time.set(time); }

    public String getStatus() { return status.get(); }

    public void setStatus(String status) { this.status.set(status); }

    public String getClient_name() { return client_name.get(); }

    public void setClient_name(String client_name) { this.client_name.set(client_name); }

    public String getClient_id() { return client_id.get(); }

    public void setClient_id(String client_id) { this.client_id.set(client_id); }

    public String getCategory_id() { return category_id.get(); }

    public void setCategory_id(String category_id) { this.category_id.set(category_id); }

    public String getCategory_name() { return category_name.get(); }

    public void setCategory_name(String category_name) { this.category_name.set(category_name); }

    public String getPrice() { return price.get(); }

    public void setPrice(String price) { this.price.set(price); }

    @Override
    public String toString() {
        return "AppointmentsController{" +
                "id=" + id +
                ", employee_id=" + employee_id +
                ", category_id=" + category_id +
                ", client_id=" + client_id +
                ", status=" + status +
                ", date=" + date +
                ", time= " + time +
                '}';
    }

    @Override
    public String toJson() {
        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<>();
        map.put("date", date.get());
        map.put("time", time.get());
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
        map.put("date", date.get());
        map.put("time", time.get());
        map.put("status", status.get()=="Свободно" ? true : false);
        map.put("employee_id", employee_id.get());
        map.put("client_id", client_id.get());
        map.put("category_id", category_id.get());
        return gson.toJson(map);
    }
}
