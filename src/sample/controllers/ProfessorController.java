package sample.controllers;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.SessionManager;
import sample.controllers.CancelAppointmentController;
import sample.Konsultimet;
import sample.Professor;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProfessorController implements Initializable {


    Connection conn;
    private String professorName = SessionManager.professor.getName();

    @FXML
    private TableView<Konsultimet> todayTableView;

    @FXML
    private TableView<Konsultimet> otherDaysTableView;

    @FXML
    private TableColumn<Konsultimet, String> todayLendaColumn;

    @FXML
    private TableColumn<Konsultimet, String> todayStudentiColumn;

    @FXML
    private TableColumn<Konsultimet, Timestamp> todayDataColumn;

    @FXML
    private TableColumn<Konsultimet, String> otherDaysLendaColumn;

    @FXML
    private TableColumn<Konsultimet, String> otherDaysStudentiColumn;

    @FXML
    private TableColumn<Konsultimet, Timestamp> otherDaysDataColumn;

    @FXML
    private Button btnProfile;

    @FXML
    private Button btnCalendar;

    @FXML
    private Button btnUpdateProfile;

    @FXML
    private Button btnEditAppointment;

    @FXML
    private Button btnCancelAppointment;

    @FXML
    private Pane pnlProfile;

    @FXML
    private Pane pnlCalendar;

    @FXML
    private TextField profIdField;

    @FXML
    private TextField profNameField;

    @FXML
    private TextField profUsernameField;

    @FXML
    private TextField profEmailField;

    @FXML
    private TextField profPhoneField;

    @FXML
    private TextField profWebsiteField;

    @FXML
    private Label updatedLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.todayLendaColumn.setCellValueFactory(new PropertyValueFactory<>("lenda"));
        this.todayStudentiColumn.setCellValueFactory(new PropertyValueFactory<>("studenti"));
        this.todayDataColumn.setCellValueFactory(new PropertyValueFactory<>("data"));
        this.otherDaysLendaColumn.setCellValueFactory(new PropertyValueFactory<>("lenda"));
        this.otherDaysStudentiColumn.setCellValueFactory(new PropertyValueFactory<>("studenti"));
        this.otherDaysDataColumn.setCellValueFactory(new PropertyValueFactory<>("data"));

        try{
            initDb();
            fillTheTables();

        } catch (Exception ex){
            ex.printStackTrace();
        }

        try{
            renderProfessor(getProfessor());
        } catch (Exception ex){
            ex.printStackTrace();
        }


        todayTableView.setOnMouseClicked(e -> {
            btnEditAppointment.setDisable(true);
            btnCancelAppointment.setDisable(true);
        });

        otherDaysTableView.setOnMouseClicked(e -> {
            btnEditAppointment.setDisable(false);
            btnCancelAppointment.setDisable(false);
        });

    }

    private void initDb() throws Exception{
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/konsultimet_test", "root", "1234");
    }

    private ArrayList<Konsultimet> getKonsultimet(boolean thisDay) throws Exception{
        LocalDate today = LocalDate.now();
        String strToday = today.toString();
        ArrayList<Konsultimet> konsultimet = new ArrayList<>();

        if (thisDay) {
            String sql = "SELECT * FROM konsultimet WHERE profesori = '" + professorName + "' and koha_fillimi like '" + strToday + "%';";
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Konsultimet k = new Konsultimet(
                        resultSet.getString("Profesori"),
                        resultSet.getString("Lenda"),
                        resultSet.getString("Studenti"),
                        resultSet.getTimestamp("Koha_fillimi")
                );

                konsultimet.add(k);
            }
            return konsultimet;

        } else {
            String sql = "select * from konsultimet where profesori = '" + professorName + "' and DATE(koha_fillimi) > CURDATE();";
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Konsultimet k = new Konsultimet(
                        resultSet.getString("Profesori"),
                        resultSet.getString("Lenda"),
                        resultSet.getString("Studenti"),
                        resultSet.getTimestamp("Koha_fillimi")
                );

                konsultimet.add(k);
            }
            return konsultimet;
        }
    }


    @FXML
    public void handleClicks(ActionEvent actionEvent) throws Exception {
        if (actionEvent.getSource() == btnProfile) {
            renderProfessor(getProfessor());
            pnlCalendar.setVisible(false);
            pnlProfile.setVisible(true);
            pnlProfile.toFront();
        }
        if (actionEvent.getSource() == btnCalendar) {
            pnlProfile.setVisible(false);
            pnlCalendar.setVisible(true);
            pnlCalendar.toFront();
            fillTheTables();
        }

    }

    @FXML
    public void onUpdateButtonClick(ActionEvent e) throws Exception{
        String id = profIdField.getText();
        String username = profUsernameField.getText();
        String phone = profPhoneField.getText();
        String website = profWebsiteField.getText();

        if(!validateUsername(username)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Username must:\n  - start with a letter\n  - end with a letter\n  - contain only letters, numbers and '.', '_' or '-'\n  - cannot contain two symbols close to each other");
            alert.setHeaderText("Wrong username format");
            alert.showAndWait();
            renderProfessor(getProfessor());
            return;

        } else if(!phone.matches("\\d{9}")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Phone number must have 9 digits!");
            alert.setHeaderText("Wrong username format");
            alert.showAndWait();
            renderProfessor(getProfessor());
            return;
        }

        String sql = "UPDATE profesoret SET username = ?, phone = ?, website = ?  WHERE id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, username);
        statement.setString(2, phone);
        statement.setString(3, website);
        statement.setString(4, id);


        try {
            int affectedRows  = statement.executeUpdate();
            updatedLabel.setVisible(true);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Profile updated successfully!");
            alert.showAndWait();
            if (affectedRows !=1) throw new Exception("ERR_MULTIPLE_ROWS_AFFECTED");
        } catch (Exception ex){
            ex.printStackTrace();

            updatedLabel.setText("Failed to update the profile!");
            updatedLabel.setStyle("-fx-text-fill: red");
            updatedLabel.setVisible(true);
            renderProfessor(getProfessor());
        };



    }





    @FXML
    public void onEditButtonClick(ActionEvent e) throws Exception{
        Konsultimet selected = otherDaysTableView.getSelectionModel().getSelectedItem();
        if(selected == null) return;

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/edit_appointment.fxml"));

        Parent parent = loader.load();
        EditAppointmentController controller = loader.getController();

        controller.oldAppointment(selected);

        Scene scene = new Scene(parent);
        Stage primaryStage = new Stage();
        primaryStage.setResizable(false);
        primaryStage.setTitle("Edit Appointment");
        primaryStage.setScene(scene);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.show();
    }


    @FXML
    public void onCancelButtonClick(ActionEvent e) throws Exception{
        Konsultimet selected = otherDaysTableView.getSelectionModel().getSelectedItem();
        if(selected == null) return;

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/cancel_appointment.fxml"));

        Parent parent = loader.load();

        CancelAppointmentController controller = loader.getController();

        controller.oldAppointment(selected);

        Scene scene = new Scene(parent);
        Stage primaryStage = new Stage();
        primaryStage.setResizable(false);
        primaryStage.setTitle("Cancel Appointment");
        primaryStage.setScene(scene);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.show();
    }

    @FXML
    public void onExitButtonClick(ActionEvent e) throws Exception{

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("You will log out now!");

        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){
            SessionManager.professor = null;
            Stage primaryStage = (Stage)((Node)e.getSource()).getScene().getWindow();
            Parent parent = FXMLLoader.load(getClass().getResource("../views/first.fxml"));
            Scene scene = new Scene(parent);
            primaryStage.setScene(scene);
        } else return;
    }

    @FXML
    public void onRefreshButtonClick(ActionEvent e) throws Exception{
        fillTheTables();
    }


    private Professor getProfessor() throws Exception{
        String sql = "SELECT * from Profesoret where name = '" + professorName + "';";
        Statement statement = conn.createStatement();

        ResultSet resultSet = statement.executeQuery(sql);
        ArrayList<Professor> professors = new ArrayList<>();

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

    private void renderProfessor(Professor p){
        profIdField.setText(p.getId());
        profNameField.setText(p.getName());
        profUsernameField.setText(p.getUsername());
        profEmailField.setText(p.getEmail());
        profPhoneField.setText(p.getPhone());
        profWebsiteField.setText(p.getWebsite());
    }

    public void fillTheTables() throws Exception{
        ObservableList<Konsultimet> itemsToday = FXCollections.observableArrayList(getKonsultimet(true));
        todayTableView.setItems(itemsToday);

        ObservableList<Konsultimet> items = FXCollections.observableArrayList(getKonsultimet(false));
        otherDaysTableView.setItems(items);
    }

    public boolean validateUsername(String username){
        String regEx = "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$";

        Pattern p = Pattern.compile(regEx);

        if(username == null){
            return false;
        }

        Matcher m = p.matcher(username);

        return m.matches();
    }
}
