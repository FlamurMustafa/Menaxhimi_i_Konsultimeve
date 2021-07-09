package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import sample.Konsultimet;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

public class CancelAppointmentController implements Initializable {

    Connection conn;
    Konsultimet konsult = new Konsultimet();

    @FXML
    private TextArea cancelReasonArea;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            initDb();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @FXML
    public void onFinishCancelingClick(ActionEvent e) throws Exception{
        String profesori = konsult.getProfesori();
        String oldDate = konsult.getData().toString();

        String sql = "DELETE from konsultimet  WHERE profesori = ? and koha_fillimi = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, profesori);
        statement.setString(2, oldDate);

        int affectedRows = statement.executeUpdate();

        if (affectedRows != 1) throw new Exception("ERR_MULTIPLE_ROWS_AFFECTED");

        Stage stage = (Stage) cancelReasonArea.getScene().getWindow();
        stage.close();

    }

    public void oldAppointment(Konsultimet k){
        this.konsult.setProfesori(k.getProfesori());
        this.konsult.setData(k.getData());
    }

    private void initDb() throws Exception{
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/konsultimet_test", "root", "1234");
    }
}
