package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sample.models.ApiModel;

public class AppointmentsController implements ApiModel {

    @FXML
    private ComboBox<?> employee_selection;

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
    private TableView<?> appointments_table;

    @FXML
    private TableColumn<?, ?> id_column;

    @FXML
    private TableColumn<?, ?> employee_column;

    @FXML
    private TableColumn<?, ?> date_column;

    @FXML
    private TableColumn<?, ?> time_column;

    @FXML
    private TableColumn<?, ?> status_column;

    @FXML
    private TableColumn<?, ?> client_column;

    @FXML
    private TableColumn<?, ?> category_column;

    @FXML
    private TableColumn<?, ?> price_column;

    @Override
    public String toJson() {
        return null;
    }

    @Override
    public String toJsonPut() {
        return null;
    }
}
