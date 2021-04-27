package sample.edit_controllers;

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
import sample.add_controllers.AddEmployeeController;
import sample.controllers.MainMenuController;
import sample.models.Category;
import sample.models.Employee;
import sample.utils.ApiSession;

import java.io.IOException;

public class EditCategoryController {

    @FXML
    private TextField name_field;

    @FXML
    private TextField price_field;

    @FXML
    private Button cancel_button;

    @FXML
    private Button edit_button;

    @FXML
    private Label message;

    private Category category;

    private ApiSession apiSession = new ApiSession();

    public static void show(Category category) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(AddEmployeeController.class.getResource("/edit_views/edit_category.fxml"));

        try {
            loader.load();
        } catch (IOException e){
            e.printStackTrace();
        }

        EditCategoryController controller = loader.getController();
        controller.buttonClick();
        controller.setCategory(category);

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.initOwner(MainMenuController.stage);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Edit Category");
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void buttonClick() {
        edit_button.setOnAction(event -> handleOk());
        cancel_button.setOnAction(event -> handleCancel());
    }

    public void setCategory(Category category) {
        this.category = category;
        name_field.setText(category.getName());
        price_field.setText(String.valueOf(category.getPrice()));
    }

    @FXML
    private  void handleCancel() {
        name_field.getScene().getWindow().hide();
    }

    @FXML
    private void handleOk() {
        try {
            Integer tmp = Integer.valueOf(price_field.getText());

            category.setName(name_field.getText());
            category.setPrice(Integer.valueOf(price_field.getText()));
            apiSession.updateCategories(category);
            name_field.getScene().getWindow().hide();
        }
        catch (Exception e){
            message.setText("Неверный тип данных для строки \"Цена\".");
        }
    }

    public EditCategoryController(){}

}