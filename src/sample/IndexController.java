package sample;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.*;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class IndexController implements Initializable {
    private DBModel model = new DBModel();
    @FXML
    private Label isConnected;
    @FXML
    private TextField nickname;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (model.isDbConnected()) {
            //todo refact
            isConnected.setText("Connected to DB");
        } else {
            //isConnected.setText("Not connected to DB");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!!!");
            alert.setHeaderText("Nick isn`t correct");
            alert.showAndWait();
        }
    }
    public void NewForm (ActionEvent e) {
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Pane root = null;
        try {
            root = loader.load(getClass().getResource("NewForm.fxml").openStream());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        NewFormController newFormController = (NewFormController)loader.getController();
        primaryStage.setTitle("Новий формуляр");
        assert root != null;
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void Login (ActionEvent e) {
        try {
            if (model.isLogin(nickname.getText())) {
                Stage primaryStage = new Stage();
                FXMLLoader loader = new FXMLLoader();
                FormController formController = (FormController)loader.getController();
//                formController.setNick(nickname.getText());
                Pane root = loader.load(getClass().getResource("Form.fxml").openStream());
 //               if (nickname.getText() != null)
                primaryStage.setTitle("Формуляр читача");
                assert root != null;
                primaryStage.setScene(new Scene(root));
                primaryStage.show();
            } else {
                //isConnected.setText("Nick isn`t correct");
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning!!!");
                alert.setHeaderText("Nick isn`t correct");
                alert.showAndWait();
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
