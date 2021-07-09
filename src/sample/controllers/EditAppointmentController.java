package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Konsultimet;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.ResourceBundle;

public class EditAppointmentController implements Initializable {
    Connection conn;
    Konsultimet konsult = new Konsultimet();

    @FXML
    private DatePicker editDatePicker;

    @FXML
    private TextField hrField;

    @FXML
    private TextField minField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            initDb();
        } catch (Exception ex){
            ex.printStackTrace();
        }

    }

    public void oldAppointment(Konsultimet k){
        this.konsult.setProfesori(k.getProfesori());
        this.konsult.setData(k.getData());
    }

    @FXML
    public void onFinishEditingClick(ActionEvent e) throws Exception{
        String profesori = konsult.getProfesori();
        String oldDate = konsult.getData().toString();
        String hr = hrField.getText();
        String min = minField.getText();

        if(editDatePicker.getValue() != null && !hr.isEmpty() && !hr.isBlank() && !min.isEmpty() && !min.isBlank()) {
            String newDate = editDatePicker.getValue().toString() + " " + hr + ":" + min + ":00";

            String sql = "UPDATE konsultimet SET koha_fillimi = ?  WHERE profesori = ? and koha_fillimi = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, newDate);
            statement.setString(2, profesori);
            statement.setString(3, oldDate);

            int affectedRows = statement.executeUpdate();

            if (affectedRows != 1) throw new Exception("ERR_MULTIPLE_ROWS_AFFECTED");

            Stage stage = (Stage) editDatePicker.getScene().getWindow();
            stage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("You need to give all the information!");
            alert.showAndWait();
        }

    }


    private void initDb() throws Exception{
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/konsultimet_test", "root", "1234");
    }
}
