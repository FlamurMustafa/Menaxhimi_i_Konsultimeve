package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.*;
import javafx.stage.*;


import java.net.URL;
import java.util.EventObject;
import java.util.ResourceBundle;

public class FirstController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void onStudentClick(ActionEvent e) throws Exception{
        Parent parent = FXMLLoader.load(getClass().getResource("../views/login.fxml"));
        Scene scene = new Scene(parent);
        Stage primaryStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.show();
        


        
    }
    @FXML
    void onProffesorClick(ActionEvent e) throws Exception{
        System.out.println("Clicked proffesor button");


    }

}
