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
import sample.models.Category;
import sample.utils.ApiSession;
import java.io.IOException;

/* Controller for adding category page */
public class AddCategoryController {
    /* FXML components */
    @FXML
    private TextField name_field;
    @FXML
    private TextField price_field;
    @FXML
    private Button add_button;
    @FXML
    private Label message;
    @FXML
    private Button cancel_button;

    /* Parameters */
    private ApiSession apiSession = new ApiSession();

    /* Show page method */
    public static void show() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(AddCategoryController.class.getResource("/add_views/add_category.fxml"));

        try {
            loader.load();
        } catch (IOException e){
            e.printStackTrace();
        }

        AddCategoryController controller = loader.getController();
        controller.buttonClick();

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.initOwner(MainMenuController.stage);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Add Category");
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
        if (name_field.getText() == null || name_field.getText().length() == 0) {
            errorMessage += "Не введено название категории.\n";
        }
        if (price_field.getText() == null || price_field.getText().length() == 0) {
            errorMessage += "Не введена цена услуги.\n";
        }

        try {
            Integer tmp = Integer.valueOf(price_field.getText());
        }
        catch (Exception e){
            errorMessage += "Неверный тип данных для строки \"Цена\".";
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
        name_field.getScene().getWindow().hide();
    }

    /* Method for ok button */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            Category category = new Category();
            category.setName(name_field.getText());
            category.setPrice(Integer.valueOf(price_field.getText()));
            apiSession.createCategory(category);
            name_field.getScene().getWindow().hide();
        }
    }

    /* Constructor */
    public AddCategoryController(){}
}