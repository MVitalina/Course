package sample;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.*;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class IndexController implements Initializable {
    public DBModel loginModel = new DBModel();
    @FXML
    private Label isConnected;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub (???)
        if (loginModel.isDbConnected()) {
            isConnected.setText("Connected to DB");
        } else {

            isConnected.setText("Not connected to DB");
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
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
