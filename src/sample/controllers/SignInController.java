package sample.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import sample.exceptions.AppException;

public class SignInController implements Initializable {

    @FXML
    private Label message;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button login_button;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { }

    private void attachEvent() {
        login_field.getScene().setOnKeyPressed(event -> { if (event.getCode() == KeyCode.ENTER) { login(); } });
    }

    @FXML
    private void login() {
        try {
            String login_ = "admin";
            String password_ = "admin";

            if (login_field.getText().equals(login_) && password_field.getText().equals(password_)) {
                MainController.show(); //входим
                cancel(); //закрываем
            } if (login_field.getText().isEmpty()) {
                throw new AppException("Поле ввода логина должно быть заполнено.");
            } if (password_field.getText().isEmpty()) {
                throw new AppException("Поле ввода логина должно быть заполнено.");
            } else {
                throw new AppException("Введен неверный логин или пароль.");
            }
        }
        catch (AppException e) {
            message.setText(e.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
            cancel();
        }
    }

    @FXML
    private void cancel() { login_button.getScene().getWindow().hide(); }

    public static void loadView(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(SignInController.class.getResource("./views/login.fxml"));
            Parent view = loader.load();
            stage.setScene(new Scene(view));

            SignInController controller = loader.getController();
            controller.attachEvent();

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
