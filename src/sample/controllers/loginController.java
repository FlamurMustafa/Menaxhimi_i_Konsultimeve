package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.repositories.DatabaseConnection;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class loginController {

    @FXML
    private Button Loginbtn;

    @FXML
    private Label loginMessage;

    @FXML
    private Button cancelBtn;
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private static Connection connDB = null;

    public void loginButton(javafx.event.ActionEvent e){
        if(!usernameField.getText().isBlank() && !passwordField.getText().isBlank()){
            DatabaseConnection connect = new DatabaseConnection();
            connDB = connect.getConn();

            String verifyLogin = "select count(1) from users where username ='" + usernameField.getText() + "' and password ='" + passwordField.getText() + "'";
            try{
                Statement statement = connDB.createStatement();
                ResultSet queryResult = statement.executeQuery(verifyLogin);

                while(queryResult.next()){
                    if (queryResult.getInt(1) == 1) {
                        Parent parent = FXMLLoader.load(getClass().getResource("../views/studentform.fxml"));
                        Scene scene = new Scene(parent);
                        Stage primaryStage = new Stage();
                        primaryStage = (Stage) ((Node) e.getSource()).getScene().getWindow();

                        primaryStage.setScene(scene);
                        primaryStage.setTitle("Menaxhimi i Konsultimeve");
                        primaryStage.show();
                    }else{
                        loginMessage.setText("Invalid login!");
                    }
                    }
                }catch(Exception ex){
                ex.printStackTrace();
            }
            }else{
            loginMessage.setText("Please enter username and password");
        }
        }
        public void cancelButton(javafx.event.ActionEvent e) throws Exception {
            Parent parent = FXMLLoader.load(getClass().getResource("../views/first.fxml"));
            Scene scene = new Scene(parent);
            Stage primaryStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.show();
        }


}
