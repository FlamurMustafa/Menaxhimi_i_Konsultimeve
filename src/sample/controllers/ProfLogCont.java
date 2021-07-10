package sample.controllers;

import javafx.event.ActionEvent;
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
import sample.Professor;
import sample.SessionManager;
import sample.repositories.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ProfLogCont {
    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField useridField;
    @FXML
    private PasswordField passwordField;

    Connection connDB = null;

    public void loginButtonOnAction(ActionEvent e) throws Exception{

        if(useridField.getText().isBlank() == false && passwordField.getText().isBlank() == false){
            DatabaseConnection connect = new DatabaseConnection();
            connDB = connect.getConn();

            String verifyLogin = "select count(1) from profesoret where id ='" + useridField.getText() + "' and password ='" + passwordField.getText() + "'";
            try{
                Statement statement = connDB.createStatement();
                ResultSet queryResult = statement.executeQuery(verifyLogin);

                while(queryResult.next()) {
                    if (queryResult.getInt(1) == 1) {
                        SessionManager.professor = getProfessor(useridField.getText());

                        Parent parent = FXMLLoader.load(getClass().getResource("../views/professor.fxml"));
                        Scene scene = new Scene(parent);
                        Stage primaryStage = new Stage();
                        primaryStage = (Stage) ((Node) e.getSource()).getScene().getWindow();

                        primaryStage.setScene(scene);
                        primaryStage.setTitle("Menaxhimi i Konsultimeve");
                        primaryStage.show();
                    } else{
                        loginMessageLabel.setText("Invalid login!");
                    }
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }else{
            loginMessageLabel.setText("Please enter username and password");
        }
    }

    public void  cancelButtonOnAction(ActionEvent e){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    private Professor getProfessor(String id) throws Exception{
        DatabaseConnection connect = new DatabaseConnection();
        connDB = connect.getConn();

        String sql = "SELECT * from Profesoret where id = '" + id + "';";
        Statement statement = connDB.createStatement();

        ResultSet resultSet = statement.executeQuery(sql);

        resultSet.next();
        Professor p = new Professor(
                resultSet.getString("id"),
                resultSet.getString("name"),
                resultSet.getString("username"),
                resultSet.getString("email"),
                resultSet.getString("phone"),
                resultSet.getString("website")
        );
        return p;
    }
}
