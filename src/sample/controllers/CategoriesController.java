package sample.controllers;

import com.google.gson.Gson;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Main;
import sample.add_controllers.AddCategoryController;
import sample.add_controllers.AddEmployeeController;
import sample.models.ApiModel;
import sample.models.Category;
import sample.models.Employee;
import sample.utils.ApiSession;

import java.util.HashMap;
import java.util.Map;

public class CategoriesController implements  ApiModel{

    @FXML
    private TableView<Category> categories_table;

    @FXML
    private TableColumn<Category, String> id_column;

    @FXML
    private TableColumn<Category, String> name_column;

    @FXML
    private TableColumn<Category, String> price_column;

    @FXML
    private Button add_button;

    @FXML
    private Button refresh_button;


    private Main main;
    private ApiSession apiSession = new ApiSession();
    private ObservableList<Category> categoryList = FXCollections.observableArrayList();

    @FXML
    private void initialize(){
        buttonClick();

        updateCategoryTable();

        id_column.setCellValueFactory(new PropertyValueFactory<Category, String>("id"));
        name_column.setCellValueFactory(new PropertyValueFactory<Category, String>("name"));
        price_column.setCellValueFactory(new PropertyValueFactory<Category, String>("price"));
    }

    private void buttonClick() {
        add_button.setOnAction(event -> AddCategoryController.show());
        refresh_button.setOnAction(event -> updateCategoryTable());
    }

    @FXML
    private void updateCategoryTable(){
        categoryList.clear();
        categoryList.addAll(apiSession.getAllCategories());
        categories_table.setItems(categoryList);
    }


    SimpleStringProperty id;
    SimpleStringProperty name;
    SimpleStringProperty price;


    public CategoriesController(String id, String name, String price) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleStringProperty(price);
    }

    public CategoriesController() {
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
        Map<String, String> map = new HashMap<>();
        map.put("id", id.get());
        map.put("name", name.get());
        map.put("price", price.get());

        Gson gson = new Gson();
        return gson.toJson(map);
    }

    @Override
    public String toJsonPut() {
        Map<String, String> map = new HashMap<>();
        map.put("id", id.get());
        map.put("name", name.get());
        map.put("price", price.get());

        Gson gson = new Gson();
        return gson.toJson(map);
    }
}
