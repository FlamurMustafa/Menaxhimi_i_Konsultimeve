package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("views/first.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Menaxhimi i Konsultimeve");
        primaryStage.setScene(scene);
        primaryStage.resizableProperty().setValue(false);
        primaryStage.show();

    }


    public static void main(String[] args) {

        Application.launch(args);
    }
}