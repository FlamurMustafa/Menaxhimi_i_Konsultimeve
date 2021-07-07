package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.*;
import javafx.stage.*;


import java.net.URL;
import java.util.ResourceBundle;

public class FirstController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void onStudentClick(ActionEvent e) throws Exception{
        System.out.println("Clicked student button");


    }
    @FXML
    void onProffesorClick(ActionEvent e) throws Exception{
        System.out.println("Clicked proffesor button");


    }

}
