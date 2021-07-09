package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ProfLogCont {
    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField useridField;
    @FXML
    private PasswordField passwordField;

    public void loginButtonOnAction(ActionEvent e){

        if(useridField.getText().isBlank() == false && passwordField.getText().isBlank() == false){
            loginMessageLabel.setText("You tried to login!");
        } else{
            loginMessageLabel.setText("Please enter your ID and Password.");
        }
    }

    public void  cancelButtonOnAction(ActionEvent e){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
