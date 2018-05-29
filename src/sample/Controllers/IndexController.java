package sample.Controllers;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.*;
import javafx.fxml.Initializable;
import sample.DBModel;
import sample.Form;
import sample.Patterns.ProxyLib;

import java.io.IOException;
import java.net.Proxy;
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
            root = loader.load(getClass().getResource("../View/NewForm.fxml").openStream());
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
            // PATTERN PROXY
            ProxyLib proxy = new ProxyLib();
            Form f = model.getByNick(nickname.getText());
            proxy.GoToForm(model.isLogin(nickname.getText()), e, f);
        } catch (SQLException | IOException e1) {
            e1.printStackTrace();
        }
    }
}
