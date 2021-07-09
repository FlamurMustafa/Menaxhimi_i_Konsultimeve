package sample.controllers;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.repositories.DatabaseConnection;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

public class studentController implements Initializable {
    UnaryOperator<TextFormatter.Change> minFilter = change -> {
        String text = change.getText();

        if (change.isDeleted()) {
            return change;
        }

        if (text.matches("[0-9]+")) {
            String newText = change.getControlNewText();
            Integer newMin = Integer.parseInt(newText);

            if(newText.length() <= 2 && newMin >= 0 && newMin <= 59) {
                return change;
            }
        };

        return null;
    };

    UnaryOperator<TextFormatter.Change> hrFilter = change -> {
        String text = change.getText();

        if (change.isDeleted()) {
            return change;
        }

        if (text.matches("[0-9]+")) {
            String newText = change.getControlNewText();
            Integer newHr = Integer.parseInt(newText);

            if(newText.length() <= 2 && newHr >= 0 && newHr <= 23) {
                return change;
            }
        };



        return null;
    };

    @FXML
    private TextField nameField;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ComboBox Profesoret;

    @FXML
    private ComboBox Lendet;

    @FXML
    private Button SubmitBtn;

    @FXML
    private Button CancelBtn;

    @FXML
    private TextField hrField;

    @FXML
    private TextField minField;

    ObservableList<String> profesori  = FXCollections.observableArrayList("Isak Shabani", "Blerim Rexha", "Valon Raca");
    ObservableList<String> lenda  = FXCollections.observableArrayList("KNK", "Rrjeta kompjuterike", "AK");


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        Profesoret.setItems(profesori);
        Lendet.setItems(lenda);

        hrField.setTextFormatter(new TextFormatter<Object>(hrFilter));
        minField.setTextFormatter(new TextFormatter<Object>(minFilter));

    }
    public void clear(){
        nameField.setText("");
        Profesoret.setValue(null);
        Lendet.setValue(null);
        datePicker.getEditor().clear();
        hrField.setText("");
        minField.setText("");
    }
    @FXML
    private void submitButton(javafx.event.ActionEvent event) throws Exception{
        String emri = nameField.getText();
        String hr = hrField.getText();
        String min = minField.getText();
        String newDate = datePicker.getValue().toString() + " " + hr + ":" + min + ":00";
        String profesori = Profesoret.getSelectionModel().getSelectedItem().toString();
        String lenda = Lendet.getSelectionModel().getSelectedItem().toString();

        DatabaseConnection connect = new DatabaseConnection();
        Connection connDB = connect.getConn();


        if(emri.isEmpty() || newDate.isEmpty() || profesori.isEmpty() || lenda.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR");
            alert.setContentText("All fields are required. Please fill them out!");
            alert.showAndWait();
            return;
        }



        String sql = "INSERT INTO konsultimet VALUES (?, ?, ?, ?);";
        PreparedStatement statement = connDB.prepareStatement(sql);
        statement.setString(1, profesori);
        statement.setString(2, emri);
        statement.setString(3, lenda);
        statement.setString(4, newDate);

        try {
            int affectedRows = statement.executeUpdate();
            if (affectedRows != 1) throw new Exception("ERR_MULTIPLE_ROWS_AFFECTED");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("SUCCESS!");
            alert.setContentText("Appointment added!");
            alert.showAndWait();
        } catch (Exception ex){
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("FAILED!");
            alert.setContentText("Failed to add appointment!");
            alert.showAndWait();
        }
        clear();
    }


    @FXML
    private void cancelButton(javafx.event.ActionEvent ev)throws Exception{
        Parent parent = FXMLLoader.load(getClass().getResource("../views/student.fxml"));
        Scene scene = new Scene(parent);
        Stage primaryStage = (Stage) ((Node) ev.getSource()).getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
